/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableColumnFooter;
import com.jaspersoft.studio.components.table.model.MTableColumnHeader;
import com.jaspersoft.studio.components.table.model.MTableDetail;
import com.jaspersoft.studio.components.table.model.MTableFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.MTableHeader;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Command to create all the cells inside a specific table section
 * 
 * @author Orlandin Marco
 */
public class CreateSectionCellsCommand extends Command {
	
	/**
	 * The section type where the cell will be created
	 */
	private Class<AMCollection> type;
	
	/**
	 * The current group name if the section is a group, otherwise null
	 */
	private String groupName = null;

	/**
	 * The section where the cells will be created
	 */
	private AMCollection section;
	
	/**
	 * The spans before the cells creations, used into the undo
	 */
	private HashMap<Cell, Integer> oldSpans = new HashMap<Cell, Integer>();
	
	/**
	 * The created cells, used into the undo
	 */
	private HashMap<Cell, BaseColumn> createdCells = new HashMap<Cell,BaseColumn>();

	/**
	 * Create the command
	 * 
	 * @param parent the section where all the cells will be created, must be not null
	 */
	@SuppressWarnings("unchecked")
	public CreateSectionCellsCommand(AMCollection parent) {
		super();
		type = (Class<AMCollection>) parent.getClass();
		this.section = parent;
		if (parent instanceof MTableGroupHeader){
			groupName = ((MTableGroupHeader) parent).getJrDesignGroup().getName();
		} else if (parent instanceof MTableGroupFooter){
			groupName = ((MTableGroupFooter) parent).getJrDesignGroup().getName();
		}
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
	protected boolean hasStyleProperties(JRPropertiesMap tableMap) {
		return (tableMap.containsProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY)
				|| tableMap.containsProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY)
				|| tableMap.containsProperty(ApplyTableStyleAction.DETAIL_PROPERTY));
	}

	/**
	 * Return the style name of the style that will be used in the new cell
	 * 
	 * @param tableMap the map from where the default styles properties are read
	 * @return the name of the style or null if a style can not be resolved
	 */
	protected String getStyleName(JRPropertiesMap tableMap){
		if (hasStyleProperties(tableMap)){
			if (type.isAssignableFrom(MTableHeader.class) || type.isAssignableFrom(MTableFooter.class)){
				return tableMap.getProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY);
			} else if (type.isAssignableFrom(MTableColumnHeader.class) || type.isAssignableFrom(MTableColumnFooter.class)
						|| (type.isAssignableFrom(MTableGroupHeader.class) || (type.isAssignableFrom(MTableGroupFooter.class)))){
				return tableMap.getProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY);
			} else if (type.isAssignableFrom(MTableDetail.class)){
				return tableMap.getProperty(ApplyTableStyleAction.DETAIL_PROPERTY);
			}
		} 
		return null;
	}
	
	/**
	 * Return a list of every columns in the table, considering also the
	 * ColumnGroup
	 * 
	 * @param cols the current set of columns, it is a recursive method
	 * @return the list of columns contained in the passed parameter (considering
	 * also the subcolumns contained by the columns groups)
	 */
	protected List<BaseColumn> getAllColumns(List<BaseColumn> cols) {
		List<BaseColumn> lst = new ArrayList<BaseColumn>();
		for (BaseColumn bc : cols) {
			if (bc instanceof ColumnGroup){
				lst.addAll(getAllColumns(((ColumnGroup) bc).getColumns()));
			} 
			lst.add(bc);
		}
		return lst;
	}
	
	/**
	 * Check if the passed column in the specific section where the cell should be created
	 * has already a cell there
	 * 
	 * @param jrColumn the looked column, must be not null
	 * @return true if the column has already a cell for this section, false otherwise
	 */
	private boolean hasCell(BaseColumn jrColumn){
		if (type.isAssignableFrom(MTableHeader.class)){
			return jrColumn.getTableHeader() != null;
		} else if (type.isAssignableFrom(MTableFooter.class)){
			return jrColumn.getTableFooter() != null;
		} else if (type.isAssignableFrom(MTableColumnHeader.class)){
			return jrColumn.getColumnHeader() != null;
		} else if (type.isAssignableFrom(MTableColumnFooter.class)){
			return jrColumn.getColumnFooter() != null;
		} else if (type.isAssignableFrom(MTableGroupHeader.class) && groupName != null){
			return jrColumn.getGroupHeader(groupName) != null;
		} else if (type.isAssignableFrom(MTableGroupFooter.class) && groupName != null){
			return jrColumn.getGroupFooter(groupName) != null;
		}
		return false;
	}
	
	/**
	 * Set the passed cell in the passed column in the section where the cells
	 * should be created
	 * 
	 * @param jrColumn the column where the cell should be set, must be not null
	 * @param cell the cell to set, if null the cell will be removed from the column
	 */
	private void setCell(BaseColumn jrColumn, Cell cell){
		if (jrColumn instanceof StandardBaseColumn){
			StandardBaseColumn jrBaseColumn = (StandardBaseColumn)jrColumn;
			if (type.isAssignableFrom(MTableHeader.class)){
				jrBaseColumn.setTableHeader(cell);
			} else if (type.isAssignableFrom(MTableFooter.class)){
				jrBaseColumn.setTableFooter(cell);
			} else if (type.isAssignableFrom(MTableColumnHeader.class)){
				jrBaseColumn.setColumnHeader(cell);
			} else if (type.isAssignableFrom(MTableColumnFooter.class)){
				jrBaseColumn.setColumnFooter(cell);
			} else if (type.isAssignableFrom(MTableGroupHeader.class) && groupName != null){
				jrBaseColumn.setGroupHeader(groupName, cell);
			} else if (type.isAssignableFrom(MTableGroupFooter.class) && groupName != null){
				jrBaseColumn.setGroupFooter(groupName, cell);
			}
		}
	}
	
	/**
	 * Return the cell of the passed column into this specific section
	 * 
	 * @param jrBaseColumn the column from where the cell is read, must be not null
	 * @return the cell into the section handled by this command, of the passed column. Can be null
	 */
	private Cell getCell(BaseColumn jrBaseColumn){
		if (type.isAssignableFrom(MTableHeader.class)){
			return jrBaseColumn.getTableHeader();
		} else if (type.isAssignableFrom(MTableFooter.class)){
			return jrBaseColumn.getTableFooter();
		} else if (type.isAssignableFrom(MTableColumnHeader.class)){
			return jrBaseColumn.getColumnHeader();
		} else if (type.isAssignableFrom(MTableColumnFooter.class)){
			return jrBaseColumn.getColumnFooter();
		} else if (type.isAssignableFrom(MTableGroupHeader.class) && groupName != null){
			return jrBaseColumn.getGroupHeader(groupName);
		} else if (type.isAssignableFrom(MTableGroupFooter.class) && groupName != null){
			return jrBaseColumn.getGroupFooter(groupName);
		}
		return null;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		MTable table = section.getMTable();
		TableManager manager = table.getTableManager();
		List<BaseColumn> columns = getAllColumns(table.getStandardTable().getColumns());
		String styleName = getStyleName(table.getPropertiesMap());
		createdCells.clear();
		
		//Create the cell for every columns that has not already one
		for(BaseColumn col : columns){
			if (!hasCell(col)){
				Cell jrCell = createCell(styleName);
				setCell(col, jrCell);
				createdCells.put(jrCell, col);
			}
		}
		//Set the height of the cells
		getHeight(table.getStandardTable().getColumns());
		
		//Calculate the new spans
		HashMap<Cell, Integer> spans = null;
		if (type.isAssignableFrom(MTableHeader.class)){
			spans = manager.getTableHeaderSpans();
		} else if (type.isAssignableFrom(MTableFooter.class)){
			spans = manager.getTableFooterSpans();
		} else if (type.isAssignableFrom(MTableColumnHeader.class)){
			spans = manager.getColumnHeaderSpans();
		} else if (type.isAssignableFrom(MTableColumnFooter.class)){
			spans = manager.getColumnFooterSpans();
		} else if (type.isAssignableFrom(MTableGroupHeader.class)){
			spans = manager.getGroupHeaderSpans(groupName);
		} else if (type.isAssignableFrom(MTableGroupFooter.class)){
			spans = manager.getGroupFooterSpans(groupName);
		}
		
		//Backup the old spans and set the new
		if (spans != null){
			for(Entry<Cell, Integer> value : spans.entrySet()){
				if (value.getKey().getRowSpan() != value.getValue()){
					oldSpans.put(value.getKey(), value.getKey().getRowSpan());
					((DesignCell)value.getKey()).setRowSpan(value.getValue());
				}
			}
		}
		
	}
	
	/**
	 * Set recursively the height of the passed columns  to compensate the presence of groups
	 * The base height is fixed to 30
	 * 
	 * @param columns the columns where the height should be set
	 * @return return in the recursion the height of a group
	 */
	private int getHeight(List<BaseColumn> columns){
		int  maxHeight = 30;
		for(BaseColumn column : columns){
			if (column instanceof StandardColumnGroup){
				int groupHeight = 30 + getHeight(((StandardColumnGroup)column).getColumns());
				maxHeight = Math.max(groupHeight, groupHeight);
			}
		}
		for(BaseColumn column : columns){
			if (column instanceof StandardColumnGroup){
				Cell cell = getCell(column);
				if (cell != null){
					((DesignCell)cell).setHeight(30);
				}
			} else {
				Cell cell = getCell(column);
				if (cell != null){
					((DesignCell)cell).setHeight(maxHeight);
				}				
			}
		}
		return maxHeight;
	}

	/**
	 * Create the new cell
	 * 
	 * @param styleName the style for the new cell, can be null
	 * @return the cell to add to the column
	 */
	protected Cell createCell(String styleName) {
		DesignCell cell = new DesignCell();
		cell.setHeight(0);
		if (styleName != null){
			JasperDesign jd = section.getJasperDesign();
			JRStyle internalStyle = jd.getStylesMap().get(styleName);
			if (internalStyle != null){
				cell.setStyle(internalStyle);
			} else {
				cell.setStyleNameReference(styleName);
			}
		}
		return cell;
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
	
	@Override
	public boolean canExecute() {
		MTable table = section.getMTable();
		List<BaseColumn> columns = getAllColumns(table.getStandardTable().getColumns());
		for(BaseColumn col : columns){
			if (hasCell(col)){
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		for(BaseColumn changedColumn : createdCells.values()){
			setCell(changedColumn, null);
		}
		createdCells.clear();
		//Restore the old spans
		for(Entry<Cell, Integer> value : oldSpans.entrySet()){
			((DesignCell)value.getKey()).setRowSpan(value.getValue());
		}
		oldSpans.clear();
	}
}
