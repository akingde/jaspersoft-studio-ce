/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.figure;

import java.awt.Font;
import java.awt.Graphics2D;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.figures.layers.GridLayer;
import com.jaspersoft.studio.editor.gef.util.FigureTextWriter;

public class WhenNoDataCellFigure extends CellFigure {
	private static final String HINT = "If the crosstab will not contain any data, this cell will be printed instead.";
	private FigureTextWriter twriter = new FigureTextWriter();

	public WhenNoDataCellFigure() {
		super();
		twriter.setText("When No Data Cell");
	}

	@Override
	public void paint(Graphics graphics) {
		Rectangle b = getHandleBounds();
		graphics.setBackgroundColor(ColorConstants.white);
		graphics.fillRectangle(b.x, b.y, b.width, b.height);

		super.paint(graphics);
		Graphics2D g = ComponentFigure.getG2D(graphics);
		if (g != null) {
			twriter.painText(g, this);

			Font currfont = g.getFont();
			g.setFont(currfont.deriveFont(16f));

			java.awt.Color currColor = g.getColor();
			g.setColor(java.awt.Color.GRAY);
			g.drawString(HINT, b.x, b.y - 15);
			g.setColor(currColor);

			g.setFont(currfont);
		}
		if (getParent() instanceof APageFigure) {
			GridLayer grid = ((APageFigure) getParent()).getGrid();
			if (grid.isVisible()) {
				grid.setBounds(b);
				grid.paint(graphics);
			}
		}
		if (getBorder() != null)
			getBorder().paint(this, graphics, APageFigure.PAGE_BORDER);
	}

	@Override
	public Rectangle getHandleBounds() {
		Rectangle r = super.getHandleBounds();
		r.x += APageFigure.PAGE_BORDER.left;
		r.y += 30;
		return r;
	}

	/**
	 * Enables/disables the showing of the band name in background.
	 * 
	 * @param showBandName
	 *            flag for band name showing.
	 */
	public void setShowBandName(boolean showBandName) {
		twriter.setShowName(showBandName);
	}

	/**
	 * Sets a human-readable text that will be painted in the band background.
	 * Usually it is the band name.
	 * <p>
	 * 
	 * <b>NOTE</b>: the text will be drawn only if the related property
	 * <i>"Show Band names"</i> from the preference page <i>Jaspersoft
	 * Studio-&gt;Report Designer</i> is enabled.
	 * 
	 * @param bandText
	 *            the band text
	 */
	public void setBandText(String bandText) {
		twriter.setText(bandText);
	}

	@Override
	protected void paintBorder(Graphics graphics) {
	}

}
