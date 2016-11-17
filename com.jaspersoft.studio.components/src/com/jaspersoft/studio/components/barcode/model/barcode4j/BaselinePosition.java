/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import com.jaspersoft.studio.components.barcode.messages.Messages;

public class BaselinePosition {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", Messages.common_top, Messages.common_bottom };
	}

	public static int getPos4BaselinePosition(String mode) {
		if (mode == null) // $NON-NLS-1$
			return 0;
		if (mode.equals("Top")) //$NON-NLS-1$
			return 1;
		if (mode.equals("Bottom")) //$NON-NLS-1$
			return 2;
		return 0;
	}

	public static String getBaselinePosition4Pos(int pos) {
		switch (pos) {
		case 0:
			return null; // $NON-NLS-1$
		case 1:
			return "Top"; //$NON-NLS-1$
		case 2:
			return "Bottom"; //$NON-NLS-1$
		}
		return null;
	}
}
