/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

public class UnknownOperand extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String value;

	public UnknownOperand(AMExpression<?> mexpr, String value) {
		super(mexpr);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toSQLString() {
		return Misc.nvl(value, "");
	}

	@Override
	public String toXString() {
		return toSQLString();
	}
}
