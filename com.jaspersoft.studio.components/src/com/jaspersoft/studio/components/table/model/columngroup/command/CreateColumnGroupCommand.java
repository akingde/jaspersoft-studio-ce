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
package com.jaspersoft.studio.components.table.model.columngroup.command;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.util.TableColumnSize;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnGroupCommand extends CreateColumnCommand {

	public CreateColumnGroupCommand(MColumn destNode, MColumnGroup srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	public CreateColumnGroupCommand(AMCollection destNode,
			MColumnGroup srcNode, int index) {
		super(destNode, srcNode, index);
	}

	@Override
	public StandardBaseColumn createColumn(JasperDesign jrDesign,
			StandardTable jrTable) {
		StandardBaseColumn col = new StandardColumnGroup();
		col.setWidth(0);

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
				TableUtil.TABLE_HEADER, null) / 2);
		col.setTableHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
				TableUtil.TABLE_FOOTER, null) / 2);
		col.setTableFooter(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
				TableUtil.COLUMN_HEADER, null) / 2);
		col.setColumnHeader(cell);

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
				TableUtil.COLUMN_FOOTER, null) / 2);
		col.setColumnFooter(cell);

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
						TableUtil.COLUMN_GROUP_HEADER, jrGroup.getName()) / 2);
				col.setGroupHeader(jrGroup.getName(), cell);

				cell = new DesignCell();
				cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
						TableUtil.COLUMN_GROUP_FOOTER, jrGroup.getName()) / 2);
				col.setGroupFooter(jrGroup.getName(), cell);
			}
		return col;
	}

}
