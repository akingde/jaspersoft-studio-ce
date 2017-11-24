/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.pref;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

import net.sf.jasperreports.components.map.MapComponent;

/*
 * 
 */
public class KeysPreferencePage extends FieldEditorOverlayPage {

	public KeysPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.MMap_ApiKeyDescription);
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		NStringFieldEditor tf = new NStringFieldEditor(MapComponent.PROPERTY_CLIENT_ID, Messages.MMap_ClientIdText,
				getFieldEditorParent());
		tf.setEmptyStringAllowed(true);
		tf.setTextLimit(1000);
		addField(tf);
		HelpSystem.setHelp(tf.getTextControl(getFieldEditorParent()),
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#" + tf.getPreferenceName());

		tf = new NStringFieldEditor(MapComponent.PROPERTY_KEY, Messages.MMap_ApiKeyText, getFieldEditorParent());
		tf.setEmptyStringAllowed(true);
		tf.setTextLimit(1000);
		addField(tf);
		HelpSystem.setHelp(tf.getTextControl(getFieldEditorParent()),
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#" + tf.getPreferenceName());

		tf = new NStringFieldEditor(MapComponent.PROPERTY_SIGNATURE, Messages.MMap_SignatureText,
				getFieldEditorParent());
		tf.setEmptyStringAllowed(true);
		tf.setTextLimit(1000);
		addField(tf);
		HelpSystem.setHelp(tf.getTextControl(getFieldEditorParent()),
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#" + tf.getPreferenceName());

		tf = new NStringFieldEditor(MapComponent.PROPERTY_VERSION, Messages.MMap_VersionText, getFieldEditorParent());
		tf.setEmptyStringAllowed(true);
		tf.setTextLimit(1000);
		addField(tf);
		HelpSystem.setHelp(tf.getTextControl(getFieldEditorParent()),
				"net.sf.jasperreports.doc/docs/components.schema.reference.html#" + tf.getPreferenceName());

		// Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(MapComponent.PROPERTY_CLIENT_ID, "");
		store.setDefault(MapComponent.PROPERTY_SIGNATURE, "");
		store.setDefault(MapComponent.PROPERTY_KEY, "");
		store.setDefault(MapComponent.PROPERTY_VERSION, "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static final String PAGE_ID = "com.jaspersoft.studio.components.map.pref.KeysPreferencePage.property"; //$NON-NLS-1$

	@Override
	public String getPageId() {
		return PAGE_ID;
	}
}
