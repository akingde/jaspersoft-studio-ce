/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor;

import com.jaspersoft.studio.messages.Messages;

public enum NullEnum {
	NOTNULL((byte) 1, ""), NULL((byte) 2, "<" + Messages.common_null.toUpperCase() + ">"), INHERITED((byte) 3, "<"
			+ Messages.common_inherited.toUpperCase() + ">"), UNDEFINED((byte) 3, Messages.NullEnum_NullEnum_Undefined);
	// Undefined has the same value of inherited, and the same use but with a more user friendly message

	private final transient byte value;
	private final transient String name;

	private NullEnum(byte value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public final byte getValue() {
		return value;
	}
}
