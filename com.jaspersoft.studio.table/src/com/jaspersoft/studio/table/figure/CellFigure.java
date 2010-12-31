package com.jaspersoft.studio.table.figure;

import org.eclipse.draw2d.LineBorder;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

public class CellFigure extends ComponentFigure {
	public CellFigure() {
		super();
		setBorder(new LineBorder(1));
	}

	@Override
	public void setJRElement(JRElement jrElement, DrawVisitor drawVisitor) {
		setSize(jrElement.getWidth(), jrElement.getHeight());
	}

}
