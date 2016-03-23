/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

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
