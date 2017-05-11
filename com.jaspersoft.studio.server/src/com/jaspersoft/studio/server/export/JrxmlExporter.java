/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class JrxmlExporter extends AExporter {
	public static final String PROP_REPORT_ISMAIN = "ireport.jasperserver.report.ismain";
	public static final QualifiedName KEY_REPORT_ISMAIN = new QualifiedName(Activator.PLUGIN_ID, PROP_REPORT_ISMAIN);

	public JrxmlExporter(IPath path) {
		super(path);
	}

	@Override
	public IFile exportToIFile(AMResource res, ResourceDescriptor rd, String fkeyname, IProgressMonitor monitor)
			throws Exception {
		IFile f = super.exportToIFile(res, rd, fkeyname, monitor);
		if (f != null) {
			boolean disposeContext = false;
			JasperReportsConfiguration jrConfig = res.getJasperConfiguration();
			if (jrConfig == null) {
				jrConfig = JasperReportsConfiguration.getDefaultJRConfig(f);
				res.setJasperConfiguration(jrConfig);
				disposeContext = true;
			} else
				jrConfig.init(f);
			try {
				JasperDesign jd = JRXmlLoader.load(jrConfig, f.getContents());
				setPropServerURL((AFileResource) res, jd);
				setPropReportUnit(res, jd);
				getResources(res, jd);

				MServerProfile sp = (MServerProfile) res.getRoot();
				if (sp != null)
					f.setContents(
							new ByteArrayInputStream(JRXmlWriterHelper
									.writeReport(null, jd, sp.getValue().getJrVersion()).getBytes("UTF-8")),
							IFile.KEEP_HISTORY | IFile.FORCE, monitor);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (disposeContext)
					jrConfig.dispose();
			}
		}
		if (f != null)
			f.setPersistentProperty(KEY_REPORT_ISMAIN, Boolean.toString(res.getValue().isMainReport()));
		return f;
	}

	protected void setPropServerURL(AFileResource res, JasperDesign jd) {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile server = (MServerProfile) n;
			ServerProfile v = server.getValue();
			try {
				jd.setProperty(AExporter.PROP_SERVERURL, v.getUrl());
				jd.setProperty(AExporter.PROP_USER,
						v.getUser() + (v.getOrganisation() != null ? "|" + v.getOrganisation() : ""));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	protected void setPropReportUnit(AMResource res, JasperDesign jd) {
		if (!res.getValue().isMainReport())
			jd.setProperty(AExporter.PROP_REPORTRESOURCE, res.getValue().getUriString());
		MReportUnit repunit = res.getReportUnit();
		if (repunit != null) {
			ResourceDescriptor runit = repunit.getValue();
			if (runit != null)
				jd.setProperty(AExporter.PROP_REPORTUNIT, runit.getUriString());
			if (!Misc.isNullOrEmpty(runit.getDescription()))
				jd.setProperty(AExporter.COM_JASPERSOFT_STUDIO_REPORT_UNIT_DESCRIPTION, runit.getDescription());
		} else
			jd.getPropertiesMap().removeProperty(AExporter.PROP_REPORTUNIT);
		if (!Misc.isNullOrEmpty(res.getValue().getDescription()))
			jd.setProperty(PHolderUtil.COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION, res.getValue().getDescription());
	}

	private void getResources(AMResource res, JasperDesign jd) throws Exception {
		if (res.getParent() instanceof MReportUnit) {
			for (INode n : res.getParent().getChildren()) {
				if (n instanceof MJar) {
					// download
				}
			}
		}
		// List<JRDesignElement> elements = ModelUtils.getAllElements(jd);
		// for (JRDesignElement ele : elements) {
		// if (ele instanceof JRDesignImage)
		// cacheResource(res, ((JRDesignImage) ele).getExpression());
		// else if (ele instanceof JRDesignSubreport) {
		// cacheResource(res, ((JRDesignSubreport) ele).getExpression());
		// // go recursively?
		// }
		// // get fonts?
		// }
	}

	protected void cacheResource(AMResource res, JRExpression imgexp) throws Exception {
	}
}
