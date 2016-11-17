/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableComponentFactory;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;

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
public class OrphanColumn4GroupCommand extends Command {

	/** The index. */
	private int index;

	private StandardBaseColumn jrColumn;
	protected StandardTable jrTable;
	private StandardColumnGroup jrGroup;
	protected RefreshColumnNamesCommand refreshNameCommand;

	private Command deleteGroupCommand = null;
	
	private ANode groupNode;
	
	
	/**
	 * Instantiates a new orphan element group command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanColumn4GroupCommand(MColumnGroup parent, MColumn child) {
		super(Messages.common_orphan_element);
		MTable tableNode = parent.getMTable();
		this.jrGroup = (StandardColumnGroup) parent.getValue();
		this.jrColumn = (StandardBaseColumn) child.getValue();
		this.jrTable = TableManager.getTable(tableNode);
		this.groupNode = parent;
		refreshNameCommand = new RefreshColumnNamesCommand(tableNode, true, true);
	}

	public OrphanColumn4GroupCommand(MColumnGroupCell parent, MColumn child) {
		super(Messages.common_orphan_element);
		MTable tableNode = parent.getMTable();
		this.jrGroup = (StandardColumnGroup) parent.getValue();
		this.jrColumn = (StandardBaseColumn) child.getValue();
		this.jrTable = TableManager.getTable(tableNode);
		this.groupNode = parent;
		refreshNameCommand = new RefreshColumnNamesCommand(tableNode, true, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrGroup.getColumns().indexOf(jrColumn);
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
		
		refreshNameCommand.execute();
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
		
		if (index >= 0 && index <= jrGroup.getColumns().size())
			jrGroup.addColumn(index, jrColumn);
		else
			jrGroup.addColumn(jrColumn);
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, jrColumn.getWidth());
		refreshNameCommand.undo();
	}
}
