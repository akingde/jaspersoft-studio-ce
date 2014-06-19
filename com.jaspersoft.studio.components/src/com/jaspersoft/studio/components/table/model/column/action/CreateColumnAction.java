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
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.RefreshColumnNamesCommand;
import com.jaspersoft.studio.editor.outline.actions.ACreateAction;
import com.jaspersoft.studio.editor.palette.JDPaletteCreationFactory;
import com.jaspersoft.studio.model.ANode;

/**
 * 
 * Generic action to create a column, this action place before and after the commands
 * to create the column the commands to refresh the column number after the execute or the undo
 * 
 * @author Orlandin Marco
 *
 */
public abstract class CreateColumnAction extends ACreateAction {
	
	public CreateColumnAction(IWorkbenchPart part) {
		super(part);
		setCreationFactory(new JDPaletteCreationFactory(MColumn.class));
	}

	/**
	 * Return the first node inside a table found inside the selection
	 * 
	 * @return a ANode of the table contained in the selection or null if it can't be found
	 */
	protected ANode getTableNode(){
		List<?> objects = getSelectedObjects();
		for (int i = 0; i < objects.size(); i++) {
			Object obj = objects.get(i);
			if (obj instanceof EditPart) {
				EditPart object = (EditPart) obj;
				if (object.getModel() instanceof MColumn){
					return (MColumn)object.getModel();
				} else if (object.getModel() instanceof AMCollection){
					return ((AMCollection)object.getModel()).getParent();
				} else if (object.getModel() instanceof MTable){
					return (ANode)object.getModel();
				}
			}
		}
		return null;
	}
	
	@Override
	public void run() {
		JSSCompoundCommand executedCommand = (JSSCompoundCommand)createCommand();
		//Append the command to refresh the column names
		ANode tableNode = getTableNode();
		executedCommand.addFirst(new RefreshColumnNamesCommand(tableNode, false, true));
		executedCommand.add(new RefreshColumnNamesCommand(tableNode, true, false));
		execute(executedCommand);
	}
}
