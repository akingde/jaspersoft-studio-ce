package com.jaspersoft.studio.server.editor;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.server.JSFileResolver;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.publish.JrxmlImport;
import com.jaspersoft.studio.utils.UIUtils;

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

	public void onSave(JasperDesign jd) {
		String prop = jd.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (prop == null)
			return;
		if (UIUtils.showConfirmation("Export Report to JasperServer",
				"Do you want to publish this report to JasperServer?")) {
			JrxmlImport action = new JrxmlImport();
			action.setJasperDesign(jd);
			action.run();
		}
	}

	public void onRun() {
		// TODO Auto-generated method stub

	}

}
