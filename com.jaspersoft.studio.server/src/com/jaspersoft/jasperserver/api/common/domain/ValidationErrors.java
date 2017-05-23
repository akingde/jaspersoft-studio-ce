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
 * The result of a validation operation, consisting of a set of validation
 * errors.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ValidationErrors.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public interface ValidationErrors {

	/**
	 * Determines whether any error has been detected during the validation.
	 * 
	 * @return <code>true</code> if the validation resulted in error(s)
	 * @see #getErrors()
	 */
	boolean isError();
	
	/**
	 * Returns the list of errors detected during the validation.
	 * 
	 * @return the list of errors detected during the validation, empty if no
	 * errors were found 
	 */
	List getErrors();

	/**
	 * Adds an error to the list of validation errors.
	 * 
	 * @param error the error to add
	 * @since 1.2.0
	 */
	void add(ValidationError error);
	
	/**
	 * Removes any previously added errors that match a specified code and 
	 * field. 
	 * 
	 * @param code the code of the errors to remove
	 * @param field the field for which errors are to be removed
	 * @since 3.0.0
	 */
	void removeError(String code, String field);
	
}
