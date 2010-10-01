package com.jaspersoft.studio.swt.widgets;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
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
		return locales[combo.getSelectionIndex()];
	}
}
