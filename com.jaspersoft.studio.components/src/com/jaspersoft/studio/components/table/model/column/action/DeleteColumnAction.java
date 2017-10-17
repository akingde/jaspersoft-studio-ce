/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.TableComponentFactory;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.RefreshColumnNamesCommand;

import net.sf.jasperreports.components.table.StandardBaseColumn;

/*
 * The Class CreateGroupAction.
 */
public class DeleteColumnAction extends DeleteAction {

	/** The Constant ID. */
	public static final String ID = "delete_table_column"; //$NON-NLS-1$

	/**
	 * Constructs a <code>DeleteColumnAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public DeleteColumnAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.DeleteColumnAction_text);
		setToolTipText(Messages.DeleteColumnAction_tooltip);
		setId(DeleteColumnAction.ID);
	}

	@Override
	public Command createDeleteCommand(List objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		JSSCompoundCommand compoundCmd = new JSSCompoundCommand(getText(), null);
		MColumn col = null;
		HashSet<StandardBaseColumn> deletedColumns = new HashSet<StandardBaseColumn>();
		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			if (object.getModel() instanceof MColumn) {
				MColumn model = (MColumn) object.getModel();
				col = model;
				compoundCmd.setReferenceNodeIfNull(model);
				if (!deletedColumns.contains(model.getValue())){
					Command cmd = TableComponentFactory.getDeleteColumnCommand(model.getParent(), model);
					if (cmd != null){
						compoundCmd.add(cmd);
						deletedColumns.add(model.getValue());
					}
				}
			}
		}
		//There are no column selected, must return null since the action is not valid
		if (compoundCmd.isEmpty()) return null;
		//Commands to refresh the columns names on undo or execute
		compoundCmd.addFirst(new RefreshColumnNamesCommand(col, false, true));
		compoundCmd.add(new RefreshColumnNamesCommand(col, true, false));
		return compoundCmd;
	}
}
