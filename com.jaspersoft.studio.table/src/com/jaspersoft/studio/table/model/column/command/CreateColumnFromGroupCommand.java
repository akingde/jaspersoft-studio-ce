/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.model.column.command;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.table.TableManager;
import com.jaspersoft.studio.table.model.column.MColumn;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.table.util.TableColumnSize;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnFromGroupCommand extends Command {

	private StandardBaseColumn jrColumn;

	protected StandardColumnGroup jrGroup;
	protected StandardTable jrTable;
	protected JasperDesign jrDesign;

	private int index;

	public CreateColumnFromGroupCommand(MColumnGroup destNode, MColumn srcNode, int index) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = CreateColumnCommand.getTable(destNode.getMTable());
		this.jrDesign = destNode.getJasperDesign();
	}

	public CreateColumnFromGroupCommand(MColumnGroupCell destNode, MColumn srcNode, int index) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = CreateColumnCommand.getTable(destNode.getMTable());
		this.jrDesign = destNode.getJasperDesign();
	}

	protected StandardBaseColumn createColumn() {
		StandardColumn col = new StandardColumn();
		col.setWidth(40);

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.TABLE_HEADER, null));
		col.setTableHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.TABLE_FOOTER, null));
		col.setTableFooter(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.COLUMN_HEADER, null));
		col.setColumnHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.COLUMN_FOOTER, null));
		col.setColumnFooter(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.COLUMN_DETAIL, null));
		col.setDetailCell(cell);

		List<?> groupsList = TableManager.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrDesignGroup = (JRDesignGroup) it.next();
				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.COLUMN_GROUP_HEADER,
						jrDesignGroup.getName()));
				col.setGroupHeader(jrDesignGroup.getName(), cell);

				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableColumnSize.COLUMN_GROUP_FOOTER,
						jrDesignGroup.getName()));
				col.setGroupFooter(jrDesignGroup.getName(), cell);
			}
		return col;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrColumn == null) {
			jrColumn = createColumn();
		}
		if (index >= 0 && index < jrGroup.getColumns().size())
			jrGroup.addColumn(index, jrColumn);
		else
			jrGroup.addColumn(jrColumn);
		jrGroup.setWidth(jrGroup.getWidth() + jrColumn.getWidth());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		index = jrGroup.getColumns().indexOf(jrColumn);
		jrGroup.removeColumn(jrColumn);
		jrGroup.setWidth(jrGroup.getWidth() - jrColumn.getWidth());
	}
}
