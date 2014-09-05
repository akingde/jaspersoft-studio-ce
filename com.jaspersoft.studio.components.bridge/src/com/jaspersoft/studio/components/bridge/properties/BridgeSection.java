/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge.properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.jasperreports.components.bridge.design.BridgeDesignComponent;
import com.jaspersoft.studio.components.bridge.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

/**
 * Properties section for the main details of the Bridge Component element.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class BridgeSection extends AbstractSection{

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		
		parent.setLayout(new GridLayout(3,false));
		
		IPropertyDescriptor pd = getPropertyDesriptor(BridgeDesignComponent.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(BridgeDesignComponent.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		SPEvaluationTime eval = new SPEvaluationTime(parent, this, pd, gpd);
		eval.getControl().setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 2, 1));
		widgets.put(pd.getId(), eval);
		createWidget4Property(parent, BridgeDesignComponent.PROPERTY_PROCESSING_CLASS);
		getWidgetFactory().createCLabel(parent, Messages.BridgeSection_BridgeItemProperties).setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		ASPropertyWidget itemPropsW = createWidget4Property(parent, BridgeDesignComponent.PROPERTY_ITEM_PROPERTIES,false);
		itemPropsW.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		getWidgetFactory().createCLabel(parent, Messages.BridgeSection_BridgeItemData).setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
		ASPropertyWidget itemDataW = createWidget4Property(parent, BridgeDesignComponent.PROPERTY_ITEM_DATA,false);
		itemDataW.getControl().setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false,3,1));
	}

	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(BridgeDesignComponent.PROPERTY_EVALUATION_TIME, Messages.BridgeSection_EvalTime);
		addProvidedProperties(BridgeDesignComponent.PROPERTY_EVALUATION_GROUP, Messages.BridgeSection_EvalGroup);
		addProvidedProperties(BridgeDesignComponent.PROPERTY_PROCESSING_CLASS, Messages.BridgeSection_ProcessingClass);
		addProvidedProperties(BridgeDesignComponent.PROPERTY_ITEM_PROPERTIES, Messages.BridgeSection_BridgeItemPropertiesDesc);
		addProvidedProperties(BridgeDesignComponent.PROPERTY_ITEM_DATA, Messages.BridgeSection_InnerConfiguration);
	}
}
