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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.utils;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
/*/*
 * The Class Colors.
 * 
 * @author Chicu Veaceslav
 */
public class Colors {
	
	/**
	 * Gets the sW t4 awt color.
	 * 
	 * @param color
	 *          the color
	 * @return the sW t4 awt color
	 */
	public static Color getSWT4AWTColor(java.awt.Color color) {
		if (color != null)
			return new Color(null, color.getRed(), color.getGreen(), color.getBlue());
		return null;
	}

	/**
	 * Gets the aW t4 swt color.
	 * 
	 * @param color
	 *          the color
	 * @return the aW t4 swt color
	 */
	public static java.awt.Color getAWT4SWTColor(Color color) {
		if (color != null)
			return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
		return null;
	}

	/**
	 * Gets the sWTRG b4 awtgb color.
	 * 
	 * @param color
	 *          the color
	 * @return the sWTRG b4 awtgb color
	 */
	public static RGB getSWTRGB4AWTGBColor(java.awt.Color color) {
		if (color != null)
			return new RGB(color.getRed(), color.getGreen(), color.getBlue());
		return null;
	}

	/**
	 * Gets the aW t4 swtrgb color.
	 * 
	 * @param color
	 *          the color
	 * @return the aW t4 swtrgb color
	 */
	public static java.awt.Color getAWT4SWTRGBColor(RGB color) {
		if (color != null)
			return new java.awt.Color(color.red, color.green, color.blue);
		return null;
	}
	
	/**
	 * Gets the SWT RGB color derived from an input AWT color.
	 * 
	 * @param color the AWT color to convert
	 * @return a converted {@link RGB} color instance, <code>null</code> otherwise
	 */
	public static RGB getRGB4AWTColor(java.awt.Color color){
		if (color!=null){
			return new RGB(color.getRed(),color.getGreen(),color.getBlue());
		}
		return null;
	}
	
	/**
	 * Gets the encoded value of a {@link java.awt.Color} instance.<br>
	 * The output is the hexadecimal conversion (i.e: #00FFFF).
	 *  
	 * @param awtColor the color to encode
	 * @return color value as encoded string
	 */
	public static String getHexEncodedAWTColor(java.awt.Color awtColor) {
		if (awtColor==null){
			return ""; //$NON-NLS-1$
		}
		String nums = "0123456789ABCDEF"; //$NON-NLS-1$
		String s = "#"; //$NON-NLS-1$
		s += nums.charAt(awtColor.getRed() / 16);
		s += nums.charAt(awtColor.getRed() % 16);
		s += nums.charAt(awtColor.getGreen() / 16);
		s += nums.charAt(awtColor.getGreen() % 16);
		s += nums.charAt(awtColor.getBlue() / 16);
		s += nums.charAt(awtColor.getBlue() % 16);
		return s;
	}
	
	/**
	 * Gets the encoded value of a {@link Color} instance.<br>
	 * The output is the hexadecimal conversion (i.e: #00FFFF).
	 * 
	 * @param swtColor the color to encode
	 * @return color value as encoded string
	 */
	public static String getHexEncodedSWTColor(Color swtColor){
		if(swtColor==null){
			return ""; //$NON-NLS-1$
		}
		return getHexEncodedAWTColor(getAWT4SWTColor(swtColor));
	}
	
	/**
	 * Gets the encoded value of a {@link RGB} instance.<br>
	 * The output is the hexadecimal conversion (i.e: #00FFFF).
	 * 
	 * @param rgbColor the color to encode
	 * @return color value as encoded string
	 */
	public static String getHexEncodedRGBColor(RGB rgbColor){
		if(rgbColor==null){
			return ""; //$NON-NLS-1$
		}
		return getHexEncodedAWTColor(getAWT4SWTRGBColor(rgbColor));
	}	
	
	/**
	 * Gets an SWT image representing a preview of the specified AWT color.
	 * The output image has size according to the specified width and height in pixels.
	 * <p>
	 * When no color is provided, a "empty" image with a grid-like pattern is returned.<br>
	 * 
	 * @param color the AWT color instance
	 * @param width width pixels 
	 * @param height height pixels
	 * @return an SWT image preview of the specified color
	 */
	public static Image getSWTColorPreview(java.awt.Color color, int width, int height){
		RGB black=new RGB(0, 0, 0);
		RGB white=new RGB(255,255,255);
		
		ImageData data=null;	
		if (color==null){
			PaletteData dataPalette = new PaletteData(new RGB[] { black, black, white});
			data = new ImageData(width, height, 4, dataPalette);
			data.transparentPixel = 0;
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					if (y==0 || y==data.height-1 || x==0 || x==data.width-1){
						// Always draw a black border
						data.setPixel(x, y, 1);
					}
					else if (y%3==0 || x%3==0){
						data.setPixel(x, y, 1);
					}
					else {
						data.setPixel(x, y, 2);
					}
				}
			}
		}
		else {
			RGB rgb = getSWTRGB4AWTGBColor(color);
			PaletteData dataPalette = new PaletteData(new RGB[] { black, black, rgb });
			data = new ImageData(width, height, 4, dataPalette);
			data.transparentPixel = 0;
			for (int y = 0; y < data.height; y++) {
				for (int x = 0; x < data.width; x++) {
					if (x == 0 || y == 0 || x == data.width - 1 || y == data.height - 1)
						data.setPixel(x, y, 1);
					else
						data.setPixel(x, y, 2);
				}
			}		
		}

		Image image = new Image(Display.getCurrent(), data);		
		return image;
	}

	/**
	 * Gets the AWT color for the specified color string.
	 * The input string should be an integer number representing the
	 * rgb int value in 16 bit.
	 * 
	 * @param colorString the rgb int value as string
	 * @return the AWT color instance converted, <code>null</code> if operation fails
	 */
	public static java.awt.Color decodeColor(String colorString) {
		java.awt.Color color = null;
		if (colorString.length() > 0) {
			try {
				color = new java.awt.Color(Integer.parseInt(colorString, 16));
			} catch (Exception ex) {
			}
		}
		return color;
	}
	
	/**
	 * Encodes the AWT color specified as HEX value (16 bit) representation.
	 * It does not include in the final output the # character commonly used
	 * in UI elements (i.e: text-boxes).
	 * 
	 * @param awtColor the color instance to encode
	 * @return the encode string
	 */
	public static String getEncodedColor(java.awt.Color awtColor){
		String s = getHexEncodedAWTColor(awtColor);
		if(s!=null && !s.isEmpty())
			// remove the # char
			return s.substring(1);
		else 
			return s;
	}
}
