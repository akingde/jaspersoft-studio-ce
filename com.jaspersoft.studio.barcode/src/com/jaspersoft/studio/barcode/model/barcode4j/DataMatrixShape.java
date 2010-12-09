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

public class DataMatrixShape {
	public static String[] getItems() {
		return new String[] { "<Default>", "Force None", "Force Square", "Force Rectangle" };
	}

	public static int getPos4Shape(String pos4shape) {
		if (pos4shape != null) {
			if (pos4shape.equals(""))
				return 0;
			if (pos4shape.equals("force-none"))
				return 1;
			if (pos4shape.equals("force-square"))
				return 2;
			if (pos4shape.equals("force-rectangle"))
				return 3;
		}
		return 0;
	}

	public static String getShape4Pos(int pos) {
		switch (pos) {
		case 0:
			return "";
		case 1:
			return "force-none";
		case 2:
			return "force-square";
		case 3:
			return "force-rectangle";
		}
		return "";
	}
}
