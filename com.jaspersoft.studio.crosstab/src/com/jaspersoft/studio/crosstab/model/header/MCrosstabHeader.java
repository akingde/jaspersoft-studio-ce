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
package com.jaspersoft.studio.crosstab.model.header;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.crosstab.model.MCell;
import com.jaspersoft.studio.crosstab.model.MCrosstab;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public class MCrosstabHeader extends MCell {
	public MCrosstabHeader() {
		super();
	}

	public MCrosstabHeader(ANode parent, JRCellContents jfRield) {
		super(parent, jfRield, "Crosstab Header");
	}

	@Override
	public Color getForeground() {
		if (getValue() == null)
			return ColorConstants.lightGray;
		return ColorConstants.black;
	}

	@Override
	public Rectangle getBounds() {
		int w = 0;
		int h = 0;
		Rectangle rect = null;
		if (getValue() != null) {
			JRDesignCellContents c = (JRDesignCellContents) getValue();
			w = c.getWidth();
			h = c.getHeight();
		}

		INode node = getParent();
		while (node != null) {
			if (node instanceof MCrosstab) {
				Rectangle b = ((MCrosstab) node).getBounds();
				return new Rectangle(b.x, b.y, w, h);
			}
			node = node.getParent();
		}

		return rect;
	}

}
