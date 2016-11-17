/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.number;

import java.util.Locale;

import org.apache.commons.validator.routines.IntegerValidator;

public class IntegerElement extends ANumberElement {

	@Override
	public Class<?> getSupportedType() {
		return Integer.class;
	}

	protected boolean isValid(String number) {
		return IntegerValidator.getInstance().isValid(number, Locale.US);
	}

	@Override
	protected Object convertString(String str) {
		return new Integer(str);
	}
}
