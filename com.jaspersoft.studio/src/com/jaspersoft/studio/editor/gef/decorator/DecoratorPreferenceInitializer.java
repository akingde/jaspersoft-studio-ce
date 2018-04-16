/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.decorator.csv.ShowCSVTagsAction;
import com.jaspersoft.studio.editor.gef.decorator.error.ShowErrorsAction;
import com.jaspersoft.studio.editor.gef.decorator.pdf.ShowPDFTagsAction;
import com.jaspersoft.studio.editor.gef.decorator.spreadsheet.ShowSpreadsheetTagsAction;
import com.jaspersoft.studio.editor.gef.decorator.xls.ShowXLSTagsAction;

public class DecoratorPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();

		store.setDefault(ShowErrorsAction.ID, new Boolean(true));
		store.setDefault(ShowXLSTagsAction.ID, new Boolean(true));
		store.setDefault(ShowPDFTagsAction.ID, new Boolean(true));
		store.setDefault(ShowCSVTagsAction.ID, new Boolean(true));
		store.setDefault(ShowSpreadsheetTagsAction.ID, new Boolean(true));
	}

}
