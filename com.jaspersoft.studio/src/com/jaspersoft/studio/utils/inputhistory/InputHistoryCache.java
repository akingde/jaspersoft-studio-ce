/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.inputhistory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.fieldassist.AutoCompleteField;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Text;

public class InputHistoryCache {
	private static Map<Object, Set<String>> cache = new HashMap<Object, Set<String>>();
	private static final String[] EMPTYARRAY = new String[0];

	public static String[] get(Object key) {
		Set<String> set = cache.get(key);
		if (set == null)
			return EMPTYARRAY;
		return set.toArray(new String[set.size()]);
	}

	public static void put(Object key, String value) {
		Set<String> set = cache.get(key);
		if (set == null) {
			set = new HashSet<String>();
			cache.put(key, set);
		}
		((HashSet<String>) set).add(value);
	}

	public static void bindText(final Text control, final String key) {
		final AutoCompleteField autocomplete = new AutoCompleteField(control, new TextContentAdapter(),
				InputHistoryCache.get(key));
		control.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (autocomplete != null) {
					autocomplete.setProposals(InputHistoryCache.get(null));
					InputHistoryCache.put(key, control.getText().trim());
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (autocomplete != null)
					autocomplete.setProposals(InputHistoryCache.get(key));
			}
		});
	}
}
