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
package com.jaspersoft.studio.preferences.virtualizer;

import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRGzipVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.editor.JSSComboFieldEditor;
import com.jaspersoft.studio.preferences.editor.number.SpinnerFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class VirtualizerPreferencePage extends FieldEditorOverlayPage {
	public static final String NSF_FILES_DELETE_ONEXIT = "net.sf.jasperreports.virtualizer.files.delete.on.exit"; //$NON-NLS-1$
	public static final String JSS_VIRTUALIZER_USE = "com.jaspersoft.studio.virtualizer.use"; //$NON-NLS-1$
	public static final String JSS_VIRTUALIZER_TYPE = "com.jaspersoft.studio.virtualizer.type"; //$NON-NLS-1$
	public static final String JSS_VIRTUALIZER_MAX_SIZE = "com.jaspersoft.studio.virtualizer.maxsize"; //$NON-NLS-1$
	public static final String JSS_VIRTUALIZER_TMP = "com.jaspersoft.studio.virtualizer.tmp"; //$NON-NLS-1$
	public static final String JSS_VIRTUALIZER_BLOCK_SIZE = "com.jaspersoft.studio.virtualizer.block.size"; //$NON-NLS-1$
	public static final String JSS_VIRTUALIZER_MIN_GROW_COUNT = "com.jaspersoft.studio.virtualizer.min.grow.count"; //$NON-NLS-1$

	public static final String JSS_VIRTUALIZER_PAGE_ELEMENT_SIZE = "net.sf.jasperreports.virtual.page.element.size"; //$NON-NLS-1$

	private BooleanFieldEditor bfeONEXIT;
	private JSSComboFieldEditor cfeType;
	private SpinnerFieldEditor msfe;
	private DirectoryFieldEditor dfeTMP;
	private BooleanFieldEditor bfeUSE;
	private SpinnerFieldEditor sfeBLOCKSIZE;
	private SpinnerFieldEditor sfeMINGROWCOUNT;
	private SpinnerFieldEditor sfePAGEELSIZE;

	public VirtualizerPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription("Virtualizer preferences");
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various
	 * types of preferences. Each field editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		bfeUSE = new BooleanFieldEditor(JSS_VIRTUALIZER_USE, "Use Virtualizer", getFieldEditorParent());
		addField(bfeUSE);

		bfeONEXIT = new BooleanFieldEditor(NSF_FILES_DELETE_ONEXIT, "Delete Temporary Files On Exit",
				getFieldEditorParent());
		addField(bfeONEXIT);
		HelpSystem.setHelp(bfeONEXIT.getDescriptionControl(getFieldEditorParent()), StudioPreferencePage.REFERENCE_PREFIX
				+ bfeONEXIT.getPreferenceName());

		sfePAGEELSIZE = new SpinnerFieldEditor(JSS_VIRTUALIZER_PAGE_ELEMENT_SIZE, "Page Element Size",
				getFieldEditorParent(), 0);
		sfePAGEELSIZE.setMinimum(1);
		sfePAGEELSIZE.setMaximum(Integer.MAX_VALUE);
		addField(sfePAGEELSIZE);
		HelpSystem.setHelp(sfePAGEELSIZE.getSpinnerControl(),
				StudioPreferencePage.REFERENCE_PREFIX + sfePAGEELSIZE.getPreferenceName());

		cfeType = new JSSComboFieldEditor(JSS_VIRTUALIZER_TYPE, "Type", new String[][] {
				{ "File Virtualizer", JRFileVirtualizer.class.getName() },
				{ "GZip In Memory Virtualizer", JRGzipVirtualizer.class.getName() },
				{ "Single Swap File Virtualizer", JRSwapFileVirtualizer.class.getName() } }, getFieldEditorParent());
		addField(cfeType);

		msfe = new SpinnerFieldEditor(JSS_VIRTUALIZER_MAX_SIZE, "Max Size", getFieldEditorParent(), 0);
		msfe.setMinimum(0);
		msfe.setMaximum(Integer.MAX_VALUE);
		msfe.getLabelControl(getFieldEditorParent()).setToolTipText(
				"Maximum number of chunks in the memory cache, before sending to the file.");
		addField(msfe);

		dfeTMP = new DirectoryFieldEditor(JSS_VIRTUALIZER_TMP, "Virtualizer Temporary Path", getFieldEditorParent());
		addField(dfeTMP);

		sfeBLOCKSIZE = new SpinnerFieldEditor(JSS_VIRTUALIZER_BLOCK_SIZE, "Block Size", getFieldEditorParent(), 0);
		sfeBLOCKSIZE.setMinimum(0);
		sfeBLOCKSIZE.setMaximum(Integer.MAX_VALUE);
		addField(sfeBLOCKSIZE);

		sfeMINGROWCOUNT = new SpinnerFieldEditor(JSS_VIRTUALIZER_MIN_GROW_COUNT, "Min Grow Count", getFieldEditorParent(),
				0);
		sfeMINGROWCOUNT.setMinimum(0);
		sfeMINGROWCOUNT.setMaximum(Integer.MAX_VALUE);
		addField(sfeMINGROWCOUNT);

		enableVirtualizers(getPreferenceStore().getBoolean(JSS_VIRTUALIZER_USE));
		this.isSwap = getPreferenceStore().getString(JSS_VIRTUALIZER_TYPE).equals(JRSwapFileVirtualizer.class.getName());
		enableSwapVirtualizer(isSwap);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (event.getProperty().equals("field_editor_value")) {
			if (event.getSource() == bfeUSE) {
				boolean newVal = (Boolean) event.getNewValue();
				enableVirtualizers(newVal);
			}
			if (event.getSource() == cfeType) {
				isSwap = event.getNewValue().equals(JRSwapFileVirtualizer.class.getName());
				enableSwapVirtualizer(isSwap);
			}
		}
	}

	private boolean isSwap = false;

	private void enableSwapVirtualizer(boolean isSwap) {
		dfeTMP.setEnabled(isSwap, getFieldEditorParent());
		sfeBLOCKSIZE.setEnabled(isSwap, getFieldEditorParent());
		sfeMINGROWCOUNT.setEnabled(isSwap, getFieldEditorParent());
	}

	private void enableVirtualizers(boolean newVal) {
		bfeONEXIT.setEnabled(newVal, getFieldEditorParent());
		cfeType.setEnabled(newVal, getFieldEditorParent());
		msfe.setEnabled(newVal, getFieldEditorParent());
		enableSwapVirtualizer(newVal && isSwap);
		sfePAGEELSIZE.setEnabled(newVal, getFieldEditorParent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(NSF_FILES_DELETE_ONEXIT, "true"); //$NON-NLS-1$
		store.setDefault(JSS_VIRTUALIZER_USE, "false"); //$NON-NLS-1$
		store.setDefault(JSS_VIRTUALIZER_TYPE, JRFileVirtualizer.class.getName());
		store.setDefault(JSS_VIRTUALIZER_MAX_SIZE, 100);
		store.setDefault(JSS_VIRTUALIZER_TMP, ""); //$NON-NLS-1$
		store.setDefault(JSS_VIRTUALIZER_BLOCK_SIZE, 100);
		store.setDefault(JSS_VIRTUALIZER_MIN_GROW_COUNT, 100);
		store.setDefault(JSS_VIRTUALIZER_PAGE_ELEMENT_SIZE, 1);
	}

	@Override
	protected String getPageId() {
		return "com.jaspersoft.studio.preferences.virtualizer.VirtualizerPreferencePage.property";
	}

}
