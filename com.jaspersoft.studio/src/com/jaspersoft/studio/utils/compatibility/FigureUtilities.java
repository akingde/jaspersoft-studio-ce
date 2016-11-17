/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.compatibility;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * This class "adds" the {@link org.eclipse.draw2d.FigureUtilities} missing methods in 3.5 and 3.6 Eclipse.
 * <p>
 * Because we still give support for 3.5 and 3.6, we need to ensure back-compatibility.
 * 
 * TODO - Replace the use of this class with the {@link org.eclipse.gef.SharedImages}
 * when support for 3.5 and 3.6 will be dropped.
 *
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class FigureUtilities {

	/**
	 * Determines whether the given figure is showing and not (completely)
	 * clipped.
	 * 
	 * @param figure
	 *            The figure to test
	 * @return <code>true</code> if the given figure is showing and not
	 *         completely clipped, <code>false</code> otherwise.
	 */
	public static boolean isNotFullyClipped(IFigure figure) {
		if (figure == null || !figure.isShowing()) {
			return false;
		}
		// check if figure is clipped
		// TODO: IClippingStrategy has to be taken into consideration as well.
		Rectangle figBounds = figure.getBounds().getCopy();
		IFigure walker = figure.getParent();
		while (!figBounds.isEmpty() && walker != null) {
			walker.translateToParent(figBounds);
			figBounds.intersect(walker.getBounds());
			walker = walker.getParent();
		}
		return !figBounds.isEmpty();
	}
}
