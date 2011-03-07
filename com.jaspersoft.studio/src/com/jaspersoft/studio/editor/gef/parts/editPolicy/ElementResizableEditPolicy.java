/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;

public class ElementResizableEditPolicy extends ResizableEditPolicy {

	/**
	 * Instantiates a new band resizable edit policy.
	 */
	public ElementResizableEditPolicy() {
		super();
	}

	/**
	 * Shows or updates feedback for a change bounds request.
	 * 
	 * @param request
	 *          the request
	 */
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		IFigure feedback = getDragSourceFeedbackFigure();

		PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
		getHostFigure().translateToAbsolute(rect);
		rect.translate(request.getMoveDelta());
		rect.resize(request.getSizeDelta());

		// Calculate changes for the figure...
		String s = "";
		int scaleH = 0;
		int scaleW = 0;
		if (getHost() instanceof FigureEditPart
				&& ((FigureEditPart) getHost()).getModelNode().getValue() instanceof JRDesignElement) {
			JRDesignElement jrElement = (JRDesignElement) ((FigureEditPart) getHost()).getModelNode().getValue();
			Rectangle oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(),
					jrElement.getHeight());

			PrecisionRectangle rect2 = new PrecisionRectangle(new Rectangle(request.getMoveDelta().x,
					request.getMoveDelta().y, request.getSizeDelta().width, request.getSizeDelta().height));
			getHostFigure().translateToRelative(rect2);

			oldBounds.translate(rect2.x, rect2.y);
			oldBounds.resize(rect2.width, rect2.height);

			s += oldBounds.x + ", " + oldBounds.y + ", " + oldBounds.width + ", " + oldBounds.height;
			if (oldBounds.width != 0)
				scaleW = rect.width / oldBounds.width - 1;
			if (oldBounds.height != 0)
				scaleH = rect.height / oldBounds.height - 1;
		}

		feedback.translateToRelative(rect);

		((ElementFeedbackFigure) feedback).setText(s);

		feedback.setBounds(rect.resize(-scaleW, -scaleH));
	}

	/**
	 * Creates the figure used for feedback.
	 * 
	 * @return the new feedback figure
	 */
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
