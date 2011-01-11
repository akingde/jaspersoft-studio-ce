package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;



public class ElementResizableEditPolicy extends ResizableEditPolicy {

	IFigure feedbackFigure = null;
	/**
	 * Instantiates a new band resizable edit policy.
	 */
	public ElementResizableEditPolicy() {
		super();
	}
	
	/**
	 * Shows or updates feedback for a change bounds request.
	 * @param request the request
	 */
	protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
		IFigure feedback = getDragSourceFeedbackFigure();
		
		PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
		getHostFigure().translateToAbsolute(rect);
		rect.translate(request.getMoveDelta());
		
		Point moveDelta = request.getLocation().getCopy();

		// The delta here is the mouse delta, but the viewport may have been
		// scrolled
		// so let's calculate the REAL delta...
		String s = "" + moveDelta + " ";
		getFeedbackLayer().translateToRelative(moveDelta);
		// The request delta is in absolute coordinates. We need to translate the
		// mouse width in
		// model coordinates...
		PrecisionRectangle rect2 = new PrecisionRectangle(new Rectangle(0, 0, moveDelta.x, moveDelta.y));
		getHostFigure().translateToRelative(rect2);
		moveDelta.x = rect2.width;
		moveDelta.y = rect2.height;
		s += "" + moveDelta;
		
		((ElementFeedbackFigure)feedback).setText(s);
		
		rect.resize(request.getSizeDelta());
		
		feedback.translateToRelative(rect);
		feedback.setBounds(rect);
	}
	
	
	/**
	 * Creates the figure used for feedback.
	 * @return the new feedback figure
	 */
	protected IFigure createDragSourceFeedbackFigure() {
		// Use a ghost rectangle for feedback
		RectangleFigure r = new ElementFeedbackFigure();

		//FigureUtilities.makeGhostShape(r);
		r.setLineStyle(Graphics.LINE_DOT);
		r.setForegroundColor(ColorConstants.black);
		r.setBounds(getInitialFeedbackBounds());
		addFeedback(r);
		return r;
	}



	
}
