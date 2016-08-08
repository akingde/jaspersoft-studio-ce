package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class FloatPropertyDescription extends NumberPropertyDescription<Float> {
	
	public FloatPropertyDescription() {
		this(null);
	}
	
	public FloatPropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}
	
	public FloatPropertyDescription(String name, String label, String description, boolean mandatory,  Float defaultValue, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, min, max, editor);
	}
	
	public FloatPropertyDescription(String name, String label, String description, boolean mandatory, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, min, max, editor);
	}
	
	@Override
	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Float.class;
	}
	
	@Override
	public ItemPropertyDescription<Float> clone(IPropertyEditor editor){
		FloatPropertyDescription result = new FloatPropertyDescription(editor);
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
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig, IPropertyEditor editor) {
		Float min = null;
		Float max = null;
		Float def = null;
		if (cpd.getMin() != null){
			min = new Float(cpd.getMin());
		}
		if (cpd.getMax() != null){
			max = new Float(cpd.getMax());
		}
		if (cpd.getDefaultValue() != null){
			def = new Float(cpd.getDefaultValue());
		}
		FloatPropertyDescription floatDesc = new FloatPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max, editor);
		floatDesc.setReadOnly(cpd.isReadOnly());
		return floatDesc;
	}
	
	@Override
	protected NumericText createSimpleEditor(Composite parent) {
		NumericText text = new NumericText(parent, SWT.BORDER, 4, 6);
		text.setRemoveTrailZeroes(true);
		Number max = getMax() != null ? getMax() : Float.MAX_VALUE;
		Number min = getMin() != null ? getMin() : Float.MIN_VALUE;
		text.setMaximum(max.doubleValue());
		text.setMinimum(min.doubleValue());
		return text;
	}

	@Override
	protected Number convertValue(String v) {
		if (v == null || v.isEmpty()) return null;
		return Float.valueOf(v);
	}
}
