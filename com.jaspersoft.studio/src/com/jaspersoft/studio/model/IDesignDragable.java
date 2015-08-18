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
package com.jaspersoft.studio.model;

import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Identify an element that can be drag and dropped inside the deisgn editor
 * 
 * @author Orlandin Marco
 *
 */
public interface IDesignDragable {

	public Rectangle getJRBounds();
	
}
