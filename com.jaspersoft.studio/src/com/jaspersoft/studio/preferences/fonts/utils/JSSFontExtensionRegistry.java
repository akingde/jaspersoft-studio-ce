package com.jaspersoft.studio.preferences.fonts.utils;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;

public class JSSFontExtensionRegistry implements ExtensionsRegistry {
	private JRPropertiesMap properties;
	private List<FontFamily> lst;
	private String strproperties;
	private PropertiesHelper ph;

	public JSSFontExtensionRegistry(JRPropertiesMap properties) {
		this.properties = properties;
	}

	public List getExtensions(Class extensionType) {
		// FIXME: this method is called many times during report executions
		// we should cache, the list, but, if user ads some font, they will not be visible
		// maybe JR should cache font list, not us
		// it will be very good, if we could know JR file, to use properties at the report level
		if (lst == null)
			lst = new ArrayList<FontFamily>();
		if (ph == null)
			ph = new PropertiesHelper(null);
		String strprop = ph.getString(FontsPreferencePage.FPP_FONT_LIST);
		if (strprop != null && (strproperties == null || !strproperties.equals(strprop))) {
			lst.clear();
			List<FontFamily> fonts = SimpleFontExtensionHelper.getInstance().loadFontFamilies(
					new ByteArrayInputStream(strprop.getBytes()));
			if (fonts != null && !fonts.isEmpty())
				lst.addAll(fonts);
			strproperties = strprop;
		}
		return lst;
	}
}
