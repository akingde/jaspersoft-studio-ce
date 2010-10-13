package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.design.JRDesignGenericElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

/**
 * @author Chicu Veaceslav
 * 
 */
public class GenericElementFigure extends ComponentFigure {
	/**
	 * Instantiates a crosstab figure.
	 */
	public GenericElementFigure() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.editor.gef.figures.ComponentFigure#draw(net.sf.jasperreports.engine.export.draw.DrawVisitor,
	 * net.sf.jasperreports.engine.JRElement)
	 */
	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {
		drawVisitor.visitGenericElement((JRDesignGenericElement) jrElement);
	}
}
