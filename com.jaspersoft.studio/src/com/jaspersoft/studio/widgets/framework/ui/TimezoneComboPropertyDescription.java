/*******************************************************************************
 * Copyright (C) 2013 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.Arrays;
import java.util.TimeZone;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.SelectableComboItemPropertyDescription;

/**
 * Widget that allows to edit the value for a timezone property.
 * It uses a combo to manage the value changes.
 *
 */
public class TimezoneComboPropertyDescription extends SelectableComboItemPropertyDescription<String>{
	
	private static String[][] tzs = null;

	public TimezoneComboPropertyDescription() {
		super();
	}
	
	public TimezoneComboPropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue) {
		super(name, label, description, mandatory, defaultValue, getTimeZones());
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			super.update(c, wip);
		} else {
			boolean isFallback = false;
			Combo localeCombo = (Combo) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v != null){
				localeCombo.setText(v);
			} else if (wip.getFallbackValue() != null) {
				localeCombo.setText(String.valueOf(wip.getFallbackValue()));
				isFallback = true;
			}
			changeFallbackForeground(isFallback, localeCombo);
			cmp.switchToSecondContainer();
		}
	}
	
	@Override
	public TimezoneComboPropertyDescription clone(){
		TimezoneComboPropertyDescription result = new TimezoneComboPropertyDescription();
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

	protected static String[][] getTimeZones() {
		if (tzs == null) {
			String[] tzones = TimeZone.getAvailableIDs();
			Arrays.sort(tzones);
			tzs = new String[tzones.length][2];
			for (int i = 0; i < tzs.length; i++) {
				tzs[i][0] = tzones[i];
				tzs[i][1] = tzones[i];
			}
		}
		return tzs;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		TimezoneComboPropertyDescription result = new TimezoneComboPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), defaultValue);
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(fallbackValue);
		return result;
	}
}
