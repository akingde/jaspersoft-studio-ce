/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model;

import net.sf.jasperreports.engine.type.HorizontalPosition;

import com.jaspersoft.studio.components.barcode.messages.Messages;

public class HorizontalPositionUtil {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", com.jaspersoft.studio.messages.Messages.common_left, com.jaspersoft.studio.messages.Messages.ExcelExporterPreferencePage_50,
				com.jaspersoft.studio.messages.Messages.common_right };
	}

	public static int getPos4TextPosition(HorizontalPosition textPosition) {
		if (textPosition == null)
			return 0;
		if (textPosition == HorizontalPosition.LEFT)
			return 1;
		if (textPosition == HorizontalPosition.CENTER)
			return 2;
		if (textPosition == HorizontalPosition.RIGHT)
			return 3;
		return 0;
	}

	public static HorizontalPosition getTextPosition4Pos(int pos) {
		switch (pos) {
		case 0:
			return null;
		case 1:
			return HorizontalPosition.LEFT;
		case 2:
			return HorizontalPosition.CENTER;
		case 3:
			return HorizontalPosition.RIGHT;
		}
		return null;
	}
}
