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
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTableDetail;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.model.INode;

/**
 * Action to delete an full row of the table
 * 
 * @author Orlandin Marco
 *
 */
public class DeleteRowAction extends DeleteAction {

	/** The Constant ID. */
	public static final String ID = "delete_table_row"; //$NON-NLS-1$
	
	public DeleteRowAction(IWorkbenchPart part) {
		super(part);
	}
	

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.DeleteRowAction_name);
		setToolTipText(Messages.DeleteRowAction_tooltip);
		setId(DeleteRowAction.ID);
	}
	
	/**
	 * Search for every AMcollection (superclass of the row of the table)
	 * and create a delte cell command for everyone of its children
	 */
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
			if (object.getModel() instanceof AMCollection && !(object.getModel() instanceof MTableDetail)) {
				AMCollection model = (AMCollection) object.getModel();
				for(INode child : model.getChildren()){
					if (child instanceof MCell){
						Command cmd = TableComponentFactory.getDeleteCellCommand(model, (MCell)child);
						if (cmd != null)
							compoundCmd.add(cmd);
					}
				}
			}
		}
		return compoundCmd;
	}
}
