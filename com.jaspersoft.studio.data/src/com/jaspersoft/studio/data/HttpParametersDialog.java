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
package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.http.StandardHttpDataLocation;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.Misc;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 *
 */
public class HttpParametersDialog extends ATitledDialog {
	private StandardHttpDataLocation dataFile;

	/**
	 * @param parentShell
	 */
	protected HttpParametersDialog(Shell parentShell,
			StandardHttpDataLocation dataFile) {
		super(parentShell);
		setTitle("Http Connection Options");
		this.dataFile = dataFile;
	}

	public StandardHttpDataLocation getDataFile() {
		return dataFile;
	}

	public void setDataFile(StandardHttpDataLocation dataFile) {
		this.dataFile = dataFile;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText("Username");
		final Text user = new Text(cmp, SWT.BORDER);
		user.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		user.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				dataFile.setUsername(user.getText());
			}
		});

		new Label(cmp, SWT.NONE).setText("Password");
		final Text password = new Text(cmp, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				dataFile.setPassword(password.getText());
			}
		});

		user.setText(Misc.nvl(dataFile.getUsername()));
		password.setText(Misc.nvl(dataFile.getPassword()));
		return cmp;
	}
}
