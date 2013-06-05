package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;

public class ParameterOperand extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public String toSQLString() {
		return "$P{}";
	}

}
