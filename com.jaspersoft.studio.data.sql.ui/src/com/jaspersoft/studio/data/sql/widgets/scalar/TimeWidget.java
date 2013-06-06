package com.jaspersoft.studio.data.sql.widgets.scalar;

import java.sql.Time;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class TimeWidget extends DateWidget {

	public TimeWidget(Composite parent, ScalarOperand<Time> operand) {
		super(parent, operand);
	}

	@Override
	protected int getDateStyle() {
		return CDT.BORDER | CDT.TIME_MEDIUM | CDT.DROP_DOWN;
	}
}
