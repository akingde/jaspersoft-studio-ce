/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableComponentFactory;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

/**
 * Delete a column from a parent column group. It also refresh the
 * name after its execution. It at the end the group is empty the 
 * group itself is removed 
 * 
 * @author Chicu Veaceslav
 */
public class DeleteColumnFromGroupCommand extends Command {

	private StandardColumnGroup jrGroup;
	
	private StandardBaseColumn jrColumn;
	
	private StandardTable jrTable;

	/** The element position. */
	private int elementPosition = 0;
	
	private Command deleteGroupCommand = null;
	
	private ANode groupNode;

	public DeleteColumnFromGroupCommand(StandardColumnGroup destNode, MColumn srcNode) {
		super();
		this.jrGroup = destNode;
		this.groupNode = getGroupNode(destNode, srcNode.getTable().getChildren());
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(srcNode.getMTable());
		elementPosition = jrGroup.getColumns().indexOf(jrColumn);
	}
	
	public DeleteColumnFromGroupCommand(MColumnGroup destNode, MColumn srcNode) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.groupNode = destNode;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(destNode.getMTable());
		elementPosition = jrGroup.getColumns().indexOf(jrColumn);
	}

	public DeleteColumnFromGroupCommand(MColumnGroupCell destNode, MColumn srcNode) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.groupNode = destNode;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(destNode.getMTable());
		elementPosition = jrGroup.getColumns().indexOf(jrColumn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		jrGroup.removeColumn(jrColumn);
		
		if (jrGroup.getColumns().isEmpty()){
			deleteGroupCommand = TableComponentFactory.getDeleteColumnCommand(groupNode.getParent(), groupNode);
			if (deleteGroupCommand != null && deleteGroupCommand.canExecute()){
				//Set the width to 0 since it is not necessary to compute a new width
				//because we already know it is empty
				jrGroup.setWidth(0);
				deleteGroupCommand.execute();
			}
		} else {
			TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, -jrColumn.getWidth());	
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrColumn == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (deleteGroupCommand != null && deleteGroupCommand.canUndo()){
			deleteGroupCommand.undo();
		}
		
		if (elementPosition < 0 || elementPosition > jrGroup.getColumns().size()){
			jrGroup.addColumn(jrColumn);
		} else {
			jrGroup.addColumn(elementPosition, jrColumn);
		}
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, jrColumn.getWidth());
	}
	
	private ANode getGroupNode(StandardColumnGroup searchedGroup, List<INode> children){
		for(INode child : children){
			if (child.getValue() == searchedGroup){
				return (ANode)child;
			}
			ANode childrenResult = getGroupNode(searchedGroup, child.getChildren());
			if (childrenResult != null){
				return childrenResult;
			}
		}
		return null;
	}

}
