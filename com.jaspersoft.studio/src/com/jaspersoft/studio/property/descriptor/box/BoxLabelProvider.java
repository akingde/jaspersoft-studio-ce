/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.box;

import java.awt.Color;

import net.sf.jasperreports.engine.JRPen;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.descriptor.NullEnum;

/**
 * @author Chicu Veaceslav
 * 
 */
public class BoxLabelProvider extends LabelProvider {

	public BoxLabelProvider() {
		super();
	}

	@Override
	public String getText(Object element) {
		if (element != null && element instanceof MLinePen) {
			JRPen pen = (JRPen) ((MLinePen) element).getValue();
			String lineStyle = pen.getOwnLineStyleValue() != null ? pen.getOwnLineStyleValue().getName() : NullEnum.INHERITED
					.getName();
			String lineWidth = pen.getOwnLineWidth() != null ? pen.getOwnLineWidth().toString() : NullEnum.INHERITED
					.getName();
			Color rgb = pen.getOwnLineColor();
			String lineColor = NullEnum.INHERITED.getName();
			if (rgb != null)
				lineColor = "RGB (" + rgb.getRed() + "," + rgb.getGreen() + "," + rgb.getBlue() + ")";
			return "[" + lineStyle + "," + lineWidth + "," + lineColor + "]";
		}
		return "";
	}

}
