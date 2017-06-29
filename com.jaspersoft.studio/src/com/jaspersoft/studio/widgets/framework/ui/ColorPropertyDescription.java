/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.swt.events.ColorSelectedEvent;
import com.jaspersoft.studio.swt.events.ColorSelectionListener;
import com.jaspersoft.studio.swt.widgets.WColorPicker;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Property description to handle a color widget, accepting only opaque values
 * 
 * @author Orlandin Marco
 */
public class ColorPropertyDescription<T> extends TextPropertyDescription<T> {

	public ColorPropertyDescription() {
		super();
	}

	public ColorPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	public ColorPropertyDescription(String name, String label, String description, boolean mandatory) {
		super(name, label, description, mandatory);
	}

	@Override
	public void handleEdit(Control txt, IWItemProperty wiProp) {
		super.handleEdit(txt, wiProp);
		if (txt instanceof WColorPicker) {
			String tvalue = Colors.getHexEncodedRGBColor(((WColorPicker) txt).getSelectedColorAsRGB());
			if (isTransaprent())
				tvalue = Colors.getRGBAEncodedRGBColor(((WColorPicker) txt).getSelectedColorAsRGB());
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;

			wiProp.setValue(tvalue, null);
		}
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionEditor = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionEditor);

		final WColorPicker simpleEditor = new WColorPicker(new AlfaRGB(new RGB(0, 0, 0), 0), cmp.getSecondContainer());
		cmp.getSecondContainer().setData(simpleEditor);
		simpleEditor.setHaveTransparency(isTransaprent());
		simpleEditor.addColorSelectionListener(new ColorSelectionListener() {

			@Override
			public void changed(ColorSelectedEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleEditor, wiProp);
			}
		});
		for (Control c : simpleEditor.getChildren()){
			setupContextMenu(c, wiProp);
		}

		setupContextMenu(textExpression, wiProp);
		cmp.switchToSecondContainer();
		return cmp;
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			Text txt = (Text) cmp.getFirstContainer().getData();
			super.update(txt, wip);
			cmp.switchToFirstContainer();
		} else {
			WColorPicker colorPicker = (WColorPicker) cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && defaultValue != null){
				v = defaultValue.toString();
			}
			colorPicker.setColor(new AlfaRGB(Colors.decodeHexStringAsSWTRGB(v), 0));
			colorPicker.setToolTipText(getToolTip());
			cmp.switchToSecondContainer();
		}
	}
	
	/**
	 * Used to know if the transparent values are accepted, can be overridden by subclass
	 * 
	 * @return by default this accept only opaque values, so it always return false
	 */
	protected boolean isTransaprent(){
		return false;
	}
	
	@Override
	public ItemPropertyDescription<T> clone(){
		ColorPropertyDescription<T> result = new ColorPropertyDescription<T>();
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
		ColorPropertyDescription<String> result = new ColorPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue());
		result.setReadOnly(cpd.isReadOnly());
		result.setFallbackValue(cpd.getFallbackValue());
		return result;
	}
}
