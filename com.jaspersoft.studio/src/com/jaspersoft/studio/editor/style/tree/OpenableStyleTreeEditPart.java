/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.style.tree;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.jaspersoft.studio.editor.outline.part.IOpenableTreeEditPart;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.engine.JRTemplateReference;

/**
 * Tree edit part used to open {@link JRTemplateReference} into an editor with a double click on the outline 
 */
public class OpenableStyleTreeEditPart extends StyleTreeEditPart implements IOpenableTreeEditPart {
	
	@Override
	public void performRequest(Request req) {
		if (RequestConstants.REQ_OPEN.equals(req.getType())) {
			ANode model = (ANode)getModel();
			JRTemplateReference value = (JRTemplateReference)model.getValue();
			IFile file = model.getJasperConfiguration().getAssociatedReportFile();
			SelectionHelper.openEditor(file.getProject().getFile(value.getLocation()), value.getLocation());
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
