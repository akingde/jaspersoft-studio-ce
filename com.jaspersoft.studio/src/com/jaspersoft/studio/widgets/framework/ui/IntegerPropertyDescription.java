/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.Locale;

import org.apache.commons.validator.routines.IntegerValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.widget.FallbackNumericText;

public class IntegerPropertyDescription extends NumberPropertyDescription<Integer> {
	
	public IntegerPropertyDescription() {
	}

	public IntegerPropertyDescription(String name, String label, String description, boolean mandatory,  Integer defaultValue, Integer min, Integer max) {
		super(name, label, description, mandatory, defaultValue, min, max);
	}
	
	public IntegerPropertyDescription(String name, String label, String description, boolean mandatory, Integer min, Integer max) {
		super(name, label, description, mandatory, min, max);
	}
	
	@Override
	public Class<? extends Number> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Integer.class;
	}
	
	@Override
	public IntegerPropertyDescription clone(){
		IntegerPropertyDescription result = new IntegerPropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.min = min;
		result.max = max;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public IntegerPropertyDescription getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		Integer min = null;
		Integer max = null;
		Integer def = null;
		Integer fallBack = null;
		
		//Setup the minimum
		if (cpd.getMin() != null){
			min = new Integer(cpd.getMin());
		} else {
			min = Integer.MIN_VALUE;
		}
	 	
		//Setup the maximum
		if (cpd.getMax() != null){
			max = new Integer(cpd.getMax());
		} else {
			max = Integer.MAX_VALUE;
		}
		
		//Setup the default value
		if (cpd.getDefaultValue() != null && !cpd.getDefaultValue().isEmpty()){
			def = new Integer(cpd.getDefaultValue());
		}
		
		//Setup the fallback value
		if (cpd.getFallbackValue() != null && !cpd.getFallbackValue().isEmpty()){
			fallBack = new Integer(cpd.getFallbackValue());
		}
		IntegerPropertyDescription intDesc = new IntegerPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max);
		intDesc.setReadOnly(cpd.isReadOnly());
		intDesc.setFallbackValue(fallBack);
		return intDesc;
	}
	
	@Override
	protected FallbackNumericText createSimpleEditor(Composite parent) {
		FallbackNumericText text = new FallbackNumericText(parent, SWT.BORDER, 0, 0);
		text.setRemoveTrailZeroes(true);
		Integer max = getMax() != null ? getMax() : Integer.MAX_VALUE;
		Integer min = getMin() != null ? getMin() : Integer.MIN_VALUE;
		text.setMaximum(max.doubleValue());
		text.setMinimum(min.doubleValue());
		return text;
	}
	
	@Override
	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (txt instanceof NumericText){
			NumericText widget = (NumericText)txt;
			Integer integerValue =  widget.getValueAsInteger();
			String tvalue = integerValue != null ? integerValue.toString() : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wiProp);
	}

	@Override
	protected Integer convertValue(String v) throws NumberFormatException {
		if (v == null || v.isEmpty()) return null;
		return IntegerValidator.getInstance().validate(v, Locale.getDefault());
	}
}
