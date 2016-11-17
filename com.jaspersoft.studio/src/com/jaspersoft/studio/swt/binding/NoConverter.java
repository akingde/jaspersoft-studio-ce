/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.binding;

import org.eclipse.core.databinding.conversion.Converter;

public class NoConverter extends Converter {

	public NoConverter(Class<?> type) {
		super(type, type);
	}

	@Override
	public Object convert(Object fromObject) {
		return fromObject;
	}

}
