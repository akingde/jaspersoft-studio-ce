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

public class ChecksumMode {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", Messages.ChecksumMode_auto, Messages.ChecksumMode_ignore, Messages.ChecksumMode_add, Messages.ChecksumMode_check };
	}

	public static int getPos4ChecksumMode(String mode) {
		if (mode != null) {
			if (mode.equals("<default>")) //$NON-NLS-1$
				return 0;
			if (mode.equals("Auto")) //$NON-NLS-1$
				return 1;
			if (mode.equals("Ignore")) //$NON-NLS-1$
				return 2;
			if (mode.equals("Add")) //$NON-NLS-1$
				return 3;
			if (mode.equals("Check")) //$NON-NLS-1$
				return 4;
		}
		return 0;
	}

	public static String getChecksumMode4Pos(int pos) {
		switch (pos) {
		case 0:
			return "<default>"; //$NON-NLS-1$
		case 1:
			return "Auto"; //$NON-NLS-1$
		case 2:
			return "Ignore"; //$NON-NLS-1$
		case 3:
			return "Add"; //$NON-NLS-1$
		case 4:
			return "Check"; //$NON-NLS-1$
		}
		return null;
	}
}
