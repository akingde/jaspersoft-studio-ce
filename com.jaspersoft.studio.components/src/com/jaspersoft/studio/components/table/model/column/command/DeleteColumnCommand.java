/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.column.MColumn;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardTable;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteColumnCommand extends Command {

	private StandardTable jrTable;
	private StandardBaseColumn jrColumn;
	private MTable tableNode;

	/** The element position. */
	private int elementPosition = 0;

	public DeleteColumnCommand(AMCollection destNode, MColumn srcNode) {
		super();
		this.tableNode = TableManager.getTableNode(destNode);
		this.jrTable = tableNode.getStandardTable();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		elementPosition = jrTable.getColumns().indexOf(jrColumn);
	}

	public DeleteColumnCommand(MTableGroupHeader destNode, MColumn srcNode) {
		super();
		this.tableNode = TableManager.getTableNode(destNode);
		this.jrTable = tableNode.getStandardTable();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		elementPosition = jrTable.getColumns().indexOf(jrColumn);
	}

	public DeleteColumnCommand(MTableGroupFooter destNode, MColumn srcNode) {
		super();
		this.tableNode = TableManager.getTableNode(destNode);
		this.jrTable = tableNode.getStandardTable();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		elementPosition = jrTable.getColumns().indexOf(jrColumn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		jrTable.removeColumn(jrColumn);
		tableNode.getTableManager().updateTableSpans();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrTable == null || jrColumn == null)
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
		if (elementPosition < 0 || elementPosition > jrTable.getColumns().size())
			jrTable.addColumn(jrColumn);
		else
			jrTable.addColumn(elementPosition, jrColumn);
		
		tableNode.getTableManager().updateTableSpans();
	}
}
