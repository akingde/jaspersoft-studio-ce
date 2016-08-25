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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.swt.widgets.NumericText;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;

public abstract class NumberPropertyDescription<T extends Number> extends TextPropertyDescription<T> {
	
	protected Number min;
	
	protected Number max;

	public NumberPropertyDescription() {
	}
	
	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, T defaultValue, Number min, Number max) {
		super(name, label, description, mandatory, defaultValue);
		this.min = min;
		this.max = max;
	}

	public NumberPropertyDescription(String name, String label, String description, boolean mandatory, Number min, Number max) {
		super(name, label, description, mandatory);
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
		DoubleControlComposite cmp = new DoubleControlComposite(parent, SWT.NONE);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control expressionControl = super.createControl(wiProp, cmp.getFirstContainer());
		cmp.getFirstContainer().setData(expressionControl);

		final NumericText simpleControl = createSimpleEditor(cmp.getSecondContainer());
		cmp.getSecondContainer().setData(simpleControl);
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.verticalAlignment = SWT.CENTER;
		textData.grabExcessVerticalSpace = true;
		simpleControl.setLayoutData(textData);
		simpleControl.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (wiProp.isRefresh())
					return;
				handleEdit(simpleControl, wiProp);
			}
			
		});
		setupContextMenu(simpleControl, wiProp);
		setupContextMenu(textExpression, wiProp);
		cmp.switchToFirstContainer();
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
		DoubleControlComposite cmp = (DoubleControlComposite) wip.getControl();
		if (wip.isExpressionMode()) {
			Text expressionControl = (Text) cmp.getFirstContainer().getData();
			super.update(expressionControl, wip);
			cmp.switchToFirstContainer();
		} else {
			boolean isFallback = false;
			NumericText simpleControl = (NumericText)cmp.getSecondContainer().getData();
			String v = wip.getStaticValue();
			if (v == null && wip.getFallbackValue() != null){
				v = wip.getFallbackValue().toString();
				isFallback = true;
			}
			simpleControl.setValue(convertValue(Misc.nvl(v)));
			changeFallbackForeground(isFallback, simpleControl);
			cmp.switchToSecondContainer();
		}
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
