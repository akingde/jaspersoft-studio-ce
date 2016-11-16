/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.dnd;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.tools.DragEditPartsTracker;
import org.eclipse.swt.graphics.Cursor;

import com.jaspersoft.studio.book.editparts.BookPagesEditPart;
import com.jaspersoft.studio.book.editparts.BookSectionEditPart;

/**
 * Drag tracker for the editparts inside the editor, allow to calculate the drop location
 * during the drag and drop operations
 * 
 * @author Orlandin Marco
 *
 */
public class PageEditPartTracker extends DragEditPartsTracker {

	/**
	 * The current drop location, shared between all the elements
	 */
	private static DropLocation dropLocation = new DropLocation(null, null);
	
	public PageEditPartTracker(EditPart sourceEditPart) {
		super(sourceEditPart);
	}
	
	private static Point getLocationFromRequest(Request request) {
		return ((DropRequest)request).getLocation();
	}

	@Override
	protected void setTargetEditPart(EditPart editpart) {
		if (getContainer() != null) getContainer().eraseTargetFeedback();
		if (getSourceEditPart() == null || getSourceEditPart() instanceof BookSectionEditPart){
			dropLocation.reset();
		} else {
			Point pt = getLocationFromRequest(getTargetRequest());
			dropLocation = getDropLocation(editpart, pt);
		}
		//DO THE VALIDATION
		if (getContainer() != null && getOperationSet().contains(getAfterPart())){
			dropLocation.reset();
		}
		if (getContainer() != null && getAfterPart() != null){
			EditPart nextPart = getContainer().getFollowingPart(getAfterPart());
			if (nextPart != null && getOperationSet().contains(nextPart)){
				dropLocation.reset();
			}
		}
		super.setTargetEditPart(getContainer());
	}
	
	public EditPart getAfterPart() {
		return dropLocation.getAfterPart();
	}

	public BookSectionEditPart getContainer() {
		return dropLocation.getContainer();
	}
	
	@Override
	protected void showTargetFeedback() {
		if (getContainer() != null) {
			getContainer().showTargetFeedback(getAfterPart());
		}
	}
	
	@Override
	protected Cursor calculateCursor() {
		if (getState() == STATE_INITIAL) return super.calculateCursor();
		else {
			if (getContainer() == null) return SharedCursors.NO;
			else return SharedCursors.CURSOR_TREE_ADD;
		}
	}
	
	@Override
	public void eraseTargetFeedback() {
		if (getContainer() != null) {
			getContainer().eraseTargetFeedback();
		}
	}
	
	/**
	 * Calculate after which element the dragged element was moved inside the container
	 * edit part
	 * 
	 * @param targetPart the target container
	 * @param location the point where the element is begin dragged at the moment
	 */
	public static DropLocation getDropLocation(EditPart targetPart, Point location){
		EditPart afterPart = null;
		EditPart container = null;
		if (targetPart instanceof BookSectionEditPart){
			container = (BookSectionEditPart)targetPart;
			List<?> children = container.getChildren();
			if (container.getChildren().size() == 0) afterPart = null;
			else  {
				Transposer transposer = new Transposer();
				Point pt = transposer.t(location);
				int multiplier = 1;
				while (multiplier < 10) {
					Point ptLeft = new PrecisionPoint(pt.x - (5 * multiplier), pt.y);
					Point ptRight = new PrecisionPoint(pt.x + (5 * multiplier), pt.y);

					EditPart itemLeft = container.getViewer().findObjectAt(ptLeft);

					if (itemLeft != null && itemLeft instanceof BookPagesEditPart) {
						afterPart = itemLeft;
						break;
					}

					EditPart itemRight = container.getViewer().findObjectAt(ptRight);
					
					if (itemRight != null && itemRight instanceof BookPagesEditPart) {
						int rightItemIndex = children.indexOf(itemRight);
						if (rightItemIndex == 0) afterPart = null;
						else afterPart = (EditPart)children.get(rightItemIndex-1);
						break;
					}
					multiplier++;
				}
				if (afterPart == null) afterPart = (EditPart)container.getChildren().get(container.getChildren().size()-1);
			}
		} else if (targetPart instanceof BookPagesEditPart){
			container = (BookSectionEditPart)targetPart.getParent();
			Rectangle elementBounds = ((BookPagesEditPart) targetPart).getFigure().getBounds();
			int halfWidth = elementBounds.width / 2;
			Rectangle splitLeft = new Rectangle(elementBounds.x, elementBounds.y, halfWidth, elementBounds.height);

			List<?> children = container.getChildren();
			if (children.isEmpty()) afterPart = null;
			if (splitLeft.contains(location)) {
				int currentIndex = children.indexOf(targetPart);
				if (currentIndex == 0) afterPart = null;
				else afterPart = (EditPart)children.get(currentIndex-1);
			} else {
				afterPart = targetPart;
			}
		}
		return new DropLocation((BookSectionEditPart)container, (BookPagesEditPart)afterPart);
	}
	
	public void updateDropLocation(DropLocation location){
		eraseTargetFeedback();
		dropLocation = location;
		showTargetFeedback();
		showSourceFeedback();
	}
}
