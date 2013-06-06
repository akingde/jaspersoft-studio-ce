package com.jaspersoft.studio.data.sql.model.query.operand;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.MExpression;

public abstract class AOperand implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	protected MExpression expression;

	public AOperand(MExpression mexpr) {
		this.expression = mexpr;
	}

	public MExpression getExpression() {
		return expression;
	}

	public abstract String toSQLString();
}
