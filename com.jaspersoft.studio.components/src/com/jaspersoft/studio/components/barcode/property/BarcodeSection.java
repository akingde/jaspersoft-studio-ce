/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

public class BarcodeSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Barcode",
				false, 2);

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION);

		IPropertyDescriptor pd = getPropertyDesriptor(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(group, pd.getDisplayName());
		widgets.put(pd.getId(), new SPEvaluationTime(group, this, pd, gpd));
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION, Messages.MBarcode_code_expression);
	}
}
