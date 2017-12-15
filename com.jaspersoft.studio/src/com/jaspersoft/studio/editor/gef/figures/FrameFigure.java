/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.ImageGraphics2D;
import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.model.MGraphicElement;

import net.sf.jasperreports.engine.JRBoxContainer;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRFrame;
import net.sf.jasperreports.engine.JRLineBox;

/*
 * The Class FrameFigure.
 * 
 * @author Chicu Veaceslav
 */
public class FrameFigure extends AHandleBoundsFigure implements IModelFigure {
	/**
	 * Instantiates a new text field figure.
	 */
	public FrameFigure(MGraphicElement frameModel) {
		super(frameModel);
		setLayoutManager(new XYLayout());
	}

	
	public FrameFigure(){
		super(null);
		setLayoutManager(new XYLayout());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.GenericFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(JSSDrawVisitor drawVisitor, JRElement jrElement) {
		if (model == null || !allowsFigureDrawCache()){
			drawVisitor.visitFrame((JRFrame) jrElement);
			return;
		} else {
			Graphics2D oldGraphics = drawVisitor.getGraphics2d();
			if (needRefresh(oldGraphics)){
				model.setChangedProperty(false);	
				cachedGraphics = getCachedGraphics(oldGraphics);
				drawVisitor.setGraphics2D(cachedGraphics);
				drawVisitor.visitFrame((JRFrame) jrElement);
				drawVisitor.setGraphics2D(oldGraphics);
			}
		}
		cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
		cachedGraphics.paintCache();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.GenericFigure#setJRElement(net.sf.jasperreports.engine.JRElement,
	 * net.sf.jasperreports.engine.export.draw.DrawVisitor)
	 */
	@Override
	public void setJRElement(JRElement jrElement, JSSDrawVisitor drawVisitor) {
		super.setJRElement(jrElement, drawVisitor);
		// set bounds + 1/2 border
		Rectangle o = calcBorder(getLineBox());
		Rectangle b = getBounds();
		setBounds(new Rectangle(b.x - o.x, b.y - o.y, b.width + o.x + o.width + 1, b.height + o.y + o.height + 1));
	}

	/**
	 * Gets the line box.
	 * 
	 * @return the line box
	 */
	protected JRLineBox getLineBox() {
		JRElement jrElement = getJrElement();
		JRLineBox box = null;
		if (jrElement != null) {
			if (jrElement instanceof JRBoxContainer)
				box = ((JRBoxContainer) jrElement).getLineBox();
			if (box == null && jrElement.getStyle() != null)
				box = jrElement.getStyle().getLineBox();
		} else {
			box = null;
		}
		return box;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.editor.gef.figures.GenericFigure#getHandleBounds()
	 */
	@Override
	public Rectangle getHandleBounds() {
		Rectangle b = getBounds();
		Rectangle o = calcBorder(getLineBox());
		return new Rectangle(b.x + o.x, b.y + o.y, getElementWidth() + 1, getElementHeight() + 1);
	}

	protected int getElementWidth() {
		return getJrElement() != null ? getJrElement().getWidth() : 0;
	}

	protected int getElementHeight() {
		return getJrElement() != null ? getJrElement().getHeight() : 0;
	}

	/**
	 * Calc border.
	 * 
	 * @param jrLineBox
	 *          the jr line box
	 * @return the rectangle
	 */
	protected Rectangle calcBorder(JRLineBox jrLineBox) {
		if (jrLineBox == null)
			return new Rectangle(0, 0, 0, 0);
		int x = (int) Math.ceil(jrLineBox.getLeftPen().getLineWidth() / 2);
		int y = (int) Math.ceil(jrLineBox.getTopPen().getLineWidth() / 2);
		int w = (int) Math.ceil(jrLineBox.getRightPen().getLineWidth() / 2) + 1;
		int h = (int) Math.ceil(jrLineBox.getBottomPen().getLineWidth() / 2) + 1;
		return new Rectangle(x, y, w, h);
	}
	
	protected ACachedGraphics getCachedGraphics(Graphics2D originalGraphics){
		return new ImageGraphics2D(originalGraphics);
	}
}
