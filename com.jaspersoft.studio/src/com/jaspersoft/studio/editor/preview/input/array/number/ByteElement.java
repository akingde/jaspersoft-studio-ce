/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.number;

import java.util.Locale;

import org.apache.commons.validator.routines.ByteValidator;

public class ByteElement extends ANumberElement {

	@Override
	public Class<?> getSupportedType() {
		return Byte.class;
	}

	protected boolean isValid(String number) {
		return ByteValidator.getInstance().isValid(number, Locale.US);
	}

	@Override
	protected Object convertString(String str) {
		return new Byte(str);
	}
}
