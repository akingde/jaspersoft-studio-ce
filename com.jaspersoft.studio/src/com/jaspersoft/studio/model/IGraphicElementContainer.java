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

import org.eclipse.draw2d.geometry.Dimension;

public interface IGraphicElementContainer {
	
	public Integer getTopPadding();

	public Integer getBottomPadding();
	
	public Integer getLeftPadding();
	
	public Integer getRightPadding();
	
	public Integer getPadding();
	
	public Dimension getSize();
}
