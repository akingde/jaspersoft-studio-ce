/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import com.jaspersoft.studio.components.barcode.messages.Messages;

public class ChecksumMode {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", Messages.ChecksumMode_auto,
				Messages.ChecksumMode_ignore, Messages.ChecksumMode_add, Messages.ChecksumMode_check };
	}

	public static int getPos4ChecksumMode(String mode) {
		if (mode == null) // $NON-NLS-1$
			return 0;
		if (mode.equalsIgnoreCase("Auto")) //$NON-NLS-1$
			return 1;
		if (mode.equalsIgnoreCase("Ignore")) //$NON-NLS-1$
			return 2;
		if (mode.equalsIgnoreCase("Add")) //$NON-NLS-1$
			return 3;
		if (mode.equalsIgnoreCase("Check")) //$NON-NLS-1$
			return 4;
		return 0;
	}

	public static String getChecksumMode4Pos(int pos) {
		switch (pos) {
		case 0:
			return null; // $NON-NLS-1$
		case 1:
			return "auto"; //$NON-NLS-1$
		case 2:
			return "ignore"; //$NON-NLS-1$
		case 3:
			return "add"; //$NON-NLS-1$
		case 4:
			return "check"; //$NON-NLS-1$
		}
		return null;
	}
}
