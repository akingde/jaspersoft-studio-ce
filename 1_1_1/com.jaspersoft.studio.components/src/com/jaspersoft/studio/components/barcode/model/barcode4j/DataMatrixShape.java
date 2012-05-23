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

public class DataMatrixShape {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", Messages.DataMatrixShape_force_none, Messages.DataMatrixShape_force_square, Messages.DataMatrixShape_force_rectangle };
	}

	public static int getPos4Shape(String pos4shape) {
		if (pos4shape != null) {
			if (pos4shape.equals("")) //$NON-NLS-1$
				return 0;
			if (pos4shape.equals("force-none")) //$NON-NLS-1$
				return 1;
			if (pos4shape.equals("force-square")) //$NON-NLS-1$
				return 2;
			if (pos4shape.equals("force-rectangle")) //$NON-NLS-1$
				return 3;
		}
		return 0;
	}

	public static String getShape4Pos(int pos) {
		switch (pos) {
		case 0:
			return ""; //$NON-NLS-1$
		case 1:
			return "force-none"; //$NON-NLS-1$
		case 2:
			return "force-square"; //$NON-NLS-1$
		case 3:
			return "force-rectangle"; //$NON-NLS-1$
		}
		return ""; //$NON-NLS-1$
	}
}
