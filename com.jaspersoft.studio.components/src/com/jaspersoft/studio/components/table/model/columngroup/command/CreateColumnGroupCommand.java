/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.columngroup.command;

import java.util.Iterator;
import java.util.List;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.CreateColumnCommand;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnGroupCommand extends CreateColumnCommand {
	private boolean resize = true;

	public CreateColumnGroupCommand(MColumn destNode, MColumnGroup srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	public CreateColumnGroupCommand(AMCollection destNode,
			MColumnGroup srcNode, int index) {
		super(destNode, srcNode, index);
	}



	public void setResize(boolean resize) {
		this.resize = resize;
	}

	@Override
	public StandardBaseColumn createColumn(JasperDesign jrDesign, StandardTable jrTable, JRPropertiesMap tableMap) {
		StandardBaseColumn col = new StandardColumnGroup();
		col.setWidth(0);

		/*DesignCell cell = new DesignCell();
		cell.setHeight(height);
		col.setTableHeader(cell);

		cell = new DesignCell();
		cell.setHeight(height);
		col.setTableFooter(cell);

		cell = new DesignCell();
		cell.setHeight(height);
		col.setColumnHeader(cell);

		cell = new DesignCell();
		cell.setHeight(height);
		col.setColumnFooter(cell);
		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				cell = new DesignCell();
				cell.setHeight(height);
				col.setGroupHeader(jrGroup.getName(), cell);

				cell = new DesignCell();
				cell.setHeight(height);
				col.setGroupFooter(jrGroup.getName(), cell);
			}*/

		return col;
	}

	@Override
	public void execute() {
		TableManager tb = tableNode.getTableManager();
		super.execute();
		if (resize) {
			int height = MColumnGroup.DEFAULT_CELL_HEIGHT;
			List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.TABLE_HEADER, "",
					height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.COLUMN_HEADER, "",
					height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.COLUMN_DETAIL, "",
					height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.COLUMN_FOOTER, "",
					height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.TABLE_FOOTER, "",
					height);
			if (groupsList != null)
				for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
					JRDesignGroup jrGroup = (JRDesignGroup) it.next();
					tb.setRowHeight(jrTable.getColumns(),
							TableUtil.COLUMN_GROUP_HEADER, jrGroup.getName(),
							height);
					tb.setRowHeight(jrTable.getColumns(),
							TableUtil.COLUMN_GROUP_FOOTER, jrGroup.getName(),
							height);
				}
		}
	}

	@Override
	public void undo() {
		if (resize) {
			int height = MColumnGroup.DEFAULT_CELL_HEIGHT;
			TableManager tb = new TableManager(jrTable, jrDesign);
			List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.TABLE_HEADER, "",
					-height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.COLUMN_HEADER, "",
					-height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.COLUMN_DETAIL, "",
					-height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.COLUMN_FOOTER, "",
					-height);
			tb.setRowHeight(jrTable.getColumns(), TableUtil.TABLE_FOOTER, "",
					-height);
			if (groupsList != null)
				for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
					JRDesignGroup jrGroup = (JRDesignGroup) it.next();
					tb.setRowHeight(jrTable.getColumns(),
							TableUtil.COLUMN_GROUP_HEADER, jrGroup.getName(),
							-height);
					tb.setRowHeight(jrTable.getColumns(),
							TableUtil.COLUMN_GROUP_FOOTER, jrGroup.getName(),
							-height);
				}
		}
		super.undo();
	}

}
