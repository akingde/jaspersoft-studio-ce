/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import com.jaspersoft.studio.components.barcode.messages.Messages;

public class DataMatrixShape {
	public static String[] getItems() {
		return new String[] { "<" + Messages.common_default + ">", Messages.DataMatrixShape_force_none,
				Messages.DataMatrixShape_force_square, Messages.DataMatrixShape_force_rectangle };
	}

	public static int getPos4Shape(String pos4shape) {
		if (pos4shape == null) // $NON-NLS-1$
			return 0;
		if (pos4shape.equals("force-none")) //$NON-NLS-1$
			return 1;
		if (pos4shape.equals("force-square")) //$NON-NLS-1$
			return 2;
		if (pos4shape.equals("force-rectangle")) //$NON-NLS-1$
			return 3;
		return 0;
	}

	public static String getShape4Pos(int pos) {
		switch (pos) {
		case 0:
			return null; // $NON-NLS-1$
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
