/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
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
