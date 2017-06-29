/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 
 * @author sanda zaharia
 * 
 */
public class CalloutFigure extends RectangleFigure {
	private String[] text;

	/**
	 * Instantiates a new map figure.
	 */
	public CalloutFigure() {
		super();
		setOpaque(true);
		setBackgroundColor(ColorConstants.yellow);
		setForegroundColor(ColorConstants.black);
		setBorder(new LineBorder(ColorConstants.orange, 1));
	}

	public void setText(String text) {
		if (text == null)
			text = "";
		this.text = text.split("\n");
	}

	@Override
	protected void fillShape(Graphics graphics) {
		super.fillShape(graphics);

		int h = graphics.getFontMetrics().getHeight();

		Rectangle r = getBounds();
		if (text != null) {
			graphics.setForegroundColor(getForegroundColor());
			int y = r.y + 2;
			int x = r.x + 2;
			for (String txt : text) {
				graphics.drawText(txt, x, y);
				y += h + 3;
			}
		}
	}
}
