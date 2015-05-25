/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.gef.parts.JSSScalableFreeformRootEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Extends the default resize tracker allowing converting
 * the compound commands into JSSCompoundCommands, that 
 * disable the editor refresh during the execution of the 
 * single commands 
 * 
 * @author Orlandin Marco
 *
 */
public class JSSCompoundResizeTracker extends ResizeTracker {

	/**
	 * The zoom manager
	 */
	private ZoomManager zoomManager;

	public JSSCompoundResizeTracker(GraphicalEditPart owner, int direction) {
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
	
	/**
	 * When the request is created it check the bounds to avoid the drag 
	 * of the item too far from the current page
	 */
	@Override
	protected Request getSourceRequest() {
		ChangeBoundsRequest request = (ChangeBoundsRequest)super.getSourceRequest();
		if (request.getEditParts() != null && request.getEditParts().size() > 0){
			ANode node = null;
			for(Object part : request.getEditParts()){
				node = (ANode)((EditPart)part).getModel();
				break;
			}
			Point maximumSize = node.getAvailableSize();
			double zoom = 1/getZoom();
			double sizeDelta_width = request.getSizeDelta().width*zoom;
			double sizeDelta_height = request.getSizeDelta().height*zoom;
			if (sizeDelta_width != 0 || sizeDelta_height != 0){
				for(Object part : request.getEditParts()){
					MGraphicElement gElement = (MGraphicElement)((EditPart)part).getModel();
					JRDesignElement jrElement = (JRDesignElement)gElement.getValue();
					
					double newWidth = jrElement.getWidth() + sizeDelta_width;
					if (Math.abs(jrElement.getX()+newWidth)>maximumSize.x){
						double delta = (maximumSize.x - jrElement.getWidth() - jrElement.getX())/zoom;
						request.getSizeDelta().setWidth((int)Math.round(delta));
					}
					
					double newHeight = jrElement.getHeight() + sizeDelta_height;
					if (Math.abs(jrElement.getY() + newHeight)>maximumSize.y){
						double delta = (maximumSize.y - jrElement.getHeight() - jrElement.getY())/zoom;
						request.getSizeDelta().setHeight((int)Math.round(delta));
					}
				}
			}
		}

		return request;
	}
}
