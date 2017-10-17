/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.pages;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import com.jaspersoft.studio.server.messages.Messages;

public class UsernameValidator implements IValidator {
	public UsernameValidator() {

	}

	private boolean allowNull = false;

	public UsernameValidator(boolean allowNull) {
		this.allowNull = allowNull;
	}

	public void setAllowNull(boolean allowNull) {
		this.allowNull = allowNull;
	}

	private static final char[] NOT_ALLOWED_ID_CHARS = new char[] { '\\', '|', ' ', '`', '"', '\'', '~', '!', '#', '$',
			'%', '^', '&', '[', ']', '*', '+', '=', ';', ':', '?', '<', '>', '}', '{', ')', '(', ']', '[', '/' };

	public IStatus validate(Object value) {
		String uname = (String) value;
		if (value == null || uname.isEmpty()) {
			if (allowNull)
				return Status.OK_STATUS;
			return ValidationStatus.error(Messages.EmptyStringValidator_EmptyError);
		}
		if (uname.length() > 100)
			return ValidationStatus.error(Messages.UsernameValidator_ErrorMsgUsernameTooLong);
		for (char c : uname.toCharArray()) {
			if (!Character.isSpaceChar(c)) {
				if (!ArrayUtils.contains(NOT_ALLOWED_ID_CHARS, c)) {
					continue;
				} else {
					return ValidationStatus.error(Messages.UsernameValidator_ErrorMsgNotAllowedChars);
				}
			} else {
				return ValidationStatus.error(Messages.UsernameValidator_ErrorMsgNoSpaceChars);
			}
		}

		return Status.OK_STATUS;
	}

}
