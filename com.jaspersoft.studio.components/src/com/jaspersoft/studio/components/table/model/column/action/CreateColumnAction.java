/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
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
	 * Return a map of the tables in the selection. This will not allow to double the operation 
	 * if two cells of the same table are selected for the action
	 * 
	 * @return a not null hashmap where the key is an unique table in the selection and
	 * the value is the edit part selected
	 */
	protected HashMap<ANode, EditPart> getTableNodes(List<?> objects){
		HashMap<ANode, EditPart> result = new HashMap<ANode, EditPart>();
		for (int i = 0; i < objects.size(); i++) {
			Object obj = objects.get(i);
			if (obj instanceof EditPart) {
				EditPart object = (EditPart) obj;
				if (object.getModel() instanceof MColumn){
					 result.put(((MColumn)object.getModel()).getTable(), object);
				} else if (object.getModel() instanceof AMCollection){
					 result.put(((AMCollection)object.getModel()).getMTable(), object);
				} else if (object.getModel() instanceof MTable){
					result.put((ANode)object.getModel(), object);
				}
			}
		}
		return result;
	}
	
	public void execute(ISelection selection){
		if (selection instanceof IStructuredSelection)
			execute(((IStructuredSelection) selection).toList());
	}
	
	public void execute(List<?> editparts){
		JSSCompoundCommand executedCommand = (JSSCompoundCommand)createCommand(editparts);
		execute(executedCommand);
	}
	
	public boolean canExecute(List<?> editparts){
		Command cmd = createCommand(editparts);
		return (cmd != null && cmd.canExecute());
	}
	
	public boolean canExecute(ISelection selection){
		if (selection instanceof IStructuredSelection)
			return canExecute(((IStructuredSelection) selection).toList());
		return false;
	}
	
	@Override
	public Command createCommand(){
		return createCommand(getSelectedObjects());
	}
	
	public Command createCommand(List<?> objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		CreateRequest createReq = new CreateRequest(RequestConstants.REQ_CREATE);
		createReq.setLocation(location);
		createReq.setFactory(creationFactory);
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (!setExtendedData(map, objects))
			return null;
		createReq.setExtendedData(map);

		HashMap<ANode, EditPart> tables = getTableNodes(objects);
		JSSCompoundCommand jssCcmd = new JSSCompoundCommand(null);
		for(Entry<ANode, EditPart> entry : tables.entrySet()){
			//Set the node if necessary to disable the refresh
			jssCcmd.setReferenceNodeIfNull(entry.getKey());	
			Command cmd = entry.getValue().getCommand(createReq);
			if (cmd != null) {
				jssCcmd.add(cmd);
			}
		}
 
		if(!jssCcmd.isEmpty()) {
			//Append the command to refresh the column names
			for(ANode tableNode : tables.keySet()){
				jssCcmd.addFirst(new RefreshColumnNamesCommand(tableNode, false, true));
				jssCcmd.add(new RefreshColumnNamesCommand(tableNode, true, false));
			}
			return jssCcmd;
		}
		else {
			return null;
		}
	}
	
	@Override
	public void run() {
		execute(getSelectedObjects());
	}
}
