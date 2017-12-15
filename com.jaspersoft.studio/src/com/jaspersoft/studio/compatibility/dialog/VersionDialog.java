/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.compatibility.dialog;

import org.eclipse.core.resources.IResource;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;

public class VersionDialog extends PersistentLocationDialog {

	private boolean hideNext = false;

	public VersionDialog(Shell parent, String version, IResource res) {
		super(parent);
		this.version = version;
	}

	public boolean isHideNext() {
		return hideNext;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.VersionDialog_title);
	}

	private String version;
	private VersionCombo cversion;

	public String getVersion() {
		return cversion.getVersion();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout(2, false));

		Label label = new Label(container, SWT.WRAP);
		label.setText(Messages.VersionDialog_label + Messages.VersionDialog_warning);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);

		label = new Label(container, SWT.NONE);
		label.setText(Messages.VersionDialog_label2);

		cversion = new VersionCombo(container);
		cversion.setVersion(version);

		final Button b = new Button(container, SWT.CHECK);
		b.setText(Messages.VersionDialog_checkbox);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				hideNext = b.getSelection();

				// ScopedPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore(res,
				// JaspersoftStudioPlugin.getUniqueIdentifier());
				// store.setValue(StudioPreferencePage.JSS_COMPATIBILITY_SHOW_DIALOG, Boolean.toString(!b.getSelection()));
				//
				// store.save();

			}

		});

		return container;
	}

}
