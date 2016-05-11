/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.protocol.restv2.CertChainValidator;
import com.jaspersoft.studio.server.protocol.restv2.CertificateDialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class CertificatesDialog extends CertificateDialog {

	public CertificatesDialog(Shell parentShell, String message, X509Certificate client, X509Certificate[] chain,
			KeyStore trustStore) {
		super(parentShell, message, client, chain);
		setTitle(Messages.CertificatesDialog_0);
		this.trustStore = trustStore;
	}

	private KeyStore trustStore;
	private boolean show = true;
	private List<X509Certificate> trustCertificates = new ArrayList<X509Certificate>();
	private Map<String, Certificate> aliases = new HashMap<String, Certificate>();

	@Override
	public boolean close() {
		boolean res = super.close();
		if (res && !show) {
			try {
				boolean changes = false;
				for (String alias : aliases.keySet()) {
					Certificate c = aliases.get(alias);
					if (!trustCertificates.contains(c)) {
						trustStore.deleteEntry(alias);
						changes = true;
					}
				}
				if (changes)
					CertChainValidator.writeTrustStore(trustStore);
			} catch (KeyStoreException e) {
				UIUtils.showError(e);
			} catch (FileNotFoundException e) {
				UIUtils.showError(e);
			} catch (NoSuchAlgorithmException e) {
				UIUtils.showError(e);
			} catch (CertificateException e) {
				UIUtils.showError(e);
			} catch (IOException e) {
				UIUtils.showError(e);
			}
		}
		return res;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, Messages.CertificatesDialog_1, true);
		createButton(parent, IDialogConstants.CANCEL_ID, Messages.CertificatesDialog_2, false);
	}

	@Override
	protected void okPressed() {
		if (show) {
			try {
				Enumeration<String> alias = trustStore.aliases();
				while (alias.hasMoreElements()) {
					String a = alias.nextElement();
					Certificate c = trustStore.getCertificate(a);
					aliases.put(a, c);
					if (c instanceof X509Certificate)
						trustCertificates.add((X509Certificate) c);
				}
				viewer.setInput(trustCertificates);
				viewer.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {

						getButton(IDialogConstants.OK_ID).setEnabled(!viewer.getSelection().isEmpty());
					}
				});
				if (!trustCertificates.isEmpty())
					viewer.setSelection(new StructuredSelection(trustCertificates.get(0)), true);
				else
					getButton(IDialogConstants.OK_ID).setEnabled(false);
				getShell().setText(Messages.CertificatesDialog_4);
				getButton(IDialogConstants.OK_ID).setText(Messages.CertificatesDialog_3);
				getButton(IDialogConstants.CANCEL_ID).setText(Messages.CertificatesDialog_2);
				show = false;
			} catch (KeyStoreException e1) {
				UIUtils.showError(e1);
			}
		} else {
			StructuredSelection sel = (StructuredSelection) viewer.getSelection();
			if (!sel.isEmpty()) {
				X509Certificate cert = (X509Certificate) sel.getFirstElement();
				int ind = trustCertificates.indexOf(cert);
				trustCertificates.remove(cert);
				viewer.refresh(true);
				if (trustCertificates.size() < ind)
					ind--;
				if (ind >= 0 && !trustCertificates.isEmpty())
					viewer.setSelection(new StructuredSelection(trustCertificates.get(ind)), true);
			}
		}
	}

	@Override
	protected void createLabel1(Composite cmp) {
	}
}
