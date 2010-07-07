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
package com.jaspersoft.studio.property.descriptor.color;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.property.descriptor.NullEnum;

/**
 * @author Chicu Veaceslav
 * 
 */
public class ColorLabelProvider extends LabelProvider {
	private NullEnum canBeNull;

	public ColorLabelProvider(NullEnum canBeNull) {
		super();
		this.canBeNull = canBeNull;
	}

	@Override
	public Image getImage(Object element) {
		if (element != null && element instanceof RGB) {
			RGB rgb = (RGB) element;
			RGB black = new RGB(0, 0, 0);
			PaletteData dataPalette = new PaletteData(new RGB[] { black, black, rgb });
			Display display = Display.getCurrent();
			ImageData data = new ImageData(16, 16, 4, dataPalette);
			data.transparentPixel = 0;
			int pixel = dataPalette.getPixel(rgb);
			data.transparentPixel = 0;
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					if (x == 0 || y == 0 || x == data.width - 1 || y == data.height - 1)
						data.setPixel(x, y, 1);
					else
						data.setPixel(x, y, 2);
				}
			}
			return new Image(display, data);
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		if (element == null || !(element instanceof RGB))
			return canBeNull.getName();
		RGB rgb = (RGB) element;
		return "RGB (" + rgb.red + "," + rgb.green + "," + rgb.blue + ")";
	}

}
