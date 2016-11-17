/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.pattern.dialog;

import java.text.NumberFormat;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

public class ScientificPattern extends NumericPattern {

	public ScientificPattern(Composite parent, String value) {
		super(parent, NumberFormat.getNumberInstance(), value);
		setDescription(Messages.ScientificPattern_description);
	}

	@Override
	protected java.util.List<String> getDefaults() {
		if (dList == null) {
			dList = new ArrayList<String>();
			dList.add("0.0##E0"); //$NON-NLS-1$

			setPattern(dList.get(0));
		}
		return dList;
	}
}
