/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.LocaleUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.widgets.WLocale;

public class LocaleInput extends ADataInput {
	private WLocale wlocal;

	public boolean isForType(Class<?> valueClass) {
		return Locale.class.isAssignableFrom(valueClass);
	}

	private String tooltip;

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (Locale.class.isAssignableFrom(param.getValueClass())) {
			wlocal = new WLocale(parent, SWT.DROP_DOWN | SWT.BORDER);
			tooltip = param.getDescription() != null ? param.getDescription() + "\n" : "";
			wlocal.setToolTipText(tooltip);
			wlocal.addFocusListener(focusListener);
			wlocal.addTraverseListener(keyListener);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			wlocal.setLayoutData(gd);
			updateInput();
			wlocal.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateModel(wlocal.getLocale());
					updateInput();
				}
			});
			for (Control c : wlocal.getChildren())
				setNullable(param, c);
		}
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof String)
			value = LocaleUtils.toLocale((String) value);
		if (value != null && value instanceof Locale) {
			Locale locale = (Locale) value;
			wlocal.setSelection(locale);
			wlocal.setToolTipText(tooltip + locale.toString());
		}
		setDecoratorNullable(param);
	}
}
