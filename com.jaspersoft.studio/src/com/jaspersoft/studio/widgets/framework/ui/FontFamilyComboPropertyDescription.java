/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * Widget that allows to edit the value for a boolean property.
 * It uses a combo to manage the value changes.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class FontFamilyComboPropertyDescription extends ComboItemPropertyDescription<String>{
	
	public FontFamilyComboPropertyDescription() {
		super();
	}
	
	public FontFamilyComboPropertyDescription(String name, String label, String description, boolean mandatory, String defaultValue, String[][] keyValues) {
		super(name, label, description, mandatory, defaultValue, keyValues);
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			super.update(c, wip);
		} else {
			boolean isFallback = false;
			Combo fontCombo = (Combo) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v != null){
				fontCombo.setText(v);
			} else if (wip.getFallbackValue() != null){
				fontCombo.setText(wip.getFallbackValue().toString());
				isFallback = true;
			}
			changeFallbackForeground(isFallback, fontCombo);
			cmp.switchToSecondContainer();
		}
	}

	@Override
	public FontFamilyComboPropertyDescription clone(){
		FontFamilyComboPropertyDescription result = new FontFamilyComboPropertyDescription();
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
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		// update the details on the fonts
		String[][] fonts = convert2KeyValue(jConfig.getFontList());
		FontFamilyComboPropertyDescription result = new FontFamilyComboPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), fonts);
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
