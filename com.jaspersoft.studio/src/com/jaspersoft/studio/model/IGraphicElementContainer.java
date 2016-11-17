/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
