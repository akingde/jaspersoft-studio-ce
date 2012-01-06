package com.jaspersoft.studio.swt.binding;

import java.util.TimeZone;

import org.eclipse.core.databinding.conversion.Converter;

public class String2TimeZoneConverter extends Converter {
	public String2TimeZoneConverter() {
		super(String.class, TimeZone.class);
	}

	public Object convert(Object source) {
		if (source != null)
			return ((TimeZone) source).getDisplayName();
		return ""; //$NON-NLS-1$
	}

}
