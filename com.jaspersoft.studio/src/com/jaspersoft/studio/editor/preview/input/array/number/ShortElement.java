/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.number;

import java.util.Locale;

import org.apache.commons.validator.routines.ShortValidator;

public class ShortElement extends ANumberElement {

	@Override
	public Class<?> getSupportedType() {
		return Short.class;
	}

	protected boolean isValid(String number) {
		return ShortValidator.getInstance().isValid(number, Locale.US);
	}

	@Override
	protected Object convertString(String str) {
		return new Short(str);
	}
}
