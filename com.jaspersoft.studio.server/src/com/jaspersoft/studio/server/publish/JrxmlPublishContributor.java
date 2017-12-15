/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.InputSource;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.model.AMJrxmlContainer;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.plugin.ExtensionManager;
import com.jaspersoft.studio.server.plugin.IPublishContributor;
import com.jaspersoft.studio.server.publish.imp.ImpChartCustomizer;
import com.jaspersoft.studio.server.publish.imp.ImpDataAdapter;
import com.jaspersoft.studio.server.publish.imp.ImpImage;
import com.jaspersoft.studio.server.publish.imp.ImpInputControls;
import com.jaspersoft.studio.server.publish.imp.ImpJRXML;
import com.jaspersoft.studio.server.publish.imp.ImpResourceBundle;
import com.jaspersoft.studio.server.publish.imp.ImpStyleTemplate;
import com.jaspersoft.studio.server.publish.imp.ImpSubreport;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.utils.JRXMLUtils;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JSSFileRepositoryService;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRPart;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;
import net.sf.jasperreports.repo.FileRepositoryService;
import net.sf.jasperreports.repo.RepositoryService;

public class JrxmlPublishContributor implements IPublishContributor {

	public static final String COM_JASPERSOFT_JRS_DATA_SOURCE = "com.jaspersoft.jrs.data.source";

	public void publishJrxml(AMJrxmlContainer mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, String version) throws Exception {
		init(mrunit.getJasperConfiguration(), version);
		publishJrxml(mrunit, monitor, jasper, fileset, file);
		if (ResourceDescriptorUtil.isReportMain(file)) {
			jasper.removeProperty(COM_JASPERSOFT_JRS_DATA_SOURCE);
			if (mrunit instanceof MJrxml && mrunit.getValue().isMainReport())
				mrunit = (AMJrxmlContainer) mrunit.getParent();
			if (mrunit instanceof MReportUnit)
				publishParameters((MReportUnit) mrunit, monitor, jasper);
		}
	}

	public void publishParameters(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper) throws Exception {
		impIC.publish(mrunit, monitor, jasper, jrConfig);
		extManager.publishParameters(jrConfig, mrunit, monitor, jasper);
	}

	private void publishJrxml(AMJrxmlContainer mres, IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file) throws Exception {
		if (monitor.isCanceled())
			return;
		MReportUnit mrunit = null;
		if (mres instanceof MReportUnit)
			mrunit = (MReportUnit) mres;
		else if (mres.getParent() instanceof MReportUnit)
			mrunit = (MReportUnit) mres.getParent();

		if (mrunit != null) {
			List<JRDesignElement> elements = ModelUtils.getAllElements(jasper);
			for (JRDesignElement ele : elements) {
				if (ele instanceof JRDesignImage)
					publishImage(mrunit, monitor, jasper, fileset, file, ele, version);
				else if (ele instanceof JRDesignSubreport) {
					publishSubreport(mrunit, monitor, jasper, fileset, file, ele, version);
				} else if (ele instanceof JRChart) {
					// Currently not used since we decided that the user need to
					// create by its own a working environment
					// publishChartCustmomizer(mrunit, monitor, jasper, fileset,
					// file, (JRChart)ele, version);
				} else {
					publishComponent(mrunit, monitor, jasper, fileset, file, ele, version);
				}
			}
			publishDataAdapters(mrunit, monitor, jasper, fileset, file, version);
			publishBundles(mrunit, monitor, jasper, fileset, file, version);
			publishTemplates(mrunit, monitor, jasper, fileset, file, version);
			publishParts(mrunit, monitor, jasper, fileset, file, version);
		}
		// here extend and give possibility to contribute to plugins
		extManager.publishJrxml(jrConfig, mres, monitor, jasper, fileset, file, version);
		setupDescription(mrunit != null ? mrunit.getValue() : null, mres.getValue(), jasper);
	}

	protected void publishParts(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file, String version) throws Exception {
		List<JRPart> elements = ModelUtils.getAllPartElements(jasper);
		for (JRPart part : elements) {
			if (part.getComponent() instanceof StandardSubreportPartComponent) {
				StandardSubreportPartComponent component = (StandardSubreportPartComponent) part.getComponent();
				MJrxml fres = (MJrxml) impJRXML.publish(jasper, component, mrunit, monitor, fileset, file);
				publishSubreport(fres, monitor, fileset);
				setupDescription(mrunit != null ? mrunit.getValue() : null, fres.getValue(), jasper);
			}
		}
	}

