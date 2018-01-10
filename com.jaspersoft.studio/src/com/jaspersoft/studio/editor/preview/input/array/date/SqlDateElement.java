/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.date;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;

public class SqlDateElement extends ADateElement {

	@Override
	public Class<?> getSupportedType() {
		return java.sql.Date.class;
	}

	@Override
	protected int getStyle() {
		return CDT.DATE_SHORT;
	}

	@Override
	protected Date getDate() {
		Date sdate = date.getSelection();
		return sdate != null ? new java.sql.Date(sdate.getTime()) : null;
	}

}
