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
package com.jaspersoft.studio.barcode.model.barcode4j;

public class TextPosition {
	public static String[] getItems() {
		return new String[] { Messages.TextPosition_default, Messages.TextPosition_none, Messages.TextPosition_bottom, Messages.TextPosition_top };
	}

	public static int getPos4TextPosition(String textPosition) {
		if (textPosition != null) {
			if (textPosition.equals("")) //$NON-NLS-1$
				return 0;
			if (textPosition.equals("none")) //$NON-NLS-1$
				return 1;
			if (textPosition.equals("bottom")) //$NON-NLS-1$
				return 2;
			if (textPosition.equals("top")) //$NON-NLS-1$
				return 3;
		}
		return 0;
	}

	public static String getTextPosition4Pos(int pos) {
		switch (pos) {
		case 0:
			return ""; //$NON-NLS-1$
		case 1:
			return "none"; //$NON-NLS-1$
		case 2:
			return "bottom"; //$NON-NLS-1$
		case 3:
			return "top"; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
}
