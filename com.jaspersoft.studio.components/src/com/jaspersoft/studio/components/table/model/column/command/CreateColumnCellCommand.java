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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.model.MTableColumnFooter;
import com.jaspersoft.studio.components.table.model.MTableColumnHeader;
import com.jaspersoft.studio.components.table.model.MTableFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.MTableHeader;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnCellCommand extends Command {

	private StandardBaseColumn jrColumn;
	private Class<ANode> type;
	private String groupName;
	private Cell jrCell;
	private int height = 0;
	private ANode column;

	@SuppressWarnings("unchecked")
	public CreateColumnCellCommand(ANode parent, MColumn srcNode) {
		super();
		type = (Class<ANode>) parent.getClass();
		if (parent instanceof MTableGroupHeader)
			groupName = ((MTableGroupHeader) parent).getJrDesignGroup()
					.getName();
		if (parent instanceof MTableGroupFooter)
			groupName = ((MTableGroupFooter) parent).getJrDesignGroup()
					.getName();
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
				if (style != null) return style;
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
			if (node instanceof MColumnGroup || node instanceof MColumnGroup){
				String style = getStyleFromColumnGroup((MColumn)node, startFromRight);
				if (style != null) return style;
			} else if (node instanceof MColumn){
				String style = (String)((APropertyNode)node).getPropertyValue(DesignCell.PROPERTY_STYLE);
				if (style != null) return style;
			}
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
		if (jrCell == null) {
			jrCell = createCell();
		}
		if (type.isAssignableFrom(MTableHeader.class)){
			jrColumn.setTableHeader(jrCell);
		}
		else if (type.isAssignableFrom(MTableFooter.class))
			jrColumn.setTableFooter(jrCell);
		else if (type.isAssignableFrom(MTableColumnHeader.class))
			jrColumn.setColumnHeader(jrCell);
		else if (type.isAssignableFrom(MTableColumnFooter.class))
			jrColumn.setColumnFooter(jrCell);

		else if (type.isAssignableFrom(MTableGroupHeader.class))
			jrColumn.setGroupHeader(groupName, jrCell);
		else if (type.isAssignableFrom(MTableGroupFooter.class))
			jrColumn.setGroupFooter(groupName, jrCell);
	}

	protected Cell createCell() {
		DesignCell cell = new DesignCell();
		cell.setHeight(height);
		String siblingStyle = getSiblingStyle(column.getParent(), column);
		if (siblingStyle != null){
			JasperDesign jd = column.getJasperDesign();
			JRStyle internalStyle = jd.getStylesMap().get(siblingStyle);
			if (internalStyle != null){
				cell.setStyle(internalStyle);
			} else {
				cell.setStyleNameReference(siblingStyle);
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
