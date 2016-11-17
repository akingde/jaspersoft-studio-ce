/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.chart.preferences.ChartCustomizerPreferencePage;
import com.jaspersoft.studio.components.map.pref.KeysPreferencePage;

/*
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#
	 * initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();

		initDefaultProperties(store);
	}

	public static void initDefaultProperties(IPreferenceStore store) {
		ComponentsPreferencePageExtension.getDefaults(store);
		KeysPreferencePage.getDefaults(store);
		ChartCustomizerPreferencePage.getDefaults(store);
	}

}
