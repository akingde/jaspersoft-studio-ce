/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.events.MouseEvent;

import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.editor.gef.parts.band.NotMovablePartDragTracker;

/**
 * Drag tracker for the element inside a subeditor. It dosen't allow to the element
 * to be moved, but allow the resize. It should be used for the edit part for wich 
 * the subeditor was opened
 * 
 * 
 * @author Orlandin Marco
 */
public class SubEditorEditPartTracker extends NotMovablePartDragTracker {

	public SubEditorEditPartTracker(EditPart owner) {
		super(owner);
	}

	/**
	 * Remove the edited edit part of a subeditor from the list
	 */
	protected void filterSelectedParts(Collection<EditPart> editPartsToProcess){
		if (!editPartsToProcess.isEmpty()) {
			EditPart pageEditPart = ToolUtilitiesCompatibility.getPageEditPart((EditPart)editPartsToProcess.iterator().next());
			if (pageEditPart != null) {
				editPartsToProcess.remove(pageEditPart.getChildren().get(0));
			}
		}
	}

	/**
	 * Filter the selection to remove the main edit part of the current subedito,
	 * if the user is inside a subeditor
	 */
	@Override
	protected Collection<EditPart> calculateMarqueeSelectedEditParts() {
		Collection<EditPart> marqueeSelectedEditParts = new HashSet<EditPart>();
		marqueeSelectedEditParts.addAll(calculatePrimaryMarqueeSelectedEditParts());
		marqueeSelectedEditParts.addAll(calculateSecondaryMarqueeSelectedEditParts(marqueeSelectedEditParts));
		filterSelectedParts(marqueeSelectedEditParts);
		return marqueeSelectedEditParts;
	}
	
	/**
	 * This will show the handle to resize thanks to the super call
	 */
	@Override
	public void mouseUp(MouseEvent me, EditPartViewer viewer) {

		boolean wasDragging = movedPastThreshold();
 		if (me.button == 1 && !wasDragging) {
			EditPart clickedPart = viewer.findObjectAt(new Point(me.x, me.y));
			if (clickedPart != null) viewer.select(clickedPart);
		} else
			super.mouseUp(me, viewer);
	};

}
