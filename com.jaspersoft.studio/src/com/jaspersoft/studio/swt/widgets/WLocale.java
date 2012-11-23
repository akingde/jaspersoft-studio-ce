/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

public class WLocale extends Composite {
	private Combo combo;
	private Locale[] locales;

	public WLocale(Composite parent, int style) {
		super(parent, SWT.NONE);
		combo = new Combo(this, style);
		combo.setItems(getLocales());
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		setLayout(layout);
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	public void setToolTipText(String string) {
		super.setToolTipText(string);
		combo.setToolTipText(string);
	}

	public void addSelectionListener(SelectionListener m) {
		combo.addSelectionListener(m);
	}

	public void removeSelectionListener(SelectionListener m) {
		combo.removeSelectionListener(m);
	}

	private String[] getLocales() {
		locales = Locale.getAvailableLocales();
		String[] str = new String[locales.length];
		for (int i = 0; i < str.length; i++)
			str[i] = locales[i].getDisplayName();
		return str;
	}

	public void setSelection(Locale locale) {
		for (int i = 0; i < locales.length; i++) {
			if (locale.equals(locales[i])) {
				combo.select(i);
				break;
			}
		}
	}

	public Locale getLocale() {
		if (combo.getSelectionIndex() >= 0 && combo.getSelectionIndex() < locales.length)
			return locales[combo.getSelectionIndex()];
		return Locale.getDefault();
	}
}
