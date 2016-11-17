/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics.heartbeat;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;

public class VersionUpdateDialog extends ATitledDialog {
	private boolean isNotShowAgain = false;
	private String newVersion;
	private String optMessage;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public VersionUpdateDialog(Shell parentShell) {
		super(parentShell);
		setTitle(Messages.VersionUpdateDialog_2);
		setDefaultSize(600, 250);
	}

	public void setNewVersion(String msg) {
		this.newVersion = msg;
	}

	public void setOptionalMessage(String msg) {
		this.optMessage = msg;
	}

	public boolean isNotShowAgain() {
		return isNotShowAgain;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout());

		Link link = new Link(container, SWT.WRAP);
		link.setText(new MessageFormat(Messages.VersionUpdateDialog_0).format(new Object[] { newVersion }));
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println(Messages.VersionUpdateDialog_3 + e.text);
				try {
					PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new URL(e.text));
				} catch (PartInitException ex) {
					UIUtils.showError(ex);
				} catch (MalformedURLException ex) {
					UIUtils.showError(ex);
				}
			}
		});
		link.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		if (optMessage != null) {
			Label lblOptMessage = new Label(container, SWT.WRAP);
			lblOptMessage.setText(optMessage);
			lblOptMessage.setLayoutData(new GridData(GridData.FILL_BOTH));
		}

		final Button btnShowAgain = new Button(container, SWT.CHECK);
		btnShowAgain.setText(Messages.VersionUpdateDialog_1);
		btnShowAgain.setSelection(isNotShowAgain);
		btnShowAgain.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				isNotShowAgain = btnShowAgain.getSelection();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

}
