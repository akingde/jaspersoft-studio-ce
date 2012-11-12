/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

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

	private Locale[] locales;
	private String[] strLocales;
	private ListViewer listViewer;
	private List list;
	
	/**
	 * Create an instance of WLocaleList, a single select list of available locales.
	 * @param parent
	 * @param style
	 */
	public WLocaleList(Composite parent, int style) {
		
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		listViewer = new ListViewer(this, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		locales = Locale.getAvailableLocales();
		initList();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private void initList() {
		
		// extract all locales names into a String[]...
		strLocales = new String[locales.length];
		for (int i = 0; i < locales.length; i++) {
			strLocales[i] = locales[i].getDisplayName();
		}
		// ...and sort the array...
		Arrays.sort(strLocales);
		
		// ...then fill the list
		for (String strLocale : strLocales) {
			
			list.add(strLocale);
		}
	}
	
	/**
	 * This returns the list index for a given locale.
	 * @param locale
	 * @return int index
	 */
	private int getIndexFromLocale(Locale locale) {
		
		int returnedIndex = -1;
		
		if (locale != null) {
			for (int i = 0; i < strLocales.length; i++) {
				if(strLocales[i].equals( locale.getDisplayName() )) {
					returnedIndex = i;
				}
			}
		}
		
		return returnedIndex;
	}
	
	/**
	 * Set the selection to a given locale.
	 * If locale is null or the list does not contain it,
	 * the default locale value is selected.
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
	 * Return the selected locale from the list.
	 * If the list has no selected locale,
	 * it returns the default locale.
	 * @return locale
	 */
	public Locale getSelectedLocale(){
		
		int selectionIndex = list.getSelectionIndex();
		if (selectionIndex < 0) {
			return Locale.getDefault();
		} else {
			String strLocale = strLocales[list.getSelectionIndex()];
			for (int i = 0; i < locales.length; i++) {
				if (locales[i].getDisplayName().equals(strLocale)) {
					return locales[i];
				}
			}
			return null;
		}
	}

	/**
	 * Check if the list contains the given locale: true if it does
	 * and false if not.
	 * @param locale
	 * @return true or false
	 */
	public boolean contains(Locale locale) {
		String displayName = locale.getDisplayName();
		if (displayName != null && displayName.length() > 0) {
			for (String strLocale : strLocales) {
				if (strLocale.equals(displayName)) return true;
			}
		}
		return false;
	}

	/**
	 * Check if the list has a selected locale.
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
	 * @param selectionAdapter
	 */
	public void setListSelectionListener(SelectionAdapter selectionAdapter) {
		list.addSelectionListener(selectionAdapter);
	}
}
