/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.menu;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.editor.action.BringBackwardAction;
import com.jaspersoft.studio.editor.action.BringForwardAction;
import com.jaspersoft.studio.editor.action.BringToBackAction;
import com.jaspersoft.studio.editor.action.BringToFrontAction;
import com.jaspersoft.studio.editor.outline.actions.CreateBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateConditionalStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDatasetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DeleteGroupReportAction;

/**
 * The Class AppContextMenuProvider.
 */
public class AppContextMenuProvider extends ContextMenuProvider {

	/** The action registry. */
	private ActionRegistry actionRegistry;

	/**
	 * Instantiates a new app context menu provider.
	 * 
	 * @param viewer
	 *          the viewer
	 * @param registry
	 *          the registry
	 */
	public AppContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	public void buildContextMenu(IMenuManager menu) {
		GEFActionConstants.addStandardActionGroups(menu);

		IAction action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		// action = getActionRegistry().getAction(ActionFactory.PASTE.getId());
		// if (action.isEnabled())
		// menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateFieldAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateVariableAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateScriptletAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateParameterAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateGroupAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateDatasetAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateStyleAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateConditionalStyleAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateStyleTemplateAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(CreateBandAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(DeleteGroupReportAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		// position actions

		action = getActionRegistry().getAction(BringToFrontAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

		action = getActionRegistry().getAction(BringForwardAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

		action = getActionRegistry().getAction(BringBackwardAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

		action = getActionRegistry().getAction(BringToBackAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_REST, action);

		// Alignment Actions
		MenuManager submenu = new MenuManager("Alignement");

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_LEFT);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_CENTER);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_RIGHT);
		if (action.isEnabled())
			submenu.add(action);

		submenu.add(new Separator());

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_TOP);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_MIDDLE);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(GEFActionConstants.ALIGN_BOTTOM);
		if (action.isEnabled())
			submenu.add(action);

		//
		// if (!submenu.isEmpty())
		// menu.appendToGroup(GEFActionConstants.GROUP_REST, submenu);
		//
		// action = getActionRegistry().getAction(ActionFactory.SAVE.getId());
		// menu.appendToGroup(GEFActionConstants.GROUP_SAVE, action);
	}

	/**
	 * Gets the action registry.
	 * 
	 * @return the action registry
	 */
	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	/**
	 * Sets the action registry.
	 * 
	 * @param actionRegistry
	 *          the new action registry
	 */
	public void setActionRegistry(ActionRegistry actionRegistry) {
		this.actionRegistry = actionRegistry;
	}

}
