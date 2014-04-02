package com.jaspersoft.studio.server.publish.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.ui.actions.ActionRegistry;

import com.jaspersoft.studio.editor.gef.ui.actions.IEditorSettingsMenuContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ServerSettingsEditorMenuContributor implements IEditorSettingsMenuContributor {

	@Override
	public void registerActions(ActionRegistry actionRegistry, JasperReportsConfiguration jConfig) {
		actionRegistry.registerAction(new ShowPublishDialogAction(jConfig));
	}

	private static List<String> actions = new ArrayList<String>();
	static {
		actions.add(ShowPublishDialogAction.ID);
	}

	@Override
	public List<String> getActionIds() {
		return actions;
	}
}
