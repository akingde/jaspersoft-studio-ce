/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SharedCursors;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Cursor;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.gef.parts.JSSScalableFreeformRootEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IDesignDragable;
import com.lowagie.text.Header;

import net.sf.jasperreports.eclipse.util.Pair;

/**
 * The resize tracker that will resize a group figure and also all its selection
 * 
 * @author Marco Orlandin
 */
public class GroupResizeTracker extends ResizeTracker {
	/**
	 * The zoom manager
	 */
	private ZoomManager zoomManager;

	/**
	 * Create the tracker
	 * 
	 * @param owner the parent edit part
	 * @param direction the direction handled by the handler associated to this tracker
	 */
	public GroupResizeTracker(GroupSelectionEditPart owner, int direction) {
		super(owner, direction);
	}

	/**
	 * Search a valid node that has as ancestor a node to disable the 
	 * editor refresh. The node is searched between the selection set
	 * 
	 * @return the node if it is found, null otherwise
	 */
	private ANode getLockableNode(){
		for(Object part : getOperationSet()){
			if (part instanceof EditPart){
				EditPart ePart = (EditPart)part;
				if (ePart.getModel() instanceof ANode){
					ANode mainNode = JSSCompoundCommand.getMainNode((ANode)ePart.getModel());
					if (mainNode != null) return mainNode;
				}
			}
		}
		return null;
	}
	
	/**
	 * If the returned command is a compound command and not a JSS compound command
	 * it is converted into a JSS compound command, otherwise it is returned as it is
	 */
	@Override
	protected Command getCurrentCommand() {
		Command command = super.getCurrentCommand();
		if (!(command instanceof JSSCompoundCommand)){
			if (command instanceof CompoundCommand){
				CompoundCommand cc = (CompoundCommand)command;
				JSSCompoundCommand jsscc = new JSSCompoundCommand(cc,getLockableNode()) ;
				command = jsscc;
			} else {
				JSSCompoundCommand jsscc = new JSSCompoundCommand(getLockableNode());
				jsscc.add(command);
				command = jsscc;
			}
		}
		return command;
	}
	
	/**
	 * Don't show the feedback if the command can`t be executed
	 */
	@Override
	protected void showTargetFeedback() {
		Command command = getCurrentCommand();
		if (command != null && command.canExecute()){
			super.showTargetFeedback();
		}
	}
	
	/**
	 * Return the current level of zoom
	 */
	protected double getZoom(){
		if (zoomManager == null){
			EditPartViewer viewer = getCurrentViewer();
			zoomManager = (ZoomManager)viewer.getProperty(ZoomManager.class.toString());
			if (zoomManager == null){
				//fallback
				zoomManager = ((JSSScalableFreeformRootEditPart) viewer.getRootEditPart()).getZoomManager();
			}
		}
		return zoomManager != null ? zoomManager.getZoom() : 1d;
	}
	
	@Override
	protected GraphicalEditPart getTargetEditPart() {
		return null;
	} 
	
	/**
	 * Create the operation set. This is composed by the selection and the group selection edit part. This must 
	 * be added because it is not a standard part of the selection
	 */
	@Override
	protected List<?> createOperationSet() {
		JSelectionManager selectionManager = ((GroupSelectionEditPart)getOwner()).getSelectionManager();
		List<Object> operationSet = new ArrayList<Object>(((StructuredSelection)selectionManager.getSelection()).toList());
		operationSet.add(getOwner());
		return operationSet;
	}
	
	@Override
	protected Cursor getDefaultCursor() {
		return SharedCursors.getDirectionalCursor(getResizeDirection(), getOwner().getFigure().isMirrored());
	}
	
