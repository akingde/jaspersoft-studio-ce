/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.engine.type.JREnum;
import net.sf.jasperreports.engine.type.ResetTypeEnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;

public class SPResetType extends SPGroupTypeCombo<IPropertyDescriptor> {

	public SPResetType(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor,
			IPropertyDescriptor gDescriptor) {
		super(parent, section, pDescriptor, gDescriptor);
	}

	@Override
	protected JREnum[] getEnumValues() {
		return ResetTypeEnum.values();
	}

	@Override
	protected JREnum getGroupEnum() {
		return ResetTypeEnum.GROUP;
	}

	@Override
	protected JREnum getByName(String name) {
		return ResetTypeEnum.getByName(name);
	}
}
