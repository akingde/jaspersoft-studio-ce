/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.figure;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRLineBox;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.FileResolver;

import org.eclipse.draw2d.XYLayout;

import com.jaspersoft.studio.editor.gef.figures.FrameFigure;

public class CellFigure extends FrameFigure {
	private JRDesignCellContents cell;

	public CellFigure() {
		super();
		setLayoutManager(new XYLayout());
	}

	public void setJRElement(JRDesignCellContents jrElement, DrawVisitor drawVisitor, FileResolver fileResolver) {
		this.cell = jrElement;
		super.setJRElement(null, drawVisitor, fileResolver);
		setSize(getElementWidth(), getElementHeight());
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
		return cell.getWidth();
	}

	@Override
	protected void draw(DrawVisitor drawVisitor, JRElement jrElement) {

	}

}
