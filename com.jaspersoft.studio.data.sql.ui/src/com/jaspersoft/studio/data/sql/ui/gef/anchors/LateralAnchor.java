/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.ui.gef.anchors;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.data.sql.ui.gef.figures.SqlTableFigure;

public class LateralAnchor extends AbstractConnectionAnchor {
	public LateralAnchor(IFigure source) {
		super(source);
	}

	public Point getLocation(Point reference) {
		Rectangle r = getOwner().getBounds().getCopy();
		getOwner().translateToAbsolute(r);
		int off = SqlTableFigure.INSETS.left * 2;
		if (r.contains(reference) || r.x < reference.x)
			return r.getRight().translate(off, 0);
		else
			return r.getLeft().translate(-off, 0);
	}

}
