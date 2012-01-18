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
package com.jaspersoft.studio.components.table.util;

import java.util.List;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.model.INode;

public class TableColumnNumerator {

	public static void renumerateColumnNames(MTable mtable) {
		JRDesignComponentElement tbl = (JRDesignComponentElement) mtable.getValue();
		if (tbl.getComponent() instanceof StandardTable) {
			StandardTable table = (StandardTable) tbl.getComponent();
			List<BaseColumn> columns = TableUtil.getAllColumns(table.getColumns());
			setColNames(mtable, columns);
		}
	}

	public static void setColNames(INode n, List<BaseColumn> columns) {
		for (INode node : n.getChildren()) {
			if (node instanceof MColumn)
				setColumnName((MColumn) node, columns);
			if (node instanceof AMCollection || node instanceof MColumn)
				setColNames(node, columns);
		}
	}

	public static void setColumnName(MColumn col, List<BaseColumn> columns) {
		StandardBaseColumn bc = (StandardBaseColumn) col.getValue();
		int i = columns.indexOf(bc) + 1;
		if (col instanceof MColumnGroup || col instanceof MColumnGroupCell) {
			int size = TableUtil.getAllColumns(((StandardColumnGroup) bc).getColumns()).size();
			col.setName(Messages.common_columns + " [" + size + "]"); //$NON-NLS-1$ //$NON-NLS-2$
		} else if (col instanceof MColumn)
			col.setName(Messages.common_column + i);
	}
}
