/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
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

	public CreateColumnFromGroupCommand(MColumnGroup destNode, MColumn srcNode,
			int index) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(destNode.getMTable());
		this.jrDesign = destNode.getJasperDesign();
	}

	public CreateColumnFromGroupCommand(MColumnGroupCell destNode,
			MColumn srcNode, int index) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrTable = TableManager.getTable(destNode.getMTable());
		this.jrDesign = destNode.getJasperDesign();
	}

	protected StandardBaseColumn createColumn() {
		boolean createTHeader = true;
		boolean createTFooter = true;
		boolean createCHeader = true;
		boolean createCFooter = true;
		boolean createGHeader = false;
		boolean createGFooter = false;

		List<BaseColumn> columns = TableUtil.getAllColumns(jrTable);
		BaseColumn sibling = null;
		if (columns.size() > 0) {
			if (index >= columns.size())
				sibling = columns.get(columns.size() - 1);
			else if (index <= 0)
				sibling = columns.get(columns.size() - 1);
			else
				sibling = columns.get(index);
		}
		if (sibling != null) {
			createTHeader = sibling.getTableHeader() != null;
			createTFooter = sibling.getTableFooter() != null;
			createCHeader = sibling.getColumnHeader() != null;
			createCFooter = sibling.getColumnFooter() != null;
			if (sibling.getGroupHeaders().size() > 0)
				createGHeader = sibling.getGroupHeaders().get(0) != null;
			if (sibling.getGroupFooters().size() > 0)
				createGFooter = sibling.getGroupFooters().get(0) != null;
		}

		StandardColumn col = new StandardColumn();
		col.setWidth(40);
		DesignCell cell = null;
		if (createTHeader) {
			cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup,TableUtil.TABLE_HEADER, null));
			col.setTableHeader(cell);
			if (sibling!=null && sibling.getTableHeader() != null) cell.setStyle(sibling.getTableHeader().getStyle());
		}
		if (createTFooter) {
			cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup,TableUtil.TABLE_FOOTER, null));
			col.setTableFooter(cell);
			if (sibling!=null && sibling.getTableFooter() != null) cell.setStyle(sibling.getTableFooter().getStyle());
		}
		if (createCHeader) {
			cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup,TableUtil.COLUMN_HEADER, null));
			col.setColumnHeader(cell);
			if (sibling!=null && sibling.getColumnHeader() != null) cell.setStyle(sibling.getColumnHeader().getStyle());
		}
		if (createCFooter) {
			cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup,TableUtil.COLUMN_FOOTER, null));
			col.setColumnFooter(cell);
			if (sibling!=null && sibling.getColumnFooter() != null) cell.setStyle(sibling.getColumnFooter().getStyle());
		}

		cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable, jrGroup,TableUtil.COLUMN_DETAIL, null));
		col.setDetailCell(cell);
		if (sibling!=null && sibling instanceof StandardBaseColumn) {
			StandardColumn siblingStandardCol = (StandardColumn)sibling;
			cell.setStyle(siblingStandardCol.getDetailCell().getStyle());
		}

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrDesignGroup = (JRDesignGroup) it.next();
				if (createGHeader) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable,jrGroup, TableUtil.COLUMN_GROUP_HEADER,jrDesignGroup.getName()));
					col.setGroupHeader(jrDesignGroup.getName(), cell);
					Cell siblingGroupCell = sibling.getGroupHeader(jrDesignGroup.getName());
					if (siblingGroupCell != null){
						cell.setStyle(siblingGroupCell.getStyle());
					}
				}
				if (createGFooter) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitGroupHeight(jrTable,jrGroup, TableUtil.COLUMN_GROUP_FOOTER,jrDesignGroup.getName()));
					col.setGroupFooter(jrDesignGroup.getName(), cell);
					Cell siblingGroupCell = sibling.getGroupFooter(jrDesignGroup.getName());
					if (siblingGroupCell != null){
						cell.setStyle(siblingGroupCell.getStyle());
					}
				}
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
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup,
				jrColumn.getWidth());
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
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup,
				-jrColumn.getWidth());
	}
}
