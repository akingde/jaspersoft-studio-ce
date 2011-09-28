package com.jaspersoft.studio.editor.gef.figures;

import net.sf.jasperreports.engine.JRComponentElement;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

public class JRComponentFigure extends FrameFigure {
	public JRComponentFigure() {
		super();
	}

	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {
		drawVisitor.visitComponentElement((JRComponentElement) jrElement);
	}
}
