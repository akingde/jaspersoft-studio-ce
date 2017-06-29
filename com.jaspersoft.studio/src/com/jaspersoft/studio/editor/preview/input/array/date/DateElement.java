/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.date;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;

public class DateElement extends ADateElement {

	@Override
	public Class<?> getSupportedType() {
		return Date.class;
	}

	@Override
	protected int getStyle() {
		return CDT.DATE_SHORT;
	}

	@Override
	protected Date getDate() {
		return date.getSelection();
	}

}
