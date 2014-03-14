/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.group;

import java.text.MessageFormat;

import net.sf.jasperreports.engine.JRGroup;
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
		JasperDesign d =  getTarget().getJasperDesign();
		if (d !=null ){
			JRGroup group = d.getGroupsMap().get(value);
			if (group != null){
				String message = MessageFormat.format(Messages.GroupSection_SameNameErrorMsg, new Object[] { value });
				return message;
			}
		}
		return null;
	}

}
