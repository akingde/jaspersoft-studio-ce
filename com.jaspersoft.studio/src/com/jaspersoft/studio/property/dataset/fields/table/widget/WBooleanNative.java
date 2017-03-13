/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

public class WBooleanNative extends WBoolean {
	public WBooleanNative(AWidget aw) {
		super(aw);
	}

	@Override
	protected String[] getValues() {
		return new String[] { "true", "false" };
	}
}
