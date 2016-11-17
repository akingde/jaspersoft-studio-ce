/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.java2d;

import java.awt.Graphics2D;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Canvas;

/*
 * /* The Class J2DLightweightSystem.
 */
public final class J2DLightweightSystem extends LightweightSystem {

	private J2DGraphicsSource gsSource;

	public J2DLightweightSystem(Canvas c) {
		super(c);
		setUpdateManager(new J2DUpdateManager());
	}

	/**
	 * Instantiates a new j2 d lightweight system.
	 */
	public J2DLightweightSystem() {
		setUpdateManager(new J2DUpdateManager());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.LightweightSystem#setControl(org.eclipse.swt.widgets.Canvas)
	 */
	public final void setControl(Canvas canvas) {
		super.setControl(canvas);
		if (canvas != null) {
			gsSource = new J2DGraphicsSource(canvas);
			getUpdateManager().setGraphicsSource(gsSource);
		}
	}

	public Graphics2D getGraphics2D() {
		if (gsSource != null)
			return gsSource.getGraphics2d();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.LightweightSystem#paint(org.eclipse.swt.graphics.GC)
	 */
	public final void paint(GC gc) {

		((J2DUpdateManager) getUpdateManager()).paintAll(gc);
	}
}
