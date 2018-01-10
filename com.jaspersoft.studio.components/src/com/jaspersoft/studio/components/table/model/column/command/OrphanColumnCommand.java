/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.column.MColumn;

/**
 * Delete a standard column from its parent. It also refresh the
 * name after its execution
 * 
 * @author Chicu Veaceslav
 */
public class OrphanColumnCommand extends Command {

	/** The index. */
	private int index;

	private StandardBaseColumn jrColumn;
	private StandardTable jrTable;
	protected RefreshColumnNamesCommand refreshNameCommand;

	/**
	 * Instantiates a new orphan element group command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanColumnCommand(AMCollection parent, MColumn child) {
		super(Messages.common_orphan_element);
		this.jrTable = TableManager.getTable(parent);
		this.jrColumn = (StandardBaseColumn) child.getValue();
		refreshNameCommand = new RefreshColumnNamesCommand(child.getMTable(), true, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrTable.getColumns().indexOf(jrColumn);
		jrTable.removeColumn(jrColumn);
		refreshNameCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index >= 0 && index <= jrTable.getColumns().size())
			jrTable.addColumn(index, jrColumn);
		else
			jrTable.addColumn(jrColumn);
		refreshNameCommand.undo();
	}
}