	/**
	 * Crate the commands to resize the group edit part and proportionally scale the selection 
	 */
	@Override
	protected Command getCommand() {
		List<?> editparts = getOperationSet();
		EditPart part;
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel("Resize Group Handle Tracker");//$NON-NLS-1$
		Pair<Double, Double> scaleFator = calculateScaleFactor();
		Integer originX = null;
		Integer originY = null;
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editPart = (EditPart) editparts.get(i);
			if (!(editPart instanceof GroupSelectionEditPart)) {
				Rectangle bounds = ((FigureEditPart)editPart).getFigure().getBounds();
				if (originX == null || originX > bounds.x) {
					originX = bounds.x;
				}
				if (originY == null || originY > bounds.y) {
					originY = bounds.y;
				}
			}
		}
		for (int i = 0; i < editparts.size(); i++) {
			part = (EditPart) editparts.get(i);
			if (part instanceof GroupSelectionEditPart) {
				command.add(part.getCommand(getSourceRequest()));
			} else {
				command.add(part.getCommand(getSourceRequest(scaleFator.getKey(), scaleFator.getValue(), part, originX, originY)));
			}
		}
		return command.unwrap();
	}
	
	/**
	 * Show the source feedback of the resize both on the group figure and on the content
	 * of the selection, these are calculated differently 
	 */
	@Override
	protected void showSourceFeedback() {
		Command command = getCurrentCommand();
		if (command != null && command.canExecute()){
	
			Pair<Double, Double> scaleFator = calculateScaleFactor();
			List<?> editParts = getOperationSet();
			Integer originX = null;
			Integer originY = null;
			for (int i = 0; i < editParts.size(); i++) {
				EditPart editPart = (EditPart) editParts.get(i);
				if (!(editPart instanceof GroupSelectionEditPart)) {
					Rectangle bounds = ((FigureEditPart)editPart).getFigure().getBounds();
					if (originX == null || originX > bounds.x) {
						originX = bounds.x;
					}
					if (originY == null || originY > bounds.y) {
						originY = bounds.y;
					}
				}
			}
			for (int i = 0; i < editParts.size(); i++) {
				EditPart editPart = (EditPart) editParts.get(i);
				if (editPart instanceof GroupSelectionEditPart) {
					editPart.showSourceFeedback(getSourceRequest());
				} else {
					editPart.showSourceFeedback(getSourceRequest(scaleFator.getKey(), scaleFator.getValue(), editPart, originX, originY));
				}
			}
			//setFlag(FLAG_SOURCE_FEEDBACK, true); FLAG_SOURCE_FEEDBACK is constant, replicate the value
			setFlag(AbstractTool.MAX_FLAG << 1, true);
		}
	}
	
	/**
	 * Calculate how much the figure of the selection must be scaled on the width and on the height.
	 * The scale depends from the ratio between the group selection figure before and after the resize
	 * 
	 * @return a not null pair where the first value is the ratio on the width and the second on the height
	 */
	private Pair<Double, Double> calculateScaleFactor(){
		ChangeBoundsRequest request = (ChangeBoundsRequest)super.getSourceRequest();
		double percentageResizeWidth = 1.0d;
		double percentageResizeHeight = 1.0d;
		double sizeDelta_width = request.getSizeDelta().width;
		double sizeDelta_height = request.getSizeDelta().height;
		if (sizeDelta_width != 0 || sizeDelta_height != 0){
			for(Object part : request.getEditParts()){
				EditPart editPart = (EditPart)part;
				if (editPart instanceof GroupSelectionEditPart) {
					Rectangle bounds = getBounds();
					double newWidth = bounds.width + sizeDelta_width;
					double newHeight = bounds.height + sizeDelta_height;
					percentageResizeWidth = newWidth / bounds.width;
					percentageResizeHeight = newHeight / bounds.height;
					break;
				}
			}
		}
		return new Pair<Double, Double>(percentageResizeWidth, percentageResizeHeight);
	}
	
	/**
	 * Return the bounds of the group figure
	 */
	protected Rectangle getBounds() {
		return getOwner().getFigure().getBounds();
	}

	/**
	 * Get the request to scale an edit part of the selection of a specific factor. The part can be also moved depending to
	 * its position and the resize direction, to fit with the other resized part in the selection
	 * 
	 * @param scaleFactorWidth how much the figure must be scaled on the width
	 * @param scaleFactorHeighthow much the figure must be scaled on the {@link Header}
	 * @param resizedPart the resized part
	 * @param originX the X position of the edit part most on the left
	 * @param originY the Y position of the edit part on the top of the selection
	 * @return a not null request
	 */
	protected Request getSourceRequest(double scaleFactorWidth, double scaleFactorHeight, EditPart resizedPart, int originX, int originY){
		ChangeBoundsRequest request = (ChangeBoundsRequest)createSourceRequest();
		request.setEditParts(resizedPart);
		ANode model = (ANode)((EditPart)resizedPart).getModel();
		if (model instanceof IDesignDragable){
			IDesignDragable element = (IDesignDragable)model;
			double zoom = getZoom();
			Rectangle bounds = element.getJRBounds();
			
			double newWidth = bounds.width * scaleFactorWidth;
			request.getSizeDelta().setWidth((int)Math.round((newWidth - bounds.width)*zoom));
			
			double newHeight = bounds.height * scaleFactorHeight;
			request.getSizeDelta().setHeight((int)Math.round((newHeight - bounds.height)*zoom));
			
			Rectangle figureBounds = ((FigureEditPart)resizedPart).getFigure().getBounds();
			
			//fix the x positions
			if (getResizeDirection() == PositionConstants.EAST || getResizeDirection() == PositionConstants.NORTH_EAST || getResizeDirection() == PositionConstants.SOUTH_EAST) {
				if (figureBounds.x != originX) {
					//the figure is ahead of the origin X
					int relativeX = figureBounds.x - originX;
					request.getMoveDelta().setX((int)Math.round((relativeX * scaleFactorWidth -  relativeX)*zoom));
				}
			} else if (getResizeDirection() == PositionConstants.WEST || getResizeDirection() == PositionConstants.NORTH_WEST || getResizeDirection() == PositionConstants.SOUTH_WEST) {
				if (figureBounds.x != originX) {
					//the figure is ahead of the origin X
					int relativeX = figureBounds.x - originX;
					int leftMovement = ((ChangeBoundsRequest)getSourceRequest()).getMoveDelta().x; 
					request.getMoveDelta().setX((int)Math.round(leftMovement + (relativeX * scaleFactorWidth -  relativeX)*zoom));
				} else {
					//compensate the movement on the left
					int leftMovement = ((ChangeBoundsRequest)getSourceRequest()).getMoveDelta().x; 
					request.getMoveDelta().setX(leftMovement);
				}
			}
			
			//fix the y positions
			if (getResizeDirection() == PositionConstants.SOUTH || getResizeDirection() == PositionConstants.SOUTH_EAST || getResizeDirection() == PositionConstants.SOUTH_WEST) {
				if (figureBounds.y != originY) {
					//the figure is below of the origin Y
					int relativeY = figureBounds.y - originY;
					request.getMoveDelta().setY((int)Math.round((relativeY * scaleFactorHeight - relativeY)*zoom));
				}
			} else if (getResizeDirection() == PositionConstants.NORTH || getResizeDirection() == PositionConstants.NORTH_WEST || getResizeDirection() == PositionConstants.NORTH_WEST) {
				if (figureBounds.y != originY) {
					//the figure is below of the origin Y
					int relativeY = figureBounds.y - originY;
					int upMovement = ((ChangeBoundsRequest)getSourceRequest()).getMoveDelta().y; 
					request.getMoveDelta().setY((int)Math.round(upMovement + (relativeY * scaleFactorHeight - relativeY)*zoom));
				} else {
					//compensate the movement up
					int upMovement = ((ChangeBoundsRequest)getSourceRequest()).getMoveDelta().y; 
					request.getMoveDelta().setY(upMovement);
				}
			}
			
		}
		return request;
	}
}
