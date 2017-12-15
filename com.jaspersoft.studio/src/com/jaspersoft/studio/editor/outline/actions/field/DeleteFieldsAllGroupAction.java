/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions.field;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.field.MFields;
import com.jaspersoft.studio.model.field.MFieldsContainer;
import com.jaspersoft.studio.model.field.command.DeleteFieldsContainerCommand;

/**
 * Action to sort the fields on the outline
 * 
 * @author Veaceslav Chicu
 *
 */
public class DeleteFieldsAllGroupAction extends ACachedSelectionAction {

	/**
	 * The Constant ID.
	 */
	public static final String ID = "deleteFieldsGroupAllTree"; //$NON-NLS-1$

	public DeleteFieldsAllGroupAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.DeleteFieldsAllGroupAction_0);
		setToolTipText(Messages.DeleteFieldsAllGroupAction_1);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor(Messages.DeleteFieldsAllGroupAction_2));
		setEnabled(false);
	}

	@Override
	protected Command createCommand() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MFieldsContainer.class);
		if (selection.size() == 1 && selection.get(0).getClass().equals(MFieldsContainer.class)) {
			MFieldsContainer fields = (MFieldsContainer) selection.get(0);
			return generateCommand(fields);
		}
		return null;
	}

	private Command generateCommand(MFieldsContainer fields) {
		return new DeleteFieldsContainerCommand(fields.getJasperConfiguration(), fields.getValue(), fields.getKey(),
				fields, DeleteFieldsContainerCommand.UNGROUP_TO_PARENT_ALL);
	}

	/**
	 * Method used to see if the action has the checkbox present or not. In this
	 * case it check for the presence of the property
	 */
	@Override
	public boolean isChecked() {
		List<Object> selection = editor.getSelectionCache().getSelectionModelForType(MFields.class);
		if (selection.size() == 1 && selection.get(0) instanceof MFieldsContainer) {
			MFields selectedVariables = (MFields) selection.get(0);
			return ShowFieldsTreeAction.isFieldsTree(selectedVariables.getJasperConfiguration());
		}
		return false;
	}
}
