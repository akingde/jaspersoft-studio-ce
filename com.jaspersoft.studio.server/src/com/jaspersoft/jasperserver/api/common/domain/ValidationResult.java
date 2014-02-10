/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
