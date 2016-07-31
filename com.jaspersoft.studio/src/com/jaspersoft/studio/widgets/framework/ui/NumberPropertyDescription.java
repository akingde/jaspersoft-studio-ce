/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.NumberValidator;
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
		Text ctrl = (Text) super.createControl(wiProp, parent);
		ctrl.addVerifyListener(new NumberValidator(min, max, getType()) {

			@Override
			public void verifyText(VerifyEvent e) {
				if (wiProp.isExpressionMode())
					return;
				super.verifyText(e);
			}

		});
		setupContextMenu(ctrl, wiProp);
		return ctrl;
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
