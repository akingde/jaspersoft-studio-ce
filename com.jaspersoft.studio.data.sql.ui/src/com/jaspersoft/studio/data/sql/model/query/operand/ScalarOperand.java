package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.utils.Misc;

public class ScalarOperand extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private Object value = "Venice";

	@Override
	public String toSQLString() {
		if (value instanceof String)
			return "'" + Misc.nvl((String) value) + "'";
		return Misc.nvl(value, "");
	}

}
