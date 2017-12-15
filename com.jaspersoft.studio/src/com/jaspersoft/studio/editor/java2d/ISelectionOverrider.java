/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import java.util.List;

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
	 * @param previousSelection the selection before the selectedPart element is clicked
	 * @param currentViewer the viewer
	 * @return true if the implementation has overridden the selection and selected something
	 * else, false otherwise
	 */
	public boolean overriddenSelection(EditPart selectedPart, List<?> previousSelection, GraphicalViewer currentViewer);
	
}
