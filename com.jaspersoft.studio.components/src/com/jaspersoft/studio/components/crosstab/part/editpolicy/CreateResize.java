/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.crosstab.part.editpolicy;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.SetValueCommand;

public class CreateResize {
	public static Command createResizeCommand(ChangeBoundsRequest request,
			GraphicalEditPart editPart) {
		if (!(editPart.getModel() instanceof MCell))
			return null;
		MCell model = (MCell) editPart.getModel();
		Dimension sizeDelta = request.getSizeDelta();
		ANode mparent = model.getParent();
		// if (request.getResizeDirection() == PositionConstants.WEST) {
		// int index = mparent.getChildren().indexOf(model);
		// // if (index > 0)
		// // model = (MColumn) mparent.getChildren().get(index - 1);
		// // else
		// return null;
		// }
		// if (request.getResizeDirection() == PositionConstants.NORTH) {
		// if(mparent instanceof MRowGroup){
		//
		// }
		// int index = mtable.getChildren().indexOf(mparent);
		// if (index > 0) {
		// // AMCollection newmc = (AMCollection) mtable.getChildren().get(
		// // index - 1);
		// // model = (MColumn) newmc.getChildren().get(
		// // mparent.getChildren().indexOf(model));
		// } else
		// return null;
		// }

		CompoundCommand c = new CompoundCommand("Change Cell Size"); //$NON-NLS-1$

		if (request.getResizeDirection() == PositionConstants.SOUTH
				|| request.getResizeDirection() == PositionConstants.SOUTH_EAST
				|| request.getResizeDirection() == PositionConstants.EAST) {

			PrecisionRectangle deltaRect = new PrecisionRectangle(
					new Rectangle(0, 0, sizeDelta.width, sizeDelta.height));
			editPart.getFigure().translateToRelative(deltaRect);

			JRDesignCellContents jrdesign = (JRDesignCellContents) model
					.getValue();
			int height = jrdesign.getHeight() + deltaRect.height;
			if (height < 0)
				height = 0;

			int width = jrdesign.getWidth() + deltaRect.width;
			if (width < 0)
				width = 0;

			if (sizeDelta.width != 0) {
				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(JRDesignCrosstabCell.PROPERTY_WIDTH);
				setCommand.setPropertyValue(width);
				c.add(setCommand);
			}
			if (sizeDelta.height != 0) {
				SetValueCommand setCommand = new SetValueCommand();
				setCommand.setTarget(model);
				setCommand.setPropertyId(JRDesignCrosstabCell.PROPERTY_HEIGHT);
				setCommand.setPropertyValue(height);
				c.add(setCommand);
			}
			return c;
		}
		if (c.isEmpty())
			return null;
		return c;
	}
}
