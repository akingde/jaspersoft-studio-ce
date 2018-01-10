/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.properties.dialog;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

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
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;

public abstract class RepositoryDialog extends ATitledDialog {
	private INode root;

	public RepositoryDialog(Shell parentShell, INode root) {
		super(parentShell);
		this.root = root;
		setTitle(Messages.RepositoryDialog_0);
	}

	private AMResource resource;
	private Text tname;
	private Text ttype;

	public AMResource getResource() {
		return resource;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout(2, false));

		RepositoryComposite rcom = new RepositoryComposite(composite, SWT.NONE, root, true) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.server.properties.dialog.
			 * RepositoryComposite #okPressed()
			 */
			@Override
			protected void okPressed() {
				RepositoryDialog.this.okPressed();
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.server.properties.dialog.
			 * RepositoryComposite #setOkButtonEnabled(boolean)
			 */
			@Override
			protected void setOkButtonEnabled(boolean resCompatible) {
				getButton(IDialogConstants.OK_ID).setEnabled(resCompatible);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.server.properties.dialog.
			 * RepositoryComposite
			 * #setResource(com.jaspersoft.studio.server.model.MResource)
			 */
			@Override
			public void setResource(AMResource res) {
				super.setResource(res);
				tname.setText(res.getValue().getUriString());
				ttype.setText(res.getValue().getWsType());

				RepositoryDialog.this.resource = res;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.jaspersoft.studio.server.properties.dialog.
			 * RepositoryComposite #
			 * isResourceCompatible(com.jaspersoft.studio.server.model.MResource
			 * )
			 */
			@Override
			public boolean isResourceCompatible(AMResource r) {
				return RepositoryDialog.this.isResourceCompatible(r);
			}
		};
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
		gd.horizontalSpan = 2;
		gd.heightHint = 300;
		gd.widthHint = 400;
		rcom.setLayoutData(gd);

		new Label(composite, SWT.NONE).setText(Messages.RepositoryDialog_1);

		tname = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(composite, SWT.NONE).setText(Messages.RepositoryDialog_2);

		ttype = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		ttype.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		return composite;
	}

	public abstract boolean isResourceCompatible(AMResource r);

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}
}
