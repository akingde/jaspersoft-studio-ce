package com.jaspersoft.studio.widgets.framework.ui;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class IntegerPropertyDescription extends NumberPropertyDescription<Integer> {
	
	public IntegerPropertyDescription() {
		this(null);
	}
	
	public IntegerPropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}
	
	public IntegerPropertyDescription(String name, String label, String description, boolean mandatory,  Integer defaultValue, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, min, max, editor);
	}
	
	public IntegerPropertyDescription(String name, String label, String description, boolean mandatory, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, min, max, editor);
	}
	
	@Override
	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return Integer.class;
	}
	
	@Override
	public ItemPropertyDescription<Integer> clone(IPropertyEditor editor){
		IntegerPropertyDescription result = new IntegerPropertyDescription(editor);
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
		Integer min = null;
		Integer max = null;
		Integer def = null;
		if (cpd.getMin() != null){
			min = new Integer(cpd.getMin());
		}
		if (cpd.getMax() != null){
			max = new Integer(cpd.getMax());
		}
		if (cpd.getDefaultValue() != null){
			def = new Integer(cpd.getDefaultValue());
		}
		IntegerPropertyDescription intDesc = new IntegerPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), def, min, max, editor);
		intDesc.setReadOnly(cpd.isReadOnly());
		return intDesc;
	}
}
