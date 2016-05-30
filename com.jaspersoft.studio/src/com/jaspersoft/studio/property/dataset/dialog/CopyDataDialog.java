/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.dataset.dialog.DataPreviewTable.DataPreviewBean;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

public class CopyDataDialog extends ATitledDialog {
	private DataPreviewTable dpt;

	protected CopyDataDialog(Shell parentShell, DataPreviewTable dpt) {
		super(parentShell);
		setTitle("Data");
		setDefaultSize(500, 400);
		this.dpt = dpt;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		Text txt = new Text(cmp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		txt.setLayoutData(new GridData(GridData.FILL_BOTH));
		txt.setText(getSQL());

		return cmp;
	}

	private String getSQL() {
		if (dpt.getColumns().isEmpty())
			return "";
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		StringBuffer tcol = new StringBuffer();
		String del = "";
		for (String f : dpt.getColumns()) {
			tcol.append(del).append(f);
			del = " , ";
		}
		sb.append(tcol).append("\nFROM \n(VALUES \n");
		del = "";
		for (DataPreviewBean it : dpt.getPreviewItems()) {
			sb.append(del).append("(");
			String rdel = "";
			for (int i = 0; i < dpt.getColumns().size(); i++) {
				sb.append(rdel).append("'" + it.getValue(i) + "'");
				rdel = ",";
			}
			sb.append(")");
			del = ",\n";
		}
		sb.append("\n) s(");
		sb.append(tcol).append(")");

		return sb.toString();
	}
}
