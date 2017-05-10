/*******************************************************************************
 * Copyright (C) 2013 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Widget that allows to edit the value for a Locale property
 * It uses a combo to manage the value changes.
 *
 */
public class LocaleComboPropertyDescription extends SelectableComboItemPropertyDescription<Boolean>{
	
	private static String[][] locs = null;

	public LocaleComboPropertyDescription() {
		super();
	}
	
	public LocaleComboPropertyDescription(String name, String label, String description, boolean mandatory, boolean defaultValue, String[][] keyValues) {
		super(name, label, description, mandatory, defaultValue, keyValues);
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
	public LocaleComboPropertyDescription clone(){
		LocaleComboPropertyDescription result = new LocaleComboPropertyDescription();
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

	protected String[][] getLocales() {
		if (locs == null) {
			Locale[] locales = Locale.getAvailableLocales();
			sortLocalesOnToString(locales);
			locs = new String[locales.length][2];
			for (int i = 0; i < locs.length; i++) {
				locs[i][0] = locales[i].getDisplayName();
				locs[i][1] = locales[i].toString();
			}
		}
		return locs;
	}

	private void sortLocalesOnToString(Locale[] locales) {
		Comparator<Locale> localeComparator = new Comparator<Locale>() {
			public int compare(Locale locale1, Locale locale2) {
				return locale1.getDisplayName().compareTo(locale2.getDisplayName());
			}
		};
		Arrays.sort(locales, localeComparator);
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		String[][] i18nOpts = getLocales();
		LocaleComboPropertyDescription result = new LocaleComboPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), defaultValue, i18nOpts);
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(fallbackValue);
		return result;
	}
}
