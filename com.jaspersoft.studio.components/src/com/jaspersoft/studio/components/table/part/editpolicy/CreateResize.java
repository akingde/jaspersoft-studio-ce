/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.part.editpolicy;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.column.command.SetColumnWidthCommand;
import com.jaspersoft.studio.components.table.part.TableCellEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.SetValueCommand;

public class CreateResize {
	
	public static Command createResizeCommand(ChangeBoundsRequest request,TableCellEditPart editPart) {
		MColumn model = editPart.getModel();
		MColumn oldmodel = model;
		Dimension sd = request.getSizeDelta();
		ANode mparent = model.getParent();
		if (request.getResizeDirection() == PositionConstants.WEST) {
			int index = mparent.getChildren().indexOf(model);
			if (index > 0){
				model = (MColumn) mparent.getChildren().get(index - 1);
			} else {
				return null;
			}
		}
		if (request.getResizeDirection() == PositionConstants.NORTH) {
			model = model.getNorth();
			if (model == null){
				return null;
			}
		}
		StandardBaseColumn jrColumn = model.getValue();
		PrecisionRectangle deltaRect = new PrecisionRectangle(new Rectangle(0,0, sd.width, sd.height));
		editPart.getFigure().translateToRelative(deltaRect);
		MTable table = getTableModel(model);
		JSSCompoundTableCommand c = new JSSCompoundTableCommand(table);
		c.setLayoutTableContent(true);
		if (request.getSizeDelta().width != 0) {
			int w = deltaRect.width;
			if (request.getResizeDirection() == PositionConstants.WEST)
				w = -w;
			int width = jrColumn.getWidth() + w;
			if (width < 0){
				return null;
			}
			SetColumnWidthCommand setWidthCmd = new SetColumnWidthCommand(model, width);
			c.add(setWidthCmd);

			if (request.getResizeDirection() == PositionConstants.WEST) {
				jrColumn = oldmodel.getValue();
				w = deltaRect.width;
				width = jrColumn.getWidth() + w;
				if (width < 0){
					return null;
				}
				setWidthCmd = new SetColumnWidthCommand(oldmodel, width);
				c.add(setWidthCmd);
			} else if (request.getResizeDirection() == PositionConstants.EAST){
				//If the request is a drag to east and the flag is enabled take the space from the next column
				
				if (table != null){
					if (table.hasColumnsAutoresizeProportional()){
						MColumn next = model.getNextColumn();
						if (next != null){
							StandardBaseColumn nextCol = next.getValue();
							int newWidth = nextCol.getWidth() - deltaRect.width;
							if (newWidth < 0) newWidth = 0;
							setWidthCmd = new SetColumnWidthCommand(next, newWidth);
							c.add(setWidthCmd);
						}
					} else if (table.hasColumnsAutoresizeNext()){
						MColumn next = model.getNextColumn();
						if (next != null){
							StandardBaseColumn nextCol = next.getValue();
							int newWidth = nextCol.getWidth() - deltaRect.width;
							if (newWidth < 0){
								//newWidth = 0;
								return null;
							}
							setWidthCmd = new SetColumnWidthCommand(next, newWidth);
							c.add(setWidthCmd);
						} 
					}
				} 
			}
		}
		if (request.getSizeDelta().height != 0 && model instanceof MColumn) {
			MColumn mc = (MColumn) model;
			int h = deltaRect.height;
			if (request.getResizeDirection() == PositionConstants.NORTH)
				h = -h;
			int height = (Integer) mc
					.getPropertyValue(DesignCell.PROPERTY_HEIGHT) + h;
			if (height < 0)
				return null;

			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setTarget(model);
			setCommand.setPropertyId(DesignCell.PROPERTY_HEIGHT);
			setCommand.setPropertyValue(height);
			c.add(setCommand);

			if (request.getResizeDirection() == PositionConstants.NORTH
					&& oldmodel instanceof MColumn) {
				mc = (MColumn) oldmodel;
				h = deltaRect.height;
				height = (Integer) mc
						.getPropertyValue(DesignCell.PROPERTY_HEIGHT) + h;
				if (height < 0)
					height = 0;

				setCommand = new SetValueCommand();
				setCommand.setTarget(oldmodel);
				setCommand.setPropertyId(DesignCell.PROPERTY_HEIGHT);
				setCommand.setPropertyValue(height);
				c.add(setCommand);
			}
		}
		if (c.isEmpty())
			return null;
		return c;
	}
	
	/*private static boolean canColumnIncrease(MColumn col, MTable table, int newWidth){
		int columnWidthBefore = 0;
		for(INode currentCol : col.getParent().getChildren()){
			if (currentCol == col){
				break;
			} else {
				StandardBaseColumn currentJRCol = (StandardBaseColumn)currentCol.getValue();
				columnWidthBefore += currentJRCol.getWidth();
			}
		}
		if (columnWidthBefore + newWidth > table.getValue().getWidth()){
			return false;
		} else {
			return true;
		}
	} */
	
	/**
	 * Search starting from a node and going up in the hierarchy an MTable
	 * 
	 * @param currentNode a node, should be a node inside a table
	 * @return an MTable if it is in the upper hierarchy of the current node or null
	 */
	private static MTable getTableModel(ANode node){
		if (node == null) return null;
		if (node instanceof MTable){
			return (MTable)node;
		} else{
			return getTableModel(node.getParent());
		}
	}
}
