/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.preferences;

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

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.protocol.restv2.CertChainValidator;
import com.jaspersoft.studio.server.protocol.restv2.CertificateDialog;
import com.jaspersoft.studio.server.protocol.restv2.CertificateDialog.CertificateLabelProvider;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class TrustStorePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {
	public static final String PAGE_ID = "com.jaspersoft.studio.server.preferences.TrustStorePreferencePage";
	private TableViewer viewer;
	private KeyStore trustStore;
	private List<X509Certificate> trustCertificates = new ArrayList<X509Certificate>();
	private Map<String, Certificate> aliases = new HashMap<String, Certificate>();

	public TrustStorePreferencePage() {
		super("Trust Store");
		noDefaultAndApplyButton();
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NULL);
		cmp.setLayout(new GridLayout(2, false));
		cmp.setFont(parent.getFont());

		viewer = new TableViewer(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		TableViewerColumn col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setWidth(600);
		col.setLabelProvider(new CertificateLabelProvider());
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		viewer.setInput(trustCertificates);

		Table table = viewer.getTable();
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 70;
		table.setLayoutData(gd);

		final Button bDel = new Button(cmp, SWT.PUSH);
		bDel.setText(Messages.common_delete);
		bDel.setEnabled(false);
		bDel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		bDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
		});

		final StyledText cTxt = new StyledText(cmp,
				SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY | SWT.BORDER);

		cTxt.setLeftMargin(3);
		cTxt.setTopMargin(3);
		cTxt.setLineSpacing(1);
		cTxt.setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) viewer.getSelection();
				StyledString ss = CertificateDialog.getStyledToolTip((X509Certificate) sel.getFirstElement());

				cTxt.setText(ss.getString());
				cTxt.setStyleRanges(ss.getStyleRanges());
				bDel.setEnabled(!sel.isEmpty());
			}
		});

		return cmp;
	}

	@Override
	public boolean performOk() {
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
		return super.performOk();
	}

	@Override
	public void init(IWorkbench workbench) {
		try {
			trustStore = CertChainValidator.getDefaultTrustStore();

			Enumeration<String> alias = trustStore.aliases();
			while (alias.hasMoreElements()) {
				String a = alias.nextElement();
				Certificate c = trustStore.getCertificate(a);
				aliases.put(a, c);
				if (c instanceof X509Certificate)
					trustCertificates.add((X509Certificate) c);
			}
		} catch (KeyStoreException e) {
			UIUtils.showError(e);
		} catch (NoSuchAlgorithmException e) {
			UIUtils.showError(e);
		} catch (CertificateException e) {
			UIUtils.showError(e);
		} catch (IOException e) {
			UIUtils.showError(e);
		}
	}

}
