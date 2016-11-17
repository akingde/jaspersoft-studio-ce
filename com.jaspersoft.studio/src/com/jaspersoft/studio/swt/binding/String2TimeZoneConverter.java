/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.binding;

import java.util.TimeZone;

import org.eclipse.core.databinding.conversion.Converter;

public class String2TimeZoneConverter extends Converter {
	public String2TimeZoneConverter() {
		super(String.class, TimeZone.class);
	}

	public Object convert(Object source) {
		if (source instanceof String)
			return TimeZone.getTimeZone((String) source);
		return null;
	}

}
