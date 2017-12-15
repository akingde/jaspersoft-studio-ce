/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.engine.type.IncrementTypeEnum;
import net.sf.jasperreports.engine.type.JREnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;

public class SPIncrementType extends SPGroupTypeCombo<IPropertyDescriptor> {

	public SPIncrementType(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor,
			IPropertyDescriptor gDescriptor) {
		super(parent, section, pDescriptor, gDescriptor);
	}

	@Override
	protected JREnum[] getEnumValues() {
		return IncrementTypeEnum.values();
	}

	@Override
	protected JREnum getGroupEnum() {
		return IncrementTypeEnum.GROUP;
	}

	@Override
	protected JREnum getByName(String name) {
		return IncrementTypeEnum.getByName(name);
	}
}
