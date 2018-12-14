/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.band;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.ViewportAutoexposeHelper;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.SimpleDragTracker;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The Class BandResizeTracker.
 */
public class BandResizeTracker extends SimpleDragTracker {

	/** The editpart. */
	private EditPart editpart;
	

	/** The expose helper. */
	private AutoexposeHelper exposeHelper;
	private SnapToHelper snapToHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SimpleDragTracker#deactivate()
	 */
	public void deactivate() {
		super.deactivate();
		setAutoexposeHelper(null);
		snapToHelper = null;
		sourceRectangle = null;
		compoundSrcRect = null;
	}

	/**
	 * Called to perform an iteration of the autoexpose process. If the expose helper is set, it will be asked to step at
	 * the current mouse location. If it returns true, another expose iteration will be queued. There is no delay between
	 * autoexpose events, other than the time required to perform the step().
	 */
	protected void doAutoexpose() {
		if (exposeHelper == null)
			return;
		if (exposeHelper.step(getLocation())) {
			handleAutoexpose();
			Display.getDefault().asyncExec(new QueuedAutoexpose());
		} else
			setAutoexposeHelper(null);
	}

	/**
	 * The Class QueuedAutoexpose.
	 */
	class QueuedAutoexpose implements Runnable {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			doAutoexpose();
		}
	}

	/**
	 * Sets the active autoexpose helper to the given helper, or <code>null</code>. If the helper is not <code>null</code>
	 * , a runnable is queued on the event thread that will trigger a subsequent {@link #doAutoexpose()}. The helper is
	 * typically updated only on a hover event.
	 * 
	 * @param helper
	 *          the new autoexpose helper or <code>null</code>
	 */
	protected void setAutoexposeHelper(AutoexposeHelper helper) {
		exposeHelper = helper;
		if (exposeHelper == null)
			return;
		Display.getDefault().asyncExec(new QueuedAutoexpose());
	}

	/**
	 * Updates the active {@link AutoexposeHelper}. Does nothing if there is still an active helper. Otherwise, obtains a
	 * new helper (possible <code>null</code>) at the current mouse location and calls
	 * {@link #setAutoexposeHelper(AutoexposeHelper)}.
	 */
	protected void updateAutoexposeHelper() {
		if (exposeHelper != null)
			return;
		AutoexposeHelper.Search search = new AutoexposeHelper.Search(getLocation());

		getCurrentViewer().findObjectAtExcluding(getLocation(), Collections.EMPTY_LIST, search);

		setAutoexposeHelper(search.result);
	}

	/**
	 * This method is called whenever an autoexpose occurs. When an autoexpose occurs, it is possible that everything in
	 * the viewer has moved a little. Therefore, by default, {@link AbstractTool#handleMove() handleMove()} is called to
	 * simulate the mouse moving even though it didn't.
	 */
	protected void handleAutoexpose() {
		handleMove();
		handleDragInProgress();
	}

	/**
	 * Constructor for SectionResizeTracker.
	 * 
	 * @param sourceEditPart
	 *          the source edit part
	 */
	public BandResizeTracker(EditPart sourceEditPart) {
		setSourceEditPart(sourceEditPart);
		setAutoexposeHelper(new ViewportAutoexposeHelper((GraphicalEditPart) sourceEditPart.getViewer().getRootEditPart()));
		updateAutoexposeHelper();
	}
	
	/**
	 * Handle the doubleclick on the resize bar, in this way is possible to resize the band to the minimum size to contains all 
	 * the element
	 */
	protected boolean handleDoubleClick(int button) {
		if (getSourceEditPart() != null) {
			if (button == 1) {
				SelectionRequest request = new SelectionRequest();
				request.setLocation(getLocation());
				request.setType(RequestConstants.REQ_OPEN);
				getSourceEditPart().performRequest(request);
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#getCommandName()
	 */
	protected String getCommandName() {
		return REQ_MOVE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#getDebugName()
	 */
	protected String getDebugName() {
		return "Section Resize Handle Tracker"; //$NON-NLS-1$
	}

	/**
	 * Gets the source edit part.
	 * 
	 * @return the source edit part
	 */
	protected EditPart getSourceEditPart() {
		return editpart;
	}

	/**
	 * Sets the source edit part.
	 * 
	 * @param part
	 *          the new source edit part
	 */
	protected void setSourceEditPart(EditPart part) {
		this.editpart = part;
		snapToHelper = null;
		if (editpart != null && getOperationSet().size() > 0)
			snapToHelper = (SnapToHelper) editpart.getParent().getAdapter(SnapToHelper.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#createOperationSet()
	 */
	protected List<EditPart> createOperationSet() {
		List<EditPart> editparts = null;
		if (editpart == null) {
			editparts = Collections.emptyList();
		} else {
			editparts = new ArrayList<EditPart>();
			editparts.add(editpart);
			ToolUtilities.filterEditPartsUnderstanding(editparts, getSourceRequest());
		}

		return editparts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.SimpleDragTracker#createSourceRequest()
	 */
	protected Request createSourceRequest() {
		ChangeBoundsRequest request = new ChangeBoundsRequest(REQ_RESIZE);
		// request.setResizeDirection(PositionConstants.NORTH_SOUTH);
		return request;
	}



	@Override
	protected void setState(int state) {
		super.setState(state);
		captureSourceDimensions();
	}

	private PrecisionRectangle sourceRectangle, compoundSrcRect;

	/**
	 * Captures the bounds of the source being dragged, and the unioned bounds of all figures being dragged. These bounds
	 * are used for snapping by the snap strategies in <code>updateTargetRequest()</code>.
	 */
	private void captureSourceDimensions() {
		List<?> editparts = getOperationSet();
		for (int i = 0; i < editparts.size(); i++) {
			GraphicalEditPart child = (GraphicalEditPart) editparts.get(i);
			IFigure figure = child.getFigure();
			PrecisionRectangle bounds = null;
			if (figure instanceof HandleBounds)
				bounds = new PrecisionRectangle(((HandleBounds) figure).getHandleBounds());
			else
				bounds = new PrecisionRectangle(figure.getBounds());
			figure.translateToAbsolute(bounds);

			if (compoundSrcRect == null)
				compoundSrcRect = new PrecisionRectangle(bounds);
			else
				compoundSrcRect = compoundSrcRect.union(bounds);
			if (child == getSourceEditPart())
				sourceRectangle = bounds;
		}
		if (sourceRectangle == null) {
			IFigure figure = ((GraphicalEditPart) getSourceEditPart()).getFigure();
			if (figure instanceof HandleBounds)
				sourceRectangle = new PrecisionRectangle(((HandleBounds) figure).getHandleBounds());
			else
				sourceRectangle = new PrecisionRectangle(figure.getBounds());
			figure.translateToAbsolute(sourceRectangle);
		}
	}

	static final int MODIFIER_NO_SNAPPING;
	static {
		if (Platform.OS_MACOSX.equals(Platform.getOS())) {
			MODIFIER_NO_SNAPPING = SWT.CTRL;
		} else {
			MODIFIER_NO_SNAPPING = SWT.ALT;
		}
	}

	/**
	 * This method can be overridden by clients to customize the snapping behavior.
	 * 
	 * @param request
	 *          the <code>ChangeBoundsRequest</code> from which the move delta can be extracted and updated
	 * @since 3.4
	 */
	protected void snapPoint(ChangeBoundsRequest request) {
		Point moveDelta = request.getMoveDelta();
		if (editpart != null && getOperationSet().size() > 0)
			snapToHelper = (SnapToHelper) editpart.getParent().getAdapter(SnapToHelper.class);
		if (snapToHelper != null && !getCurrentInput().isModKeyDown(MODIFIER_NO_SNAPPING)) {
			PrecisionRectangle baseRect = sourceRectangle.getPreciseCopy();
			PrecisionRectangle jointRect = compoundSrcRect.getPreciseCopy();
			baseRect.translate(moveDelta);
			jointRect.translate(moveDelta);

			PrecisionPoint preciseDelta = new PrecisionPoint(moveDelta);
			snapToHelper.snapPoint(request, PositionConstants.HORIZONTAL | PositionConstants.VERTICAL,
					new PrecisionRectangle[] { baseRect, jointRect }, preciseDelta);
			request.setMoveDelta(preciseDelta);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.tools.AbstractTool#getCommand()
	 */
	protected Command getCommand() {
		List<?> editparts = getOperationSet();
		EditPart part = null;
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		command.setDebugLabel("Move Section Handle Tracker"); //$NON-NLS-1$
		for (int i = 0; i < editparts.size(); i++) {
			part = (EditPart) editparts.get(i);
			command.setReferenceNodeIfNull(part.getModel());
			command.add(part.getCommand(getSourceRequest()));
		}
		return command.unwrap();
	}
	
  /**
   * Update the request and freeze the drag when it has reached it maximum dimension
   * @return return always true because when the drag is bigger than the available space the request
   * is changed to fit the available space.
   */
	protected boolean conditionallyUpdateSourceRequest() {
		Dimension d = getDragMoveDelta();
		
		ChangeBoundsRequest request = (ChangeBoundsRequest) getSourceRequest();
		BandEditPart part = (BandEditPart) getOperationSet().get(0);
		IFigure figure = part.getFigure();
		int oldHeight = 0;
		if (part.getModel() != null) {
			oldHeight = part.getModel().getBounds().height();
		} else {
			//this should never happen but just in case fallback
			oldHeight = figure.getBounds().height - 1;
		}
		//using the zoom to transform from relative to absolute
		ZoomManager zoomMgr = (ZoomManager) part.getViewer().getProperty(ZoomManager.class.toString());
		int deltaHeight = (int)Math.round(d.height / zoomMgr.getZoom());
		int newHeight = oldHeight + deltaHeight;
		int maxBandHeight = ModelUtils.getMaxBandHeight(part.getBand(),part.getJasperDesign());
		boolean inBoundContidion = newHeight > 0 && newHeight <= maxBandHeight;
		if (deltaHeight<0 || inBoundContidion){
			//the drag is valid with the current space
			int differences = newHeight - maxBandHeight;
			//Correct the end point of the dragging
			if (differences > 0) {
				deltaHeight-=differences;
			}
			request.setSizeDelta(new Dimension(0, (int)(deltaHeight * zoomMgr.getZoom())));
			request.setEditParts(getOperationSet());
			request.setResizeDirection(PositionConstants.SOUTH);
			request.setLocation(getLocation());
			snapPoint(request);
			request.setLocation(getLocation());
		} else if (oldHeight < maxBandHeight) {
			//the drag is bigger than the maximum space but there is still some space to grow
			request.setSizeDelta(new Dimension(0, (int)Math.ceil(((maxBandHeight-oldHeight)*zoomMgr.getZoom()))));
			request.setEditParts(getOperationSet());
			request.setResizeDirection(PositionConstants.NORTH);
			snapPoint(request);
			request.setLocation(getLocation());
		} else {
			//now space to drag
			request.setSizeDelta(new Dimension(0, 0));
			request.setEditParts(getOperationSet());
			request.setResizeDirection(PositionConstants.NORTH);
			snapPoint(request);
			request.setLocation(getLocation());
		}
		return true;
	}
	

	/**
	 * Copy of isInDragProgess, since it has package visibility but was still needed a new definition 
	 * was done
	 */
	protected boolean dragInProgress() {
		return isInState(STATE_DRAG_IN_PROGRESS
				| STATE_ACCESSIBLE_DRAG_IN_PROGRESS);
	}
	
	/**
	 * Override the original drag in progress to freeze the drag on the reaching of the maximum 
	 * band dimension
	 */
	@Override
	public boolean handleDragInProgress() {
			if (dragInProgress() && conditionallyUpdateSourceRequest()) {
				showSourceFeedback();
				setCurrentCommand(getCommand());
				updateAutoexposeHelper();
			}
			return true;
	}

}
