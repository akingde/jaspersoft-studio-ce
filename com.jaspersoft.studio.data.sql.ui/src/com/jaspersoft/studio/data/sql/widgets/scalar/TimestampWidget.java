/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets.scalar;

import java.sql.Timestamp;
import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class TimestampWidget extends DateWidget {

	public TimestampWidget(Composite parent, ScalarOperand<Timestamp> operand, AQueryDesigner designer) {
		super(parent, operand, designer);
	}

	protected Date convertDate(Date d) {
		return new java.sql.Timestamp(d.getTime());
	}

	@Override
	protected int getDateStyle() {
		return CDT.BORDER | CDT.DATE_SHORT | CDT.TIME_MEDIUM | CDT.DROP_DOWN;
	}
}
