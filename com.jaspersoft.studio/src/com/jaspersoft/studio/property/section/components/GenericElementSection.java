/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.components;

import net.sf.jasperreports.engine.design.JRDesignGenericElement;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.genericElement.MGenericElement;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

public class GenericElementSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		IPropertyDescriptor pd = getPropertyDesriptor(JRDesignGenericElement.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(JRDesignGenericElement.PROPERTY_EVALUATION_GROUP_NAME);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPEvaluationTime(parent, this, pd, gpd));

		createWidget4Property(parent, MGenericElement.PROPERTY_NAME);
		createWidget4Property(parent, MGenericElement.PROPERTY_NAMESPACE);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignGenericElement.PROPERTY_EVALUATION_TIME, Messages.common_evaluation_time);
		addProvidedProperties(MGenericElement.PROPERTY_NAME, Messages.MGenericElement_generic_type_name);
		addProvidedProperties(MGenericElement.PROPERTY_NAMESPACE, Messages.MGenericElement_generic_type_namespace);
	}
}
