/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band;

import net.sf.jasperreports.engine.design.JRDesignGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.property.IPostSetValue;

/**
 * Refactor Reset and Increment, evaluation time fields when group is renamed
 * 
 * @author Veaceslav Chicu
 */
public class PostSetParameterName implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop, Object newValue, Object oldValue) {
		JSSCompoundCommand c = new JSSCompoundCommand(null);
		// Check if the updated element is a dataset and the updated property is the name
		if (target instanceof MGroup && prop.equals(JRDesignGroup.PROPERTY_NAME)) {
			// MGroup mprm = (MGroup) target;

		}
		return c;
	}

}
