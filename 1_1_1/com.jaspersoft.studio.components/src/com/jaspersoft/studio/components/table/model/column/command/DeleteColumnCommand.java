/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.table.model.column.command;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

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
		this.jrTable = CreateColumnCommand.getTable(destNode);
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	public DeleteColumnCommand(MTableGroupHeader destNode, MColumn srcNode) {
		super();
		this.jrTable = CreateColumnCommand.getTable((ANode) destNode.getParent());
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	public DeleteColumnCommand(MTableGroupFooter destNode, MColumn srcNode) {
		super();
		this.jrTable = CreateColumnCommand.getTable((ANode) destNode.getParent());
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
