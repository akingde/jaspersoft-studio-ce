/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Command to move and element, it is similar to SetConstraintCommand but
 * with this one the parent of the element doesn't change. From the set
 * position command the value set in this are taken always as absolute, so
 * they are directly set on the element
 * 
 * @author Orlandin Marco
 *
 */
public class SetAbsolutePositionCommand extends SetPositionCommand {

	@Override
	public void execute() {
		if (jrElement != null) {
			oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
			jrElement.setX(newBounds.x);
			jrElement.setY(newBounds.y);
			jrElement.setWidth(newBounds.width);
			jrElement.setHeight(newBounds.height);
			
			layoutChildAndParent();
		}
	}
	
}
