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
