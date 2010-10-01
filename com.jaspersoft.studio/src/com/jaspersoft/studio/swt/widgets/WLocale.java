/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.swt.widgets;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class WLocale extends Composite {
	private CCombo combo;
	private Locale[] locales;

	public WLocale(Composite parent, int style) {
		super(parent, SWT.NONE);
		combo = new CCombo(this, style);
		combo.setItems(getLocales());
		setLayout(new GridLayout());
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
