/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import java.awt.Graphics2D;

import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.java2d.ImageGraphics2D;
import com.jaspersoft.studio.jasper.JSSDrawVisitor;
import com.jaspersoft.studio.jasper.LazyImageConverter;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.image.MImage;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintElement;
/*
 * The Class ChartFigure.
 */
public class ImageFigure extends FrameFigure {

	
	/**
	 * Instantiates a new ImageFigure.
	 */
	public ImageFigure(MImage imageModel) {
		super(imageModel);
	}

	protected void visitElement(JSSDrawVisitor drawVisitor, MGraphicElement model) {
		JRElement element = model.getValue();
		JRPrintElement printElement = LazyImageConverter.getInstance().convertImage(drawVisitor.getReportConverter(), model);
		try {
			printElement.accept(drawVisitor.getDrawVisitor(), JSSDrawVisitor.elementOffset(element));
		} catch (Throwable t) {
			t.printStackTrace();
		}
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
		Graphics2D oldGraphics = drawVisitor.getGraphics2d();
		if (needRefresh(oldGraphics)){
			cachedGraphics = getCachedGraphics(oldGraphics);
			drawVisitor.setGraphics2D(cachedGraphics);
			visitElement(drawVisitor, model);
			drawVisitor.setGraphics2D(oldGraphics);
			model.setChangedProperty(false);
		} else {
			//If the image dosen't need to be reloaded than a recheck is launched to see if it was updated
			LazyImageConverter.getInstance().convertImage(drawVisitor.getReportConverter(), model);
		}
		cachedGraphics.setGraphics(drawVisitor.getGraphics2d());
		cachedGraphics.paintCache();
	}

	protected JRPen getLinePen() {
		return ((JRImage) getJrElement()).getLinePen();
	}

	protected Rectangle calcBorder(JRLineBox jrLineBox) {
		int x = (int) Math.ceil(jrLineBox.getLeftPen().getLineWidth() / 2);
		int y = (int) Math.ceil(jrLineBox.getTopPen().getLineWidth() / 2);
		int w = (int) Math.ceil(jrLineBox.getRightPen().getLineWidth() / 2);
		int h = (int) Math.ceil(jrLineBox.getBottomPen().getLineWidth() / 2);

		if (x == 0 && y == 0 && w == 0 && h == 0) {
			JRPen jrPen = getLinePen();
			x = (int) Math.ceil(jrPen.getLineWidth() / 2);
			y = x;
			w = x;
			h = x;
		}
		return new Rectangle(x, y, w, h);
	}

	/**
	 * Need to use the image cache, otherwise the SWG images will not be 
	 * correctly rendered
	 */
	protected ACachedGraphics getCachedGraphics(Graphics2D originalGraphics){
		return new ImageGraphics2D(originalGraphics);
	}
}
