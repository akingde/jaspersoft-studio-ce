package com.jaspersoft.studio.data.sql.model.query.operand;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

public abstract class AOperand implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public abstract String toSQLString();
}
