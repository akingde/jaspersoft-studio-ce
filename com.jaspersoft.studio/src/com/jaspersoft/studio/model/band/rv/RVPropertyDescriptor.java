/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band.rv;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;

public class RVPropertyDescriptor extends NTextPropertyDescriptor implements IPropertyDescriptorWidget {

	public RVPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		return new RVPropertiesCellEditor(parent);
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet())
			return super.getLabelProvider();
		return new RVPropertiesLabelProvider();
	}

	@Override
	public ASPropertyWidget<RVPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPReturnValuesButton<RVPropertyDescriptor>(parent, section, this, getDisplayName());
	}
}
