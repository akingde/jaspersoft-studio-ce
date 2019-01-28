/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.widgets.CustomReadOnlyCombo;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Widget used to provide a combo where it is not possible to type
 */
public class SelectableComboItemPropertyDescription<T> extends ComboItemPropertyDescription<T> {
	


	public SelectableComboItemPropertyDescription() {
		super();
	}

	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, String[] values) {
		super(name, label, description, mandatory, defaultValue, values);
	}

	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, String[] values) {
		super(name, label, description, mandatory, values);
	}
	
	public SelectableComboItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,	String[][] keyValues) {
		super(name, label, description, mandatory, defaultValue, keyValues);
	}
	
	@Override
	protected Combo createComboControl(Composite parent) {
		CustomReadOnlyCombo result = (CustomReadOnlyCombo)super.createComboControl(parent);
		result.setReadOnly(true);
		return result;
	}
	
	@Override
	public ItemPropertyDescription<T> clone(){
		SelectableComboItemPropertyDescription<T> result = new SelectableComboItemPropertyDescription<T>();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.keyValues = keyValues;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public void handleEdit(Control txt, IWItemProperty wProp) {
		if (txt instanceof Combo) {
			int indx = ((Combo) txt).getSelectionIndex();
			String tvalue = indx >= 0 && indx < keyValues.length ? keyValues[indx][0] : null;
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wProp);
	}
	
	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			super.update(c, wip);
		} else {
			boolean isFallback = false;
			CustomReadOnlyCombo combo = (CustomReadOnlyCombo) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			boolean found = false;
			for (int i = 0; i < keyValues.length; i++) {
				if (keyValues[i][0].equals(v)) {
					combo.select(i);
					found = true;
					break;
				}
			}
			if (!found) {
				combo.setText(v);
				//if the value is not found but it is null, and it is not mandatory then it is acceptable as value, so mark it as found
				if (!isMandatory() && v == null) {
					found = true;
				}
			}
			combo.setError(!found);
			combo.setToolTipText(getToolTip());
			changeFallbackForeground(isFallback, combo);
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		if (cpd.getComboOptions() != null) {
			String[][] opts = cpd.getComboOptions();
			String[][] i18nOpts = new String[opts.length][2];
			for (int i = 0; i < opts.length; i++) {
				i18nOpts[i][0] = opts[i][0];
				i18nOpts[i][1] = cd.getLocalizedString(opts[i][1]);
			}
			SelectableComboItemPropertyDescription<String> result = new SelectableComboItemPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), i18nOpts);
			result.setReadOnly(cpd.isReadOnly());
			result.setFallbackValue(cpd.getFallbackValue());
			return result;
		}
		return null;
	}
}
