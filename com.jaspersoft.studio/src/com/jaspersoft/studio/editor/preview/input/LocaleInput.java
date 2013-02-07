/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.util.Locale;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.swt.widgets.WLocale;

public class LocaleInput extends ADataInput {
	private WLocale wlocal;

	public boolean isForType(Class<?> valueClass) {
		return Locale.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (Locale.class.isAssignableFrom(param.getValueClass())) {
			wlocal = new WLocale(parent, SWT.DROP_DOWN | SWT.BORDER);
			wlocal.setToolTipText(param.getDescription());
			wlocal.addFocusListener(focusListener);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			wlocal.setLayoutData(gd);
			updateInput();
			wlocal.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					updateModel(wlocal.getLocale());
				}

			});
		}
	}

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof Locale) {
			Locale locale = (Locale) value;
			wlocal.setSelection(locale);
			wlocal.setToolTipText(locale.toString());
		}
	}
}
