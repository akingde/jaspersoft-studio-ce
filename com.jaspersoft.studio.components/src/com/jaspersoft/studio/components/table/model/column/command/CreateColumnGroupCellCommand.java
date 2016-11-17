/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableColumnFooter;
import com.jaspersoft.studio.components.table.model.MTableColumnHeader;
import com.jaspersoft.studio.components.table.model.MTableDetail;
import com.jaspersoft.studio.components.table.model.MTableFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.MTableHeader;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.GroupCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardGroupCell;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Create a cell for a column group
 * 
 * @author Orlandin Marco
 *
 */
public class CreateColumnGroupCellCommand extends JSSCompoundCommand {

	/**
	 * The column group where the cell is created
	 */
	private StandardBaseColumn jrColumn;
	
	/**
	 * The group name if the section is a group header or footer
	 */
	private String groupName;
	
	/**
	 * The created cell
	 */
	private Cell jrCell;
	
	/**
	 * The height for the new cell
	 */
	private int height = 0;
	
	/**
	 * The section where the column group is placed
	 */
	private ANode section;
	
	/**
	 * The section type where the column group is placed
	 */
	private Class<AMCollection> type;
	
	/**
	 * The table node
	 */
	private MTable tableNode;
	
	/**
	 * The parent of the column group
	 */
	private ANode groupColumnParent;

	/**
	 * Create the command
	 * 
	 * @param section a not null section where the column group is placed
	 * @param srcNode the column group where the cell will be created
	 */
	public CreateColumnGroupCellCommand(AMCollection section, MColumnGroup srcNode) {
		this(section, srcNode.getParent(), srcNode.getValue());
	}
	
	/**
	 * Create the command
	 * 
	 * @param section a not null section where the column group is placed
	 * @param groupColumnParent the parent of the group where the cell will be placed
	 * @param groupColumn the column group where the cell will be created
	 */
	@SuppressWarnings("unchecked")
	public CreateColumnGroupCellCommand(AMCollection section, ANode groupColumnParent, StandardColumnGroup groupColumn) {
		super(section);
		this.tableNode = (MTable)section.getParent();
		this.type = (Class<AMCollection>) section.getClass();
		this.jrColumn = groupColumn;
		this.section = section;
		this.groupColumnParent = groupColumnParent;
		if (section instanceof MTableGroupHeader){
			groupName = ((MTableGroupHeader) section).getJrDesignGroup().getName();
		}
		if (section instanceof MTableGroupFooter){
			groupName = ((MTableGroupFooter) section).getJrDesignGroup().getName();
		}
	}
	
	private int getGroupIndex(ANode groupNode){
		if (groupNode instanceof MTableGroupHeader){
			int startIndex = -1;
			for(INode node : groupNode.getParent().getChildren()){
				if (startIndex == -1 && node instanceof MTableGroupHeader){
					startIndex = 0;
				}
				if (node == groupNode) break;
				else if (startIndex > -1) startIndex++;
			}
			return startIndex;
		} else if (groupNode instanceof MTableGroupFooter){
			int startIndex = -1;
			for(INode node : groupNode.getParent().getChildren()){
				if (startIndex == -1 && node instanceof MTableGroupFooter){
					startIndex = 0;
				}
				if (node == groupNode) break;
				else if (startIndex > -1) startIndex++;
			}
			return startIndex;
		}
		return -1;
	}
	
	/**
	 * Get all the cell into a specific into a specific column and its subcolumn
	 * if it is a group. In this case the cells of the group itself are not returned
	 * Also the cell are taken only from the section where the new cell will be created
	 * 
	 * @param column a not null column
	 * @return a not null list of cell
	 */
	private List<Cell> getAllCells(BaseColumn column){
		List<Cell> result = new ArrayList<Cell>();
		if (column instanceof StandardColumnGroup){
			StandardColumnGroup group = (StandardColumnGroup)column;
			for(BaseColumn subCol : group.getColumns()){
				result.addAll(getAllCells(subCol));
			}
		} else {
			Cell currentCell = null;
			if (type.isAssignableFrom(MTableHeader.class))
				currentCell = column.getTableHeader();
			else if (type.isAssignableFrom(MTableFooter.class))
				currentCell = column.getTableFooter();
			else if (type.isAssignableFrom(MTableColumnHeader.class))
				currentCell = column.getColumnHeader();
			else if (type.isAssignableFrom(MTableColumnFooter.class))
				currentCell = column.getColumnFooter();
			else if (type.isAssignableFrom(MTableGroupHeader.class)){
				int groupIndex = getGroupIndex(section);
				List<GroupCell> groupHeaders = column.getGroupHeaders();
				if (groupIndex != -1 && groupHeaders.size()>groupIndex){
					for(GroupCell groupHeader : groupHeaders){
						if (groupHeader.getCell() != null){
							result.add(groupHeader.getCell());
						}
					}
				}
			} else if (type.isAssignableFrom(MTableGroupFooter.class)){
				int groupIndex = getGroupIndex(section);
				List<GroupCell> groupFooters = column.getGroupFooters();
				if (groupIndex != -1 && groupFooters.size()>groupIndex){
					for(GroupCell groupFooter : groupFooters){
						if (groupFooter.getCell() != null){
							result.add(groupFooter.getCell());
						}
					}
				}
			}
			if (currentCell != null) result.add(currentCell);
		}
		return result;
	}
	
