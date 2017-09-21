/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.Misc;

public class DeleteFieldConfirmationDialog extends ATitledDialog {
	public static final int DELETE = 0;
	public static final int UNGROUP_TO_PARENT = 1;
	public static final int UNGROUP_TO_ROOT = 2;
	private String pkey;

	public DeleteFieldConfirmationDialog(Shell parentShell, String pkey) {
		super(parentShell);
		setTitle(com.jaspersoft.studio.messages.Messages.common_delete);
		setDescription("Select the appropriate action below.");
		setDefaultSize(450, 300);
		this.pkey = pkey;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		Button btn = new Button(cmp, SWT.PUSH);
		btn.setText("Delete all enclosing fields.");
		btn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				choise = DELETE;
				close();
			}
		});
		if (!Misc.isNullOrEmpty(pkey)) {
			btn = new Button(cmp, SWT.PUSH);
			btn.setText("Move fields to parent");
			btn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			btn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					choise = UNGROUP_TO_PARENT;
					close();
				}
			});
		}
		btn = new Button(cmp, SWT.PUSH);
		btn.setText("Move fields to root");
		btn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				choise = UNGROUP_TO_ROOT;
				close();
			}
		});

		return cmp;
	}

	private int choise = DELETE;

	public int getChoise() {
		return choise;
	}

}
