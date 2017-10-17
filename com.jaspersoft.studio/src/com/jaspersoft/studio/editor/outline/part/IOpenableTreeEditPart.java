/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.part;

import org.eclipse.gef.Request;

/**
 * Interface implemented by a tree edit part to mark that it can accept open requests with doubleclick
 * on the outline view
 */
public interface IOpenableTreeEditPart {

	public void performRequest(Request req);

	public boolean understandsRequest(Request req);
	
}
