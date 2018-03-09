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

import com.jaspersoft.studio.server.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;

public class StringValueDialog extends Dialog {
	private String value;

	public StringValueDialog(Shell parentShell, String value) {
		super(parentShell);
		this.value = value;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.QueryVisibleColumnsTable_2);
	}

	public String getValue() {
		return value;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText(Messages.QueryVisibleColumnsTable_2);

		final Text txt = new Text(cmp, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 150;
		txt.setLayoutData(gd);
		txt.setText(Misc.nvl(value));
		txt.addModifyListener(e -> {
			value = txt.getText();
			getButton(OK).setEnabled(!Misc.isNullOrEmpty(value));
		});

		return cmp;
	}
}