	/**
	 * Generate recursively a series of commands to add a delta to the height of 
	 * every MCell found, except all the cell descendant of an excluded ancestor
	 * 
	 * @param children the current children
	 * @param exclusion the excluded ancestor
	 * @param newHeightDelta the delta to apply to the height (can be positive or negative)
	 */
	private void setCellToIncrease(List<INode> children, StandardBaseColumn exclusion, int newHeightDelta){
		for(INode child : children){
			if (child.getValue() != exclusion){
				if (child.getClass().equals(MCell.class)){
					add(new AddCellDeltaHeightCommand(((MCell)child).getCell(), newHeightDelta));
				}
				setCellToIncrease(child.getChildren(), exclusion, newHeightDelta);
			}
		}
	}
	
	/**
	 * Generate recursively a series of commands to add a delta to the height of 
	 * every MCell found
	 * 
	 * @param children the current children
	 * @param newHeightDelta the delta to apply to the height (can be positive or negative)
	 */
	private void setCellHeightDelta(List<Cell> children, int newHeightDelta){
		for(Cell child : children){
			add(new AddCellDeltaHeightCommand(child, newHeightDelta));
		}
	}
	
	/**
	 * If the commands has size zero it still can execute because this means that
	 * there are no resize of other cell needed, table with one column for example
	 */
	public boolean canExecute() {
		return getCommands().size() == 0 || super.canExecute();
	};
	
	/**
	 * Recursively search the minimum valid value to resize all the cell, to avoid to
	 * set a minimum height
	 * 
	 * @param children the analyzed children
	 * @param actualMin the comparison min
	 * @return the safe min value
	 */
	private int getMinCellHeight(List<Cell> children, int actualMin){
		for(Cell child : children){
			int cellHehight = child.getHeight();
			if (actualMin > cellHehight) actualMin = cellHehight;
		}
		return actualMin;
	}
	
	/**
	 * Search if there are other group cells on the same level and at the first found the height is 
	 * returned. If it didn't find any group cell it return -1
	 * 
	 * @param parent the parent, the search of the group cell will be inside its children
	 * @return the height of the first group cell of -1 if no one can be found inside the children 
	 * of the parent
	 */
	private int searchSuggestedHeight(ANode parent){
		for(INode child : parent.getChildren()){
			if (child instanceof MColumnGroupCell) {
				MColumnGroupCell groupCell = (MColumnGroupCell)child;
				if (groupCell.getCell() != null)
					return groupCell.getCell().getHeight();
			}
		}
		return -1;
	}

	/**
	 * Calculate the height of the new group cell
	 */
	protected void computeHeight(){
		height = searchSuggestedHeight(groupColumnParent);
		if (height == -1){
			//It's the first added group cell in the row,  need to increase space for 
			//all the cells outside this group
			height = MColumnGroup.DEFAULT_CELL_HEIGHT;
			setCellToIncrease(section.getChildren(), jrColumn, height);
		} else {
			//Need to decrease the cell height inside this group if they are bigger enough
			//otherwise we take the value most closer to the height
			List<Cell> columnCells = getAllCells(jrColumn);
			height = getMinCellHeight(columnCells, height);
			setCellHeightDelta(columnCells, -height);
		}
	}
	
