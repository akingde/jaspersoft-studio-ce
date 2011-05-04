/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.menu;

import java.util.List;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.internal.InternalImages;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.ExtensionManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ShowPropertyViewAction;
import com.jaspersoft.studio.editor.action.align.Align2BorderAction;
import com.jaspersoft.studio.editor.action.order.BringBackwardAction;
import com.jaspersoft.studio.editor.action.order.BringForwardAction;
import com.jaspersoft.studio.editor.action.order.BringToBackAction;
import com.jaspersoft.studio.editor.action.order.BringToFrontAction;
import com.jaspersoft.studio.editor.action.size.MatchSizeAction;
import com.jaspersoft.studio.editor.action.size.Size2BorderAction;
import com.jaspersoft.studio.editor.outline.actions.CreateBandAction;
import com.jaspersoft.studio.editor.outline.actions.CreateConditionalStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateDatasetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateGroupAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleAction;
import com.jaspersoft.studio.editor.outline.actions.CreateStyleTemplateAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DeleteGroupReportAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.section.report.PageFormatAction;

/*
 * The Class AppContextMenuProvider.
 */
public class AppContextMenuProvider extends ContextMenuProvider {

	public String getID() {
		return "com.jaspersoft.studio.outline.contextmenu";
	}

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

		// ----------------------------------------

		action = getActionRegistry().getAction(ActionFactory.CUT.getId());
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		action = getActionRegistry().getAction(ActionFactory.COPY.getId());
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		action = getActionRegistry().getAction(ActionFactory.PASTE.getId());
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_COPY, action);

		// -----------------------------------------------------------

		action = getActionRegistry().getAction(CreateFieldAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateSortFieldAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateVariableAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateScriptletAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateParameterAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateGroupAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateDatasetAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateStyleAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateConditionalStyleAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateStyleTemplateAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(CreateBandAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();
		List<String> lst = m.getActionIDs();
		for (String ids : lst) {
			action = getActionRegistry().getAction(ids);
			if (action != null && action.isEnabled())
				menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);
		}

		action = getActionRegistry().getAction(DeleteGroupReportAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, action);

		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(GEFActionConstants.DIRECT_EDIT);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		// position actions
		MenuManager submenu = new MenuManager(Messages.AppContextMenuProvider_order,
				JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/elcl16/bring_to_front.gif"), BringToFrontAction.ID); //$NON-NLS-1$

		action = getActionRegistry().getAction(BringToFrontAction.ID);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(BringForwardAction.ID);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(BringBackwardAction.ID);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(BringToBackAction.ID);
		if (action.isEnabled())
			submenu.add(action);

		menu.add(submenu);

		// Alignment Actions
		submenu = new MenuManager(Messages.AppContextMenuProvider_align_components, InternalImages.DESC_HORZ_ALIGN_LEFT,
				GEFActionConstants.ALIGN_LEFT);

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

		menu.add(submenu);

		// Alignment Actions
		submenu = new MenuManager(Messages.AppContextMenuProvider_align_to_container,
				JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/align-band-left.gif"), //$NON-NLS-1$
				Align2BorderAction.ID_ALIGN_LEFT);

		action = getActionRegistry().getAction(Align2BorderAction.ID_ALIGN_LEFT);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(Align2BorderAction.ID_ALIGN_CENTER);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(Align2BorderAction.ID_ALIGN_RIGHT);
		if (action.isEnabled())
			submenu.add(action);

		submenu.add(new Separator());

		action = getActionRegistry().getAction(Align2BorderAction.ID_ALIGN_TOP);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(Align2BorderAction.ID_ALIGN_MIDDLE);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(Align2BorderAction.ID_ALIGN_BOTTOM);
		if (action.isEnabled())
			submenu.add(action);

		menu.add(submenu);

		// match size Actions
		submenu = new MenuManager(Messages.AppContextMenuProvider_size_components, InternalImages.DESC_MATCH_WIDTH,
				GEFActionConstants.MATCH_WIDTH);

		action = getActionRegistry().getAction(GEFActionConstants.MATCH_WIDTH);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(GEFActionConstants.MATCH_HEIGHT);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(MatchSizeAction.ID);
		if (action.isEnabled())
			submenu.add(action);

		menu.add(submenu);
		// ------------------------------

		submenu = new MenuManager(Messages.AppContextMenuProvider_size_to_container,
				JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/size_to_control_width.gif"), //$NON-NLS-1$
				Size2BorderAction.ID_SIZE_WIDTH);

		action = getActionRegistry().getAction(Size2BorderAction.ID_SIZE_WIDTH);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(Size2BorderAction.ID_SIZE_HEIGHT);
		if (action.isEnabled())
			submenu.add(action);

		action = getActionRegistry().getAction(Size2BorderAction.ID_SIZE_BOTH);
		if (action.isEnabled())
			submenu.add(action);

		menu.add(submenu);
		// ------------------------------

		action = getActionRegistry().getAction(ShowPropertyViewAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);

		action = getActionRegistry().getAction(PageFormatAction.ID);
		if (action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_VIEW, action);
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
