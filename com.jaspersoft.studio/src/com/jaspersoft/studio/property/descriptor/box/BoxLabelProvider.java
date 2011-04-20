/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.box;

import java.awt.Color;

import net.sf.jasperreports.engine.JRPen;

import org.eclipse.jface.viewers.LabelProvider;

import com.jaspersoft.studio.model.MLinePen;
import com.jaspersoft.studio.property.descriptor.NullEnum;
/*
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
				lineColor = "RGB (" + rgb.getRed() + "," + rgb.getGreen() + "," + rgb.getBlue() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			return "[" + lineStyle + "," + lineWidth + "," + lineColor + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		return ""; //$NON-NLS-1$
	}

}
