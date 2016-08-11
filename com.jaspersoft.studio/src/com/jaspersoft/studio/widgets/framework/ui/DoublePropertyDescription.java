package com.jaspersoft.studio.widgets.framework.ui;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.commons.validator.routines.DoubleValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class DoublePropertyDescription extends NumberPropertyDescription<BigDecimal> {
	
	public DoublePropertyDescription() {
	}
	
	public DoublePropertyDescription(String name, String label, String description, boolean mandatory,  BigDecimal defaultValue, Number min, Number max) {
		super(name, label, description, mandatory, defaultValue, min, max);
	}
	
	public DoublePropertyDescription(String name, String label, String description, boolean mandatory, Number min, Number max) {
		super(name, label, description, mandatory, min, max);
	}
	
	@Override
	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Double.class;
	}
	
	@Override
	public ItemPropertyDescription<BigDecimal> clone(){
		DoublePropertyDescription result = new DoublePropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.min = min;
		result.max = max;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		BigDecimal min = null;
		BigDecimal max = null;
		BigDecimal def = null;
		if (cpd.getMin() != null){
			min = new BigDecimal(cpd.getMin());
		}
		if (cpd.getMax() != null){
			max = new BigDecimal(cpd.getMax());
		}
		if (cpd.getDefaultValue() != null){
			def = new BigDecimal(cpd.getDefaultValue());
		}
		DoublePropertyDescription doubleDesc = new DoublePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max);
		doubleDesc.setReadOnly(cpd.isReadOnly());
		return doubleDesc;
	}

	@Override
	protected NumericText createSimpleEditor(Composite parent) {
		NumericText text = new NumericText(parent, SWT.BORDER, 6, 10);
		text.setRemoveTrailZeroes(true);
		Number max = getMax() != null ? getMax() : Double.MAX_VALUE;
		Number min = getMin() != null ? getMin() : Double.MIN_VALUE;
		text.setMaximum(max.doubleValue());
		text.setMinimum(min.doubleValue());
		return text;
	}

	@Override
	protected Number convertValue(String v) {
		if (v == null || v.isEmpty()) return null;
		return DoubleValidator.getInstance().validate(v, Locale.getDefault());
	}
}
