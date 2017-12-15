/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import java.util.ArrayList;
import java.util.List;

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
	 * Selection overriders for the current viewer, empty is the default value
	 * and means that not overrider is used
	 */
	protected List<ISelectionOverrider> selectionOverriders = new ArrayList<ISelectionOverrider>();
	
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
	@Override
	public void appendSelection(EditPart editpart) {
		boolean wasOverride = false;
		for (ISelectionOverrider selectionOverrider : selectionOverriders){
			wasOverride = selectionOverrider.overriddenSelection(editpart, getSelectedEditParts(), this);
			if (wasOverride){
				break;
			}
		}
		if (!wasOverride) super.appendSelection(editpart);
	}
	
	
	@Override
	public void select(EditPart editpart) {
		//Store the selection for the overridder
		List<?> previousSelection= primDeselectAll();
		
		boolean wasOverride = false;
		for (ISelectionOverrider selectionOverrider : selectionOverriders){
			wasOverride = selectionOverrider.overriddenSelection(editpart, previousSelection, this);
			if (wasOverride){
				break;
			}
		}
		if (!wasOverride) {
			//this code is the same of the super.select, the difference this call the super.appendSelection
			//calling directly super.select will called this appendSelection causing the overrider to be evaluated
			//twice
			if ((getSelectedEditParts().size() == 1) && (getSelectedEditParts().get(0) == editpart))
				return;
			primDeselectAll();
			super.appendSelection(editpart); 
		}
	}
	
	/**
	 * Deselect all the elements
	 */
	protected List<?> primDeselectAll() {
		EditPart part;
		List<?> list = primGetSelectedEditParts();
		for (int i = 0; i < list.size(); i++) {
			part = (EditPart) list.get(i);
			part.setSelected(EditPart.SELECTED_NONE);
		}
		List<?> result = new ArrayList<Object>(list);
		list.clear();
		return result;
	}
	
	/**
	 * Select an element without call the selection overrider, so force
	 * the selection of the passed element
	 * 
	 * @param editpart the element to select
	 */
	public void forceSelect(EditPart editpart) {
		// If selection isn't changing, do nothing.
		if ((getSelectedEditParts().size() == 1)
				&& (getSelectedEditParts().get(0) == editpart))
			return;
		primDeselectAll();
		super.appendSelection(editpart);
	}

	/**
	 * Set a selection overrider for the current viewer 
	 * 
	 * @param overrider the overrider or null if no overrider should be used
	 */
	public void addSelectionOverrider(ISelectionOverrider overrider){
		if (overrider != null && !selectionOverriders.contains(overrider)){
			selectionOverriders.add(overrider);
		}
	}
	
	/**
	 * Visibility modifier to get the figure canvas
	 */
	public FigureCanvas getFigureCanvas(){
		return super.getFigureCanvas();
	}
}
