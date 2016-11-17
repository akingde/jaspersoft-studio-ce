/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.validator;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;

import net.sf.jasperreports.eclipse.ui.validator.EmptyStringValidator;

public class URLValidator extends EmptyStringValidator {

	@Override
	public IStatus validate(Object value) {
		IStatus st = super.validate(value);
		if (st.isOK() && !((String) value).matches("^(https?)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"))
			return ValidationStatus.error("URL is wrong");
		return st;
	}
}
