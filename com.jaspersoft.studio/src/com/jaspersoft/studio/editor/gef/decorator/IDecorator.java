/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator;

import org.eclipse.draw2d.Graphics;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

public interface IDecorator {
	public void paint(Graphics graphics, ComponentFigure fig);
}
