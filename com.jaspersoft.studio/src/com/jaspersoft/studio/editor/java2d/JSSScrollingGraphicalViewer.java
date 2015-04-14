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

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;

/**
 * Extended graphical viewer that provide an ovverride of the selected elements
 * and also a flag that can be used to avoid the selective painting of the visible elements,
 * that in some cases can cause problems, like when the outline thumbnail is shown
 * 
 * @author Orlandin Marco
 *
 */
public class JSSScrollingGraphicalViewer extends ScrollingGraphicalViewer {
	
	/**
	 * Selection overrider for the current viewer, null is the default value
	 * and means that not overrider is used
	 */
	protected ISelectionOverrider selectionOverrider = null;
	
	/**
	 * Flag used to request that only the visible are painted
	 */
	private boolean paintOnlyVisibleElements = true;
	
	/**
	 * Return if the elements on the editor should be painted only
	 * when they are visible
	 * 
	 * @return a boolean value
	 */
	public boolean isPaintOnlyVisibleElements(){
		return paintOnlyVisibleElements;
	}
	
	/**
	 * Set if the elements on the editor should be painted only
	 * when they are visible
	 * 
	 * @param value the new value
	 */
	public void setPaintOnlyVisibleElements(boolean value){
		this.paintOnlyVisibleElements = value;
	}
	
	/**
	 * Append the provided editpart to the current selection, but only if there is not 
	 * an overrider that override the current selection to select something else
	 */
	public void appendSelection(EditPart editpart) {
		boolean wasOverride = false;
		if (selectionOverrider != null){
			wasOverride = selectionOverrider.overriddenSelection(editpart, this);
		}
		if (!wasOverride) super.appendSelection(editpart);
	}

	/**
	 * Set a selection overrider for the current viewer 
	 * 
	 * @param overrider the overrider or null if no overrider should be used
	 */
	public void setSelectionOverrider(ISelectionOverrider overrider){
		selectionOverrider = overrider;
	}
	
	/**
	 * Visibility modifier to get the figure canvas
	 */
	public FigureCanvas getFigureCanvas(){
		return super.getFigureCanvas();
	}
}
