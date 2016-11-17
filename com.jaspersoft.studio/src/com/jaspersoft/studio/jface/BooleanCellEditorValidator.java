/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;

public class BooleanCellEditorValidator implements ICellEditorValidator {

	private NullEnum canBeNull;

	public BooleanCellEditorValidator(NullEnum canBeNull) {
		super();
		this.canBeNull = canBeNull;
	}

	public String isValid(Object value) {
		if (canBeNull != NullEnum.NOTNULL && value == null)
			return null;
		if (value instanceof Boolean)
			return null;
		else
			return Messages.BooleanCellEditorValidator_value_is_not_instance_of_boolean; 
	}
}
