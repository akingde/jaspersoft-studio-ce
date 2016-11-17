/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
