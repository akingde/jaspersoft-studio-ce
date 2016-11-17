/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish;

public enum OverwriteEnum {
	OVERWRITE("true"), IGNORE("false"), ONLY_EXPRESSION("overwriteExpression");
	private String value;

	OverwriteEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static OverwriteEnum getByValue(String value) {
		if (value == null)
			return null;
		if (value.equals(OVERWRITE.getValue()))
			return OVERWRITE;
		if (value.equals(IGNORE.getValue()))
			return IGNORE;
		if (value.equals(ONLY_EXPRESSION.getValue()))
			return ONLY_EXPRESSION;
		return null;
	}
}
