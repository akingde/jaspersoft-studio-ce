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
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import net.sf.jasperreports.components.barcode4j.BarcodeComponent;

import com.jaspersoft.studio.components.barcode.messages.Messages;

public class Orientation {
	public static String[] getItems() {
		return new String[] { Messages.Orientation_up, Messages.Orientation_left, Messages.Orientation_down, Messages.Orientation_right };
	}

	public static int getPos4Orientation(int orientation) {
		switch (orientation) {
		case BarcodeComponent.ORIENTATION_UP:
			return 0;
		case BarcodeComponent.ORIENTATION_LEFT:
			return 1;
		case BarcodeComponent.ORIENTATION_DOWN:
			return 2;
		case BarcodeComponent.ORIENTATION_RIGHT:
			return 3;
		}
		return -1;
	}

	public static int getOrientation4Pos(int pos) {
		switch (pos) {
		case 0:
			return BarcodeComponent.ORIENTATION_UP;
		case 1:
			return BarcodeComponent.ORIENTATION_LEFT;
		case 2:
			return BarcodeComponent.ORIENTATION_DOWN;
		case 3:
			return BarcodeComponent.ORIENTATION_RIGHT;
		}
		return 0;
	}
}
