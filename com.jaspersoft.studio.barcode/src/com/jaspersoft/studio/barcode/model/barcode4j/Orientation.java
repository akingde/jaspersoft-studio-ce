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

import com.jaspersoft.studio.barcode.messages.Messages;

import net.sf.jasperreports.components.barcode4j.BarcodeComponent;

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
