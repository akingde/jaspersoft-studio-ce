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
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;

/**
 * An implementation of this class can be used inside a J2DScrollingGraphicalViewer to 
 * modify of the selection is done. This was done to allow to editors to override the
 * standard selection mechanism with a custom one
 * 
 * @author Orlandin Marco
 *
 */
public interface ISelectionOverrider {

	/**
	 * Get the last selected element and can or not override the selection.
	 * If the selection is overridden then the viewer will not select the edit part
	 * selected by the user. 
	 * 
	 * @param selectedPart the last selected part on the viewer
	 * @param currentViewer the viewer
	 * @return true if the implementation has overridden the selection and selected something
	 * else, false otherwise
	 */
	public boolean overriddenSelection(EditPart selectedPart, GraphicalViewer currentViewer);
	
}
