package com.jaspersoft.studio.editor.toolitems;

import java.util.List;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class ToolItemsPreferencePage extends FieldEditorOverlayPage {

	public ToolItemsPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Toolbars Visibility");
	}

	/**
	 *
	 */
	public void createFieldEditors() {
		Composite fieldEditorParent = getFieldEditorParent();

		List<ToolItemsSet> list = JaspersoftStudioPlugin.getToolItemsManager().getSets();
		for (ToolItemsSet tis : list) {
			BooleanFieldEditor beditor = new BooleanFieldEditor(tis.getId(), tis.getName(), fieldEditorParent);
			beditor.getDescriptionControl(fieldEditorParent).setToolTipText(tis.getDescription());
			addField(beditor);
		}
	}

	public static void getDefaults(IPreferenceStore store) {
		List<ToolItemsSet> list = JaspersoftStudioPlugin.getToolItemsManager().getSets();
		for (ToolItemsSet tis : list) {
			store.setDefault(tis.getId(), new Boolean(tis.isVisibility()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static final String PAGE_ID = "com.jaspersoft.studio.editor.toolitems.ToolItemsPreferencePage.property";

	@Override
	protected String getPageId() {
		return PAGE_ID;
	}
}
