/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.texture;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;

public class EmptyTexture {
	private static final Color COLOR1 = new Color(224, 224, 224);
	private static final Color COLOR2 = new Color(255, 255, 255);

	public static TexturePaint createTexture(Color c1, Color c2) {
		int gridSize = 10;
		BufferedImage img = new BufferedImage(gridSize * 2, gridSize * 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();

		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
		g2.setComposite(ac);

		g2.setColor(c1 != null ? c1 : COLOR1);
		g2.fillRect(0, 0, 10, 10);
		g2.fillRect(10, 10, 10, 10);

		g2.setColor(c2 != null ? c2 : COLOR2);
		g2.fillRect(10, 0, 10, 10);
		g2.fillRect(0, 10, 10, 10);

		return new TexturePaint(img, new java.awt.Rectangle(0, 0, gridSize, gridSize));
	}
}
