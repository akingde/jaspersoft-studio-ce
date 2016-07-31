/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
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
	
	public TransparentColorPropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}
	
	public TransparentColorPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, editor);
	}
	
	@Override
	protected boolean isTransaprent() {
		return true;
	}

	@Override
	public ItemPropertyDescription<T> clone(IPropertyEditor editor){
		TransparentColorPropertyDescription<T> result = new TransparentColorPropertyDescription<T>(editor);
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig, IPropertyEditor editor) {
		TransparentColorPropertyDescription<?> result = new TransparentColorPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), editor);
		result.setReadOnly(cpd.isReadOnly());
		return result;
	}
}
