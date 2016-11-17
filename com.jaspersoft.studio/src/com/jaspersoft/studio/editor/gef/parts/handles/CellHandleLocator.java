/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.handles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;

/*
 * The Class BandHandleLocator.
 */
public class CellHandleLocator implements Locator {
	private static final int bandHandleOffset = 5;
	private int position;
	/** The reference. */
	private IFigure reference;

	/**
	 * Creates a new MoveHandleLocator and sets its reference figure to <code>ref</code>. The reference figure should be
	 * the handle's owner figure.
	 * 
	 * @param ref
	 *          the ref
	 */
	public CellHandleLocator(IFigure ref, int position) {
		setReference(ref);
		this.position = position;
	}

	/**
	 * Returns the reference figure for this locator.
	 * 
	 * @return the reference
	 */
	protected IFigure getReference() {
		return reference;
	}

	/**
	 * Sets the handle's bounds to that of its owner figure's bounds, expanded by the handle's Insets.
	 * 
	 * @param target
	 *          the target
	 */
	public void relocate(IFigure target) {
		Rectangle bounds = getReference().getBounds().getCopy();

		switch (position) {
		case PositionConstants.NORTH:
			bounds.y = bounds.y + bandHandleOffset - 1;
			bounds.height = bandHandleOffset;
			break;
		case PositionConstants.SOUTH:
			bounds.y = bounds.y + bounds.height - bandHandleOffset - 1;
			bounds.height = bandHandleOffset;
			break;
		}
		// bounds.x = bounds.x + bounds.width - bandHandleOffset - 1;
		// bounds.width = bandHandleOffset;

		getReference().translateToAbsolute(bounds);
		target.translateToRelative(bounds);
		target.setBounds(bounds);
	}

	/**
	 * Sets the reference figure.
	 * 
	 * @param follow
	 *          the new reference
	 */
	public void setReference(IFigure follow) {
		this.reference = follow;
	}

}
