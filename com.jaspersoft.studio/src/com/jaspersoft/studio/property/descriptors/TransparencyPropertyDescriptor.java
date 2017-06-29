/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPTransparency;

/*
 * The Class FloatPropertyDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class TransparencyPropertyDescriptor extends FloatPropertyDescriptor {

	/**
	 * Instantiates a new float property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public TransparencyPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public ASPropertyWidget<IPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		SPTransparency spTransparency = new SPTransparency(parent, section, this);
		spTransparency.setDigits(0, 0, Float.class);
		return spTransparency;
	}
}
