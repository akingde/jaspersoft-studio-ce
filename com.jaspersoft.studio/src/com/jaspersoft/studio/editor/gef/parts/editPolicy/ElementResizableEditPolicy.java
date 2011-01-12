package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.IGraphicElement;
import com.jaspersoft.studio.model.MGraphicElement;



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
		
		/*
		
		
		s += rect;
		
		Point moveDelta = request.getLocation().getCopy();

		// The delta here is the mouse delta, but the viewport may have been
		// scrolled
		// so let's calculate the REAL delta...
		
		getFeedbackLayer().translateToRelative(moveDelta);
		// The request delta is in absolute coordinates. We need to translate the
		// mouse width in
		// model coordinates...
		PrecisionRectangle rect2 = new PrecisionRectangle(new Rectangle(0, 0, moveDelta.x, moveDelta.y));
		getHostFigure().translateToRelative(rect2);
		moveDelta.x = rect2.width;
		moveDelta.y = rect2.height;
		//s += "" + moveDelta;
		
		// Calculate the position and size of the new bounds...
		FigureEditPart fed = (FigureEditPart)getHost();
		
		int top = ((Integer) ((MGraphicElement)fed.getModel()).getPropertyValue( JRDesignElement.PROPERTY_Y)).intValue();
		
		top += moveDelta.y;
		
		//s = ""+top+"px " + moveDelta;
		*/
		
		PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds().getCopy());
		getHostFigure().translateToAbsolute(rect);
		rect.translate(request.getMoveDelta());
		rect.resize(request.getSizeDelta());
		
		
		// Calculate changes for the figure...
		String s = "";
		
		if (getHost() instanceof FigureEditPart  && ((FigureEditPart)getHost()).getModelNode().getValue() instanceof JRDesignElement) {
			JRDesignElement jrElement = (JRDesignElement) ((FigureEditPart)getHost()).getModelNode().getValue();
			Rectangle oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
			
			PrecisionRectangle rect2 = new PrecisionRectangle(new Rectangle(0, 0, request.getMoveDelta().x, request.getMoveDelta().y));
			getHostFigure().translateToRelative(rect2);
			
			oldBounds.translate(rect2.width, rect2.height);
			
			rect2 = new PrecisionRectangle(new Rectangle(0, 0, request.getSizeDelta().width, request.getSizeDelta().height));
			getHostFigure().translateToRelative(rect2);
			
			oldBounds.resize(rect2.width, rect2.height);
			
			s += oldBounds.x +", " + oldBounds.y + ", " + oldBounds.width + ", " + oldBounds.height;
			
		}
		
		feedback.translateToRelative(rect);
		
		((ElementFeedbackFigure)feedback).setText(s);
		
		
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
