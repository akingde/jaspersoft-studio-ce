/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import java.sql.Time;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;
import com.jaspersoft.studio.utils.Misc;

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
			if (Number.class.isAssignableFrom(type))
				return NumberFormat.getInstance().format((Number) value);
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
