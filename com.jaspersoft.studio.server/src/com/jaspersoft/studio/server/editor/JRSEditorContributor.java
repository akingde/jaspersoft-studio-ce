/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.server.editor;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.EditorPart;

import com.jaspersoft.studio.editor.JrxmlEditor;
import com.jaspersoft.studio.plugin.AContributorAction;
import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.server.JSFileResolver;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.server.publish.wizard.SaveConfirmationDialog;
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

		// prop = jd.getProperty("com.jaspersoft.ji.adhoc");
		// if (prop != null && prop.equals("1")) {
		// UIUtils.showWarning("You have selected to edit an Ad Hoc report.\n"
		// + "If you continue, the report will lose its sorting and grouping.\n"
		// +
		// "Furthermore, any changes you make in Jaspersoft Studio will be lost\n"
		// + "next Time you edit it via the Ad Hoc report editor.\n"
		// + "Continue anyway?");
		// }

	}

	public static final String KEY_PUBLISH2JSS = "PUBLISH2JSS";
	public static final String KEY_PUBLISH2JSS_SILENT = "PUBLISH2JSS.SILENT";

	public void onSave(JasperReportsConfiguration jrConfig,
			IProgressMonitor monitor) {
		JasperDesign jd = jrConfig.getJasperDesign();

		String prop = jd.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (prop == null)
			return;

		boolean run = jrConfig.get(KEY_PUBLISH2JSS, false);
		boolean allways = jrConfig.get(KEY_PUBLISH2JSS_SILENT, false);
		if (!allways) {
			SaveConfirmationDialog dialog = new SaveConfirmationDialog(Display
					.getDefault().getActiveShell());
			run = (dialog.open() == Dialog.OK);
			jrConfig.put(KEY_PUBLISH2JSS, run);
			jrConfig.put(KEY_PUBLISH2JSS_SILENT, dialog.getAllways());
		}
		if (run)
			getAction(monitor, jrConfig).run();
	}

	protected static JrxmlPublishAction getAction(IProgressMonitor monitor,
			JasperReportsConfiguration jrConfig) {
		JrxmlPublishAction publishAction = new JrxmlPublishAction(2, monitor);
		publishAction.setJrConfig(jrConfig);
		return publishAction;
	}

	public void onRun() {
		// TODO Auto-generated method stub

	}

	public AContributorAction[] getActions() {
		return new AContributorAction[] { new JrxmlPublishAction() };
	}

}
