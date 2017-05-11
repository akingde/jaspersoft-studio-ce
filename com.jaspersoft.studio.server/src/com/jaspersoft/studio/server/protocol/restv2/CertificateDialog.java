/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.jaspersoft.studio.server.messages.Messages;

import jersey.repackaged.com.google.common.collect.Lists;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class CertificateDialog extends ATitledDialog {
	private X509Certificate client;
	private String message;
	private X509Certificate[] chain;
	protected TableViewer viewer;

	public CertificateDialog(Shell parentShell, String message, X509Certificate client, X509Certificate[] chain) {
		super(parentShell, SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.OK | SWT.APPLICATION_MODAL, false);
		setTitle(Messages.CertificateDialog_0);
		this.client = client;
		this.message = message;
		this.chain = chain;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, Messages.CertificateDialog_1, true);
		createButton(parent, IDialogConstants.CANCEL_ID, Messages.CertificatesDialog_2, false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		createLabel1(cmp);

		viewer = new TableViewer(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		TableViewerColumn col = new TableViewerColumn(viewer, SWT.NONE);
		col.getColumn().setWidth(600);
		col.setLabelProvider(new CertificateLabelProvider());
		viewer.setContentProvider(ArrayContentProvider.getInstance());

		List<X509Certificate> input = Lists.reverse(Arrays.asList(chain));
		viewer.setInput(input);

		Table table = viewer.getTable();
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 70;
		gd.widthHint = 500;
		table.setLayoutData(gd);

		final StyledText cTxt = new StyledText(cmp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);

		cTxt.setLeftMargin(3);
		cTxt.setTopMargin(3);
		cTxt.setLineSpacing(1);
		gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 500;
		gd.heightHint = 400;
		cTxt.setLayoutData(gd);
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) viewer.getSelection();
				StyledString ss = getStyledToolTip((X509Certificate) sel.getFirstElement());

				cTxt.setText(ss.getString());
				cTxt.setStyleRanges(ss.getStyleRanges());
			}
		});

		viewer.setSelection(new StructuredSelection(client), true);
		return cmp;
	}

	protected void createLabel1(Composite cmp) {
		StyledText lbl1 = new StyledText(cmp, SWT.READ_ONLY | SWT.WRAP);
		lbl1.setLineSpacing(2);
		lbl1.setBackground(cmp.getBackground());
		String msg1 = Messages.CertificateDialog_2;
		lbl1.setText(msg1 + message);
		lbl1.setStyleRanges(new StyleRange[] { new StyleRange(0, msg1.length(), null, null, SWT.BOLD),
				new StyleRange(msg1.length(), message.length(), null, null, SWT.ITALIC) });
		lbl1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	public static CertificateLabelProvider getLabelProvider() {
		return new CertificateLabelProvider();
	}

	public static class CertificateLabelProvider extends ColumnLabelProvider {

		@Override
		public String getText(Object element) {
			return ((X509Certificate) element).getSubjectDN().getName();
		}

	}

	public static StyledString getStyledToolTip(X509Certificate cert) {
		StyledString ss = new StyledString();
		if (cert == null)
			return ss;
		ss.append(Messages.CertificateDialog_4, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getVersion() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		ss.append(Messages.CertificateDialog_7, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSerialNumber() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		ss.append(Messages.CertificateDialog_10, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSigAlgName() + " (" + cert.getSigAlgOID() + ")\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		ss.append(Messages.CertificateDialog_14, StyledString.QUALIFIER_STYLER);
		ss.append(" " + Misc.nvl(cert.getSigAlgParams(), Messages.CertificateDialog_16) + "\n"); //$NON-NLS-1$ //$NON-NLS-3$
		ss.append(Messages.CertificateDialog_18, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getIssuerDN() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		ss.append(Messages.CertificateDialog_21, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getNotBefore() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		ss.append(Messages.CertificateDialog_24, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getNotAfter() + "\n\n"); //$NON-NLS-1$ //$NON-NLS-2$
		ss.append(Messages.CertificateDialog_27, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSubjectDN() + "\n\n"); //$NON-NLS-1$ //$NON-NLS-2$

		ss.append(Messages.CertificateDialog_30, StyledString.QUALIFIER_STYLER);

		ss.append(" " + WordUtils.wrap(cert.getPublicKey().toString(), 50, null, true) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

		ss.append(Messages.CertificateDialog_33, StyledString.QUALIFIER_STYLER);
		ss.append(" " + cert.getSigAlgName() + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
		ss.append(Messages.CertificateDialog_36, StyledString.QUALIFIER_STYLER);
		ss.append(" " + WordUtils.wrap(Hex.encodeHexString(cert.getSignature()), 50, null, true) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$

		ss.append(Messages.CertificateDialog_39, StyledString.QUALIFIER_STYLER);
		for (String ext : cert.getCriticalExtensionOIDs()) {
			ss.append(Messages.CertificateDialog_40, StyledString.QUALIFIER_STYLER);
			ss.append(Messages.CertificateDialog_41 + Misc.nvl(certExtensions.get(ext)) + " (" + ext + ")\n"); //$NON-NLS-2$ //$NON-NLS-3$
			try {
				ss.append(getExtensionValue(cert, ext) + "\n"); //$NON-NLS-1$
			} catch (IOException e) {
			}
		}
		for (String ext : cert.getNonCriticalExtensionOIDs()) {
			ss.append(Messages.CertificateDialog_45, StyledString.QUALIFIER_STYLER);
			ss.append(Messages.CertificateDialog_46 + Misc.nvl(certExtensions.get(ext)) + " (" + ext + ")\n"); //$NON-NLS-2$ //$NON-NLS-3$
			try {
				ss.append(getExtensionValue(cert, ext) + "\n"); //$NON-NLS-1$
			} catch (IOException e) {
			}
		}
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
		return ""; //$NON-NLS-1$
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
		certExtensions.put("2.5.29.1", Messages.CertificateDialog_52); //$NON-NLS-1$
		certExtensions.put("2.5.29.2", Messages.CertificateDialog_54); //$NON-NLS-1$
		certExtensions.put("2.5.29.3", Messages.CertificateDialog_56); //$NON-NLS-1$
		certExtensions.put("2.5.29.4", Messages.CertificateDialog_58); //$NON-NLS-1$
		certExtensions.put("2.5.29.9", Messages.CertificateDialog_60); //$NON-NLS-1$
		certExtensions.put("2.5.29.14", Messages.CertificateDialog_62); //$NON-NLS-1$
		certExtensions.put("2.5.29.15", Messages.CertificateDialog_64); //$NON-NLS-1$
		certExtensions.put("2.5.29.16", Messages.CertificateDialog_66); //$NON-NLS-1$
		certExtensions.put("2.5.29.17", Messages.CertificateDialog_68); //$NON-NLS-1$
		certExtensions.put("2.5.29.18", Messages.CertificateDialog_70); //$NON-NLS-1$
		certExtensions.put("2.5.29.19", Messages.CertificateDialog_72); //$NON-NLS-1$
		certExtensions.put("2.5.29.20", Messages.CertificateDialog_74); //$NON-NLS-1$
		certExtensions.put("2.5.29.21", Messages.CertificateDialog_76); //$NON-NLS-1$
		certExtensions.put("2.5.29.23", Messages.CertificateDialog_78); //$NON-NLS-1$
		certExtensions.put("2.5.29.24", Messages.CertificateDialog_80); //$NON-NLS-1$
		certExtensions.put("2.5.29.27", Messages.CertificateDialog_82); //$NON-NLS-1$
		certExtensions.put("2.5.29.28", Messages.CertificateDialog_84); //$NON-NLS-1$
		certExtensions.put("2.5.29.29", Messages.CertificateDialog_86); //$NON-NLS-1$
		certExtensions.put("2.5.29.30", Messages.CertificateDialog_88); //$NON-NLS-1$
		certExtensions.put("2.5.29.31", Messages.CertificateDialog_90); //$NON-NLS-1$
		certExtensions.put("2.5.29.32", Messages.CertificateDialog_92); //$NON-NLS-1$
		certExtensions.put("2.5.29.33", Messages.CertificateDialog_94); //$NON-NLS-1$
		certExtensions.put("2.5.29.35", Messages.CertificateDialog_96); //$NON-NLS-1$
		certExtensions.put("2.5.29.36", Messages.CertificateDialog_98); //$NON-NLS-1$
		certExtensions.put("2.5.29.37", Messages.CertificateDialog_100); //$NON-NLS-1$
		certExtensions.put("2.5.29.46", Messages.CertificateDialog_102); //$NON-NLS-1$
		certExtensions.put("2.5.29.54", Messages.CertificateDialog_104); //$NON-NLS-1$

	}
}
