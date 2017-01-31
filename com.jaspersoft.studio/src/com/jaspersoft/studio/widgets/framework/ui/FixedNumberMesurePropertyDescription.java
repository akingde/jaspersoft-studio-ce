/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.Map;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Widget used to insert values in different measure units. The measure units list are built using the 
 * combo entry of the widget definitions. If they are not provided it will use a default set. This widget
 * doesn't do any conversion on the inserted value, simply store the numeric inserted value.
 * The measure unit is stored into the model with a separate properties.
 * 
 * @author Orlandin Marco
 *
 */
public class FixedNumberMesurePropertyDescription extends FixedMeasurePropertyDescription {

	public FixedNumberMesurePropertyDescription() {
	}
	
	public FixedNumberMesurePropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue, long min, long max, Map<String, String> unitsMap) {
		super(name, label, description, mandatory, defaultValue, min, max, unitsMap);
	}

	public FixedNumberMesurePropertyDescription(String name, String label, String description, boolean mandatory, long min, long max, Map<String, String> unitsMap) {
		super(name, label, description, mandatory, min, max, unitsMap);
	}
	
	/**
	 * This is the values that will be written in the model and it is generated by rounding the numeric
	 * value removing unecessary zeroes or decimal digit when using an integer unit (px). This override
	 * write only the number on the model without appending the measure unit
	 * 
	 * @param measureUnitKey the key of the measure unit that should be written in the model
	 * @param measureUnitName the label of the measure, typed by the user
	 * @param value the numeric value
	 * @return the value that will be writtenn on the model
	 */
	@Override
	protected String getWrittenValue(String measureUnitKey, String measureUnitName, Double value){
		if (value == null) return null;
		String roundedValue = getRoundedValue(measureUnitKey, value);
		return roundedValue;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		Long min = createMin(cpd);
		Long max = createMax(cpd);
		Map<String, String> i18nOpts = createMesureUnitsMap(cd, cpd);
		FixedNumberMesurePropertyDescription intDesc = new FixedNumberMesurePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), 
																													cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), min, max, i18nOpts);
		intDesc.setReadOnly(cpd.isReadOnly());
		intDesc.setFallbackValue(cpd.getFallbackValue());
		intDesc.setjConfig(jConfig);
		return intDesc;
	}
	
	@Override
	public ItemPropertyDescription<String> clone() {
		FixedNumberMesurePropertyDescription result = new FixedNumberMesurePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.min = min;
		result.max = max;
		result.nameKeyUnitsMap = nameKeyUnitsMap;
		result.autocompleteValues = autocompleteValues;
		result.fallbackValue = fallbackValue;
		return result;
	}
}