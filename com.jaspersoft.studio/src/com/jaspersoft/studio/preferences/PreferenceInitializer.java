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
package com.jaspersoft.studio.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_PAGE_DESIGN_BORDER_STYLE, "shadow");
		store.setDefault(PreferenceConstants.P_ELEMENT_DESIGN_BORDER_STYLE, "rectangle");

		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_SHOWRULER, new Boolean(true));
		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGUIDES, new Boolean(true));

		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_SHOWGRID, new Boolean(true));
		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGRID, new Boolean(true));
		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_SNAPTOGEOMETRY, new Boolean(true));
		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_GRIDSPACEX, new Integer(10));
		store.setDefault(PreferenceConstants.P_PAGE_RULERGRID_GRIDSPACEY, new Integer(10));

	}

}
