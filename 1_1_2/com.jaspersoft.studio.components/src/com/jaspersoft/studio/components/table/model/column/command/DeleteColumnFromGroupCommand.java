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
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteColumnFromGroupCommand extends Command {

	private StandardColumnGroup jrGroup;
	private StandardBaseColumn jrColumn;
	private StandardTable jrTable;

	/** The element position. */
	private int elementPosition = 0;

	public DeleteColumnFromGroupCommand(MColumnGroup destNode, MColumn srcNode) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(destNode.getMTable());
	}

	public DeleteColumnFromGroupCommand(MColumnGroupCell destNode, MColumn srcNode) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(destNode.getMTable());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrGroup.getColumns().indexOf(jrColumn);

		jrGroup.removeColumn(jrColumn);
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, -jrColumn.getWidth());
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
		if (elementPosition < 0 || elementPosition > jrGroup.getColumns().size())
			jrGroup.addColumn(elementPosition, jrColumn);
		else
			jrGroup.addColumn(jrColumn);
		jrGroup.setWidth(jrGroup.getWidth() + jrColumn.getWidth());
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, jrColumn.getWidth());
	}

}
