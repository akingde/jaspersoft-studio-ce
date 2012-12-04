/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.compatibility.dialog;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class VersionDialog extends Dialog {

	private IProject project;

	public VersionDialog(Shell parent, String version, IProject project) {
		super(parent);
		this.version = version;
		this.project = project;
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
		b.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ScopedPreferenceStore overlayStore = null;
				boolean isprj = true;
				QualifiedName key = new QualifiedName(StudioPreferencePage.PAGE_ID, FieldEditorOverlayPage.USERESOURCESETTINGS);
				String uuid = JaspersoftStudioPlugin.getUniqueIdentifier();
				try {
					isprj = project.getPersistentProperty(key) == null;
					if (!isprj)
						overlayStore = new ScopedPreferenceStore(JasperReportsConfiguration.INSTANCE_SCOPE, uuid);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
				if (overlayStore == null)
					overlayStore = new ScopedPreferenceStore(new ProjectScope(project), uuid);
				overlayStore.putValue(StudioPreferencePage.JSS_COMPATIBILITY_SHOW_DIALOG, Boolean.toString(!b.getSelection()));
				try {
					project.setPersistentProperty(key, Boolean.toString(isprj));
					overlayStore.save();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		return container;
	}

}
