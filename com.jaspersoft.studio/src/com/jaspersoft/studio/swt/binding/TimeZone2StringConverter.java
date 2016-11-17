/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.binding;

import java.util.TimeZone;

import org.eclipse.core.databinding.conversion.Converter;

public class TimeZone2StringConverter extends Converter {
	public TimeZone2StringConverter() {
		super(TimeZone.class, String.class);
	}

	public Object convert(Object source) {
		if (source != null)
			return ((TimeZone) source).getDisplayName();
		return ""; //$NON-NLS-1$
	}

}
