package com.jaspersoft.studio.data.sql.prefs;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class SQLEditorPreferencesPage extends FieldEditorOverlayPage {
	public static final String P_IDENTIFIER_QUOTE = "pageRulerGrid_SHOWRULER"; //$NON-NLS-1$

	public SQLEditorPreferencesPage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("SQL Query Editor Settings");
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected void createFieldEditors() {
		addField(new ComboFieldEditor(P_IDENTIFIER_QUOTE, "Identifier Quotes", new String[][] { { "", "" }, { "\"", "\"" }, { "`", "`" } }, getFieldEditorParent()));
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(P_IDENTIFIER_QUOTE, "");

	}

	public static final String PAGE_ID = "com.jaspersoft.studio.data.sql.prefs.SQLEditorPreferencesPage.property"; //$NON-NLS-1$

	@Override
	protected String getPageId() {
		return PAGE_ID;
	}
}
