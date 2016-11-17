/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.ui.actions.ActionRegistry;

import com.jaspersoft.studio.editor.gef.ui.actions.IEditorSettingsMenuContributor;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ServerSettingsEditorMenuContributor implements IEditorSettingsMenuContributor {
	private JasperReportsConfiguration jConfig;

	@Override
	public void registerActions(ActionRegistry actionRegistry, JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
		actionRegistry.registerAction(new ShowPublishDialogAction(jConfig));
	}

	private static List<String> actions = new ArrayList<String>();
	static {
		actions.add(ShowPublishDialogAction.ID);
	}

	@Override
	public List<String> getActionIds() {
		Boolean doSave = jConfig.getPropertyBoolean(JRSPreferencesPage.PUBLISH_REPORT_TOJRSONSAVE, Boolean.TRUE);
		if (doSave)
			return actions;
		return new ArrayList<String>();
	}
}
