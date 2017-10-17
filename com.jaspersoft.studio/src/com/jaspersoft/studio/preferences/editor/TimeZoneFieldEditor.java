/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor;

import java.util.Arrays;
import java.util.TimeZone;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.swt.widgets.Composite;

public class TimeZoneFieldEditor extends ComboFieldEditor {

	public TimeZoneFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, getTimeZones(), parent);
	}

	private static String[][] tzs;

	private static String[][] getTimeZones() {
		if (tzs == null) {
			String[] tzones = TimeZone.getAvailableIDs();
			Arrays.sort(tzones);
			tzs = new String[tzones.length][2];
			for (int i = 0; i < tzs.length; i++) {
				tzs[i][0] = tzones[i];// TimeZone.getTimeZone(tzones[i]).getDisplayName();
				tzs[i][1] = tzones[i];
			}
		}
		return tzs;
	}

}
