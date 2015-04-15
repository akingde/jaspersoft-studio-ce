/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
