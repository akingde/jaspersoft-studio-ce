/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.parts.handles;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;

/**
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
