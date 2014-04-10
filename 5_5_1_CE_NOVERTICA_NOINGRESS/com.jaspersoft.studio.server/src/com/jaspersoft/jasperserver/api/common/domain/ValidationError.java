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

/**
 * Error detected when validating objects according to rules defined in various
 * services.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ValidationError.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 */
@JasperServerAPI
public interface ValidationError {
	
	/**
	 * Returns the localization key of the error message.
	 * 
	 * <p>
	 * The localized message is created by looking up the key in the resource
	 * bundles.
	 * </p>
	 * 
	 * @return the error message key
	 * @see #getErrorArguments()
	 * @see #getDefaultMessage()
	 */
	String getErrorCode();
	
	/**
	 * Returns a list of values that are used to fill the placeholders in the 
	 * localized error messages.
	 * 
	 * @return the values to use for the localized error message, or
	 * <code>null</code> if the message does not include values
	 */
	Object[] getErrorArguments();
	
	/**
	 * Returns a default message that should be used when the error message key
	 * is not found in the localization resource bundles.
	 * 
	 * @return a default (non localized/English) error message
	 */
	String getDefaultMessage();
	
	/**
	 * Returns the specific field of the object that failed to validate.
	 * 
	 * <p>
	 * The field can be used in some cases in addition to the error code to
	 * construct compound message keys that resolve to localized messages
	 * specific to the field.
	 * </p>
	 * 
	 * @return the object field that failed the validation, or <code>null</code>
	 * if the error is not related to a particular field
	 */
	String getField();
	
}
