package com.jaspersoft.studio.editor.preview.element;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.empty.EmptyDataAdapterDescriptor;
import com.jaspersoft.studio.data.reader.DataPreviewScriptlet;
import com.jaspersoft.studio.data.reader.DatasetReader;
import com.jaspersoft.studio.data.storage.JRDefaultDataAdapterStorage;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.data.cache.ColumnDataCacheHandler;
import net.sf.jasperreports.data.cache.DataCacheHandler;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.viewer.BrowserUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.SimpleReportContext;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class ElementPreviewer {
	private Composite cmp;
	private Browser browser;

	public ElementPreviewer(Composite parent) {
		cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmp.setLayout(layout);

		browser = BrowserUtils.getSWTBrowserWidget(cmp, SWT.NONE);
		browser.setJavascriptEnabled(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		browser.setLayoutData(gd);
	}

	public Composite getControl() {
		return cmp;
	}

	private JasperDesign jd;

	public String runReport(JasperReportsConfiguration jConf, JRElement element, boolean fromCache) {
		ClassLoader originalClassLoader = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(jConf.getClassLoader());
		Map<String, Object> hm = null;
		try {
			JasperDesign jDesign = jConf.getJasperDesign();
			// initialise the report
			if (jd == null)
				jd = getJasperDesign(jConf);
			setupDatasets(jConf, jDesign);
			replaceElement((JRDesignElement) element.clone(), jd);

			JasperReport jrobj = DatasetReader.compile(jConf, jd);
			if (jrobj == null)
				return null;
			hm = DatasetReader.prepareParameters(jConf, 100);

			DataAdapterDescriptor da = prepareDataAdapter(jConf, jDesign);

			setupCache(hm, fromCache);

			JasperPrint jrPrint = DatasetReader.fillReport(jConf, jDesign.getMainDesignDataset(), da, jrobj, hm);

			// create a temp dir and a temp file for html
			File destDir = FileUtils.createTempDir();
			String dest = new File(destDir, "index.html").getAbsolutePath();
			JasperExportManager.getInstance(jConf).exportToHtmlFile(jrPrint, dest);
			System.out.println(dest);
			browser.setToolTipText(dest);
			browser.setUrl(dest);
			return dest;
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			String message = sw.getBuffer().toString();
			browser.setText("<HTML><BODY><pre>" + message + "</pre></body></html>");
		} finally {
			Thread.currentThread().setContextClassLoader(originalClassLoader);
			if (hm != null)
				hm.remove(JRParameter.REPORT_CONTEXT);
		}

		return null;
	}

	public void resetCache() {
		reportContext = null;
	}

	private SimpleReportContext reportContext;

	private void setupCache(Map<String, Object> hm, boolean fromCache) {
		if (fromCache) {
			if (reportContext == null) {
				reportContext = new SimpleReportContext();
				ColumnDataCacheHandler cacheHandler = new ColumnDataCacheHandler();
				reportContext.setParameterValue(DataCacheHandler.PARAMETER_DATA_CACHE_HANDLER, cacheHandler);
			}
			hm.put(JRParameter.REPORT_CONTEXT, reportContext);
		} else
			reportContext = null;
	}

	protected void setupDatasets(JasperReportsConfiguration jConf, JasperDesign jDesign) throws JRException {
		for (JRDataset ds : jd.getDatasets())
			jd.removeDataset(ds);
		List<String> columns = new ArrayList<String>();
		for (JRField f : jDesign.getMainDataset().getFields())
			columns.add(f.getName());
		DatasetReader.setupDataset(jd, (JRDesignDataset) jDesign.getMainDesignDataset(), jConf, columns);

		for (JRDataset ds : jDesign.getDatasets()) {
			jd.addDataset((JRDesignDataset) ds.clone());
		}
	}

	protected JasperDesign getJasperDesign(JasperReportsConfiguration jConfig) throws IOException, JRException {
		FileInputStream is = null;
		try {
			String reportLocation = JaspersoftStudioPlugin.getInstance()
					.getFileLocation(DataPreviewScriptlet.PREVIEW_REPORT_PATH);
			is = new FileInputStream(reportLocation);
			JasperDesign jd = JRXmlLoader.load(jConfig, is);

			jd.setLeftMargin(0);
			jd.setRightMargin(0);
			jd.setTopMargin(0);
			jd.setBottomMargin(0);
			jd.setTitle(null);
			jd.setPageHeader(null);
			jd.setColumnHeader(null);
			((JRDesignSection) jd.getDetailSection()).removeBand(0);
			jd.setColumnFooter(null);
			jd.setPageFooter(null);
			jd.setLastPageFooter(null);
			jd.setNoData(null);
			jd.setBackground(null);
			jd.setScriptletClass(null);
			for (JRScriptlet s : jd.getScriptlets())
				jd.removeScriptlet(s);
			return jd;
		} finally {
			FileUtils.closeStream(is);
		}
	}

	public DataAdapterDescriptor prepareDataAdapter(JasperReportsConfiguration jConf, JasperDesign jDesign) {
		return prepareDataAdapter(jConf, jDesign.getMainDesignDataset());
	}

	public DataAdapterDescriptor prepareDataAdapter(JasperReportsConfiguration jConf, JRDesignDataset jDataset) {
		JRDefaultDataAdapterStorage defaultStorage = DataAdapterManager.getJRDefaultStorage(jConf);
		DataAdapterDescriptor da = null;
		String defAdapter = jDataset.getPropertiesMap().getProperty(DataQueryAdapters.DEFAULT_DATAADAPTER);
		if (defAdapter != null) {
			da = defaultStorage.getDefaultJRDataAdapter(defAdapter);
			if (da == null)
				da = DataAdapterManager.getPreferencesStorage().findDataAdapter(defAdapter);
		}
		if (da == null)
			da = defaultStorage.getDefaultJRDataAdapter(jDataset);
		if (da == null) {
			// I think we should use some predefined datasets
			da = new EmptyDataAdapterDescriptor();
		}
		return da;
	}

	protected void replaceElement(JRDesignElement element, JasperDesign jd) {
		JRDesignBand bs = (JRDesignBand) jd.getSummary();
		for (JRElement jrel : bs.getElements())
			bs.removeElement((JRDesignElement) jrel);
		bs.addElement(element);
		element.setX(0);
		element.setY(0);
		element.setWidth(browser.getBounds().width - 20);
		element.setHeight(browser.getBounds().height - 20);
		jd.setPageHeight(element.getHeight());
		jd.setPageWidth(element.getWidth());
		jd.setColumnWidth(jd.getPageWidth());
		bs.setHeight(element.getHeight());
	}
}
