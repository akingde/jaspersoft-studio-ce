/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.table.TableComponentFactory;
import com.jaspersoft.studio.components.table.model.MTableDetail;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.model.ANode;

/*
 * The Class CreateGroupAction.
 */
public class DeleteColumnCellAction extends DeleteAction {

	/** The Constant ID. */
	public static final String ID = "delete_table_cell"; //$NON-NLS-1$

	/**
	 * Constructs a <code>DeleteColumnCellAction</code> using the specified
	 * part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public DeleteColumnCellAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText("Delete Cell");
		setToolTipText("Delete selected table cell");
		setId(DeleteColumnCellAction.ID);
	}

	@Override
	public Command createDeleteCommand(List objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		GroupRequest deleteReq = new GroupRequest(RequestConstants.REQ_DELETE);
		deleteReq.setEditParts(objects);

		CompoundCommand compoundCmd = new CompoundCommand(getText());
		for (int i = 0; i < objects.size(); i++) {
			EditPart object = (EditPart) objects.get(i);
			if (object.getModel() instanceof MCell) {
				MColumn model = (MColumn) object.getModel();
				ANode parent = model.getParent();
				//The cell of the detail can not be deleted
				if (!(parent instanceof MTableDetail)){
					Command cmd = TableComponentFactory.getDeleteCellCommand(
							model.getParent(), model);
					if (cmd != null)
						compoundCmd.add(cmd);
				}
			}
		}
		return compoundCmd;
	}
}
