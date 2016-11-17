/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.pin;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

public class PinFigure extends Figure {

	private static BufferedImage bimage;

	public PinFigure() {
		if (bimage == null)
			try {
				String fileLocation = JaspersoftStudioPlugin.getInstance().getFileLocation("icons/pin-16.png");
				bimage = ImageIO.read(new File(fileLocation));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	protected void paintFigure(Graphics graphics) {
		super.paintFigure(graphics);
		Graphics2D g = ComponentFigure.getG2D(graphics);
		if (g != null) {
			Rectangle r = getBounds();
			if (bimage != null)
				g.drawImage(bimage, r.x, r.y, null);
			else {
				g.drawOval(r.x, r.y, 16, 16);
			}
		}
	}

}
