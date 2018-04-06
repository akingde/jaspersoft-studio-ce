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

package com.jaspersoft.jasperserver.remote.exception;

import com.jaspersoft.jasperserver.dto.common.ErrorDescriptor;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: IllegalParameterValueException.java 30161 2013-03-22 19:20:15Z
 *          inesterenko $
 */
public class IllegalParameterValueException extends RemoteException {
	public static final String ERROR_CODE = "illegal.parameter.value.error";

	public IllegalParameterValueException(String parameterName, String parameterValue) {
		this("Value of parameter " + (parameterName != null ? "'" + parameterName + "'" : "") + " invalid",
				parameterName, parameterValue);
	}

	public IllegalParameterValueException(String message, String... parameters) {
		super(message);
		setErrorDescriptor(
				new ErrorDescriptor().setMessage(message).setErrorCode(ERROR_CODE).setParameters(parameters));
	}

	public IllegalParameterValueException(ErrorDescriptor errorDescriptor) {
		super(errorDescriptor);
	}
}
