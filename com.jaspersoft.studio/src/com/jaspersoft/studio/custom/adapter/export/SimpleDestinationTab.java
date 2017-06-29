/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.custom.adapter.export;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.pde.internal.ui.PDEPlugin;
import org.eclipse.pde.internal.ui.PDEUIMessages;
import org.eclipse.pde.internal.ui.SWTFactory;
import org.eclipse.pde.internal.ui.util.SWTUtil;
import org.eclipse.pde.internal.ui.wizards.exports.ExportDestinationTab;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;

/**
 * Area where the destination path are shown, it deny the access
 * to some methods and redefine the method to enable\disable the text
 * areas when the radio selection changes. Essentially it allow 
 * only to save to a file on the disk
 * 
 * @author Orlandin Marco
 *
 */

@SuppressWarnings("restriction")
public class SimpleDestinationTab extends ExportDestinationTab {

	public SimpleDestinationTab(ExportAdapterWizardPage page) {
		super(page);
	}

	public Control createControl(Composite parent) {
		Composite composite = SWTFactory.createComposite(parent, 2, 1, GridData.FILL_HORIZONTAL);

		fDirectoryCombo = SWTFactory.createCombo(composite, SWT.BORDER, 1, null);
		((GridData) fDirectoryCombo.getLayoutData()).horizontalIndent = 15;
		

		fBrowseDirectory = SWTFactory.createPushButton(composite, PDEUIMessages.ExportWizard_browse, null);
		SWTUtil.setButtonDimensionHint(fBrowseDirectory);

		return composite;
	}

	protected void initialize(IDialogSettings settings) {
		initializeCombo(settings, S_DESTINATION, fDirectoryCombo);
		hookListeners();
	}

	protected void hookListeners() {
		fDirectoryCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				((ExportAdapterWizardPage)fPage).pageChanged();
			}
		});

		fBrowseDirectory.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				chooseDestination(fDirectoryCombo);
			}
		});
	}

	private void chooseDestination(Combo combo) {
		DirectoryDialog dialog = new DirectoryDialog(fPage.getShell(), SWT.SAVE);
		String path = combo.getText();
		if (path.trim().length() == 0)
			path = PDEPlugin.getWorkspace().getRoot().getLocation().toString();
		dialog.setFilterPath(path);
		dialog.setText(PDEUIMessages.ExportWizard_dialog_title);
		dialog.setMessage(PDEUIMessages.ExportWizard_dialog_message);
		String res = dialog.open();
		if (res != null) {
			if (combo.indexOf(res) == -1)
				combo.add(res, 0);
			combo.setText(res);
		}
	}

	public void saveSettings(IDialogSettings settings) {
		int type = 1;
		settings.put(S_EXPORT_TYPE, type);
		saveCombo(settings, S_DESTINATION, fDirectoryCombo);
	}

	public String validate() {
		if (fDirectoryCombo.getText().trim().length() == 0)
			return PDEUIMessages.ExportWizard_status_nodirectory;
		else if (!isValidLocation(fDirectoryCombo.getText().trim()))
			return PDEUIMessages.ExportWizard_status_invaliddirectory;
		return null;
	}


	protected String getDestination() {
		File dir = new File(fDirectoryCombo.getText().trim());
		return dir.getAbsolutePath();
	}

	protected boolean doExportToDirectory() {
		return  true;
	}
}
