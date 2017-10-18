/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.editor.gef.parts.EditableFigureEditPart;
import com.jaspersoft.studio.model.ANode;

/**
 * Tree edit part used for elements that can be opened into a subeditor 
 */
public class OpenableNotDraggableContainerTreeEditPart extends NotDragableContainerTreeEditPart {

	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			Object value = ((ANode) getModel()).getValue();
			IEditorPart editorPart = ((DefaultEditDomain) getViewer().getEditDomain()).getEditorPart();
			EditableFigureEditPart.openEditor(value, editorPart, ((ANode) getModel()));
		}
		super.performRequest(req);
	}

	@Override
	public boolean understandsRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			return true;
		}
		return super.understandsRequest(req);
	}
	
}
