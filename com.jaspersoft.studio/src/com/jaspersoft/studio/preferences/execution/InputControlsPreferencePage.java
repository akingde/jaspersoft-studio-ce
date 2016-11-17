/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.execution;

import java.net.MalformedURLException;
import java.net.URL;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;

public class InputControlsPreferencePage extends FieldEditorOverlayPage {
	public static final String ALL_EMPTY = "allEmpty"; //$NON-NLS-1$
	public static final String ALWAYS = "always"; //$NON-NLS-1$
	public static final String NEVER = "never"; //$NON-NLS-1$

	public static final String JSS_IC_SHOW = "com.jaspersoft.studio.ic.SHOWIC"; //$NON-NLS-1$

	public static final String JSS_DATE_FORMAT = "com.jaspersoft.studio.ic.format.date"; //$NON-NLS-1$
	public static final String JSS_TIME_FORMAT = "com.jaspersoft.studio.ic.format.time"; //$NON-NLS-1$
	public static final String JSS_TIMESTAMP_FORMAT = "com.jaspersoft.studio.ic.format.timestamp"; //$NON-NLS-1$

	public InputControlsPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various
	 * types of preferences. Each field editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {

		addField(new ComboFieldEditor(JSS_IC_SHOW, Messages.InputControlsPreferencePage_2, new String[][] {
				{ Messages.InputControlsPreferencePage_3, ALWAYS }, { Messages.InputControlsPreferencePage_4, ALL_EMPTY },
				{ Messages.InputControlsPreferencePage_0, NEVER } }, getFieldEditorParent()));

		Link link = new Link(getFieldEditorParent(), SWT.WRAP);
		String message = Messages.InputControlsPreferencePage_5;
		link.setText(message);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		link.setLayoutData(gd);
		link.setSize(400, 100);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
				} catch (PartInitException ex) {
					UIUtils.showError(ex);
				} catch (MalformedURLException ex) {
					UIUtils.showError(ex);
				}
			}
		});

		Label lbl = new Label(getFieldEditorParent(), SWT.NONE);
		lbl.setText(""); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		addField(new StringFieldEditor(JSS_DATE_FORMAT, Messages.InputControlsPreferencePage_7, getFieldEditorParent()));
		addField(new StringFieldEditor(JSS_TIME_FORMAT, Messages.InputControlsPreferencePage_8, getFieldEditorParent()));
		addField(new StringFieldEditor(JSS_TIMESTAMP_FORMAT, Messages.InputControlsPreferencePage_9, getFieldEditorParent()));
		
		//Eventually create the extensions for the page
		super.createFieldEditors();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JSS_DATE_FORMAT, null);
		store.setDefault(JSS_TIME_FORMAT, null);
		store.setDefault(JSS_TIMESTAMP_FORMAT, null);

		store.setDefault(JSS_TIMESTAMP_FORMAT, ALWAYS);
	}

	@Override
	public String getPageId() {
		return "com.jaspersoft.studio.preferences.execution.InputControlsPreferencePage.property"; //$NON-NLS-1$
	}

}
