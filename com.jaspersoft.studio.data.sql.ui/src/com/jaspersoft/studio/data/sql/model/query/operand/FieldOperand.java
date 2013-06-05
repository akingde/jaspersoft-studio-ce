package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.metadata.MColumn;

public class FieldOperand extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private MColumn value;

	public FieldOperand(MColumn value) {
		this.value = value;
	}

	@Override
	public String toSQLString() {
		if (value == null)
			return "";
		return value.toSQLString();
	}

}
