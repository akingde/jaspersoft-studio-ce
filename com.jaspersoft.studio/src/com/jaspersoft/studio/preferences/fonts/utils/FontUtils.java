/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts.utils;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.ui.PreferenceConstants;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.preferences.util.PreferencesUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class FontUtils {
	public static String separator = "__________________";

	/**
	 * Convert a list of array of string into a single array of string, ready to be inserted into a combo
	 * 
	 * @param fontsList
	 *          List of array of fonts (every list is a category)
	 * @param useSeparator
	 *          specify if a separator must be used between each category
	 * 
	 * @return List of combo item
	 */
	public static String[] stringToItems(List<String[]> fontsList, boolean useSeparator) {
		List<String> itemsList = new ArrayList<String>();
		for (int index = 0; index < fontsList.size(); index++) {
			String[] fonts = fontsList.get(index);
			for (String element : fonts)
				itemsList.add(element);
			if (index + 1 != fontsList.size() && fonts.length > 0 && useSeparator)
				itemsList.add(separator);
		}
		String[] result = new String[itemsList.size()];
		return itemsList.toArray(result);
	}

	/**
	 * Convert a list of array of string into a single array of string, ready to be inserted into a combo
	 * 
	 * @param fontsList
	 *          List of array of fonts, between every array will be inserted a separator
	 * @return List of combo item
	 */
	public static String[] stringToItems(List<String[]> fontsList) {
		return stringToItems(fontsList, true);
	}

	/**
	 * Gets the shared font across JSS editors (i.e: expression editor, query editors).
	 * 
	 * @return the shared font for JSS editors
	 */
	public static Font getEditorsFont(JasperReportsConfiguration jconfig) {
		String fontDataStr = null;
		if (jconfig != null) {
			fontDataStr = jconfig
					.getProperty(DesignerPreferencePage.P_INTERNAL_EDITORS_FONT, getTextEditorFontDataAsString());
		}
		if (fontDataStr == null) {
			// Gets default from JaspersoftStudio plugin
			fontDataStr = PreferencesUtils.getJaspersoftStudioPrefStore().getString(
					DesignerPreferencePage.P_INTERNAL_EDITORS_FONT);
		}
		FontData[] fontDataArray = PreferenceConverter.basicGetFontData(fontDataStr);
		return ResourceManager.getFont(fontDataArray[0].getName(), fontDataArray[0].getHeight(),
				fontDataArray[0].getStyle());

	}

	/**
	 * Gets the {@link FontData} instance representing the basic text font.
	 * 
	 * @see JFaceResources#TEXT_FONT
	 */
	public static FontData getTextEditorFontData() {
		FontData textFontData = PreferenceConverter.getFontData(PreferenceConstants.getPreferenceStore(),
				JFaceResources.TEXT_FONT);
		return textFontData;
	}

	/**
	 * Gets the string preference property representing the basic text font.
	 * 
	 * @see JFaceResources#TEXT_FONT
	 */
	public static String getTextEditorFontDataAsString() {
		FontData editorTextFontData = getTextEditorFontData();
		if (editorTextFontData != null) {
			return PreferenceConverter.getStoredRepresentation(new FontData[] { editorTextFontData });
		}
		return null;
	}

}
