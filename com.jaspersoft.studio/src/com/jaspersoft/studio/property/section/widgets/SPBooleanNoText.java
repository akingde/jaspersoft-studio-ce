/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;

public class SPBooleanNoText<T extends IPropertyDescriptor> extends SPBoolean<T> {
	
	public SPBooleanNoText(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}
	

	public void createComponent(Composite parent) {
		super.createComponent(parent);
		cmb3Bool.setText("");
	}

}
