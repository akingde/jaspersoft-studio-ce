/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.color;

import java.awt.Color;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.Colors;

/*
 * @author Chicu Veaceslav
 */
public class ColorLabelProvider extends LabelProvider {
	private NullEnum canBeNull;

	public ColorLabelProvider(NullEnum canBeNull) {
		super();
		this.canBeNull = canBeNull;
	}

	@Override
	public Image getImage(Object element) {
		if (element == null)
			return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/nocolor.png");
		if (element instanceof RGB) {
			RGB rgb = (RGB) element;
			RGB black = new RGB(0, 0, 0);
			PaletteData dataPalette = new PaletteData(new RGB[] { black, black, rgb });
			Display display = Display.getCurrent();
			ImageData data = new ImageData(16, 16, 4, dataPalette);
			data.transparentPixel = 0;
			// int pixel = dataPalette.getPixel(rgb);
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

	public Image getImage(Object element, int width, int height) {
		if (element == null)
			return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/nocolor.png");
		if (element instanceof RGB) {
			RGB rgb = (RGB) element;
			RGB black = new RGB(0, 0, 0);
			PaletteData dataPalette = new PaletteData(new RGB[] { black, black, rgb });
			Display display = Display.getCurrent();
			ImageData data = new ImageData(width, height, 4, dataPalette);
			data.transparentPixel = 0;
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
		if (element == null)
			return canBeNull.getName();
		if (element instanceof Color)
			element = Colors.getRGB4AWTColor((Color) element);
		if (element instanceof RGB) {
			RGB rgb = (RGB) element;
			return "RGB (" + rgb.red + "," + rgb.green + "," + rgb.blue + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		return canBeNull.getName();
	}

}
