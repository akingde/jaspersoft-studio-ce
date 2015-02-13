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
package com.jaspersoft.studio.server.wizard.pages;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.jaspersoft.studio.server.messages.Messages;

public class UsernameValidator implements IValidator {
	
	private static final char[] NOT_ALLOWED_ID_CHARS = new char[] { '\\', '|',
		' ', '`', '"', '\'', '~', '!', '#', '$', '%', '^', '&', '[', ']',
		'*', '+', '=', ';', ':', '?', '<', '>', '}', '{', ')', '(', ']',
		'[', '/' };

	public IStatus validate(Object value) {
		String uname = (String) value;
		if (value == null || uname.isEmpty())
			return ValidationStatus.error(Messages.EmptyStringValidator_EmptyError);
		if (uname.length() > 100)
			return ValidationStatus.error(Messages.UsernameValidator_ErrorMsgUsernameTooLong);
		for (char c : uname.toCharArray()) {
			if (!Character.isSpaceChar(c)) {
				if(!ArrayUtils.contains(NOT_ALLOWED_ID_CHARS, c)) {
					continue;
				}
				else {
					return ValidationStatus.error(Messages.UsernameValidator_ErrorMsgNotAllowedChars);					
				}
			}
			else {
				return ValidationStatus.error(Messages.UsernameValidator_ErrorMsgNoSpaceChars);
			}
		}
		
		return Status.OK_STATUS;
	}
	
}