	/**
	 * Create the group cell and update the table span
	 */
	protected void createGroupCell(){
		if (jrCell == null) {
			jrCell = createCell();
		}
		if (type.isAssignableFrom(MTableHeader.class))
			jrColumn.setTableHeader(jrCell);
		else if (type.isAssignableFrom(MTableFooter.class))
			jrColumn.setTableFooter(jrCell);
		else if (type.isAssignableFrom(MTableColumnHeader.class))
			jrColumn.setColumnHeader(jrCell);
		else if (type.isAssignableFrom(MTableColumnFooter.class))
			jrColumn.setColumnFooter(jrCell);

		else if (type.isAssignableFrom(MTableGroupHeader.class)){
			int groupIndex = getGroupIndex(section);
			List<GroupCell> groupHeaders = jrColumn.getGroupHeaders();
			if (groupIndex != -1 && groupHeaders.size()> groupIndex){
				StandardGroupCell groupCell = new StandardGroupCell(groupName, jrCell);
				groupHeaders.add(groupIndex, groupCell);
				jrColumn.getEventSupport().fireCollectionElementAddedEvent(StandardBaseColumn.PROPERTY_GROUP_HEADERS, groupCell, groupIndex);
			} else {
				jrColumn.setGroupHeader(groupName, jrCell);
			}

		}
		else if (type.isAssignableFrom(MTableGroupFooter.class)){
			int groupIndex = getGroupIndex(section);
			List<GroupCell> groupFooters = jrColumn.getGroupFooters();
			if (groupIndex != -1 && groupFooters.size()>groupIndex){
				StandardGroupCell groupCell = new StandardGroupCell(groupName, jrCell);
				groupFooters.add(groupIndex, groupCell);
				jrColumn.getEventSupport().fireCollectionElementAddedEvent(StandardBaseColumn.PROPERTY_GROUP_FOOTERS, groupCell, groupIndex);
			} else {
				jrColumn.setGroupFooter(groupName, jrCell);
			}
		}
		
		super.execute();
		tableNode.getTableManager().updateTableSpans();
	}
	
	/**
	 * Execute the command, first calculate the new cell height and then
	 * create it
	 */
	@Override
	public void execute() {
		computeHeight();
		createGroupCell();
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	/**
	 * The redo differently from the execute doens't recalculate the height
	 * but recreate only the cell
	 */
	@Override
	public void redo() {
		createGroupCell();
	}

	protected Cell createCell() {
		DesignCell cell = new DesignCell();
		cell.setHeight(height);
		String styleName = getStyleName(tableNode.getPropertiesMap());
		if (styleName != null){
			JasperDesign jd = tableNode.getJasperDesign();
			JRStyle internalStyle = jd.getStylesMap().get(styleName);
			if (internalStyle != null){
				cell.setStyle(internalStyle);
			} else {
				cell.setStyleNameReference(styleName);
			}
		}
		return cell;
	}

	@Override
	public void undo() {
		if (type.isAssignableFrom(MTableHeader.class))
			jrColumn.setTableHeader(null);
		else if (type.isAssignableFrom(MTableFooter.class))
			jrColumn.setTableFooter(null);
		else if (type.isAssignableFrom(MTableColumnHeader.class))
			jrColumn.setColumnHeader(null);
		else if (type.isAssignableFrom(MTableColumnFooter.class))
			jrColumn.setColumnFooter(null);

		else if (type.isAssignableFrom(MTableGroupHeader.class))
			jrColumn.setGroupHeader(groupName, null);
		else if (type.isAssignableFrom(MTableGroupFooter.class))
			jrColumn.setGroupFooter(groupName, null);
		super.undo();
		tableNode.getTableManager().updateTableSpans();
	}
	
	/**
	 * Return the style name of the style that will be used in the new cell
	 * 
	 * @param tableMap the map from where the default styles properties are read
	 * @return the name of the style or null if a style can not be resolved
	 */
	protected String getStyleName(JRPropertiesMap tableMap){
		if (type.isAssignableFrom(MTableHeader.class) || type.isAssignableFrom(MTableFooter.class)){
			return tableMap.getProperty(ApplyTableStyleAction.TABLE_HEADER_PROPERTY);
		} else if (type.isAssignableFrom(MTableColumnHeader.class) || type.isAssignableFrom(MTableColumnFooter.class)
					|| (type.isAssignableFrom(MTableGroupHeader.class) || (type.isAssignableFrom(MTableGroupFooter.class)))){
			return tableMap.getProperty(ApplyTableStyleAction.COLUMN_HEADER_PROPERTY);
		} else if (type.isAssignableFrom(MTableDetail.class)){
			return tableMap.getProperty(ApplyTableStyleAction.DETAIL_PROPERTY);
		} else {
			return null;
		}
	}

}
