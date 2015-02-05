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
package com.jaspersoft.studio.server.properties.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.model.MResource;

public abstract class RepositoryDialog extends Dialog {
	private INode root;

	public RepositoryDialog(Shell parentShell, INode root) {
		super(parentShell);
		this.root = root;
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.SHELL_TRIM);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("JasperServer Repository Browser");
	}

	private MResource resource;
	private Text tname;
	private Text ttype;

	public MResource getResource() {
		return resource;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(2, false));

		RepositoryComposite rcom = new RepositoryComposite(composite, SWT.NONE,
				root) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jaspersoft.studio.server.properties.dialog.RepositoryComposite
			 * #okPressed()
			 */
			@Override
			protected void okPressed() {
				RepositoryDialog.this.okPressed();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jaspersoft.studio.server.properties.dialog.RepositoryComposite
			 * #setOkButtonEnabled(boolean)
			 */
			@Override
			protected void setOkButtonEnabled(boolean resCompatible) {
				getButton(IDialogConstants.OK_ID).setEnabled(resCompatible);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jaspersoft.studio.server.properties.dialog.RepositoryComposite
			 * #setResource(com.jaspersoft.studio.server.model.MResource)
			 */
			@Override
			public void setResource(MResource res) {
				super.setResource(res);
				tname.setText(res.getValue().getUriString());
				ttype.setText(res.getValue().getWsType());
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.jaspersoft.studio.server.properties.dialog.RepositoryComposite
			 * #
			 * isResourceCompatible(com.jaspersoft.studio.server.model.MResource
			 * )
			 */
			@Override
			public boolean isResourceCompatible(MResource r) {
				return RepositoryDialog.this.isResourceCompatible(r);
			}
		};
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 2;
		gd.minimumHeight = 300;
		gd.minimumWidth = 400;
		rcom.setLayoutData(gd);

		new Label(composite, SWT.NONE).setText("Resource Name");

		tname = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText("Resource Type");

		ttype = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		return composite;
	}

	public abstract boolean isResourceCompatible(MResource r);

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}
}
