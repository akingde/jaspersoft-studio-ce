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
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignGroup;

import com.jaspersoft.studio.table.model.column.MColumn;
import com.jaspersoft.studio.table.model.column.command.CreateColumnFromGroupCommand;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.table.util.TableColumnSize;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnGroupFromGroupCommand extends CreateColumnFromGroupCommand {

	public CreateColumnGroupFromGroupCommand(MColumnGroup destNode, MColumn srcNode, int index) {
		super(destNode, srcNode, index);
	}

	public CreateColumnGroupFromGroupCommand(MColumnGroupCell destNode, MColumn srcNode, int index) {
		super(destNode, srcNode, index);
	}

	@Override
	protected StandardBaseColumn createColumn() {
		StandardBaseColumn col = new StandardColumnGroup();
		col.setWidth(0);
		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableUtil.TABLE_HEADER, null) / 2);
		col.setTableHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableUtil.TABLE_FOOTER, null) / 2);
		col.setTableFooter(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableUtil.COLUMN_HEADER, null) / 2);
		col.setColumnHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableUtil.COLUMN_FOOTER, null) / 2);
		col.setColumnFooter(cell);

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrDesignGroup = (JRDesignGroup) it.next();
				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableUtil.COLUMN_GROUP_HEADER,
						jrDesignGroup.getName()) / 2);
				col.setGroupHeader(jrDesignGroup.getName(), cell);

				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup, TableUtil.COLUMN_GROUP_FOOTER,
						jrDesignGroup.getName()) / 2);
				col.setGroupFooter(jrDesignGroup.getName(), cell);
			}
		return col;
	}

}
