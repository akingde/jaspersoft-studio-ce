/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.number;

import java.util.Locale;

import org.apache.commons.validator.routines.DoubleValidator;

public class DoubleElement extends ANumberElement {

	@Override
	public Class<?> getSupportedType() {
		return Double.class;
	}

	protected boolean isValid(String number) {
		return DoubleValidator.getInstance().isValid(number, Locale.US);
	}

	@Override
	protected Object convertString(String str) {
		return new Double(str);
	}
}
