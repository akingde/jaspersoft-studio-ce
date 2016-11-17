/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.binding;

import java.util.Locale;

import org.eclipse.core.databinding.conversion.Converter;

public class LocaleToStringConverter extends Converter {
	public LocaleToStringConverter() {
		super(Locale.class, String.class);
	}

	public Object convert(Object source) {
		if (source != null)
			return ((Locale) source).getDisplayName();
		return ""; //$NON-NLS-1$
	}

}
