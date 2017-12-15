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
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnGroupCellCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

/**
 * Action used to create a column group cell. This action can create one cell at time
 * 
 * @author Orlandin Marco
 */
public class CreateColumnGroupCellAction extends ACachedSelectionAction{

	/** The Constant ID. */
	public static final String ID = "create_table_column_group_cell"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public CreateColumnGroupCellAction(IWorkbenchPart part) {
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
		setId(CreateColumnGroupCellAction.ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}

	/**
	 * Create the command for a single column group
	 */
	@Override
	protected Command createCommand() {
		List<Object> cells = editor.getSelectionCache().getSelectionModelForType(MColumnGroup.class);
		if (cells.size() == 1){	
			for(Object rawCell : cells){
				MColumnGroup cell = (MColumnGroup)rawCell;
				return new CreateColumnGroupCellCommand(cell.getSection(), cell);
			}
		}
		return null;
	}
}
