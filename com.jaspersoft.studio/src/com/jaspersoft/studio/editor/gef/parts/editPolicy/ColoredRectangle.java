/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.editPolicy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

/**
 * Rectangle figure with colored border, used show a color feedback on some 
 * elements
 * @author Orlandin Marco
 *
 */
public class ColoredRectangle extends RectangleFigure{
	
	protected Color borderColor;
	
	protected float borderWidth;
	
	public ColoredRectangle(Color borderColor, float borderWidth){
		this.borderColor = borderColor;
		this.borderWidth = borderWidth;
	}
	
	protected void outlineShape(Graphics graphics) {
		Graphics2D g = ComponentFigure.getG2D(graphics);
		float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
		int inset1 = (int) Math.floor(lineInset);
		int inset2 = (int) Math.ceil(lineInset);

		Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
		r.x += inset1;
		r.y += inset1;
		r.width -= inset1 + inset2;
		r.height -= inset1 + inset2;
		g.setStroke(new BasicStroke(borderWidth));
		g.setColor(borderColor);
		g.drawRect(r.x,r.y,r.width,r.height);
	}

}
