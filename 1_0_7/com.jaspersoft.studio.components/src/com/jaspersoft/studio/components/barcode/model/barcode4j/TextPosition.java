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

import com.jaspersoft.studio.components.barcode.messages.Messages;

public class TextPosition {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", Messages.TextPosition_none, Messages.common_bottom, Messages.common_top };
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
