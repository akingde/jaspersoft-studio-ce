/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts.utils;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.fonts.FontsPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.fonts.FontExtensionsCollector;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.fonts.FontSet;
import net.sf.jasperreports.engine.fonts.SimpleFontExtensionHelper;
import net.sf.jasperreports.extensions.ExtensionsRegistry;

public class JSSFontExtensionRegistry implements ExtensionsRegistry {
	private FontExtensionsCollector lst;
	private boolean fill = true;

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
		setJrContext(JasperReportsConfiguration.getDefaultInstance());
	}

	public void setJrContext(JasperReportsContext jrContext) {
		this.jrContext = jrContext;
	}

	public <T> List<T> getExtensions(Class<T> extensionType) {
		if (extensionType == FontFamily.class) {
			readFonts();
			if (!Misc.isNullOrEmpty(lst.getFontFamilies()))
				return (List<T>) lst.getFontFamilies();
		} else if (extensionType == FontSet.class) {
			readFonts();
			if (!Misc.isNullOrEmpty(lst.getFontSets()))
				return (List<T>) lst.getFontSets();
		}
		return null;
	}

	protected void readFonts() {
		if (fill) {
			lst = new FontExtensionsCollector();
			String strprop = jrContext.getProperty(FontsPreferencePage.FPP_FONT_LIST);
			if (strprop != null)
				SimpleFontExtensionHelper.getInstance().loadFontExtensions(jrContext,
						new ByteArrayInputStream(strprop.getBytes()), lst);
			fill = false;
		}
	}
}
