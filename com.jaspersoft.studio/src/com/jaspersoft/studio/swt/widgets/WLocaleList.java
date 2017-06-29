/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.Arrays;
import java.util.Locale;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class WLocaleList extends Composite {

	private ListViewer listViewer;
	private List list;

	/**
	 * Create an instance of WLocaleList, a single select list of available locales.
	 * 
	 * @param parent
	 * @param style
	 */
	public WLocaleList(Composite parent, int style) {

		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		initList();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private void initList() {
		// extract all locales names into a String[]...
		String[] strLocales = WLocale.getLocales();

		// ...and sort the array...
		Arrays.sort(strLocales);

		// ...then fill the list
		for (String strLocale : strLocales) {
			list.add(strLocale);
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
			for (int i = 0; i < WLocale.strLocales.length; i++) {
				if (WLocale.strLocales[i].equals(locale.getDisplayName())) {
					returnedIndex = i;
				}
			}
		}

		return returnedIndex;
	}

	/**
	 * Set the selection to a given locale. If locale is null or the list does not contain it, the default locale value is
	 * selected.
	 * 
	 * @param locale
	 */
	public void setSelection(Locale locale) {

		int index;

		if (locale == null) {
			index = getIndexFromLocale(Locale.getDefault());
		} else {
			index = getIndexFromLocale(locale);
		}

		list.setSelection(index);
	}

	/**
	 * Return the selected locale from the list. If the list has no selected locale, it returns the default locale.
	 * 
	 * @return locale
	 */
	public Locale getSelectedLocale() {
		int selectionIndex = list.getSelectionIndex();
		if (selectionIndex < 0) {
			return Locale.getDefault();
		} else {
			String strLocale = WLocale.strLocales[list.getSelectionIndex()];
			for (int i = 0; i < WLocale.locales.length; i++) {
				if (WLocale.locales[i].getDisplayName().equals(strLocale)) {
					return WLocale.locales[i];
				}
			}
			return null;
		}
	}

	/**
	 * Check if the list contains the given locale: true if it does and false if not.
	 * 
	 * @param locale
	 * @return true or false
	 */
	public boolean contains(Locale locale) {
		String displayName = locale.getDisplayName();
		if (displayName != null && displayName.length() > 0) {
			for (String strLocale : WLocale.strLocales) {
				if (strLocale.equals(displayName))
					return true;
			}
		}
		return false;
	}

	/**
	 * Check if the list has a selected locale.
	 * 
	 * @return true or false
	 */
	public boolean hasSelectedLocale() {
		if (list.getSelection().length > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Set the list selection listeners
	 * 
	 * @param selectionAdapter
	 */
	public void setListSelectionListener(SelectionAdapter selectionAdapter) {
		list.addSelectionListener(selectionAdapter);
	}
}
