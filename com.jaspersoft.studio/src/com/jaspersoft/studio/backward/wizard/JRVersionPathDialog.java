/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.backward.wizard;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.backward.JRBackwardManager;
import com.jaspersoft.studio.utils.Misc;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class JRVersionPathDialog extends ATitledDialog {
	private JRDefinition value;

	protected JRVersionPathDialog(Shell parentShell, JRDefinition value) {
		super(parentShell);
		setTitle("JasperReports Runtime Path");
		setDefaultSize(500, 180);
		this.value = value;
	}

	public JRDefinition getValue() {
		return value;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText("Path where to look for JasperReports runtime:");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		final Text tpath = new Text(cmp, SWT.BORDER);
		tpath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tpath.setTextLimit(300);
		tpath.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				Button okButton = getButton(IDialogConstants.OK_ID);
				if (new File(tpath.getText()).exists()) {
					try {
						value.setVersion(JRBackwardManager.verify(tpath.getText()));
						value.setResourceURL(tpath.getText());
						setError(null);
						okButton.setEnabled(true);
						return;
					} catch (Exception e1) {
						setError(e1.getMessage());
						okButton.setEnabled(false);
						return;
					}
				}
				okButton.setEnabled(false);
				setError("Please select a valid path with JasperReports runtime");
			}
		});

		Button b = new Button(cmp, SWT.PUSH);
		b.setText("Browse");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(parent.getShell());
				if (value != null && !Misc.isNullOrEmpty(value.getResourceURL()))
					dlg.setFilterPath(value.getResourceURL());
				dlg.setText(title);
				dlg.setMessage(title);
				String dir = dlg.open();
				if (dir != null) {
					tpath.setText(dir);
					value.setResourceURL(tpath.getText());
				}
			}
		});
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				tpath.setText(value.getResourceURL());
			}
		});
		return cmp;
	}
}
