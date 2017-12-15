/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.ArrayList;
import java.util.Collections;
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
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.model.dialog.ApplyTableStyleAction;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;

import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnCellCommand extends Command {

	private StandardBaseColumn jrColumn;
	
	private Class<AMCollection> type;
	
	private String groupName;
	
	private Cell jrCell;
	
	private int height = 0;
	
	private ANode column;
	
	private HashMap<Cell, Integer> oldSpans = new HashMap<Cell, Integer>();

	@SuppressWarnings("unchecked")
	public CreateColumnCellCommand(AMCollection parent, MColumn srcNode) {
		super();
		type = (Class<AMCollection>) parent.getClass();
		if (parent instanceof MTableGroupHeader){
			groupName = ((MTableGroupHeader) parent).getJrDesignGroup().getName();
		}
		if (parent instanceof MTableGroupFooter){
			groupName = ((MTableGroupFooter) parent).getJrDesignGroup().getName();
		}
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.column = srcNode;
		height = srcNode.getBounds().height;
		//don't create cell with zero height to provide a feedback on the graphical editor
		if (height == 0) height = 30;
	}
	
	/**
	 * Search the style on the sibling of the current column and if found return it. If the sibling 
	 * is a group then the column is searched inside. If the style can't be found in the current sibling
	 * and the column is inside a group then search in the sibling of the group
	 * 
	 * @return the name of the style if found, otherwise null
	 */
	protected String getSiblingStyle(ANode parent, ANode column){
		int index = parent.getChildren().indexOf(column);
		int leftIndex = index - 1;
		int rightIndex = index + 1;
		int size = parent.getChildren().size();
		while((leftIndex >= 0 || rightIndex < size)){
			if (leftIndex >= 0){
				INode leftNode = parent.getChildren().get(leftIndex);
				String style = null;
				if (leftNode instanceof MColumnGroup || leftNode instanceof MColumnGroupCell){
					style = getStyleFromColumnGroup((MColumn)leftNode, true);
				} else if (leftNode instanceof MColumn){
					style = (String)((APropertyNode)leftNode).getPropertyValue(DesignCell.PROPERTY_STYLE);
				}
				if (style != null) return style;
				else {
					leftIndex --;
				}
			}
			if (rightIndex < size){
				INode rightNode = parent.getChildren().get(rightIndex);
				String style = null;
				if (rightNode instanceof MColumnGroup || rightNode instanceof MColumnGroupCell){
					style = getStyleFromColumnGroup((MColumnGroup)rightNode, false);
				} else if (rightNode instanceof MColumn){
					style = (String)((APropertyNode)rightNode).getPropertyValue(DesignCell.PROPERTY_STYLE);
				}
				if (style != null && !style.isEmpty()) return style;
				else {
					rightIndex ++;
				}
			}
		}
		//style not found, if the current column is inside a group search in the sibling of the group
		if (parent instanceof MColumnGroup || parent instanceof MColumnGroupCell){
			String style = getSiblingStyle(parent.getParent(), parent);
			if (style != null){
				return style;
			}
		}
		return null;
	}
	
	
	/**
	 * Search for a style inside a group recursively
	 * 
	 * @param group the group where to search
	 * @param startFromRight true if the search start from the list child of the group, 
	 * false if it start from the first one
	 * @return the name of a style or null if it can't be found
	 */
	protected String getStyleFromColumnGroup(MColumn group, boolean startFromRight){
		List<INode> children = new ArrayList<INode>(group.getChildren());
		if (startFromRight){
			Collections.reverse(children);
		}
		for(INode node : group.getChildren()){
			if (node instanceof MColumnGroup || node instanceof MColumnGroupCell){
				String style = getStyleFromColumnGroup((MColumn)node, startFromRight);
				if (style != null) return style;
			} else if (node instanceof MColumn){
				String style = (String)((APropertyNode)node).getPropertyValue(DesignCell.PROPERTY_STYLE);
				if (style != null && !style.isEmpty()) return style;
			}
		}
		return null;
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
			} else {
				return null;
			}
		} else {
			return getSiblingStyle(column.getParent(), column);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		MTable table = ((MColumn)column).getMTable();
		TableManager manager = table.getTableManager();
		if (jrCell == null) {
			String styleName = getStyleName(table.getPropertiesMap());
			jrCell = createCell(styleName);
		}
		HashMap<Cell, Integer> spans = null;
		if (type.isAssignableFrom(MTableHeader.class)){
			jrColumn.setTableHeader(jrCell);
			spans = manager.getTableHeaderSpans();
		} else if (type.isAssignableFrom(MTableFooter.class)){
			jrColumn.setTableFooter(jrCell);
			spans = manager.getTableFooterSpans();
		}
		else if (type.isAssignableFrom(MTableColumnHeader.class)){
			jrColumn.setColumnHeader(jrCell);
			spans = manager.getColumnHeaderSpans();
		} else if (type.isAssignableFrom(MTableColumnFooter.class)){
			jrColumn.setColumnFooter(jrCell);
			spans = manager.getColumnFooterSpans();
		}else if (type.isAssignableFrom(MTableGroupHeader.class)){
			jrColumn.setGroupHeader(groupName, jrCell);
			spans = manager.getGroupHeaderSpans(groupName);
		} else if (type.isAssignableFrom(MTableGroupFooter.class)){
			jrColumn.setGroupFooter(groupName, jrCell);
			spans = manager.getGroupFooterSpans(groupName);
		}
		
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
	 * Create the new cell
	 * 
	 * @param styleName the style for the new cell, can be null
	 * @return the cell to add to the column
	 */
	protected Cell createCell(String styleName) {
		DesignCell cell = new DesignCell();
		cell.setHeight(height);
		if (styleName != null){
			JasperDesign jd = column.getJasperDesign();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		//Restore the old spans
		for(Entry<Cell, Integer> value : oldSpans.entrySet()){
			((DesignCell)value.getKey()).setRowSpan(value.getValue());
		}
		oldSpans.clear();
		
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
	}
}
