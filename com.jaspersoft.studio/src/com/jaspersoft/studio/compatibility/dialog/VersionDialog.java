/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.compatibility.dialog;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;

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

	private String[][] versions = JRXmlWriterHelper.getVersions();

	private String[] getItems() {
		String[] r = new String[versions.length];
		for (int i = 0; i < versions.length; i++)
			r[i] = versions[i][0];
		return r;
	}

	private int getVersionIndex() {
		for (int i = 0; i < versions.length; i++)
			if (versions[i][1].equals(version))
				return i;
		return 0;
	}

	private String version;

	public String getVersion() {
		return version;
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

		final Combo c = new Combo(container, SWT.SINGLE | SWT.READ_ONLY);
		c.setItems(getItems());
		c.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int ind = c.getSelectionIndex();
				if (ind >= 0 && ind < versions.length)
					version = versions[ind][1];
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		c.select(getVersionIndex());

		final Button b = new Button(container, SWT.CHECK);
		b.setText(Messages.VersionDialog_checkbox);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		b.setLayoutData(gd);
		b.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				ScopedPreferenceStore overlayStore = new ScopedPreferenceStore(new ProjectScope(project),
						JaspersoftStudioPlugin.getUniqueIdentifier());
				overlayStore.putValue(StudioPreferencePage.JSS_COMPATIBILITY_SHOW_DIALOG, Boolean.toString(b.getSelection()));
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		return container;
	}

}
