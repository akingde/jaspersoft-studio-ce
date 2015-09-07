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
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

/**
 * Abstract class for a graphics2d that supports the caching of the
 * operation. Can be used when something need a many calculations to
 * be drawn. Until something that modify the calculations happen
 * it's useless to repeat them only because of a refresh. The idea 
 * is to cache in some way the operations executed the first time
 * and be able to repeat them
 * 
 * 
 * @author Orlandin Marco
 *
 */
public abstract class ACachedGraphics extends Graphics2D {
	
	/**
	 * Execute the operation in the cache to paint the content
	 * on the set graphics
	 */
	public abstract void paintCache();
	
	/**
	 * The graphics where the operation in the cache will paint
	 * 
	 * @param graphics a not null graphics 2d
	 */
	public abstract void setGraphics(Graphics2D graphics);
	
}
