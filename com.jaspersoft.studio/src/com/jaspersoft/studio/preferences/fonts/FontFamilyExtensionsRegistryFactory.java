package com.jaspersoft.studio.preferences.fonts;

import java.io.ByteArrayInputStream;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.fonts.FontExtensionsRegistry;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionsRegistryFactory;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

public class FontFamilyExtensionsRegistryFactory extends SimpleFontExtensionsRegistryFactory {
	public final static String PROPERTY_LIST = SimpleFontExtensionsRegistryFactory.PROPERTY_SIMPLE_FONT_FAMILIES_REGISTRY_FACTORY
			+ ".list";

	@SuppressWarnings("rawtypes")
	@Override
	public ExtensionsRegistry createRegistry(String registryId, JRPropertiesMap properties) {
		ExtensionsRegistry reg = super.createRegistry(registryId, properties);
		List lst = reg.getExtensions(FontFamily.class);

		String strprop = properties.getProperty(FontFamilyExtensionsRegistryFactory.PROPERTY_LIST);
		if (strprop != null) {
			List fonts = SimpleFontExtensionHelper.getInstance().loadFontFamilies(new ByteArrayInputStream(strprop.getBytes()));
			if (fonts != null && !fonts.isEmpty())
				lst.addAll(fonts);
		}

		return new FontExtensionsRegistry(lst);
	}
}
