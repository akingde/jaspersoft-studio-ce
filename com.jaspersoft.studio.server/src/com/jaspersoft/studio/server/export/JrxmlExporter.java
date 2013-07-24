/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.export;

import java.io.ByteArrayInputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.utils.ModelUtils;

public class JrxmlExporter extends AExporter {
	public static final String PROP_SERVERURL = "ireport.jasperserver.url";
	public static final String PROP_REPORTUNIT = "ireport.jasperserver.reportUnit";
	public static final String PROP_REPORTRESOURCE = "ireport.jasperserver.report.resource";
	public static final String PROP_REPORT_ISMAIN = "ireport.jasperserver.report.ismain";
	public static final QualifiedName KEY_REPORT_ISMAIN = new QualifiedName(Activator.PLUGIN_ID, PROP_REPORT_ISMAIN);

	@Override
	public IFile exportToIFile(MResource res, ResourceDescriptor rd, String fkeyname, IProgressMonitor monitor) throws Exception {
		IFile f = super.exportToIFile(res, rd, fkeyname, monitor);
		if (f != null) {
			try {
				JasperDesign jd = JRXmlLoader.load(f.getContents());
				setPropServerURL(res, jd);
				setPropReportUnit(res, jd);
				getResources(res, jd);

				MServerProfile sp = (MServerProfile) res.getRoot();
				if (sp != null)
					f.setContents(new ByteArrayInputStream(JRXmlWriterHelper.writeReport(null, jd, sp.getValue().getJrVersion()).getBytes("UTF-8")), IFile.KEEP_HISTORY | IFile.FORCE, monitor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (f != null)
			f.setPersistentProperty(KEY_REPORT_ISMAIN, Boolean.toString(rd.isMainReport()));
		return f;
	}

	protected void setPropServerURL(MResource res, JasperDesign jd) {
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			MServerProfile server = (MServerProfile) n;
			jd.setProperty(PROP_SERVERURL, server.getValue().getUrl());
		}
	}

	protected void setPropReportUnit(MResource res, JasperDesign jd) {
		if (!res.getValue().isMainReport())
			jd.setProperty(PROP_REPORTRESOURCE, res.getValue().getUriString());
		MReportUnit repunit = res.getReportUnit();
		if (repunit != null) {
			ResourceDescriptor runit = repunit.getValue();
			if (runit != null)
				jd.setProperty(PROP_REPORTUNIT, runit.getUriString());
		} else
			jd.getPropertiesMap().removeProperty(PROP_REPORTUNIT);
	}

	@Override
	public String getExtension() {
		return ".jrxml";
	}

	private void getResources(MResource res, JasperDesign jd) throws Exception {
		List<JRDesignElement> elements = ModelUtils.getAllElements(jd);
		for (JRDesignElement ele : elements) {
			if (ele instanceof JRDesignImage)
				cacheResource(res, ((JRDesignImage) ele).getExpression());
			else if (ele instanceof JRDesignSubreport) {
				cacheResource(res, ((JRDesignSubreport) ele).getExpression());
				// go recursively?
			}
			// get fonts?
		}
	}

	protected void cacheResource(MResource res, JRExpression imgexp) throws Exception {
	}
}
