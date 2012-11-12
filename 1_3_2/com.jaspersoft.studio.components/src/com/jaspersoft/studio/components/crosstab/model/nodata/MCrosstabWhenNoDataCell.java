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
package com.jaspersoft.studio.components.crosstab.model.nodata;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.model.ANode;

public class MCrosstabWhenNoDataCell extends MCell {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MCrosstabWhenNoDataCell() {
		super();
	}

	public MCrosstabWhenNoDataCell(ANode parent, JRCellContents jfRield,
			int index) {
		super(parent, jfRield,
				Messages.MCrosstabWhenNoDataCell_when_no_data_cell, index);
	}

	@Override
	public Color getForeground() {
		if (getValue() == null)
			return ColorConstants.lightGray;
		return ColorConstants.black;
	}

	@Override
	public Rectangle getBounds() {
		MCrosstab mc = getMCrosstab();
		if (mc != null) {
			Dimension d = mc.getCrosstabManager().getSize();

			return new Rectangle(0, d.height + 4 * APageFigure.PAGE_BORDER.top,
					getValue().getWidth() + 2, getValue().getHeight() + 2);
		}
		return null;
	}
}
