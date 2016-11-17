/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.toolitems;

import java.util.List;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class ToolItemsPreferencePage extends FieldEditorOverlayPage {
	
	public ToolItemsPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.ToolItemsPreferencePage_0);
	}


	public void createFieldEditors() {
		Composite fieldEditorParent = getFieldEditorParent();
		List<ToolItemsSet> list = JaspersoftStudioPlugin.getToolItemsManager().getSets();
		for (ToolItemsSet tis : list) {
			BooleanFieldEditor beditor = new BooleanFieldEditor(tis.getId(), tis.getName(), fieldEditorParent);
			addField(beditor);
		}
		
		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		List<ToolItemsSet> list = JaspersoftStudioPlugin.getToolItemsManager().getSets();
		for (ToolItemsSet tis : list) {
			store.setDefault(tis.getId(), tis.isVisible());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static final String PAGE_ID = "com.jaspersoft.studio.editor.toolitems.ToolItemsPreferencePage.property"; //$NON-NLS-1$

	@Override
	public String getPageId() {
		return PAGE_ID;
	}
}
