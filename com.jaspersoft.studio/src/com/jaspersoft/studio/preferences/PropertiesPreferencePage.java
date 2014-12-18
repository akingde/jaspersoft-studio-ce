/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.properties.PropertyComparator;
import com.jaspersoft.studio.preferences.editor.properties.SearchPropertyListFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

/*
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing
 * <samp>FieldEditorPreferencePage</samp>, we can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself. <p> This page is used to modify preferences only. They
 * are stored in the preference store that belongs to the main plug-in class. That way, preferences can be accessed
 * directly via the preference store.
 */

public class PropertiesPreferencePage extends FieldEditorOverlayPage {

	public PropertiesPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various
	 * types of preferences. Each field editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		addField(new SearchPropertyListFieldEditor(
				"properties_list", Messages.PropertiesPreferencePage_jrPropertiesTitle, getFieldEditorParent())); //$NON-NLS-1$
	}

	public static void getDefaults(IPreferenceStore store) {
		List<PropertySuffix> lst = JRPropertiesUtil.getInstance(DefaultJasperReportsContext.getInstance())
				.getProperties("");//$NON-NLS-1$
		Collections.sort(lst, new PropertyComparator());

		Properties props = new Properties();
		for (PropertySuffix ps : lst)
			props.setProperty(ps.getKey(), ps.getValue());
		store.setDefault(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES, FileUtils.getPropertyAsString(props));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	protected String getPageId() {
		return "com.jaspersoft.studio.preferences.PropertiesPreferencePage.property"; //$NON-NLS-1$
	}

}
