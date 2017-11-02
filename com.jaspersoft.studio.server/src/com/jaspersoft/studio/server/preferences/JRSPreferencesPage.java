/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.preferences;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.editor.text.NStringFieldEditor;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.utils.Callback;
import com.jaspersoft.studio.utils.browser.BrowserInfo;

import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.Misc;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class JRSPreferencesPage extends FieldEditorOverlayPage {

	public static final String PUBLISH_REPORT_TOJRSONSAVE = "PUBLISH_REPORT_TOJRSONSAVE"; //$NON-NLS-1$
	public static final String PUBLISH_REPORT_OVERRIDEBYDEFAULT = "com.jaspersoft.studio.server.PUBLISH_REPORT_OVERRIDEBYDEFAULT"; //$NON-NLS-1$

	public JRSPreferencesPage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		getDefaults(getPreferenceStore());
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common GUI
	 * blocks needed to manipulate various types of preferences. Each field editor
	 * knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addField(new BooleanFieldEditor(PUBLISH_REPORT_TOJRSONSAVE,
				com.jaspersoft.studio.server.messages.Messages.JRSPreferencesPage_1, getFieldEditorParent()));
		addField(new ComboFieldEditor(PUBLISH_REPORT_OVERRIDEBYDEFAULT, Messages.JRSPreferencesPage_2,
				new String[][] { { Messages.JRSPreferencesPage_3, "overwrite" }, // $NON-NLS-2$
						{ Messages.JRSPreferencesPage_5, "true" }, { Messages.JRSPreferencesPage_7, "ignore" } }, //$NON-NLS-2$ //$NON-NLS-4$
				getFieldEditorParent()));

		NStringFieldEditor tf = new NStringFieldEditor(HttpUtils.USER_AGENT, Messages.JRSPreferencesPage_9,
				getFieldEditorParent());
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 400;
		final Text txt = tf.getTextControl();
		txt.setLayoutData(gd);
		txt.setToolTipText(Messages.JRSPreferencesPage_10);
		txt.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				txt.setToolTipText(Messages.JRSPreferencesPage_13 + "\n\n" + txt.getText()); //$NON-NLS-1$
			}
		});
		addField(tf);

		Button b = new Button(getFieldEditorParent(), SWT.PUSH);
		b.setText(Messages.JRSPreferencesPage_12);
		b.setToolTipText(Messages.JRSPreferencesPage_13);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				b.setEnabled(false);
				Job job = new Job(Messages.JRSPreferencesPage_14) {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						BrowserInfo.findUserAgent(new Callback<String>() {

							@Override
							public void completed(String value) {
								tf.getTextControl().setText(Misc.nvl(value));
								b.setEnabled(true);
							}
						});
						return Status.OK_STATUS;
					}

				};
				job.setPriority(Job.LONG);
				job.setUser(true);
				job.schedule();
			}
		});

		// Eventually create the extensions for the page
		super.createFieldEditors();
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(PUBLISH_REPORT_TOJRSONSAVE, true); // $NON-NLS-1$
		store.setDefault(PUBLISH_REPORT_OVERRIDEBYDEFAULT, "true"); // $NON-NLS-1$ //$NON-NLS-1$
		store.setDefault(HttpUtils.USER_AGENT, ""); // $NON-NLS-1$ //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	@Override
	public String getPageId() {
		return "com.jaspersoft.studio.server.preferences.JRSPreferencesPage.property"; //$NON-NLS-1$
	}

}
