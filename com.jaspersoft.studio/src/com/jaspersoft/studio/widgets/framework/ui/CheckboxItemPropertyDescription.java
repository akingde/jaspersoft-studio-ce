/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;

/**
 * Property Description to show a checkbox and store the true and false value for the 
 * property
 */
public class CheckboxItemPropertyDescription extends AbstractExpressionPropertyDescription<Boolean> {

	// Constants for true / false values
	// Note: HC understands only these string values
	public static final String TRUE_VALUE = "true";
	public static final String FALSE_VALUE = "false";
	
	public CheckboxItemPropertyDescription() {
		super();
	}

	public CheckboxItemPropertyDescription(String name, String label, String description, boolean mandatory, boolean defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}
	
	public void handleEdit(Control txt, IWItemProperty wProp) {
		super.handleEdit(txt, wProp);
		if (txt instanceof Button) {
			boolean selected = ((Button) txt).getSelection();
			String tvalue = String.valueOf(selected);
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wProp.setValue(tvalue, null);
		}
	}
	
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lazyCreateExpressionControl(wiProp, cmp);

		final Button simpleControl = new Button(cmp.getSecondContainer(), SWT.CHECK);
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		
		simpleControl.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
		});
		setupContextMenu(simpleControl, wiProp);
		cmp.switchToSecondContainer();
		return cmp;
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		boolean isFallback = false;
		if (wip.isExpressionMode()) {
			lazyCreateExpressionControl(wip, cmp);
			Text txt = (Text) cmp.getFirstContainer().getData();
			super.update(txt, wip);
			cmp.switchToFirstContainer();
		} else {
			Button button = (Button) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			button.setSelection(Boolean.parseBoolean(Misc.nvl(v)));
			button.setToolTipText(getToolTip());
			changeFallbackForeground(isFallback, button);
			cmp.switchToSecondContainer();
		}
	}
	
	protected boolean verifyValue(String value) {
		return TRUE_VALUE.equalsIgnoreCase(value) || FALSE_VALUE.equalsIgnoreCase(value);
	}
	
	@Override
	public CheckboxItemPropertyDescription clone(){
		CheckboxItemPropertyDescription result = new CheckboxItemPropertyDescription();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		boolean defaultValue = false;
		boolean fallbackValue = false;
		if (verifyValue(cpd.getDefaultValue())){
			defaultValue = Boolean.parseBoolean(cpd.getDefaultValue());
		}
		if (verifyValue(cpd.getFallbackValue())){
			fallbackValue = Boolean.parseBoolean(cpd.getFallbackValue());
		}
		CheckboxItemPropertyDescription result = new CheckboxItemPropertyDescription(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), defaultValue);
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(fallbackValue);
		return result;
	}
}
