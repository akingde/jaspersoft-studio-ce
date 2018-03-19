/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.server.messages.Messages;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

public class LovDialog extends ATitledDialog {
	private LovComposite lovc;

	protected LovDialog(Shell parentShell, String v) {
		super(parentShell, false);
		setTitle(Messages.LovPageContent_lov);
		lovc = new LovComposite(v);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout(2, false));

		lovc.createComposite(composite);

		return composite;
	}

	public String getValue() {
		return lovc.getValue();
	}

}
