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
package com.jaspersoft.studio.property.descriptors;

import com.jaspersoft.studio.messages.Messages;

/**
 * Validator for an element that check if a value is a not null 
 * and valid integer (or a string that can be converted into an integer)
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPixelNotNullValidator extends AbstractJSSCellEditorValidator {
	
	@Override
	public String isValid(Object value) {
		try {
			if (value == null){
				return Messages.JSSPixelEditorValidator_errorNull;
			}
			if (value instanceof Integer)
				return null;
			if (value instanceof String){
				value = Integer.valueOf((String) value);
			}
			return null;
		} catch (NumberFormatException exc) {
			return Messages.common_this_is_not_an_integer_number; 
		}
	}
}
