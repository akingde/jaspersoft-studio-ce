/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.part.editpolicy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;

import com.jaspersoft.studio.editor.gef.parts.handles.CellResizeHandle2;

/**
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
	 * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
	 */
	@Override
	protected List createSelectionHandles() {
		List<AbstractHandle> list = new ArrayList<AbstractHandle>();
		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.SOUTH));
		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.NORTH));
		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.EAST));
		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.WEST));

		return list;
	}

}
