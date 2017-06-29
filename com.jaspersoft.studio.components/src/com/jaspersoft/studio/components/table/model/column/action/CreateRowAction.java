/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMFooterHeaderCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.command.CreateSectionCellsCommand;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

/**
 * Action to create all the cell inside a table section
 * 
 * @author Orlandin Marco
 *
 */
public class CreateRowAction extends ACachedSelectionAction {

	/** The Constant ID. */
	public static final String ID = "create_table_row"; //$NON-NLS-1$
	
	public CreateRowAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateRowAction_createRowName);
		setToolTipText(Messages.CreateRowAction_createRowTooltip);
		setId(CreateRowAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
	}
	
	@Override
	protected Command createCommand() {
		List<Object> selectedSections = editor.getSelectionCache().getSelectionModelForType(AMFooterHeaderCollection.class);
		if (!selectedSections.isEmpty()){
			MTable table = ((AMFooterHeaderCollection)selectedSections.get(0)).getMTable();
			JSSCompoundTableCommand command = new JSSCompoundTableCommand(table);
			for (Object section : selectedSections){
				command.add(new CreateSectionCellsCommand((AMFooterHeaderCollection)section));
			}
			return command;
		}
		return null;
	}
	
	@Override
	public void run() {
		super.run();
		handleSelectionChanged();
	}
}
