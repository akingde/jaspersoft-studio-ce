/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;

import com.jaspersoft.studio.editor.outline.editpolicy.JDContainerEditPolicy;
import com.jaspersoft.studio.editor.outline.editpolicy.JDTreeContainerEditPolicy;
import com.jaspersoft.studio.model.MGraphicElement;

public class NotDragableContainerTreeEditPart extends NotDragableTreeEditPart {
	/**
	 * Creates and installs pertinent EditPolicies.
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new JDContainerEditPolicy());
		installEditPolicy(EditPolicy.TREE_CONTAINER_ROLE, new JDTreeContainerEditPolicy());
		// If this editpart is the contents of the viewer, then it is not deletable!
		if (getParent() instanceof RootEditPart)
			installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
	}

	@Override
	public boolean understandsRequest(Request req) {
		if (getModel() instanceof MGraphicElement && req.getType() == RequestConstants.REQ_ALIGN)
			return true;
		return super.understandsRequest(req);
	}
	
	//These two methods allow to show a visual feedback on the main editor, if available
	
	@Override
	public void showTargetFeedback(Request request) {
		super.showTargetFeedback(request);
		showTargetFeedbackOnEditor(request);
	}
	
	@Override
	public void eraseTargetFeedback(Request request) {
		super.eraseTargetFeedback(request);
		eraseTargetFeedbackOnEditor(request);
	}
	
}
