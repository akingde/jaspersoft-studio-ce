/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

public class TextPropertyDescription<T> extends AbstractExpressionPropertyDescription<T> {
	
	public TextPropertyDescription() {
	}

	public TextPropertyDescription(String name, String description, boolean mandatory) {
		this(name, name, description, mandatory, null);
	}

	public TextPropertyDescription(String name, String label, String description, boolean mandatory) {
		this(name, label, description, mandatory, null);
	}

	public TextPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	public void handleEdit(Control txt, IWItemProperty wiProp) {
		if (wiProp == null)
			return;
		if (!wiProp.isExpressionMode() && txt instanceof Text){
			String tvalue = ((Text) txt).getText();
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			wiProp.setValue(tvalue, null);
		} else super.handleEdit(txt, wiProp);
	}

	// Flag used to overcome the problem of focus events in Mac OS X
	// - JSS Bugzilla 42999
	// - Eclipse Bug 383750
	// It makes sense only on E4 platform and Mac OS X operating systems.
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//create the expression  control
		lazyCreateExpressionControl(wiProp, cmp);
		
		//create the simple control
		final Text simpleControl =  new Text(cmp.getSecondContainer(), SWT.BORDER);
		cmp.getSecondContainer().setData(simpleControl);
		cmp.setSimpleControlToHighlight(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		simpleControl.setLayoutData(textData);
		simpleControl.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
		});
		// Flag used to overcome the problem of focus events in Mac OS X
		// - JSS Bugzilla 42999
		// - Eclipse Bug 383750
		// It makes sense only on E4 platform and Mac OS X operating systems.
		simpleControl.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (UIUtil.isMacAndEclipse4()) {
					if (((Text) e.getSource()).isDisposed())
						return;
					wiProp.updateWidget(false);
				}
			}

		});

		if (isReadOnly()){
			simpleControl.setEnabled(false);
		} else {
			setupContextMenu(simpleControl, wiProp);
		}
		
		cmp.switchToFirstContainer();
		return cmp;
	}

	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()){
			lazyCreateExpressionControl(wip, cmp);
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			Text txtValue = (Text)cmp.getSecondContainer().getData();
			String txt;
			boolean isFallback = false;
			if (wip.getStaticValue() != null){
				txt = wip.getStaticValue();
			} else if (wip.getFallbackValue() != null){
				txt = Misc.nvl(wip.getFallbackValue().toString());
				isFallback = true;
			} else {
				txt = "";
			}
			
			Point oldSelection = txtValue.getSelection();

			txtValue.setText(txt);
			changeFallbackForeground(isFallback, txtValue);

			oldSelection.x = Math.min(txt.length(), oldSelection.x);
			oldSelection.y = Math.min(txt.length(), oldSelection.y);
			txtValue.setSelection(oldSelection);

			txtValue.setToolTipText(getToolTip());
			
			cmp.switchToSecondContainer();
		}
	}

	@Override
	public ItemPropertyDescription<T> clone(){
		TextPropertyDescription<T> result = new TextPropertyDescription<T>();
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		result.fallbackValue = fallbackValue;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig) {
		TextPropertyDescription<String> result = new TextPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
