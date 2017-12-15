/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Property description to handle a color widget, it accept also transparent values
 * 
 * @author Orlandin Marco
 */
public class TransparentColorPropertyDescription<T> extends ColorPropertyDescription<T> {
	
	public TransparentColorPropertyDescription() {
		super();
	}
	
	public TransparentColorPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}
	
	@Override
	protected boolean isTransaprent() {
		return true;
	}

	@Override
	public ItemPropertyDescription<T> clone(){
		TransparentColorPropertyDescription<T> result = new TransparentColorPropertyDescription<T>();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		TransparentColorPropertyDescription<String> result = new TransparentColorPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
