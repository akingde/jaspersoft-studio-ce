/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.util;

import java.util.List;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.table.messages.Messages;
import com.jaspersoft.studio.table.model.AMCollection;
import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.table.model.column.MColumn;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroupCell;

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
