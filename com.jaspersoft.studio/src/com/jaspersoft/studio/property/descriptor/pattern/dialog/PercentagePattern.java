/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

public class PercentagePattern extends NumericPattern {

	public PercentagePattern(Composite parent, String value) {
		super(parent, NumberFormat.getPercentInstance(), value);
		setDescription(Messages.PercentagePattern_description);
	}

	@Override
	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("#,##0.##%"); //$NON-NLS-1$
			dList.add("#,##0.##\u2030"); //$NON-NLS-1$
			dList.add("#,##0.##%;(#,##0.###) %"); //$NON-NLS-1$
			dList.add("#,##0.##%;(-#,##0.###) %"); //$NON-NLS-1$
			dList.add("#,##0.##%;(#,##0.###-) %"); //$NON-NLS-1$
			setPattern(dList.get(0));
		}
		return dList;
	}
}
