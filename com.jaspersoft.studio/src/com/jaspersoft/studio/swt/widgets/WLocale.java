/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import net.sf.jasperreports.eclipse.util.Misc;

public class WLocale extends Composite {
	private Combo combo;
	public static Locale[] locales;
	public static String[] strLocales;

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

	public void addModifyListener(ModifyListener m) {
		combo.addModifyListener(m);
	}

	public void removeModifyListener(ModifyListener m) {
		combo.removeModifyListener(m);
	}

	public void addSelectionListener(SelectionListener m) {
		combo.addSelectionListener(m);
	}

	public void removeSelectionListener(SelectionListener m) {
		combo.removeSelectionListener(m);
	}

	public Combo getCombo() {
		return combo;
	}

	public static String[] getLocales() {
		if (locales == null) {
			locales = Locale.getAvailableLocales();
			strLocales = new String[locales.length];
			for (int i = 0; i < strLocales.length; i++)
				strLocales[i] = locales[i].getDisplayName();
			Arrays.sort(strLocales);
		}
		return strLocales;
	}

	public void setSelection(Locale locale) {
		int index;
		if (locale == null)
			index = getIndexFromLocale(Locale.getDefault());
		else
			index = getIndexFromLocale(locale);
		combo.select(index);
		if (index < 0 && locale != null)
			combo.setText(locale.toString());
	}

	public Locale getLocale() {
		int selectionIndex = combo.getSelectionIndex();
		if (selectionIndex < 0) {
			if (Misc.isNullOrEmpty(combo.getText()))
				return Locale.getDefault();
			return LocaleUtils.toLocale(combo.getText());
		} else {
			String strLocale = strLocales[combo.getSelectionIndex()];
			for (int i = 0; i < locales.length; i++) {
				if (locales[i].getDisplayName().equals(strLocale))
					return locales[i];
			}
			return Locale.getDefault();
		}
	}

	/**
	 * This returns the list index for a given locale.
	 * 
	 * @param locale
	 * @return int index
	 */
	private int getIndexFromLocale(Locale locale) {
		int returnedIndex = -1;
		if (locale != null) {
			for (int i = 0; i < strLocales.length; i++) {
				if (strLocales[i].equals(locale.getDisplayName()))
					returnedIndex = i;
			}
		}
		return returnedIndex;
	}
}
