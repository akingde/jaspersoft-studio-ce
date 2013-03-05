/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.tools.DragEditPartsTracker;

import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;

/**
 * This drag tracker redefine the behavior when an element is moved or resized. Usually an element can not be moved over
 * other edit part, and can not be resized to overlap them. This dragtracker change this behavior taking as the target
 * edit part an icontainer, that could be the actual target or the container of the actual target
 * 
 * @author Orlandin Marco
 * 
 */
public class SearchParentDragTracker extends DragEditPartsTracker {

	/** defines the range where autoscroll is active inside a viewer */
	private static final int DEFAULT_EXPOSE_THRESHOLD = 50;

	/**
	 * An implementation of {@link org.eclipse.gef.AutoexposeHelper} that performs autoscrolling of a
	 * <code>Viewport</code> figure. This helper is for use with graphical editparts that contain a viewport figure.
	 * Autoscroll will occur when the detect location is near the viewport edges. It will continue for as long as the
	 * location continues to meet these criteria.
	 * 
	 * @author Orlandin Marco
	 * 
	 */
	private class DragAutoExpose implements AutoexposeHelper {

		/**
		 * Viewport of the scrolled area
		 */
		private Viewport port;

		/** the last time an auto expose was performed */
		private long lastStepTime = 0;

		/** The insets for this helper. */
		private int threshold;

		/**
		 * Create the class
		 * 
		 * @param port
		 *          the viewport figure
		 * @param threshold
		 *          if the location is inside the area between the viewport edges and the threshold then view will be
		 *          autoscrolled. Other than this the threshold is used to calculate how much the screen is scrolled, infact
		 *          it is scrolled twice the threshold in the correct direction
		 */
		public DragAutoExpose(Viewport port, int threshold) {
			this.port = port;
			this.threshold = threshold;
		}

		/**
		 * Build the class with a default threshold of 50
		 * 
		 * @param port
		 */
		public DragAutoExpose(Viewport port) {
			this(port, DEFAULT_EXPOSE_THRESHOLD);
		}

		/**
		 * Returns <code>true</code> if the given point is inside the viewport, but near its edge, so if the autoscroll
		 * action is needed
		 * 
		 * @param where
		 *          the mouse's current location in the viewer
		 * @return true if the autoexpose action should be done because the location is too near to the viewports edge,
		 *         otherwise true
		 */
		@Override
		public boolean detect(Point where) {
			lastStepTime = 0;
			Rectangle rect = Rectangle.SINGLETON;
			port.getClientArea(rect);
			port.translateToParent(rect);
			port.translateToAbsolute(rect);
			boolean needChange = false;
			if (where.x >= rect.width - threshold)
				needChange = true;
			if (where.y >= rect.height - threshold)
				needChange = true;
			if (where.x < threshold)
				needChange = true;
			if (where.y < threshold)
				needChange = true;
			return needChange;
		}

		/**
		 * Check if the autoscroll action should be take from a time point o view. This is done to avoid the the autoscroll
		 * is too fast so between every autoscroll a little lag is introduced
		 * 
		 * @return true if the last autoscroll was done too few ago and we need to wait, false otherwise
		 */
		private boolean needToWait() {
			// set scroll offset (speed factor)
			int scrollOffset = 0;

			// calculate time based scroll offset
			if (lastStepTime == 0)
				lastStepTime = System.currentTimeMillis();

			long difference = System.currentTimeMillis() - lastStepTime;

			if (difference > 0) {
				scrollOffset = ((int) difference / 3);
				lastStepTime = System.currentTimeMillis();
			}

			if (scrollOffset == 0)
				return true;
			return false;
		}

		/**
		 * Returns <code>true</code> if the given point is outside the viewport or near its edge. Scrolls the viewport by a
		 * calculated (time based) amount in the current direction. To avoid infinite autoscroll a there is a bound, calculated 
		 * on the dragged element size
		 * 
		 * @param where
		 *          the current location of the mouse in the viewer
		 * 
		 * @return a hint indicating whether this helper should continue to be invoked
		 */
		@Override
		public boolean step(Point where) {
			Rectangle rect = Rectangle.SINGLETON;
			port.getClientArea(rect);
			port.translateToParent(rect);
			port.translateToAbsolute(rect);
			Point loc = port.getViewLocation();
			Point targetloc = port.getViewLocation();
			Rectangle dragSize = ((FigureEditPart)getSourceEditPart()).getFigure().getBounds();
			//The autoscroll bounds are calculated on the size of the element dragged
			int elementSizeOffset = (dragSize.x + dragSize.y)*2;
			if (needToWait())
				return true;
			if (where.x >= rect.width - threshold && loc.x< elementSizeOffset)
				targetloc.x += threshold * 2;
			if (where.y >= rect.height - threshold && loc.y<elementSizeOffset)
				targetloc.y += threshold * 2;
			if (where.x < threshold && loc.x> -elementSizeOffset)
				targetloc.x -= threshold * 2;
			if (where.y < threshold && loc.y + elementSizeOffset >0)
				targetloc.y -= threshold * 2;
			if (!loc.equals(targetloc)) {
				port.setViewLocation(targetloc);
				updateTargetUnderMouse();
				return true;
			}
			return false;
		}

	}

	public SearchParentDragTracker(EditPart sourceEditPart) {
		super(sourceEditPart);
	}

	/**
	 * Handle the drag in progress event, it is different from the default one because it check if the AutoxposeHelper is
	 * set, and if it isn't it create a new one using the viewport of the active window. Then it call the default
	 * handleDragInProgress
	 */
	protected boolean handleDragInProgress() {
		/*
		 * If the drag tracker has not an autoexpose helper it will be set. Since we are doing this in the drag in progress
		 * we can take it from the active window, that should be the report editor
		 */
		if (getAutoexposeHelper() == null) {
			// JrxmlEditor editor =
			// (JrxmlEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart().getSite().getPart();
			// editor.getReportContainer().getMainEditor().getEditor().getViewport();
			org.eclipse.draw2d.Viewport port = ((FigureCanvas) getCurrentViewer().getControl()).getViewport();
			setAutoexposeHelper(new DragAutoExpose(port, 50));
		}
		return super.handleDragInProgress();
	}

	/**
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "SameParentDragTracker:" + getCommandName();
	}

	/**
	 * Take an edit part and search it's container
	 * 
	 * @param child
	 * @return the container of the child, could be null
	 */
	private EditPart searchParent(EditPart child) {
		if (child != null) {
			// This use the model for the search because every EditPart in the report has the same father.
			Object parentModel = ((ANode) child.getModel()).getParent();
			for (Object actualChild : child.getParent().getChildren()) {
				EditPart actualChildPart = (EditPart) actualChild;
				if (parentModel == actualChildPart.getModel()) {
					return actualChildPart;
				}
			}
		}
		return null;
	}

	/**
	 * Called to get the destination edit part during a drag and drop, if the destination its not a container the it
	 * parent is taken
	 */
	protected EditPart getTargetEditPart() {
		EditPart target = super.getTargetEditPart();
		EditPart parent = null;
		if (!(target instanceof IContainer))
			parent = searchParent(target);
		return parent != null ? parent : target;
	}

};
