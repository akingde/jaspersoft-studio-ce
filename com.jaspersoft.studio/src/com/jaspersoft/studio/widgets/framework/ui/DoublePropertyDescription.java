package com.jaspersoft.studio.widgets.framework.ui;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.ValidatedDecimalFormat;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
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
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		BigDecimal min = null;
		BigDecimal max = null;
		BigDecimal def = null;
		BigDecimal fallBack = null;
		if (cpd.getMin() != null){
			min = new BigDecimal(new Double(cpd.getMin()));
		}
		if (cpd.getMax() != null){
			max = new BigDecimal(new Double(cpd.getMax()));
		}
		if (cpd.getDefaultValue() != null && !cpd.getDefaultValue().isEmpty()){
			def = new BigDecimal(new Double(cpd.getDefaultValue()));
		}
		if (cpd.getFallbackValue() != null && !cpd.getFallbackValue().isEmpty()){
			fallBack = new BigDecimal(new Double(cpd.getFallbackValue()));
		}
		DoublePropertyDescription doubleDesc = new DoublePropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max);
		doubleDesc.setReadOnly(cpd.isReadOnly());
		doubleDesc.setFallbackValue(fallBack);
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
	
	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (txt instanceof NumericText){
			NumericText widget = (NumericText)txt;
			Double floatValue =  widget.getValueAsDouble();
			String tvalue = floatValue != null ? floatValue.toString() : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wiProp);
	}

	@Override
	protected Number convertValue(String v) {
		if (v == null || v.isEmpty()) return null;
		char separator = ValidatedDecimalFormat.DECIMAL_SEPARATOR;
		//convert the separator if necessary, since the internal double always use the dot
		//as separator
		if (separator != '.'){
			v = v.replace(separator, '.');
		} 
		return Double.valueOf(v);
	}
}
