/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.Misc;

public class ItemPropertyDescription<T> {
	private String name;
	private String label;
	private String description;
	private boolean mandatory;
	private T defaultValue;

	public ItemPropertyDescription(String name, String description, boolean mandatory) {
		this(name, name, description, mandatory, null);
	}

	public ItemPropertyDescription(String name, String label, String description, boolean mandatory) {
		this(name, label, description, mandatory, null);
	}

	public ItemPropertyDescription(String name, String description, boolean mandatory, T defaultValue) {
		this(name, name, description, mandatory, defaultValue);
	}

	public ItemPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue) {
		super();
		this.name = name;
		this.label = label;
		this.description = description;
		this.mandatory = mandatory;
		this.defaultValue = defaultValue;
	}

	public boolean isMultiline() {
		return false;
	}

	public String getLabel() {
		return label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public String getDefaultValueString() {
		if (defaultValue != null)
			return defaultValue.toString();
		return ""; //$NON-NLS-1$
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Class<?> getType() {
		if (defaultValue != null)
			return defaultValue.getClass();
		return String.class;
	}

	public String toSimpleString(String original) {
		return original;
	}

	public void handleEdit(Control txt, StandardItemProperty value) {
		if (value == null)
			return;
		if (txt instanceof Text) {
			String tvalue = ((Text) txt).getText();
			if (tvalue != null && tvalue.isEmpty())
				tvalue = null;
			if (value.getValueExpression() != null)
				((JRDesignExpression) value.getValueExpression()).setText(tvalue);
			else
				value.setValue(tvalue);
		}
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		final Text textExpression = new Text(parent, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		textExpression.setLayoutData(new GridData(GridData.FILL_BOTH));
		textExpression.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = textExpression.getSelection();

				handleEdit(textExpression, wiProp.getValue());
				wiProp.setValue(wiProp.getValue());
				// if (!textExpression.isDisposed())
				textExpression.setSelection(p);
			}
		});
		return textExpression;
	}

	public void setValue(Control c, IWItemProperty wip) {
		if (c instanceof Text) {
			Text txtExpr = (Text) c;
			String txt = wip.getLabelProvider().getText(wip.getValue());
			txt = toSimpleString(txt);
			Point oldSelection = txtExpr.getSelection();

			txtExpr.setText(txt);

			oldSelection.x = Math.max(txt.length(), oldSelection.x);
			oldSelection.y = Math.max(txt.length(), oldSelection.y);
			txtExpr.setSelection(oldSelection);

			String tooltip = "";
			if (!Misc.isNullOrEmpty(txt))
				tooltip += "\n\n" + txt;
			tooltip += "\n\n" + getDescription();
			txtExpr.setToolTipText(tooltip.trim());
		}
	}
}
