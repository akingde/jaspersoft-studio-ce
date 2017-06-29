/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.band;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.MoveHandleLocator;

import com.jaspersoft.studio.editor.java2d.J2DGraphics;

public class BandMoveHandle extends MoveHandle {

	public BandMoveHandle(GraphicalEditPart owner, Locator loc) {
		super(owner, loc);
	}
	
	public BandMoveHandle(GraphicalEditPart owner) {
		this(owner, new MoveHandleLocator(owner.getFigure()));
	}

	@Override
	public void paintFigure(Graphics graphics) {
		Rectangle bounds = getBounds();
		Graphics2D g = ((J2DGraphics)graphics).getGraphics2D();
		Shape oldClip = g.getClip();
		g.setClip(null);
		g.setColor(Color.red);
		g.fillRect(bounds.x-5, bounds.y, 4, bounds.height);
		g.setClip(oldClip);
		setBounds(bounds);
	}
	
}
