/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array.date;

import java.sql.Timestamp;
import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;

public class TimestampElement extends ADateElement {

	@Override
	public Class<?> getSupportedType() {
		return Timestamp.class;
	}

	@Override
	protected int getStyle() {
		return CDT.DATE_SHORT | CDT.TIME_MEDIUM;
	}

	@Override
	protected Date getDate() {
		Date sdate = date.getSelection();
		return sdate != null ? new java.sql.Timestamp(sdate.getTime()) : null;
	}

}
