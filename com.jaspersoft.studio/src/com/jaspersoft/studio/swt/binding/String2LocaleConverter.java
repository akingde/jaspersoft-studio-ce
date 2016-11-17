/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.binding;

import java.util.Locale;

import org.eclipse.core.databinding.conversion.Converter;

public class String2LocaleConverter extends Converter {
	public String2LocaleConverter() {
		super(String.class, Locale.class);
	}

	public Object convert(Object source) {
		if (source != null)
			return new Locale((String) source);
		return ""; //$NON-NLS-1$
	}

}
