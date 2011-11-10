/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.table.part.editpolicy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.MoveHandle;

import com.jaspersoft.studio.editor.gef.figures.borders.Line1Border;

/*
 * The Class BandResizableEditPolicy.
 */
public class TableCellResizableEditPolicy extends ResizableEditPolicy {

	/**
	 * Instantiates a new band resizable edit policy.
	 */
	public TableCellResizableEditPolicy() {
		super();
		setDragAllowed(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
	 */
	@Override
	protected List<AbstractHandle> createSelectionHandles() {
		List<AbstractHandle> list = new ArrayList<AbstractHandle>();

		MoveHandle hand = new MoveHandle((GraphicalEditPart) getHost());
		hand.setBorder(new Line1Border(ColorConstants.darkBlue, 4));

		list.add(hand);

		// List<AbstractHandle> list = new ArrayList<AbstractHandle>();
		// list.add(new CellResizeHandle2((GraphicalEditPart) getHost(),
		// PositionConstants.SOUTH));
		// list.add(new CellResizeHandle2((GraphicalEditPart) getHost(),
		// PositionConstants.NORTH));
		// list.add(new CellResizeHandle2((GraphicalEditPart) getHost(),
		// PositionConstants.EAST));
		// list.add(new CellResizeHandle2((GraphicalEditPart) getHost(),
		// PositionConstants.WEST));

		return list;
	}

}
