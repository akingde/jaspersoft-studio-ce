/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class JRVersionPathDialog extends ATitledDialog {
	private JRDefinition value;

	protected JRVersionPathDialog(Shell parentShell, JRDefinition value) {
		super(parentShell);
		setTitle(Messages.JRVersionPathDialog_0);
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
		lbl.setText(Messages.JRVersionPathDialog_1);
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
				setError(Messages.JRVersionPathDialog_2);
			}
		});

		Button b = new Button(cmp, SWT.PUSH);
		b.setText(Messages.JRVersionPathDialog_3);
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
