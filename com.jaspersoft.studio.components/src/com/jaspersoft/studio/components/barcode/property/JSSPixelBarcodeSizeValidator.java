/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.property;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptors.AbstractJSSCellEditorValidator;

/**
 * Validator for an element that check if a value is a not null 
 * and valid integer (or a string that can be converted into an integer)
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPixelBarcodeSizeValidator extends AbstractJSSCellEditorValidator {
	
	@Override
	public String isValid(Object value) {
		try {
			if (value != null){
				Integer intValue = null;
				
				//Convert the value to integer if possible
				if (value instanceof Integer)
					intValue = (Integer)value;
				else if (value instanceof String){
					intValue = Integer.valueOf((String) value);
				} else {
					return Messages.common_this_is_not_an_integer_number; 
				}
				
				//Check if it is positive
				if (intValue < 0){
					return com.jaspersoft.studio.components.barcode.messages.Messages.JSSPixelBarcodeSizeValidator_negativeBarcodeSize;
				}
			}
			return null;
		} catch (NumberFormatException exc) {
			return Messages.common_this_is_not_an_integer_number; 
		}
	}
}
