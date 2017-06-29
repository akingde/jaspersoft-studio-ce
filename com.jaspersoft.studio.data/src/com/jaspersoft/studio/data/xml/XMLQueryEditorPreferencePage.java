/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.xml;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

/**
 * XML query editor preference page.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class XMLQueryEditorPreferencePage extends FieldEditorOverlayPage {

	public static final String P_USE_RECURSIVE_RETRIEVAL = "xmlChildrenRecursiveRetrieval";//$NON-NLS-1$
	public static final String PAGE_ID = "com.jaspersoft.studio.data.preferences.XMLQueryEditorPreferencePage.property"; //$NON-NLS-1$
	public static final String P_CONSIDER_EMPTY_NODES = "com.jaspersoft.studio.data.preferences.XMLQueryEditorPreferencePage.considerEmptyNodes"; //$NON-NLS-1$

	public XMLQueryEditorPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.XMLQueryEditorPreferencePage_Description);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	public String getPageId() {
		return PAGE_ID;
	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(P_USE_RECURSIVE_RETRIEVAL,
				Messages.XMLQueryEditorPreferencePage_RecursiveReadFields, getFieldEditorParent()));
		addField(new BooleanFieldEditor(P_CONSIDER_EMPTY_NODES,
				Messages.XMLQueryEditorPreferencePage_ConsiderEmptyNodesOption, getFieldEditorParent()));
		
		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(P_USE_RECURSIVE_RETRIEVAL, new Boolean(false)); //$//$NON-NLS-1$
		store.setDefault(P_CONSIDER_EMPTY_NODES, new Boolean(false)); //$//$NON-NLS-1$
	}
}
