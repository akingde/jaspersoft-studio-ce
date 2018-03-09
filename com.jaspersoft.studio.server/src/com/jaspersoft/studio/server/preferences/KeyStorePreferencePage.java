/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.preferences;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.jface.dialogs.NameDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.protocol.restv2.CertChainValidator;
import com.jaspersoft.studio.server.protocol.restv2.CertificateDialog;
import com.jaspersoft.studio.server.protocol.restv2.CertificateDialog.CertificateLabelProvider;
import com.jaspersoft.studio.utils.UIUtil;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class KeyStorePreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	class Certificates {
		public String alias;
		public Key key;
		public Certificate[] chain;

		@Override
		public String toString() {
			return alias;
		}
	}

	public static final String PAGE_ID = "com.jaspersoft.studio.server.preferences.KeyStorePreferencePage";
	// private TableViewer viewer;
	private TreeViewer treeviewer;
	private KeyStore trustStore;
	private Map<String, Certificates> aliases = new HashMap<String, Certificates>();
	private List<Certificates> certificates = new ArrayList<Certificates>();

	public KeyStorePreferencePage() {
		super("Client Authentication Key Store");
		noDefaultAndApplyButton();
	}

	@Override
	protected Control createContents(final Composite parent) {
		Composite cmp = new Composite(parent, SWT.NULL);
		cmp.setLayout(new GridLayout(2, false));
		cmp.setFont(parent.getFont());

		Tree wtree = new Tree(cmp, SWT.V_SCROLL | SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = 70;
		wtree.setLayoutData(gd);

		treeviewer = new TreeViewer(wtree);
		treeviewer.setContentProvider(new ITreeContentProvider() {

			@Override
			public boolean hasChildren(Object element) {
				return element instanceof Certificates;
			}

			@Override
			public Object getParent(Object element) {
				return null;
			}

			@Override
			public Object[] getElements(Object element) {
				return getChildren(element);
			}

			@Override
			public Object[] getChildren(Object element) {
				if (element instanceof Collection)
					return ((Collection<?>) element).toArray();
				if (element instanceof Certificates) {
					List<Object> res = new ArrayList<Object>();
					if (((Certificates) element).key != null)
						res.add(((Certificates) element).key);
					if (((Certificates) element).chain != null)
						res.addAll(Arrays.asList(((Certificates) element).chain));
					return res.toArray();
				}
				return null;
			}

			@Override
			public void dispose() {
				// nothing to do
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				// nothing to do
			}
		});
		treeviewer.setLabelProvider(new CertificateLabelProvider());

		UIUtil.setViewerCellEditingOnDblClick(treeviewer);
		ColumnViewerToolTipSupport.enableFor(treeviewer, ToolTip.NO_RECREATE);
		treeviewer.setInput(certificates);
		treeviewer.expandAll();

		Composite bcmp = new Composite(cmp, SWT.NONE);
		bcmp.setLayout(new GridLayout());
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.verticalSpan = 2;
		bcmp.setLayoutData(gd);

		Button nb = new Button(bcmp, SWT.PUSH);
		nb.setText(Messages.common_add);
		nb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		nb.addSelectionListener(new SelectionAdapter() {
			private String askPassword(String p) {
				NameDialog d = new NameDialog(parent.getShell(), "Key Store Password", p, SWT.BORDER | SWT.PASSWORD,
						true);
				if (d.open() == Dialog.OK)
					return d.getName();
				return null;
			}

			@Override
			public void widgetSelected(SelectionEvent ev) {
				FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.p12;*.pkcs12;*.pfx;", "*.jks", /* "*.pem", */ "*.*" });
				String result = dialog.open();
				if (!Misc.isNullOrEmpty(result)) {
					BufferedInputStream in = null;
					try {
						in = new BufferedInputStream(new FileInputStream(result));
						in.mark(Integer.MAX_VALUE);
						if (result.endsWith(".pem")) {

						} else if (result.endsWith(".der")) {

						} else if (result.endsWith(".p12") || result.endsWith(".pkcs12") || result.endsWith(".pfx"))
							readKeyStore(in, KeyStore.getInstance("pkcs12"));
						else if (result.endsWith(".jks") || result.endsWith(".cer"))
							readKeyStore(in, KeyStore.getInstance(KeyStore.getDefaultType()));
					} catch (CertificateException e) {
						UIUtils.showError(e);
					} catch (IOException e) {
						UIUtils.showError(e);
					} catch (NoSuchAlgorithmException e) {
						UIUtils.showError(e);
					} catch (KeyStoreException e) {
						UIUtils.showError(e);
					} finally {
						net.sf.jasperreports.eclipse.util.FileUtils.closeStream(in);
					}
				}
			}

			protected void readKeyStore(BufferedInputStream in, KeyStore kspkcs12)
					throws NoSuchAlgorithmException, CertificateException, IOException, KeyStoreException {
				String password = "";
				char[] pass = password.toCharArray();
				while (true) {
					password = askPassword(password);
					if (password == null)
						return;
					pass = password.toCharArray();
					try {
						kspkcs12.load(in, pass);
						break;
					} catch (IOException ex) {
						in.reset();
						UIUtils.showError(ex);
					}
				}
				// what if alias exists? just overwrite?
				Enumeration<String> eAliases = kspkcs12.aliases();
				while (eAliases.hasMoreElements()) {
					Certificates c = readCertificates(eAliases.nextElement(), kspkcs12, pass);
					Certificates existing = null;
					for (Certificates it : certificates) {
						if (it.alias.equals(c.alias)) {
							existing = it;
							break;
						}
					}
					if (existing != null) {
						certificates.remove(existing);
						Certificates old = aliases.get(c.alias);
						if (old != null)
							aliases.put(c.alias, c);
					}
					certificates.add(c);
				}
				treeviewer.refresh();
				treeviewer.expandAll();
			}
		});

		final Button db = new Button(bcmp, SWT.PUSH);
		db.setText(Messages.common_delete);
		db.setEnabled(false);
		db.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		db.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				Object fe = sel.getFirstElement();
				if (fe instanceof Certificates) {
					certificates.remove(fe);
					treeviewer.refresh();
					treeviewer.expandAll();
				}
			}
		});

		final StyledText cTxt = new StyledText(cmp,
				SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY | SWT.BORDER);

		cTxt.setLeftMargin(3);
		cTxt.setTopMargin(3);
		cTxt.setLineSpacing(1);
		cTxt.setLayoutData(new GridData(GridData.FILL_BOTH));

		treeviewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection) treeviewer.getSelection();
				Object fe = sel.getFirstElement();
				if (fe instanceof X509Certificate) {
					StyledString ss = CertificateDialog.getStyledToolTip((X509Certificate) fe);

					cTxt.setText(ss.getString());
					cTxt.setStyleRanges(ss.getStyleRanges());
				} else {
					StyledString ss = new StyledString("");
					cTxt.setText(ss.getString());
					cTxt.setStyleRanges(ss.getStyleRanges());
				}

				db.setEnabled(!sel.isEmpty() && fe instanceof Certificates);
			}

		});

		return cmp;
	}

	@Override
	public boolean performOk() {
		try {
			for (String alias : aliases.keySet()) {
				Certificates c = aliases.get(alias);
				if (!certificates.contains(c))
					trustStore.deleteEntry(alias);
			}

			for (Certificates c : certificates) {
				trustStore.deleteEntry(c.alias);
				if (c.key != null)
					trustStore.setKeyEntry(c.alias, c.key, CertChainValidator.kpass, c.chain);
				else
					for (Certificate cert : c.chain) {
						trustStore.setCertificateEntry(c.alias, cert);

					}
			}
			CertChainValidator.writeKeyStore(trustStore);
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
			trustStore = CertChainValidator.getDefaultKeyStore();
			Enumeration<String> alias = trustStore.aliases();
			while (alias.hasMoreElements()) {
				Certificates c = readCertificates(alias.nextElement(), trustStore, CertChainValidator.kpass);
				certificates.add(c);
				aliases.put(c.alias, c);
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

	protected Certificates readCertificates(String alias, KeyStore ks, char[] pass)
			throws KeyStoreException, NoSuchAlgorithmException {
		Certificates c = new Certificates();
		c.alias = alias;
		if (ks.isKeyEntry(c.alias)) {
			try {
				c.key = ks.getKey(c.alias, pass);
			} catch (UnrecoverableKeyException e) {
				UIUtils.showError(e);
			}
		}
		c.chain = ks.getCertificateChain(c.alias);
		if (c.chain == null)
			c.chain = new Certificate[] { ks.getCertificate(c.alias) };
		return c;
	}

}
