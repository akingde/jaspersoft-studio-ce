/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class ItemPropertyDescription<T> {
	protected String name;
	protected String label;
	protected String description;
	protected boolean mandatory;
	protected T defaultValue;
	protected boolean readOnly;
	protected JasperReportsConfiguration jConfig;
	protected Text textExpression;

	public ItemPropertyDescription() {
		super();
	}

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

	public void setjConfig(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
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

	// Flag used to overcome the problem of focus events in Mac OS X
	// - JSS Bugzilla 42999
	// - Eclipse Bug 383750
	// It makes sense only on E4 platform and Mac OS X operating systems.
	// DO NOT USE THIS FLAG FOR OTHER PURPOSES.
	private boolean editHappened = false;

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		textExpression = new Text(parent, SWT.BORDER);
		textExpression.setLayoutData(new GridData(GridData.FILL_BOTH));
		InputHistoryCache.bindText(textExpression, name);
		if (UIUtil.isMacAndEclipse4()) {
			textExpression.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					if (wiProp.isRefresh())
						return;
					editHappened = true;
				}
			});
		}
		textExpression.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (UIUtil.isMacAndEclipse4() && editHappened) {
					if (((Text) e.getSource()).isDisposed())
						return;
					setValue(((Text) e.getSource()), wiProp);
					editHappened = false;
				}
			}

		});
		textExpression.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (wiProp.isRefresh())
					return;
				Point p = ((Text) e.getSource()).getSelection();

				StandardItemProperty v = wiProp.getValue();
				if (v == null)
					v = getNewProperty(getName(), null, null);
				handleEdit(((Text) e.getSource()), v);
				wiProp.setValue(v);
				// if (!textExpression.isDisposed())
				((Text) e.getSource()).setSelection(p);
			}
		});
		setupContextMenu(textExpression, wiProp);

		return textExpression;
	}

	public StandardItemProperty getNewProperty(String name, String value, JRExpression valueExpression) {
		return new StandardItemProperty(name, null, null);
	}

	protected void setupContextMenu(final Control c, final IWItemProperty wiProp) {
		if (getDefaultValue() != null) {
			Menu controlMenu = c.getMenu();
			if (controlMenu == null) {
				controlMenu = new Menu(c);
				c.setMenu(controlMenu);
			}
			for (MenuItem mi : controlMenu.getItems())
				if (mi.getText().equals(Messages.ASPropertyWidget_0))
					return;

			MenuItem refreshItem = new MenuItem(controlMenu, SWT.NONE);
			refreshItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					StandardItemProperty v = getNewProperty(getName(), getDefaultValueString(), null);
					wiProp.setValue(v);
				}
			});
			refreshItem.setText(Messages.ASPropertyWidget_0);
		}
		if (!isMandatory()) {
			Menu controlMenu = c.getMenu();
			if (controlMenu == null) {
				controlMenu = new Menu(c);
				c.setMenu(controlMenu);
			}
			for (MenuItem mi : controlMenu.getItems())
				if (mi.getText().equals(Messages.ASPropertyWidget_1))
					return;

			MenuItem refreshItem = new MenuItem(controlMenu, SWT.NONE);
			refreshItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					StandardItemProperty v = wiProp.getValue();
					if (v != null) {
						v.setValue(null);
						v.setValueExpression(null);
					}
					wiProp.setValue(v);
				}
			});
			refreshItem.setText(Messages.ASPropertyWidget_1);
		}

	}

	public void setValue(Control c, IWItemProperty wip) {
		if (c instanceof Text) {
			Text txtExpr = (Text) c;
			if (wip.getValue() == null)
				wip.setValue(getNewProperty(getName(), null, null));
			String txt = wip.getLabelProvider().getText(wip.getValue());
			txt = toSimpleString(txt);
			Point oldSelection = txtExpr.getSelection();

			boolean r = wip.isRefresh();
			wip.setRefresh(true);
			txtExpr.setText(txt);
			wip.setRefresh(r);

			oldSelection.x = Math.min(txt.length(), oldSelection.x);
			oldSelection.y = Math.min(txt.length(), oldSelection.y);
			txtExpr.setSelection(oldSelection);

			String tooltip = "";
			if (!Misc.isNullOrEmpty(txt))
				tooltip += "\n\n" + txt;
			tooltip += "\n" + getToolTip();
			txtExpr.setToolTipText(tooltip.trim());
		}
	}

	public String getToolTip() {
		String tt = Misc.nvl(getDescription());
		tt += "\n" + (isMandatory() ? "Mandatory" : "Optional");
		if (!Misc.isNullOrEmpty(getDefaultValueString()))
			tt += "\nDefault: " + getDefaultValueString();
		return tt;
	}

}
