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
package com.jaspersoft.studio.preferences.fonts.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class JSSFontExtensionRegistry implements ExtensionsRegistry {
	private List<FontFamily> lst;
	private boolean fill = true;
	private PropertiesHelper ph;

	private PreferenceListener preferenceListener;
	private JasperReportsContext jrContext;

	private final class PreferenceListener implements IPropertyChangeListener {

		public void propertyChange(org.eclipse.jface.util.PropertyChangeEvent event) {
			if (event.getProperty().equals(FontsPreferencePage.FPP_FONT_LIST)) {
				fill = true;
			}
		}
	}

	public JSSFontExtensionRegistry(JRPropertiesMap properties) {
		preferenceListener = new PreferenceListener();
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(preferenceListener);
		setJrContext(DefaultJasperReportsContext.getInstance());
	}

	public void setJrContext(JasperReportsContext jrContext) {
		this.jrContext = jrContext;
	}

	public <T> List<T> getExtensions(Class<T> extensionType) {
		if (extensionType != FontFamily.class)
			return null;
		if (lst == null)
			lst = new ArrayList<FontFamily>();
		if (fill) {
			if (ph == null)
				ph = PropertiesHelper.getInstance(jrContext);
			String strprop = ph.getString(FontsPreferencePage.FPP_FONT_LIST);
			if (strprop != null) {
				lst.clear();
				List<FontFamily> fonts = SimpleFontExtensionHelper.getInstance().loadFontFamilies(jrContext,
						new ByteArrayInputStream(strprop.getBytes()));
				if (fonts != null && !fonts.isEmpty())
					lst.addAll(fonts);
			}
			fill = false;
		}

		return (List<T>) lst;
	}
}
