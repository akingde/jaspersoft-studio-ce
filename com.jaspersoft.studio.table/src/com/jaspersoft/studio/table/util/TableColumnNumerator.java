package com.jaspersoft.studio.table.util;

import java.util.List;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.table.TableManager;
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
