/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.validator;

import java.net.MalformedURLException;
import java.net.URL;

import net.sf.jasperreports.eclipse.ui.validator.EmptyStringValidator;

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class URLValidator extends EmptyStringValidator {
	@Override
	public IStatus validate(Object value) {
		IStatus st = super.validate(value);
		if (st.isOK()) {
			try {
				new URL((String) value);
			} catch (MalformedURLException e) {
				return ValidationStatus.error("URL is not good", e);
			}
			return Status.OK_STATUS;
		}
		return st;
	}
}
