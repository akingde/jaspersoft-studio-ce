/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.utils.NumberValidator;

public class WNumber extends WText {

	public WNumber(AWidget aw) {
		super(aw);

	}

	@Override
	protected void createControl(Composite parent) {
		super.createControl(parent);
		try {
			txt.addVerifyListener(new NumberValidator(null, null, Class.forName(aw.getTColumn().getPropertyType())));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
