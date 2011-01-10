package com.jaspersoft.studio.table.figure;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import org.eclipse.draw2d.LineBorder;

import com.jaspersoft.studio.editor.gef.figures.FrameFigure;

public class CellFigure extends FrameFigure {
	private DesignCell cell;
	private StandardBaseColumn column;

	public CellFigure() {
		super();
		setBorder(new LineBorder(1));
	}

	public void setJRElement(DesignCell cell, StandardBaseColumn column, DrawVisitor drawVisitor) {
		this.cell = cell;
		this.column = column;
		super.setJRElement(null, drawVisitor);
		setSize(getElementWidth() + 3, getElementHeight() + 3);
	}

	protected JRLineBox getLineBox() {
		JRLineBox box = null;
		box = cell.getLineBox();
		if (box == null && cell.getStyle() != null)
			box = cell.getStyle().getLineBox();

		return box;
	}

	@Override
	protected int getElementHeight() {
		return cell.getHeight();
	}

	@Override
	protected int getElementWidth() {
		return column.getWidth();
	}

	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {

	}
}
