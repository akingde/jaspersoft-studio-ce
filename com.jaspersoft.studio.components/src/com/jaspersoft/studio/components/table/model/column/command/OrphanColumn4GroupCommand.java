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
package com.jaspersoft.studio.components.table.model.column.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

/**
 * Delete a column group column from its parent. It also refresh the
 * name after its execution
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
		refreshNameCommand = new RefreshColumnNamesCommand(tableNode, true, true);
	}

	public OrphanColumn4GroupCommand(MColumnGroupCell parent, MColumn child) {
		super(Messages.common_orphan_element);
		MTable tableNode = parent.getMTable();
		this.jrGroup = (StandardColumnGroup) parent.getValue();
		this.jrColumn = (StandardBaseColumn) child.getValue();
		this.jrTable = TableManager.getTable(tableNode);
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
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, -jrColumn.getWidth());
		refreshNameCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index >= 0 && index <= jrGroup.getColumns().size())
			jrGroup.addColumn(index, jrColumn);
		else
			jrGroup.addColumn(jrColumn);
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, jrColumn.getWidth());
		refreshNameCommand.undo();
	}
}
