/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPDegree;

/*
 * The Class FloatPropertyDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class DegreePropertyDescriptor extends DoublePropertyDescriptor {

	/**
	 * Instantiates a new float property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public DegreePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public ASPropertyWidget<IPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		SPDegree spDegree = new SPDegree(parent, section, this);
		spDegree.setDigits(2, 2, Double.class);
		spDegree.setBounds(0, 360);
		return spDegree;
	}
}
