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

package com.jaspersoft.jasperserver.api.common.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * Filter that determines a subset of validation rules to check during a
 * validation operation.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ValidationError.java 8408 2007-05-29 23:29:12Z melih $
 * @since 2.1.0
 */
@JasperServerAPI
public interface ValidationErrorFilter 
{

	/**
	 * Decides whether the rules associated to a specific error should
	 * be checked during a validation operation.
	 * 
	 * @param error the error 
	 * @return whether the specified error should be checked
	 * @see #matchErrorCode(String)
	 */
	boolean matchError(ValidationError error);


	/**
	 * Decides whether the rules associated to a specific error code should
	 * be checked during a validation operation.
	 * 
	 * @param errorCode the error code
	 * @return whether the specified error should be checked
	 */
	boolean matchErrorCode(String errorCode);
	
	/**
	 * Decides whether a specific field of the validation object should be
	 * checked during the validation.
	 * 
	 * @param errorField the object field name
	 * @return whether the specified field should be checked
	 */
	boolean matchErrorField(String errorField);

}