	protected void publishSubreport(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, JRDesignElement ele, String version) throws Exception {
		MJrxml fres = (MJrxml) impSRP.publish(jasper, ele, mrunit, monitor, fileset, file);
		publishSubreport(fres, monitor, fileset);
		if (fres != null)
			setupDescription(mrunit != null ? mrunit.getValue() : null, fres.getValue(), jasper);
	}

	private void setupDescription(ResourceDescriptor runit, ResourceDescriptor rd, JasperDesign jd) {
		String d = jd.getProperty(PHolderUtil.COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
		if (Misc.isNullOrEmpty(rd.getDescription()) && !Misc.isNullOrEmpty(d))
			rd.setDescription(d);
		if (runit != null && Misc.isNullOrEmpty(runit.getDescription())) {
			if (!Misc.isNullOrEmpty(d))
				runit.setDescription(d);
			d = jd.getProperty(AExporter.COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION);
			if (!Misc.isNullOrEmpty(d))
				runit.setDescription(d);
		}
	}

	protected void publishSubreport(MJrxml fres, IProgressMonitor monitor, Set<String> fileset) throws Exception {
		if (fres == null)
			return;
		IFile fs = FileUtils.getInProjectFile(fres.getFile().toURI(), monitor);
		if (fs != null) {
			JasperDesign jrd = readJR(fs);
			fres.setJd(jrd);
			if (jrd != null) {
				publishJrxml(fres, monitor, jrd, fileset, fs);
				File f = FileUtils.createTempFile("jrsres", ".jrxml");
				FileUtils.writeFile(f, JRXmlWriterHelper.writeReport(jrConfig, jrd, version));
				fres.setFile(f);
			}
			if (fs.isLinked())
				fs.delete(true, monitor);
		} else if (fres.getFile().exists()) {
			JasperDesign jrd = readJR(fs);
			fres.setJd(jrd);
			if (jrd != null) {
				File f = FileUtils.createTempFile("jrsres", ".jrxml");
				FileUtils.writeFile(f, JRXmlWriterHelper.writeReport(jrConfig, jrd, version));
				fres.setFile(f);
			}
		}
	}

	protected JasperDesign readJR(File f) {
		JasperDesign jd = null;
		InputStream in = null;
		InputSource is = null;
		try {
			in = JRXMLUtils.getJRXMLInputStream(jrConfig, f.toURI().toURL().openStream(),
					FilenameUtils.getExtension(f.getName()), "UTF-8", version);
			is = new InputSource(new InputStreamReader(in, "UTF-8"));
			jd = new JRXmlLoader(jrConfig, JRXmlDigesterFactory.createDigester(jrConfig)).loadXML(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(in);
		}
		return jd;
	}

	protected JasperDesign readJR(IFile f) {
		JasperDesign jd = null;
		InputStream in = null;
		InputSource is = null;
		try {
			in = JRXMLUtils.getJRXMLInputStream(jrConfig, f.getContents(), f.getFileExtension(), f.getCharset(true),
					version);
			is = new InputSource(new InputStreamReader(in, "UTF-8"));
			jd = new JRXmlLoader(jrConfig, JRXmlDigesterFactory.createDigester(jrConfig)).loadXML(is);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			FileUtils.closeStream(in);
		}
		return jd;
	}

	@Override
	public void publishComponent(AMJrxmlContainer mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, JRDesignElement ele, String version) throws Exception {
		extManager.publishComponent(jrConfig, mrunit, monitor, jasper, fileset, file, ele, version);
	}

	protected void publishImage(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file, JRDesignElement ele, String version) throws Exception {
		impImg.publish(jasper, ele, mrunit, monitor, fileset, file);
	}

	/**
	 * Publish the jar resources required by the chart customizers of a specific
	 * chart
	 */
	protected void publishChartCustmomizer(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, JRChart chart, String version) throws Exception {
		String customizerClass = chart.getCustomizerClass();
		impChartCustomizer.publish(jasper, customizerClass, mrunit, monitor, fileset, file, version);
		for (String subCustomizerClass : impChartCustomizer.getSubCustmizersClass(chart)) {
			impChartCustomizer.publish(jasper, subCustomizerClass, mrunit, monitor, fileset, file, version);
		}
	}

	protected void publishTemplates(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, String version) throws Exception {
		for (JRReportTemplate rt : jasper.getTemplatesList())
			impStyle.publish(jasper, rt, mrunit, monitor, fileset, file);
	}

	protected void publishDataAdapters(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, String version) throws Exception {
		List<JRDataset> ds = new ArrayList<JRDataset>();
		ds.add(jasper.getMainDataset());
		List<JRDataset> datasetsList = jasper.getDatasetsList();
		if (datasetsList != null && !datasetsList.isEmpty())
			ds.addAll(datasetsList);
		boolean syncDA = mrunit.getWsClient().getServerProfile().isSyncDA();
		for (JRDataset d : ds) {
			JRPropertiesMap pmap = d.getPropertiesMap();
			String dapath = pmap.getProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION);
			if (syncDA && Misc.isNullOrEmpty(dapath)) {
				String name = pmap.getProperty(DataQueryAdapters.DEFAULT_DATAADAPTER);
				if (!Misc.isNullOrEmpty(name)) {
					ADataAdapterStorage storage = DataAdapterManager.getJRDefaultStorage(jrConfig);
					for (DataAdapterDescriptor dad : storage.getDataAdapterDescriptors()) {
						if (dad.getDataAdapter().getName().equals(name)) {
							dapath = storage.getUrl(dad).toString();
							break;
						}
					}
				}
			}
			if (Misc.isNullOrEmpty(dapath))
				continue;

			impDa.publish((JRDesignDataset) d, dapath, mrunit, monitor, fileset, file);
		}
	}

	protected void publishBundles(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jasper,
			Set<String> fileset, IFile file, String version) throws Exception {
		List<JRDataset> ds = new ArrayList<JRDataset>();
		ds.add(jasper.getMainDataset());
		List<JRDataset> datasetsList = jasper.getDatasetsList();
		if (datasetsList != null && !datasetsList.isEmpty())
			ds.addAll(datasetsList);
		for (JRDataset d : ds) {
			String dapath = d.getResourceBundle();
			if (Misc.isNullOrEmpty(dapath))
				continue;
			impBundle.publish(jrConfig, jasper, dapath, mrunit, monitor, fileset, file);

			JSSFileRepositoryService repService = jrConfig.getFileRepositoryService();
			List<String> roots = new ArrayList<String>();
			List<RepositoryService> rservices = repService.getRepositoryServices();
			for (RepositoryService rs : rservices) {
				if (rs instanceof FileRepositoryService) {
					FileRepositoryService frs = (FileRepositoryService) rs;
					roots.add(frs.getRoot());
				}
			}
			List<File> files = new ArrayList<File>();
			Set<String> fileNames = new HashSet<String>();
			for (String r : roots)
				look4Files(r, dapath, fileNames, files);
			for (File f : files) {
				String p = f.getName();
				p = p.substring(0, p.length() - ".properties".length());
				impBundle.publish(jrConfig, jasper, p, mrunit, monitor, fileset, file);
				if (monitor.isCanceled())
					return;
			}
			// in this case with getAvailableLocale, JR will look also in
			// classpath and other places, but it could be slow

			// for (Locale l : Locale.getAvailableLocales()) {
			// impBundle.publish(jrConfig, jasper,
			// dapath + "_" + l.toString(), mrunit, monitor, fileset,
			// file);
			// if (monitor.isCanceled())
			// return;
			// }
			if (monitor.isCanceled())
				return;
		}
	}

	private void look4Files(String root, String dapath, Set<String> fileNames, List<File> files) {
		File dir = new File(root);
		FileFilter fileFilter = new WildcardFileFilter(dapath + "_*.properties");
		for (File f : dir.listFiles(fileFilter)) {
			if (fileNames.contains(f.getName()))
				continue;
			fileNames.add(f.getName());
			files.add(f);
		}
	}

	public void init(JasperReportsConfiguration jrConfig, String version) {
		this.version = version;
		init(jrConfig);
	}

	private JasperReportsConfiguration jrConfig;
	private String version;
	private ExtensionManager extManager;
	private ImpResourceBundle impBundle;
	private ImpDataAdapter impDa;
	private ImpStyleTemplate impStyle;
	private ImpImage impImg;
	private ImpSubreport impSRP;
	private ImpInputControls impIC;
	private ImpJRXML impJRXML;
	private ImpChartCustomizer impChartCustomizer;

	@Override
	public void init(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
		extManager = Activator.getExtManager();
		impDa = new ImpDataAdapter(jrConfig);
		impBundle = new ImpResourceBundle(jrConfig);
		impStyle = new ImpStyleTemplate(jrConfig);
		impImg = new ImpImage(jrConfig);
		impSRP = new ImpSubreport(jrConfig);
		impIC = new ImpInputControls(jrConfig);
		impJRXML = new ImpJRXML(jrConfig);
		impChartCustomizer = new ImpChartCustomizer(jrConfig);
	}

}
