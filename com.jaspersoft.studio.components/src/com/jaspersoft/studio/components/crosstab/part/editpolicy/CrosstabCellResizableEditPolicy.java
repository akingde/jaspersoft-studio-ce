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
package com.jaspersoft.studio.components.crosstab.part.editpolicy;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.figures.borders.Line1Border;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementFeedbackFigure;
/*
 * The Class BandResizableEditPolicy.
 */
public class CrosstabCellResizableEditPolicy extends ResizableEditPolicy {

	/**
	 * Instantiates a new band resizable edit policy.
	 */
	public CrosstabCellResizableEditPolicy() {
		super();
		setDragAllowed(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.ResizableEditPolicy#createSelectionHandles()
	 */
	@Override
	protected List<AbstractHandle> createSelectionHandles() {
		List<AbstractHandle> list = new ArrayList<AbstractHandle>();

		MoveHandle hand = new MoveHandle((GraphicalEditPart) getHost());
		hand.setBorder(new Line1Border(ColorConstants.darkBlue, 4));

		list.add(hand);
		
//		List<AbstractHandle> list = new ArrayList<AbstractHandle>();
//		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.SOUTH));
//		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.NORTH));
//		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.EAST));
//		list.add(new CellResizeHandle2((GraphicalEditPart) getHost(), PositionConstants.WEST));
//
		return list;
	}

	/*
	 * Shows or updates feedback for a change bounds request.
	 * 
	 * @param request the request
	 */
	@Override
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		IFigure feedback = getDragSourceFeedbackFigure();

		PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
		getHostFigure().translateToAbsolute(rect);
		rect.translate(request.getMoveDelta());
		rect.resize(request.getSizeDelta());

		// Calculate changes for the figure...
		String s = ""; //$NON-NLS-1$
		int scale = 0;
		if (getHost() instanceof FigureEditPart
				&& ((FigureEditPart) getHost()).getModelNode().getValue() instanceof JRDesignCellContents) {
			JRDesignCellContents jrElement = (JRDesignCellContents) ((FigureEditPart) getHost()).getModelNode().getValue();
			Rectangle oldBounds = new Rectangle(0, 0, jrElement.getWidth(), jrElement.getHeight());

			PrecisionRectangle rect2 = new PrecisionRectangle(new Rectangle(request.getMoveDelta().x,
					request.getMoveDelta().y, request.getSizeDelta().width, request.getSizeDelta().height));
			getHostFigure().translateToRelative(rect2);

			// oldBounds.translate(rect2.x, rect2.y);
			oldBounds.resize(rect2.width, rect2.height);

			s += oldBounds.width + ", " + oldBounds.height; //$NON-NLS-1$
			if (oldBounds.width != 0)
				scale = rect.width / oldBounds.width - 1;
			else if (oldBounds.height != 0)
				scale = rect.height / oldBounds.height - 1;
		}

		feedback.translateToRelative(rect);

		((ElementFeedbackFigure) feedback).setText(s);

		feedback.setBounds(rect.resize(-scale, -scale));
	}

	/**
	 * Creates the figure used for feedback.
	 * 
	 * @return the new feedback figure
	 */
	@Override
	protected IFigure createDragSourceFeedbackFigure() {
		// Use a ghost rectangle for feedback
		RectangleFigure r = new ElementFeedbackFigure();

		// FigureUtilities.makeGhostShape(r);
		r.setLineStyle(Graphics.LINE_DOT);
		r.setForegroundColor(ColorConstants.black);
		r.setBounds(getInitialFeedbackBounds().resize(-1, -1));// new Rectangle(ifb.x, ifb.y, ifb.width -100, ifb.height));
		addFeedback(r);
		return r;
	}

}
