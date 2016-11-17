/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.group;

import java.text.MessageFormat;

import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptors.AbstractJSSCellEditorValidator;

/**
 * Validator to check if a name for a group is valid. It is valid essentially if it 
 * is unique. If it is not then an error message is returned
 * 
 * @author Orlandin Marco
 *
 */
public class GroupNameValidator extends AbstractJSSCellEditorValidator {
	
	/**
	 * The object must be the new name for the group, and using the target check if there are other variables
	 * with the same name, under the same dataset
	 */
	@Override
	public String isValid(Object value) {
		String selectedName = ((JRGroup)getTarget().getValue()).getName();
		if (value.equals(selectedName)) return null;
		JasperDesign d =  getTarget().getJasperDesign();
		if (d !=null){
			JRGroup group = d.getGroupsMap().get(value);
			if (group != null){
				String message = MessageFormat.format(Messages.GroupSection_SameNameErrorMsg, new Object[] { value });
				return message;
			}
			//Check if there is an existing variable for the group
			//JRDesignDataset dataset = ModelUtils.getDataset(getTarget());
			JRVariable groupVar = getTarget().getJasperDesign().getVariablesMap().get(value  + "_COUNT");
			if (groupVar != null){
				String message = MessageFormat.format(Messages.GroupSection_SameVariableNameErrorMsg, new Object[] {value  + "_COUNT", value });
				return message;
			}
		}
		return null;
	}

}
