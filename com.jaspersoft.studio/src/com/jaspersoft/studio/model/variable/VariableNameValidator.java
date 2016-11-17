/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.variable;

import java.text.MessageFormat;

import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptors.AbstractJSSCellEditorValidator;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Validator to check if a name for a variable is valid. It is valid essentially if it 
 * is unique. If it is not then an error message is returned
 * 
 * @author Orlandin Marco
 *
 */
public class VariableNameValidator extends AbstractJSSCellEditorValidator {
	
	/**
	 * The object must be the new name for the variable, and using the target check if there are other variables
	 * with the same name, under the same dataset
	 */
	@Override
	public String isValid(Object value) {
		JRDesignDataset d = ModelUtils.getDataset(getTarget());
		if (d !=null ){
			JRVariable variable = d.getVariablesMap().get(value);
			if (variable != null && getTarget().getValue() != variable){
				String message = MessageFormat.format(Messages.VariableNameValidator_variableDuplicatedName, new Object[] { value });
				return message;
			}
		}
		return null;
	}

}
