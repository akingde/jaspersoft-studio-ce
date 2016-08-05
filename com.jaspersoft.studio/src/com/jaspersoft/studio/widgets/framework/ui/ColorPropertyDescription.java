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
import org.eclipse.swt.custom.StackLayout;
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
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
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
	
	public ColorPropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}

	public ColorPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, editor);
	}

	public ColorPropertyDescription(String name, String label, String description, boolean mandatory, IPropertyEditor editor) {
		super(name, label, description, mandatory, editor);
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
		Composite cmp = new Composite(parent, SWT.NONE);
		StackLayout layout = new StackLayout();
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(wiProp, cmp);

		final WColorPicker cp = new WColorPicker(new AlfaRGB(new RGB(0, 0, 0), 0), cmp);
		cp.setHaveTransparency(isTransaprent());
		cp.addColorSelectionListener(new ColorSelectionListener() {

			@Override
			public void changed(ColorSelectedEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(cp, wiProp);
			}
		});
		for (Control c : cp.getChildren())
			setupContextMenu(c, wiProp);
		layout.topControl = cp;

		setupContextMenu(textExpression, wiProp);
		return cmp;
	}

	@Override
	public void update(Control c, IWItemProperty wip) {
		Composite cmp = (Composite) wip.getControl();
		StackLayout layout = (StackLayout) cmp.getLayout();
		if (wip.isExpressionMode()) {
			Text txt = (Text) cmp.getChildren()[0];
			super.update(txt, wip);
			layout.topControl = txt;
		} else {
			WColorPicker colorPicker = (WColorPicker) cmp.getChildren()[1];
			layout.topControl = colorPicker;
			String v = wip.getStaticValue();
			colorPicker.setColor(new AlfaRGB(Colors.decodeHexStringAsSWTRGB(v), 0));
		}
		cmp.layout();
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
	public ItemPropertyDescription<T> clone(IPropertyEditor editor){
		ColorPropertyDescription<T> result = new ColorPropertyDescription<T>(editor);
		result.defaultValue = defaultValue;
		result.description = description;
		result.jConfig = jConfig;
		result.label = label;
		result.mandatory = mandatory;
		result.name = name;
		result.readOnly = readOnly;
		return result;
	}
	
	@Override
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig, IPropertyEditor editor) {
		ColorPropertyDescription<?> result = new ColorPropertyDescription<String>(cpd.getName(), cd.getLocalizedString(cpd.getLabel()), cd.getLocalizedString(cpd.getDescription()), cpd.isMandatory(), cpd.getDefaultValue(), editor);
		result.setReadOnly(cpd.isReadOnly());
		return result;
	}
}
