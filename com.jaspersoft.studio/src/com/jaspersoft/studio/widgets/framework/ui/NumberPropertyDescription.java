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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;

public abstract class NumberPropertyDescription<T extends Number> extends TextPropertyDescription<T> {
	
	protected Number min;
	
	protected Number max;


	public NumberPropertyDescription() {
		this(null);
	}
	
	public NumberPropertyDescription(IPropertyEditor propertyEditor) {
		super(propertyEditor);
	}

	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue,
			Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, defaultValue, editor);
		this.min = min;
		this.max = max;
	}

	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, Number min, Number max, IPropertyEditor editor) {
		super(name, label, description, mandatory, editor);
		this.min = min;
		this.max = max;
	}
	
	public abstract Class<?> getType();

	public Number getMin() {
		return min;
	}

	public Number getMax() {
		return max;
	}

	@Override
	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		StackLayout layout = new StackLayout();
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(wiProp, cmp);

		final NumericText cp = createSimpleEditor(cmp);
		cp.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(cp, wiProp);
			}
			
		});
		setupContextMenu(cp, wiProp);
		layout.topControl = cp;
		setupContextMenu(textExpression, wiProp);
		return cmp;
	}
	
	/**
	 * Create the editor control to edit the number directly
	 * 
	 * @param parent the parent of the element
	 * @return a not null NumericText to handle the numeric field
	 */
	protected abstract NumericText createSimpleEditor(Composite parent);
	
	/**
	 * Convert the string into a number for the simple control when necessary
	 */
	protected abstract Number convertValue(String v);
	
	/**
	 * Set the value inside the correct control, if the editor is in 
	 * expression mode or not
	 */
	@Override
	public void update(Control c, IWItemProperty wip) {
		Composite cmp = (Composite) wip.getControl();
		StackLayout layout = (StackLayout) cmp.getLayout();
		if (wip.isExpressionMode()) {
			Text txt = (Text) cmp.getChildren()[0];
			super.update(txt, wip);
			layout.topControl = txt;
		} else {
			NumericText colorPicker = (NumericText) cmp.getChildren()[1];
			layout.topControl = colorPicker;
			String v = wip.getStaticValue();
			colorPicker.setValue(convertValue(v));
		}
		cmp.layout();
	}

	@Override
	public String getToolTip() {
		String tt = super.getToolTip();
		if (getMin() != null)
			tt += "\nmin: " + getMin();
		if (getMax() != null)
			tt += "\nmax: " + getMax();
		return tt;
	}
}
