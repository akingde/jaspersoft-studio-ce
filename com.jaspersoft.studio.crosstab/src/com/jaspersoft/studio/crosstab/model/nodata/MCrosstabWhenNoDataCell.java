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
package com.jaspersoft.studio.crosstab.model.nodata;

import net.sf.jasperreports.crosstabs.JRCellContents;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

import com.jaspersoft.studio.crosstab.model.cell.MCell;
import com.jaspersoft.studio.model.ANode;

public class MCrosstabWhenNoDataCell extends MCell {
	public MCrosstabWhenNoDataCell() {
		super();
	}

	public MCrosstabWhenNoDataCell(ANode parent, JRCellContents jfRield, int index) {
		super(parent, jfRield, Messages.MCrosstabWhenNoData_when_no_data, index);
	}

	@Override
	public Color getForeground() {
		if (getValue() == null)
			return ColorConstants.lightGray;
		return ColorConstants.black;
	}
}
