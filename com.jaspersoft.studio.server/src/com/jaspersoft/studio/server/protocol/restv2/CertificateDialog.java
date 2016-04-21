/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.WordUtils;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERObject;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.jaspersoft.studio.utils.Misc;

import jersey.repackaged.com.google.common.collect.Lists;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.FileUtils;

public class CertificateDialog extends ATitledDialog {
	private X509Certificate client;
	private String message;
	private X509Certificate[] chain;

	protected CertificateDialog(Shell parentShell, String message, X509Certificate client, X509Certificate[] chain) {
		super(parentShell);
		setTitle("Invalid Security Certificate");
		setDefaultSize(550, 500);
		this.client = client;
		this.message = message;
		this.chain = chain;
	}

	@Override
	protected Button createButton(Composite parent, int id, String label, boolean defaultButton) {
		Button b = super.createButton(parent, id, label, defaultButton);
		if (id == IDialogConstants.OK_ID)
			b.setText("Trust");
		return b;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		StyledText text = new StyledText(cmp, SWT.READ_ONLY | SWT.WRAP);
		text.setLineSpacing(2);
		text.setBackground(cmp.getBackground());
		String msg1 = "The certificate for this server is invalid.\n\t";
		text.setText(msg1 + message);
		text.setStyleRanges(new StyleRange[] { new StyleRange(0, msg1.length(), null, null, SWT.BOLD),
				new StyleRange(msg1.length(), message.length(), null, null, SWT.ITALIC) });
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		final TableViewer viewer = new TableViewer(cmp,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		TableViewerColumn col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setWidth(600);
		col.setLabelProvider(new CertificateLabelProvider());
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		List<X509Certificate> input = Lists.reverse(Arrays.asList(chain));
		viewer.setInput(input);

		Table table = viewer.getTable();
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 70;
		table.setLayoutData(gd);

		final StyledText cTxt = new StyledText(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);

		cTxt.setLeftMargin(3);
		cTxt.setTopMargin(3);
		cTxt.setLineSpacing(1);
		cTxt.setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) viewer.getSelection();
				StyledString ss = getStyledToolTip((X509Certificate) sel.getFirstElement());

				cTxt.setText(ss.getString());
				cTxt.setStyleRanges(ss.getStyleRanges());
			}
		});

		new Label(cmp, SWT.NONE)
				.setText("\nPush Trust button if you are trusting this certificate or Cancel to abort the connection.");

		viewer.setSelection(new StructuredSelection(client), true);
		return cmp;
	}

	class CertificateLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			return ((X509Certificate) element).getSubjectDN().getName();
		}

	}

	static StyledString getStyledToolTip(X509Certificate cert) {
		StyledString ss = new StyledString();

		ss.append("Version:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getVersion() + "\n");
		ss.append("SerialNumber:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSerialNumber() + "\n");
		ss.append("Signature Algorithm:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSigAlgName() + " (" + cert.getSigAlgOID() + ")\n");
		ss.append("Parameters:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + Misc.nvl(cert.getSigAlgParams(), "none") + "\n");
		ss.append("IssuerDN:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getIssuerDN() + "\n");
		ss.append("Start Date:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getNotBefore() + "\n");
		ss.append("Final Date:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getNotAfter() + "\n\n");
		ss.append("SubjectDN:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSubjectDN() + "\n\n");

		ss.append("Public Key:", StyledString.QUALIFIER_STYLER);

		ss.append(" " + WordUtils.wrap(cert.getPublicKey().toString(), 50, null, true) + "\n");

		ss.append("Signature Algorithm:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSigAlgName() + "\n");
		ss.append("Signature:", StyledString.QUALIFIER_STYLER);
		ss.append(" " + WordUtils.wrap(Hex.encodeHexString(cert.getSignature()), 50, null, true) + "\n");

		ss.append("Extensions:\n", StyledString.QUALIFIER_STYLER);
		for (String ext : cert.getCriticalExtensionOIDs()) {
			ss.append("Critical: ", StyledString.QUALIFIER_STYLER);
			ss.append("YES " + Misc.nvl(certExtensions.get(ext)) + " (" + ext + ")\n");
			try {
				ss.append(getExtensionValue(cert, ext) + "\n");
			} catch (IOException e) {
			}
		}
		for (String ext : cert.getNonCriticalExtensionOIDs()) {
			ss.append("Critical: ", StyledString.QUALIFIER_STYLER);
			ss.append("NO " + Misc.nvl(certExtensions.get(ext)) + " (" + ext + ")\n");
			try {
				ss.append(getExtensionValue(cert, ext) + "\n");
			} catch (IOException e) {
			}
		}
		System.out.println(cert.toString());
		return ss;
	}

	private static String getExtensionValue(X509Certificate X509Certificate, String oid) throws IOException {
		byte[] extensionValue = X509Certificate.getExtensionValue(oid);
		return fromByteArray(extensionValue);
	}

	private static String fromByteArray(byte[] extensionValue) throws IOException {
		if (extensionValue != null) {
			DERObject derObject = toDERObject(extensionValue);
			if (derObject instanceof DEROctetString) {
				derObject = toDERObject(((DEROctetString) derObject).getOctets());
				if (derObject instanceof DERUTF8String)
					return DERUTF8String.getInstance(derObject).getString();
				if (derObject instanceof DERBitString)
					return DERBitString.getInstance(derObject).getString();
				if (derObject instanceof DERSequence)
					return DERSequence.getInstance(derObject).toString();
				if (derObject instanceof DEROctetString)
					return DEROctetString.getInstance(derObject).toString();
				else
					return derObject.getClass().getCanonicalName();
			}
		}
		return "";
	}

	private static DERObject toDERObject(byte[] data) throws IOException {
		DERObject der = null;
		ByteArrayInputStream inStream = new ByteArrayInputStream(data);
		ASN1InputStream asnInputStream = new ASN1InputStream(inStream);
		try {
			der = asnInputStream.readObject();
		} finally {
			FileUtils.closeStream(inStream);
			FileUtils.closeStream(asnInputStream);
		}
		return der;
	}

	private static Map<String, String> certExtensions = new HashMap<String, String>();
	static {
		certExtensions.put("2.5.29.1", "old Authority Key Identifier");
		certExtensions.put("2.5.29.2", "old Primary Key Attributes");
		certExtensions.put("2.5.29.3", "Certificate Policies");
		certExtensions.put("2.5.29.4", "Primary Key Usage Restriction");
		certExtensions.put("2.5.29.9", "Subject Directory Attributes");
		certExtensions.put("2.5.29.14", "Subject Key Identifier");
		certExtensions.put("2.5.29.15", "Key Usage");
		certExtensions.put("2.5.29.16", "Private Key Usage Period");
		certExtensions.put("2.5.29.17", "Subject Alternative Name");
		certExtensions.put("2.5.29.18", "Issuer Alternative Name");
		certExtensions.put("2.5.29.19", "Basic Constraints");
		certExtensions.put("2.5.29.20", "CRL Number");
		certExtensions.put("2.5.29.21", "Reason code");
		certExtensions.put("2.5.29.23", "Hold Instruction Code");
		certExtensions.put("2.5.29.24", "Invalidity Date");
		certExtensions.put("2.5.29.27", "Delta CRL indicator");
		certExtensions.put("2.5.29.28", "Issuing Distribution Point");
		certExtensions.put("2.5.29.29", "Certificate Issuer");
		certExtensions.put("2.5.29.30", "Name Constraints");
		certExtensions.put("2.5.29.31", "CRL Distribution Points");
		certExtensions.put("2.5.29.32", "Certificate Policies");
		certExtensions.put("2.5.29.33", "Policy Mappings");
		certExtensions.put("2.5.29.35", "Authority Key Identifier");
		certExtensions.put("2.5.29.36", "Policy Constraints");
		certExtensions.put("2.5.29.37", "Extended key usage");
		certExtensions.put("2.5.29.46", "FreshestCRL");
		certExtensions.put("2.5.29.54", "X.509 version 3 certificate extension Inhibit Any-policy");

	}
}
