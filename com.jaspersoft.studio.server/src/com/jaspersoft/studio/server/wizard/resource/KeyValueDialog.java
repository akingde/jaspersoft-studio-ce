/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.eclipse.util.Misc;

public class KeyValueDialog extends Dialog {
	private String key;
	private String value;

	public KeyValueDialog(Shell parentShell, String key, String value) {
		super(parentShell);
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText("Name");

		final Text txt = new Text(cmp, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 150;
		txt.setLayoutData(gd);
		txt.setText(Misc.nvl(key));
		txt.addModifyListener(e -> {
			key = txt.getText();
			getButton(OK).setEnabled(!Misc.isNullOrEmpty(key));
		});

		new Label(cmp, SWT.NONE).setText("Value");

		final Text vtxt = new Text(cmp, SWT.BORDER);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 150;
		vtxt.setLayoutData(gd);
		vtxt.setText(Misc.nvl(value));
		vtxt.addModifyListener(e -> {
			value = vtxt.getText();
			getButton(OK).setEnabled(!Misc.isNullOrEmpty(value));
		});

		return cmp;
	}
}
