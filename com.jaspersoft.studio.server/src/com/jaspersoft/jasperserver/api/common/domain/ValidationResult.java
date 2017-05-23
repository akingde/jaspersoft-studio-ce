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

import java.util.List;

/**
 * The result of a report unit validation.
 * 
 * @author tkavanagh
 * @version $Id: ValidationResult.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
//TODO add ValidationDetail to the API
@JasperServerAPI
public interface ValidationResult {

	/**
	 * Validation state used when the validation was successful.
	 * 
	 * @see #getValidationState()
	 */
	String STATE_VALID = "VALID";
	
	/**
	 * Validation state used when errors were detected during the validation.
	 * 
	 * @see #getValidationState()
	 */
	String STATE_ERROR = "ERROR";

	/**
	 * Returns the list of items detected during validation.
	 * 
	 * @return list of <code>ValidationDetail<code> objects
	 */
	public List getResults();
	
	/**
	 * Returns the state of the validation result.
	 * 
	 * @return <code>STATE_ERROR</code> if the validation resulted in errors
	 * @see #STATE_VALID
	 * @see #STATE_ERROR
	 */
	public String getValidationState();
	
}
