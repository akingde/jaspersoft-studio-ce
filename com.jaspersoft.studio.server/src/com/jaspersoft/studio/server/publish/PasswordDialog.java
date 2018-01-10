/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.ServerProfile;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.Misc;

public class PasswordDialog extends ATitledDialog {
	private ServerProfile sp;
	private String value;

	public PasswordDialog(Shell parentShell, ServerProfile sp) {
		super(parentShell, SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.OK | SWT.APPLICATION_MODAL | SWT.SHEET, false);
		setTitle(Messages.PasswordDialog_0);
		setDescription(Messages.PasswordDialog_1);
		// setDefaultSize(600, 250);
		this.sp = sp;
	}

	public String getValue() {
		return value;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		if (cmp.getLayout() instanceof GridLayout) {
			((GridLayout) cmp.getLayout()).numColumns = 2;
			((GridLayout) cmp.getLayout()).makeColumnsEqualWidth = false;
		} else
			cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText(Messages.PasswordDialog_2);

		Text turl = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		try {
			turl.setText(Misc.nvl(sp.getUrl()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		turl.setEnabled(false);
		turl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE).setText(Messages.PasswordDialog_3);

		Text torg = new Text(cmp, SWT.BORDER);
		torg.setText(Misc.nvl(sp.getOrganisation()));
		torg.setEnabled(false);
		torg.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE).setText(Messages.PasswordDialog_4);

		Text tuser = new Text(cmp, SWT.BORDER);
		tuser.setText(Misc.nvl(sp.getUser()));
		tuser.setEnabled(false);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE).setText(Messages.PasswordDialog_5);

		final Text tpass = new Text(cmp, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tpass.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				value = tpass.getText();
			}
		});
		tpass.setFocus();
		return cmp;
	}

}
