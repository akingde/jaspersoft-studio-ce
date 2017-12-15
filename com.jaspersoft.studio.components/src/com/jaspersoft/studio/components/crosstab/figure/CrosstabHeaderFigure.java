/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.figure;

import org.eclipse.draw2d.Graphics;

public class CrosstabHeaderFigure extends CellFigure {
	@Override
	public void paint(Graphics graphics) {
		if (cell != null)
			super.paint(graphics);
	}
}
