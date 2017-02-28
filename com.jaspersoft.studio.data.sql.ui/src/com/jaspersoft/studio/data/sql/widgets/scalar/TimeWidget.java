/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets.scalar;

import java.sql.Time;
import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class TimeWidget extends DateWidget {

	public TimeWidget(Composite parent, ScalarOperand<Time> operand, AQueryDesigner designer) {
		super(parent, operand, designer);
	}

	protected Date convertDate(Date d) {
		return new java.sql.Time(d.getTime());
	}

	@Override
	protected int getDateStyle() {
		return CDT.BORDER | CDT.TIME_MEDIUM | CDT.DROP_DOWN;
	}
}
