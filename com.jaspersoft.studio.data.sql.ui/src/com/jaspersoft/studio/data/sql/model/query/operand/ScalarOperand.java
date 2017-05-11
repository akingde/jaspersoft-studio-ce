/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import java.sql.Time;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

public class ScalarOperand<T> extends AOperand {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private T value;
	private Class<? extends T> type;

	public ScalarOperand(AMExpression<?> mexpr, T value) {
		super(mexpr);
		this.value = value;
		if (value != null)
			this.type = (Class<? extends T>) value.getClass();
	}

	public T getValue() {
		return value;
	}

	public Class<? extends T> getType() {
		return type;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public String toSQLString() {
		if (value != null) {
			if (Number.class.isAssignableFrom(type)) {
				if (value instanceof String)
					return (String) value;
				return NumberFormat.getInstance().format((Number) value);
			}
			if (Time.class.isAssignableFrom(type))
				return "'"
						+ new SimpleDateFormat("HH:mm:ss.ssss")
								.format((Date) value) + "'";
			if (java.sql.Date.class.isAssignableFrom(type))
				return "'"
						+ new SimpleDateFormat("yyyy-MM-dd")
								.format((Date) value) + "'";
			if (Date.class.isAssignableFrom(type))
				return "'"
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.ssss")
								.format((Date) value) + "'";
			return "'" + value + "'";
		}
		return Misc.nvl(value, "NULL");
	}

	@Override
	public String toXString() {
		return toSQLString();
	}
}
