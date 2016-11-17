/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;

public abstract class AOperand implements Serializable, Cloneable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	protected AMExpression<?> expression;

	public AOperand(AMExpression<?> mexpr) {
		this.expression = mexpr;
	}

	public AMExpression<?> getExpression() {
		return expression;
	}

	public void setExpression(AMExpression<?> expression) {
		this.expression = expression;
	}

	public abstract String toXString();

	public abstract String toSQLString();

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
