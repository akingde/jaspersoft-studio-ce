/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors;

import java.util.Arrays;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.book.editors.actions.CreateNewBookPartAction;
import com.jaspersoft.studio.book.editors.actions.CreateNewGroupAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookPartAction;
import com.jaspersoft.studio.book.editors.actions.DeleteBookSectionAction;
import com.jaspersoft.studio.editor.AContextMenuProvider;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.editor.action.ActionUtils;
import com.jaspersoft.studio.editor.action.ShowPropertyViewAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterAction;
import com.jaspersoft.studio.editor.outline.actions.CreateParameterSetAction;
import com.jaspersoft.studio.editor.outline.actions.CreateScriptletAction;
import com.jaspersoft.studio.editor.outline.actions.CreateSortFieldAction;
import com.jaspersoft.studio.editor.outline.actions.CreateVariableAction;
import com.jaspersoft.studio.editor.outline.actions.DynamicActionContributionItem;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultVariablesAction;
import com.jaspersoft.studio.editor.outline.actions.HideDefaultsParametersAction;
import com.jaspersoft.studio.editor.outline.actions.SortParametersAction;
import com.jaspersoft.studio.editor.outline.actions.SortVariablesAction;
import com.jaspersoft.studio.editor.outline.actions.field.CreateFieldAction;
import com.jaspersoft.studio.editor.outline.actions.field.CreateFieldsContainerAction;
import com.jaspersoft.studio.editor.outline.actions.field.DeleteFieldsAllGroupAction;
import com.jaspersoft.studio.editor.outline.actions.field.DeleteFieldsGroupAction;
import com.jaspersoft.studio.editor.outline.actions.field.ShowFieldsTreeAction;
import com.jaspersoft.studio.editor.outline.actions.field.SortFieldsAction;
import com.jaspersoft.studio.property.dataset.dialog.DatasetAction;

public class BookEditorContextMenuProvider extends AContextMenuProvider {

	public BookEditorContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer, registry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		ActionRegistry actionRegistry = getActionRegistry();
		menu.add(new Separator(GEFActionConstants.GROUP_UNDO));
		ActionUtils.appendActionToGroup(menu,
				Arrays.asList(new String[] { ActionFactory.UNDO.getId(), ActionFactory.REDO.getId() }), actionRegistry,
				GEFActionConstants.GROUP_UNDO);

		menu.add(new Separator(GEFActionConstants.GROUP_ADD));

		IAction action = getActionRegistry().getAction(SortParametersAction.ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_ADD,
					new DynamicActionContributionItem((ACachedSelectionAction) action));

			action = getActionRegistry().getAction(HideDefaultsParametersAction.ID);
			if (action != null && action.isEnabled()) {
				menu.appendToGroup(GEFActionConstants.GROUP_ADD,
						new DynamicActionContributionItem((ACachedSelectionAction) action));
			}

			menu.appendToGroup(GEFActionConstants.GROUP_ADD, new Separator());
		}

		action = getActionRegistry().getAction(SortVariablesAction.ID);
		if (action != null && action.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_ADD,
					new DynamicActionContributionItem((ACachedSelectionAction) action));

			action = getActionRegistry().getAction(HideDefaultVariablesAction.ID);
			if (action != null && action.isEnabled()) {
				menu.appendToGroup(GEFActionConstants.GROUP_ADD,
						new DynamicActionContributionItem((ACachedSelectionAction) action));
			}

			menu.appendToGroup(GEFActionConstants.GROUP_ADD, new Separator());
		}

		IAction actionF1 = getActionRegistry().getAction(SortFieldsAction.ID);
		if (actionF1 != null && actionF1.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_ADD,
					new DynamicActionContributionItem((ACachedSelectionAction) actionF1));
		}

		IAction actionF2 = getActionRegistry().getAction(ShowFieldsTreeAction.ID);
		if (actionF2 != null && actionF2.isEnabled()) {
			menu.appendToGroup(GEFActionConstants.GROUP_ADD,
					new DynamicActionContributionItem((ACachedSelectionAction) actionF2));
		}
		if (actionF1 != null || actionF2 != null)
			menu.appendToGroup(GEFActionConstants.GROUP_ADD, new Separator());

		action = getActionRegistry().getAction(DeleteFieldsGroupAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		action = getActionRegistry().getAction(DeleteFieldsAllGroupAction.ID);
		if (action != null && action.isEnabled())
			menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		ActionUtils.appendActionToGroup(menu,
				Arrays.asList(new String[] { CreateNewGroupAction.ID, CreateNewBookPartAction.ID,
						CreateParameterAction.ID, CreateParameterSetAction.ID, CreateFieldAction.ID,
						CreateFieldsContainerAction.ID, CreateSortFieldAction.ID, CreateVariableAction.ID,
						CreateScriptletAction.ID, DatasetAction.ID }),
				actionRegistry, GEFActionConstants.GROUP_ADD);

		menu.add(new Separator(GEFActionConstants.GROUP_COPY));
		ActionUtils.appendActionToGroup(menu, Arrays.asList(
				new String[] { ActionFactory.CUT.getId(), ActionFactory.COPY.getId(), ActionFactory.PASTE.getId() }),
				actionRegistry, GEFActionConstants.GROUP_COPY);

		menu.add(new Separator(GEFActionConstants.GROUP_EDIT));
		ActionUtils.appendActionToGroup(menu,
				Arrays.asList(new String[] { DeleteBookPartAction.ID, DeleteBookSectionAction.ID }), actionRegistry,
				GEFActionConstants.GROUP_EDIT);

		menu.add(new Separator(GEFActionConstants.GROUP_VIEW));
		ActionUtils.appendActionToGroup(menu, Arrays.asList(new String[] { ShowPropertyViewAction.ID }), actionRegistry,
				GEFActionConstants.GROUP_VIEW);
	}

}
