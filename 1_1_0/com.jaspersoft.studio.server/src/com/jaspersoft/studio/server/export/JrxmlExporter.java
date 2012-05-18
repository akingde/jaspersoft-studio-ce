/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.export;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.utils.ModelUtils;

public class JrxmlExporter extends AExporter {
	public static final String PROP_SERVERURL = "ireport.jasperserver.url";
	public static final String PROP_REPORTUNIT = "ireport.jasperserver.reportUnit";

	@Override
	public File exportFile(MResource res, ResourceDescriptor rd, String fkeyname)
			throws Exception {

		File f = super.exportFile(res, rd, fkeyname);
		if (f != null) {
			JasperDesign jd = JRXmlLoader.load(f);
			setPropServerURL(res, jd);
			setPropReportUnit(res, jd);
			getResources(res, jd);

			FileWriter fw = new FileWriter(f);
			try {
				fw.write(JRXmlWriterHelper.writeReport(jd, "4_0_2"));
			} finally {
				fw.close();
			}
		}
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
		MReportUnit repunit = res.getReportUnit();
		if (repunit != null) {
			ResourceDescriptor runit = repunit.getValue();
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

	protected void cacheResource(MResource res, JRExpression imgexp)
			throws Exception {
		// if (imgexp != null && imgexp.getText() != null) {
		// String s = JRExpressionUtil.getSimpleExpressionText(imgexp);
		// if (s.startsWith("repo:")) {
		// String uri = s.substring(5);

		// ResourceDescriptor rd = new ResourceDescriptor();
		// MReportUnit repunit = res.getReportUnit();
		// if (!uri.startsWith("/") && repunit != null)
		// uri = repunit.getValue().getUriString() + "_files/" + uri;
		// rd.setUriString(uri);

		// INode n = res.getRoot();
		// if (n != null && n instanceof MServerProfile) {
		// WSClient c = ((MServerProfile) n).getWsClient();
		// rd = c.get(rd, null);
		// // String fname = JasperServerManager
		// // .createTmpFileName("img", "");
		// // rd = c.get(rd, new File(fname));
		// // RepoImageCache.getInstance()
		// // .put(s, new File(fname));
		//
		// }

		// }
		// }
	}
}
