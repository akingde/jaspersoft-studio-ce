/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.crosstab.figure;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;

import com.jaspersoft.studio.editor.gef.figures.FrameFigure;

public class CellFigure extends FrameFigure {
	private JRDesignCellContents cell;

	public CellFigure() {
		super();
	}

	public void setJRElement(JRDesignCellContents jrElement,
			DrawVisitor drawVisitor) {
		this.cell = jrElement;
		super.setJRElement(null, drawVisitor);
		setSize(getElementWidth(), getElementHeight());
	}

	@Override
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
		return cell.getWidth();
	}

	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {

	}

}
