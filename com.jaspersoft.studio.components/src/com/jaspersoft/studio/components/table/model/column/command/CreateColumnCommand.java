/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.Column;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnCommand extends Command {

	private StandardBaseColumn jrColumn;

	protected StandardColumnGroup pColGroup;

	protected StandardTable jrTable;

	protected JasperDesign jrDesign;

	protected JRPropertiesMap tableMap;

	private int index;

	protected MTable tableNode;

	public CreateColumnCommand(AMCollection destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateColumnCommand(MTableGroupHeader destNode, MColumn srcNode, int index) {
		this((ANode) destNode.getParent(), srcNode, index);
	}

	public CreateColumnCommand(MTableGroupFooter destNode, MColumn srcNode, int index) {
		this((ANode) destNode.getParent(), srcNode, index);
	}

	public CreateColumnCommand(MTable destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateColumnCommand(MColumn destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
		this.index = index;
	}

	protected CreateColumnCommand(ANode destNode, MColumn srcNode, int index) {
		super();
		this.tableNode = TableManager.getTableNode(destNode);
		this.jrTable = tableNode != null ? tableNode.getStandardTable() : null;
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrDesign = destNode.getJasperDesign();
		this.tableMap = tableNode != null ? tableNode.getPropertiesMap() : null;
		if (destNode instanceof MColumnGroup || destNode instanceof MColumnGroupCell) {
			pColGroup = (StandardColumnGroup) destNode.getValue();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (tableNode.hasColumnsAutoresizeProportional()) {
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			createAndAddColumn();
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			createAndAddColumn();
		}
	}

	@Override
	public boolean canExecute() {
		return tableNode != null && jrTable != null;
	}

	private void createAndAddColumn() {
		if (jrColumn == null)
			jrColumn = createColumn(jrDesign, jrTable, tableMap);
		if (pColGroup != null) {
			if (index >= 0 && index < pColGroup.getColumns().size())
				pColGroup.addColumn(index, jrColumn);
			else
				pColGroup.addColumn(jrColumn);
		} else {
			if (index >= 0 && index < jrTable.getColumns().size())
				jrTable.addColumn(index, jrColumn);
			else
				jrTable.addColumn(jrColumn);
		}

		tableNode.getTableManager().updateTableSpans();
	}

	/**
	 * Create a column that cab be added to the table, take the styles
	 * information for the new cells from the table properties map if available.
	 * If this informations are not available the style informations are
	 * recovered from the siblings columns
	 * 
	 * @param jrDesign
	 *            the JasperDesign of the current report
	 * @param jrTable
	 *            the table where the column is added
	 * @param tableMap
	 *            the properties map of the table
	 * @return the created columns
	 */
	public StandardBaseColumn createColumn(JasperDesign jrDesign, StandardTable jrTable, JRPropertiesMap tableMap) {
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

		StandardColumn result = null;
		if (hasStyleProperties(tableMap)) {
			result = CreateColumnCommand.addColumnWithStyle(jrDesign, jrTable, tableMap, createTHeader, createTFooter,
					createCHeader, createCFooter, createGHeader, createGFooter);
		} else {
			result = CreateColumnCommand.addColWithSibling(jrDesign, jrTable, createTHeader, createTFooter,
					createCHeader, createCFooter, createGHeader, createGFooter, sibling);
		}

		return result;
	}

	/**
	 * Create a column that cab be added to the table, take the styles
	 * information for the new cells from the table properties map if available.
	 * If this informations are not available the style informations are
	 * recovered from the siblings columns
	 * 
	 * @param jrDesign
	 *            the JasperDesign of the current report
	 * @param jrTable
	 *            the table where the column is added
	 * @param tableMap
	 *            the properties map of the table
	 * @param createTHeader
	 *            flag to add or not the table header cell
	 * @param createTFooter
	 *            flag to add or not the table footer cell
	 * @param createCHeader
	 *            flag to add or not the column header cell
	 * @param createCFooter
	 *            flag to add or not the column footer cell
	 * @param createGHeader
	 *            flag to add or not the group header cell
	 * @param createGFooter
	 *            flag to add or not the group footer cell
	 * @param index
	 *            index where the column should be inserted, used to get the
	 *            siblings
	 * @return the created columns
	 */
	public static StandardColumn addColumn(JasperDesign jrDesign, StandardTable jrTable, JRPropertiesMap tableMap,
			boolean createTHeader, boolean createTFooter, boolean createCHeader, boolean createCFooter,
			boolean createGHeader, boolean createGFooter, int index) {
		StandardColumn result = null;
		if (hasStyleProperties(tableMap)) {
			result = CreateColumnCommand.addColumnWithStyle(jrDesign, jrTable, tableMap, createTHeader, createTFooter,
					createCHeader, createCFooter, createGHeader, createGFooter);
		} else {
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
			result = CreateColumnCommand.addColWithSibling(jrDesign, jrTable, createTHeader, createTFooter,
					createCHeader, createCFooter, createGHeader, createGFooter, sibling);
		}
		return result;
	}

	/**
	 * Create a column that can be added to the table, take the styles
	 * information for the new cells from the table properties map, used to
	 * recover the default styles
	 * 
	 * @param jrDesign
	 *            the JasperDesign of the current report
	 * @param jrTable
	 *            the table where the column is added
	 * @param tableMap
	 *            the properties map of the table
	 * @param isTHead
	 *            flag to add or not the table header cell
	 * @param isTFoot
	 *            flag to add or not the table footer cell
	 * @param isCHead
	 *            flag to add or not the column header cell
	 * @param isCFoot
	 *            flag to add or not the column footer cell
	 * @param isGHead
	 *            flag to add or not the group header cell
	 * @param isGFoot
	 *            flag to add or not the group footer cell
	 * @return the created columns
	 */
	public static StandardColumn addColumnWithStyle(JasperDesign jrDesign, StandardTable jrTable,
			JRPropertiesMap tableMap, boolean isTHead, boolean isTFoot, boolean isCHead, boolean isCFoot,
			boolean isGHead, boolean isGFoot) {
		// Get the default styles from the table properties
		JRStyle tableHeaderStyle = null;
		JRStyle columnHeaderStyle = null;
		JRStyle detailStyle = null;
		if (tableMap != null) {

			// Get the style for the table header
			String tableHeaderName = tableMap.getProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY);
			tableHeaderStyle = tableHeaderName != null ? jrDesign.getStylesMap().get(tableHeaderName) : null;

			// Get the style for the column header
			String columnHeaderName = tableMap.getProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY);
			columnHeaderStyle = columnHeaderName != null ? jrDesign.getStylesMap().get(columnHeaderName) : null;

			// Get the style for the detail
			String detailName = tableMap.getProperty(ApplyTableStyleAction.DETAIL_PROPERTY);
			detailStyle = detailName != null ? jrDesign.getStylesMap().get(detailName) : null;
		}

		StandardColumn col = new StandardColumn();
		col.setWidth(40);
		if (isTHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.TABLE_HEADER, null));
			cell.setStyle(tableHeaderStyle);
			col.setTableHeader(cell);
		}

		if (isTFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.TABLE_FOOTER, null));
			cell.setStyle(tableHeaderStyle);
			col.setTableFooter(cell);
		}

		if (isCHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_HEADER, null));
			cell.setStyle(columnHeaderStyle);
			col.setColumnHeader(cell);
		}

		if (isCFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_FOOTER, null));
			cell.setStyle(columnHeaderStyle);
			col.setColumnFooter(cell);
		}

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_DETAIL, null));
		col.setDetailCell(cell);
		cell.setStyle(detailStyle);

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				if (isGHead) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_GROUP_HEADER,
							jrGroup.getName()));
					cell.setStyle(columnHeaderStyle);
					col.setGroupHeader(jrGroup.getName(), cell);
				}
				if (isGFoot) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_GROUP_FOOTER,
							jrGroup.getName()));
					cell.setStyle(columnHeaderStyle);
					col.setGroupFooter(jrGroup.getName(), cell);
				}
			}

		return col;
	}

	/**
	 * Add a column to the table, take the styles information for the new cells
	 * from another column passed as parameter
	 * 
	 * @param jrDesign
	 *            the JasperDesign of the current report
	 * @param jrTable
	 *            the table where the column is added
	 * @param isTHead
	 *            flag to add or not the table header cell
	 * @param isTFoot
	 *            flag to add or not the table footer cell
	 * @param isCHead
	 *            flag to add or not the column header cell
	 * @param isCFoot
	 *            flag to add or not the column footer cell
	 * @param isGHead
	 *            flag to add or not the group header cell
	 * @param isGFoot
	 *            flag to add or not the group footer cell
	 * @param sibling
	 *            the sibling columns from where the styles are taken
	 * @return the created columns
	 */
	private static StandardColumn addColWithSibling(JasperDesign jrDesign, StandardTable jrTable, boolean isTHead,
			boolean isTFoot, boolean isCHead, boolean isCFoot, boolean isGHead, boolean isGFoot, BaseColumn sibling) {
		StandardColumn col = new StandardColumn();
		col.setWidth(40);
		if (isTHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.TABLE_HEADER, null));
			if (sibling != null && sibling.getTableHeader() != null)
				cell.setStyle(sibling.getTableHeader().getStyle());
			col.setTableHeader(cell);
		}

		if (isTFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.TABLE_FOOTER, null));
			if (sibling != null && sibling.getTableFooter() != null)
				cell.setStyle(sibling.getTableFooter().getStyle());
			col.setTableFooter(cell);
		}

		if (isCHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_HEADER, null));
			if (sibling != null && sibling.getColumnHeader() != null)
				cell.setStyle(sibling.getColumnHeader().getStyle());
			col.setColumnHeader(cell);
		}

		if (isCFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_FOOTER, null));
			if (sibling != null && sibling.getColumnFooter() != null)
				cell.setStyle(sibling.getColumnFooter().getStyle());
			col.setColumnFooter(cell);
		}

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_DETAIL, null));
		col.setDetailCell(cell);
		if ((sibling != null) && (sibling instanceof Column)) {
			Column detailCol = (Column) sibling;
			if (detailCol.getDetailCell() != null)
				cell.setStyle(detailCol.getDetailCell().getStyle());
		}

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				if (isGHead) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_GROUP_HEADER,
							jrGroup.getName()));
					if (sibling != null) {
						Cell groupHeader = sibling.getGroupHeader(jrGroup.getName());
						if (groupHeader != null)
							cell.setStyle(groupHeader.getStyle());
					}
					col.setGroupHeader(jrGroup.getName(), cell);
				}
				if (isGFoot) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_GROUP_FOOTER,
							jrGroup.getName()));
					if (sibling != null) {
						Cell groupFooter = sibling.getGroupFooter(jrGroup.getName());
						if (groupFooter != null)
							cell.setStyle(groupFooter.getStyle());
					}
					col.setGroupFooter(jrGroup.getName(), cell);
				}
			}

		return col;
	}

	/**
	 * Check if the passed map has one of the properties that bind the table to
	 * its default styles
	 * 
	 * @param tableMap
	 *            the properties map of the table
	 * @return true if the table map has one of the properties that reference
	 *         the default style, fasle otherwise
	 */
	protected static boolean hasStyleProperties(JRPropertiesMap tableMap) {
		return (tableMap.containsProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY)
				|| tableMap.containsProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY)
				|| tableMap.containsProperty(ApplyTableStyleAction.DETAIL_PROPERTY));
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

	private void removeAddedColumn() {
		if (pColGroup != null) {
			index = pColGroup.getColumns().indexOf(jrColumn);
			pColGroup.removeColumn(jrColumn);
		} else {
			index = jrTable.getColumns().indexOf(jrColumn);
			jrTable.removeColumn(jrColumn);
		}
	}

	@Override
	public void undo() {
		// when the operaiton is undone refresh the size
		if (tableNode.hasColumnsAutoresizeProportional()) {
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			removeAddedColumn();
			tableNode.setPropertyValue(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, true);
		} else {
			removeAddedColumn();
		}
	}
}
