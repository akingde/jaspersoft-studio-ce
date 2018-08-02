/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.Misc;

public class TextDialog extends ATitledDialog {

	private String v;

	protected TextDialog(Shell parentShell, String v) {
		super(parentShell, false);
		setTitle("Input Control Value Definition");
		this.v = v;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(2, false));

		Text txt = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		txt.setText(Misc.nvl(v));
		txt.addModifyListener(e -> v = txt.getText());

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 50;
		gd.widthHint = 300;
		txt.setLayoutData(gd);

		return composite;
	}

	public String getValue() {
		return v;
	}

}
