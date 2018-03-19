/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.ic;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.data.designer.IFilterQuery;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

public class QueryDialog extends ATitledDialog {
	private QueryComposite qc;

	protected QueryDialog(Shell parentShell, String v, IFilterQuery fq) {
		super(parentShell, false);
		setTitle(Messages.QueryDialog_0);
		qc = new QueryComposite(v, fq);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		qc.createComposite(composite);
		return composite;
	}

	public String getValue() {
		return qc.getValue();
	}

}
