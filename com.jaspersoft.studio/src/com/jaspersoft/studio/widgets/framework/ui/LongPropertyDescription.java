/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.Locale;

import org.apache.commons.validator.routines.LongValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.widget.FallbackNumericText;

public class LongPropertyDescription extends NumberPropertyDescription<Long> {
	
	public LongPropertyDescription() {
	}

	public LongPropertyDescription(String name, String label, String description, boolean mandatory,  Long defaultValue, Long min, Long max) {
		super(name, label, description, mandatory, defaultValue, min, max);
	}
	
	public LongPropertyDescription(String name, String label, String description, boolean mandatory, Long min, Long max) {
		super(name, label, description, mandatory, min, max);
	}
	
	@Override
	public Class<? extends Number> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Long.class;
	}
	
	@Override
	public LongPropertyDescription clone(){
		LongPropertyDescription result = new LongPropertyDescription();
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
	public LongPropertyDescription getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		Long min = null;
		Long max = null;
		Long def = null;
		Long fallBack = null;
		
		//Setup the minimum
		if (cpd.getMin() != null){
			min = new Long(cpd.getMin());
		} else {
			min = Long.MIN_VALUE;
		}
	 	
		//Setup the maximum
		if (cpd.getMax() != null){
			max = new Long(cpd.getMax());
		} else {
			max = Long.MAX_VALUE;
		}
		
		//Setup the default value
		if (cpd.getDefaultValue() != null && !cpd.getDefaultValue().isEmpty()){
			def = new Long(cpd.getDefaultValue());
		}
		
		//Setup the fallback value
		if (cpd.getFallbackValue() != null && !cpd.getFallbackValue().isEmpty()){
			fallBack = new Long(cpd.getFallbackValue());
		}
		LongPropertyDescription intDesc = new LongPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max);
		intDesc.setReadOnly(cpd.isReadOnly());
		intDesc.setFallbackValue(fallBack);
		return intDesc;
	}
	
	@Override
	protected FallbackNumericText createSimpleEditor(Composite parent) {
		FallbackNumericText text = new FallbackNumericText(parent, SWT.BORDER, 0, 0);
		text.setRemoveTrailZeroes(true);
		Long max = getMax() != null ? getMax() : Long.MAX_VALUE;
		Long min = getMin() != null ? getMin() : Long.MIN_VALUE;
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
			Long longValue =  widget.getValueAsLong();
			String tvalue = longValue != null ? longValue.toString() : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wiProp);
	}

	@Override
	protected Long convertValue(String v) throws NumberFormatException {
		if (v == null || v.isEmpty()) return null;
		return LongValidator.getInstance().validate(v, Locale.getDefault());
	}
}
