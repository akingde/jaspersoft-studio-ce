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
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCellCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.part.editpolicy.JSSCompoundTableCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

/**
 *  Action used to create a column cell action. The cell is a simple one, for
 *  the group cell there is a separate action
 *  
 *  @author Orlandin Marco
 */
public class CreateColumnCellAction extends ACachedSelectionAction{

	/** The Constant ID. */
	public static final String ID = "create_table_column_cell"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public CreateColumnCellAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateColumnCellAction_create_column);
		setToolTipText(Messages.CreateColumnCellAction_create_column_tool_tip);
		setId(CreateColumnCellAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
	
	/**
	 * Create the command for the action for each selected column, excluding the column group
	 */
	@Override
	protected Command createCommand() {
		List<Object> cells = editor.getSelectionCache().getSelectionModelForType(MColumn.class);
		if (!cells.isEmpty()){
			MTable table = ((MColumn)cells.get(0)).getTable();
			JSSCompoundTableCommand compundTableCommand = new JSSCompoundTableCommand(table);	
			for(Object rawCell : cells){
				MColumn col = (MColumn)rawCell;
				if (!(col instanceof MCell) && !(col instanceof MColumnGroup)){
					 compundTableCommand.add(new CreateColumnCellCommand(col.getSection(), col));
				}
			}
			if (!compundTableCommand.isEmpty()) return compundTableCommand;
		}
		return null;
	}
}
