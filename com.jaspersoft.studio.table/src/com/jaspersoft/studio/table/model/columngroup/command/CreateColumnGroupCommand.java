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
package com.jaspersoft.studio.table.model.columngroup.command;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.engine.design.JRDesignGroup;

import com.jaspersoft.studio.table.TableManager;
import com.jaspersoft.studio.table.model.AMCollection;
import com.jaspersoft.studio.table.model.column.MColumn;
import com.jaspersoft.studio.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.table.util.TableColumnSize;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnGroupCommand extends CreateColumnCommand {

	public CreateColumnGroupCommand(MColumn destNode, MColumnGroup srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateColumnGroupCommand(AMCollection destNode, MColumnGroup srcNode, int index) {
		super(destNode, srcNode, index);
	}

	@Override
	protected StandardBaseColumn createColumn() {
		StandardBaseColumn col = new StandardColumnGroup();
		col.setWidth(0);

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableColumnSize.TABLE_HEADER, null) / 2);
		col.setTableHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableColumnSize.TABLE_FOOTER, null) / 2);
		col.setTableFooter(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableColumnSize.COLUMN_HEADER, null) / 2);
		col.setColumnHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableColumnSize.COLUMN_FOOTER, null) / 2);
		col.setColumnFooter(cell);

		List<?> groupsList = TableManager.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableColumnSize.COLUMN_GROUP_HEADER,
						jrGroup.getName()) / 2);
				col.setGroupHeader(jrGroup.getName(), cell);

				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableColumnSize.COLUMN_GROUP_FOOTER,
						jrGroup.getName()) / 2);
				col.setGroupFooter(jrGroup.getName(), cell);
			}
		return col;
	}

}
