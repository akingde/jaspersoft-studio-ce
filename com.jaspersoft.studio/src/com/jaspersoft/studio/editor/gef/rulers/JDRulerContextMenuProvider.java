package com.jaspersoft.studio.editor.gef.rulers;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IMenuManager;

import com.jaspersoft.studio.editor.gef.rulers.actions.CreateGuideAction;

public class JDRulerContextMenuProvider extends ContextMenuProvider {
	public JDRulerContextMenuProvider(EditPartViewer viewer) {
		super(viewer);
	}

	public void buildContextMenu(IMenuManager menu) {
		GEFActionConstants.addStandardActionGroups(menu);
		menu.appendToGroup(GEFActionConstants.GROUP_ADD, new CreateGuideAction(getViewer()));
	}

}
