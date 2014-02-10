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
package com.jaspersoft.studio.components.table.model.column.command;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.model.ANode;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteColumnCommand extends Command {

	private StandardTable jrTable;
	private StandardBaseColumn jrColumn;

	/** The element position. */
	private int elementPosition = 0;

	public DeleteColumnCommand(AMCollection destNode, MColumn srcNode) {
		super();
		this.jrTable = TableManager.getTable(destNode);
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	public DeleteColumnCommand(MTableGroupHeader destNode, MColumn srcNode) {
		super();
		this.jrTable = TableManager.getTable((ANode) destNode.getParent());
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	public DeleteColumnCommand(MTableGroupFooter destNode, MColumn srcNode) {
		super();
		this.jrTable = TableManager.getTable((ANode) destNode.getParent());
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrTable.getColumns().indexOf(jrColumn);

		jrTable.removeColumn(jrColumn);
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
			jrTable.addColumn(elementPosition, jrColumn);
		else
			jrTable.addColumn(jrColumn);
	}

}
