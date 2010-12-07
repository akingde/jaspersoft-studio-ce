/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.barcode.model.barcode4j;

public class BaselinePosition {
	public static String[] getItems() {
		return new String[] { "<Default>", "Top", "Bottom" };   
	}

	public static int getPos4BaselinePosition(String mode) {
		if (mode != null) {
			if (mode.equals("<Default>")) 
				return 0;
			if (mode.equals("Top")) 
				return 1;
			if (mode.equals("Bottom")) 
				return 2;
		}
		return 0;
	}

	public static String getBaselinePosition4Pos(int pos) {
		switch (pos) {
		case 0:
			return "<Default>"; 
		case 1:
			return "Top"; 
		case 2:
			return "Bottom"; 
		}
		return null;
	}
}
