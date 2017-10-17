/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.binding;

import java.text.Format;
import java.text.ParseException;

import org.eclipse.core.databinding.conversion.Converter;

public class StringToObjectFomatConverter extends Converter {
	private Format formater;

	public StringToObjectFomatConverter(Object toType, Format formater) {
		super(String.class, toType);
		this.formater = formater;
	}

	@Override
	public Object convert(Object fromObject) {
		try {
			return formater.parseObject((String) fromObject);
		} catch (ParseException e) {
			return null;
		}
	}

}
