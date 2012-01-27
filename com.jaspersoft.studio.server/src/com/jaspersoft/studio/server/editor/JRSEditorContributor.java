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
package com.jaspersoft.studio.server.editor;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.plugin.AContributorAction;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.server.JSFileResolver;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.publish.JrxmlImport;
import com.jaspersoft.studio.utils.UIUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JRSEditorContributor implements IEditorContributor {

	public void onLoad(JasperDesign jd, EditorPart editor) {
		if (!(editor instanceof JrxmlEditor))
			return;
		JrxmlEditor edit = (JrxmlEditor) editor;

		String prop = jd.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (prop == null)
			return;
		edit.addFileResolver(new JSFileResolver(jd));

		prop = jd.getProperty("com.jaspersoft.ji.adhoc");
		if (prop != null && prop.equals("1")) {
			UIUtils.showWarning("You have selected to edit an Ad Hoc report.\n"
					+ "If you continue, the report will lose its sorting and grouping.\n"
					+ "Furthermore, any changes you make in Jaspersoft Studio will be lost\n"
					+ "next Time you edit it via the Ad Hoc report editor.\n"
					+ "Continue anyway?");
		}

	}

	public void onSave(JasperReportsConfiguration jrConfig) {
		JasperDesign jd = jrConfig.getJasperDesign();

		String prop = jd.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (prop == null)
			return;
		if (UIUtils.showConfirmation("Export Report to JasperServer",
				"Do you want to publish this report to JasperServer?")) {
			JrxmlImport action = new JrxmlImport();
			action.setJrConfig(jrConfig);
			action.run();
		}
	}

	public void onRun() {
		// TODO Auto-generated method stub

	}

	public AContributorAction[] getActions() {
		return new AContributorAction[] { new JrxmlImport() };
	}

}
