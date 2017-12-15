/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.ValidatedDecimalFormat;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.widget.FallbackNumericText;

public class FloatPropertyDescription extends NumberPropertyDescription<Float> {
	
	public FloatPropertyDescription() {
	}
	
	public FloatPropertyDescription(String name, String label, String description, boolean mandatory,  Float defaultValue, Float min, Float max) {
		super(name, label, description, mandatory, defaultValue, min, max);
	}
	
	public FloatPropertyDescription(String name, String label, String description, boolean mandatory, Float min, Float max) {
		super(name, label, description, mandatory, min, max);
	}
	
	@Override
	public Class<? extends Number> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Float.class;
	}
	
	@Override
	public FloatPropertyDescription clone(){
		FloatPropertyDescription result = new FloatPropertyDescription();
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
	public FloatPropertyDescription getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		Float min = null;
		Float max = null;
		Float def = null;
		Float fallBack = null;
		
		//Setup the minimum
		if (cpd.getMin() != null){
			min = new Float(cpd.getMin());
		} else {
			min = Float.MIN_VALUE;
		}
		
		//Setup the maximum
		if (cpd.getMax() != null){
			max = new Float(cpd.getMax());
		} else {
			max = Float.MAX_VALUE;
		}
		
		//Setup the default value
		if (cpd.getDefaultValue() != null && !cpd.getDefaultValue().isEmpty()){
			def = new Float(cpd.getDefaultValue());
		}
		
		//Setup the fallback value
		if (cpd.getFallbackValue() != null && !cpd.getFallbackValue().isEmpty()){
			fallBack = new Float(cpd.getFallbackValue());
		}
		FloatPropertyDescription floatDesc = new FloatPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max);
		floatDesc.setReadOnly(cpd.isReadOnly());
		floatDesc.setFallbackValue(fallBack);
		return floatDesc;
	}
	
	@Override
	protected FallbackNumericText createSimpleEditor(Composite parent) {
		FallbackNumericText text = new FallbackNumericText(parent, SWT.BORDER, 4, 6);
		text.setRemoveTrailZeroes(true);
		Float max = getMax() != null ? getMax() : Float.MAX_VALUE;
		Float min = getMin() != null ? getMin() : Float.MIN_VALUE;
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
			Float floatValue =  widget.getValueAsFloat(); 
			String tvalue = floatValue != null ? floatValue.toString() : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wiProp);
	}

	@Override
	protected Number convertValue(String v) throws NumberFormatException {
		if (v == null || v.isEmpty()) return null;
		char separator = ValidatedDecimalFormat.DECIMAL_SEPARATOR;
		//externally convert the current separator to the locale separator
		if (separator != '.'){
			v = v.replace(separator, '.');
		} 
		return Float.valueOf(v);
	}
}
