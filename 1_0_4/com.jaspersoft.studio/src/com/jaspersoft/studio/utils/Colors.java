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
import org.eclipse.swt.graphics.RGB;
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
}
