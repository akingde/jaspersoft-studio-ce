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
