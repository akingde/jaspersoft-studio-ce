/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPSpinner;

/**
 * Property descriptor for an element represented with a spinner control
 * 
 * @author Orlandin Marco
 *
 */
public class SpinnerPropertyDescriptor extends PropertyDescriptor  implements IPropertyDescriptorWidget, IHelp {

	public SpinnerPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	private IHelpRefBuilder refBuilder;

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}

	/**
	 * Build an ASProperyWidget with inside a spinner and return it
	 * 
	 */
	@Override
	public ASPropertyWidget<SpinnerPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPSpinner<SpinnerPropertyDescriptor>(parent,section, this);
	} 

}
