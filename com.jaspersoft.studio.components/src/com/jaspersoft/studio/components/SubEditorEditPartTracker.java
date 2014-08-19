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
package com.jaspersoft.studio.components;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.events.MouseEvent;

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
	 * This will show the handle to resize thanks to the super call
	 */
	@Override
	public void mouseUp(MouseEvent me, EditPartViewer viewer) {

		boolean wasDragging = movedPastThreshold();
 		if (me.button == 1 && !wasDragging) {
			EditPart clickedPart = viewer.findObjectAt(new Point(me.x, me.y));
			viewer.select(clickedPart);
		} else
			super.mouseUp(me, viewer);
	};

}
