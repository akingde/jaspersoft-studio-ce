/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.ResizeTracker;

import com.jaspersoft.studio.compatibility.ToolUtilitiesCompatibility;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementResizableEditPolicy;

/**
 * Edit policy used for elements that can be opened inside subeditors. It is really similar to 
 * the standard one but when an element is opened inside its subeditor it uses a special resize
 * tracker that doesn't allow to change the position of the element in another editor when dragging
 * from the North or West border
 * 
 * @author Marco Orlandin
 *
 */
public class SubeditorResizableEditPolicy extends ElementResizableEditPolicy {
	
	/**
	 * When the element is not in the subeditor works as standard, otherwise return a special
	 * resize tracker 
	 */
	@Override
	protected ResizeTracker getResizeTracker(int direction) {
		if(ToolUtilitiesCompatibility.isSubeditorMainElement(getHost())) return new SubEditorResizeTracker((GraphicalEditPart) getHost(), direction);
		else return super.getResizeTracker(direction);
	};
	
}
