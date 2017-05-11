/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.pages;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.net.ssl.SSLHandshakeException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Async;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.conn.ssl.BrowserCompatHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionValidator;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.jasperserver.dto.authority.ClientRole;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.compatibility.dialog.VersionCombo;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.preferences.CASListFieldEditor;
import com.jaspersoft.studio.server.preferences.CASPreferencePage;
import com.jaspersoft.studio.server.preferences.SSOServer;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.protocol.JSSTrustStrategy;
import com.jaspersoft.studio.server.protocol.JdbcDriver;
import com.jaspersoft.studio.server.protocol.Version;
import com.jaspersoft.studio.server.protocol.restv2.CertChainValidator;
import com.jaspersoft.studio.server.secret.JRServerSecretsProvider;
import com.jaspersoft.studio.server.wizard.ServerProfileWizardDialog;
import com.jaspersoft.studio.server.wizard.validator.URLValidator;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.swt.widgets.WLocale;
import com.jaspersoft.studio.swt.widgets.WSecretText;
import com.jaspersoft.studio.swt.widgets.WTimeZone;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.WizardEndingStateListener;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.ui.validator.EmptyStringValidator;
import net.sf.jasperreports.eclipse.ui.validator.NotEmptyIFolderValidator;
import net.sf.jasperreports.eclipse.util.CastorHelper;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class ServerProfilePage extends WizardPage implements WizardEndingStateListener {
	private MServerProfile sprofile;
	private WSecretText tpass;
	private Text tuser;
	private Text ttimeout;
	private Text lpath;
	private Button bchunked;
	private Combo bmime;
	private Button bdaterange;
	private Button bUseSoap;
	private Button bSyncDA;
	private Button blpath;
	private VersionCombo cversion;
	private DataBindingContext dbc;
	private Text txtInfo;
	private WLocale loc;
	private WTimeZone tz;
	private Combo bSSO;
	private Combo ccas;
	private boolean refreshing = false;

	public ServerProfilePage(MServerProfile sprofile) {
		super("serverprofilepage"); //$NON-NLS-1$
		setTitle(Messages.ServerProfilePage_1);
		setDescription(Messages.ServerProfilePage_2);
		this.sprofile = sprofile;
	}

	public void createControl(final Composite parent) {
		dbc = new DataBindingContext();
		WizardPageSupport.create(this, dbc);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		new Label(composite, SWT.NONE).setText(Messages.ServerProfilePage_3);
		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);

		Composite urlCmp = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		urlCmp.setLayout(layout);

		new Label(urlCmp, SWT.NONE).setText(Messages.ServerProfilePage_4);

		ssLabel = new Label(urlCmp, SWT.NONE);
		ssLabel.setImage(Activator.getDefault().getImage("icons/lock.png")); //$NON-NLS-1$
		ssLabel.setToolTipText(Messages.ServerProfilePage_48);
		gd = new GridData();
		gd.widthHint = 24;
		ssLabel.setLayoutData(gd);

		Text turl = new Text(composite, SWT.BORDER);
		turl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		turl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				closeConnection();
			}
		});

		new Label(composite, SWT.RIGHT);
		cstatus = new Label(composite, SWT.RIGHT);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END);
		gd.widthHint = 200;
		cstatus.setLayoutData(gd);

		Group gr = new Group(composite, SWT.NONE);
		gr.setText(Messages.ServerProfilePage_8);
		gr.setLayout(new GridLayout(2, false));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gr.setLayoutData(gd);

		new Label(gr, SWT.NONE).setText(Messages.ServerProfilePage_9);
		Text torg = new Text(gr, SWT.BORDER);
		torg.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		torg.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				closeConnection();
			}
		});

		createCredentials(gr);

		final Section expcmp = new Section(composite, ExpandableComposite.TREE_NODE);
		expcmp.setTitleBarForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		UIUtil.setBold(expcmp);
		expcmp.setText(Messages.ServerProfilePage_advancedsettings);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		expcmp.setLayoutData(gd);
		expcmp.setExpanded(false);

		tabFolder = new CTabFolder(expcmp, SWT.BOTTOM);
		expcmp.setClient(tabFolder);

		CTabItem bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ServerProfilePage_0);
		bptab.setControl(createAdvancedSettings(tabFolder));

		bptab = new CTabItem(tabFolder, SWT.NONE);
		bptab.setText(Messages.ServerProfilePage_5);
		bptab.setControl(createInfo(tabFolder));

		createJdbcDrivers(tabFolder);

		tabFolder.setSelection(0);

		expcmp.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				UIUtils.relayoutDialog(getShell(), 0, -1);
			}
		});
		ServerProfile value = sprofile.getValue();
		try {
			refreshing = true;
			proxy = new Proxy(value);
			dbc.bindValue(SWTObservables.observeText(tname, SWT.Modify), PojoObservables.observeValue(value, "name"), //$NON-NLS-1$
					new UpdateValueStrategy().setAfterConvertValidator(new EmptyStringValidator() {
						@Override
						public IStatus validate(Object value) {
							IStatus s = super.validate(value);
							if (s.equals(Status.OK_STATUS) && !ServerManager.isUniqueName(sprofile, (String) value)) {
								return ValidationStatus.warning(Messages.ServerProfilePage_13);
							}
							return s;
						}
					}), null);
			dbc.bindValue(SWTObservables.observeText(turl, SWT.Modify), PojoObservables.observeValue(proxy, "url"), //$NON-NLS-1$
					new UpdateValueStrategy().setAfterConvertValidator(new URLValidator() {
						@Override
						public IStatus validate(Object value) {
							IStatus status = super.validate(value);
							((ServerProfileWizardDialog) getContainer()).setTestButtonEnabled(status.isOK());
							return status;
						}
					}), null);
			dbc.bindValue(SWTObservables.observeText(lpath, SWT.Modify),
					PojoObservables.observeValue(proxy, "projectPath"), //$NON-NLS-1$
					new UpdateValueStrategy().setAfterConvertValidator(new NotEmptyIFolderValidator()), null);
			dbc.bindValue(SWTObservables.observeText(torg, SWT.Modify),
					PojoObservables.observeValue(value, "organisation")); //$NON-NLS-1$
			userValidator = new UsernameValidator(!(value.isUseSSO() || value.isAskPass()));
			dbc.bindValue(SWTObservables.observeText(tuser, SWT.Modify), PojoObservables.observeValue(value, "user"), //$NON-NLS-1$
					new UpdateValueStrategy().setAfterConvertValidator(userValidator), null);
			dbc.bindValue(SWTObservables.observeText(tuserA, SWT.Modify), PojoObservables.observeValue(value, "user"), //$NON-NLS-1$
					new UpdateValueStrategy().setAfterConvertValidator(userValidator), null);
			dbc.bindValue(SWTObservables.observeText(tpass, SWT.Modify), PojoObservables.observeValue(value, "pass")); //$NON-NLS-1$

			dbc.bindValue(SWTObservables.observeText(ttimeout, SWT.Modify),
					PojoObservables.observeValue(value, "timeout")); //$NON-NLS-1$

			dbc.bindValue(SWTObservables.observeSelection(bchunked), PojoObservables.observeValue(value, "chunked")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeText(bmime), PojoObservables.observeValue(proxy, "mime")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeText(loc.getCombo()), PojoObservables.observeValue(value, "locale")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeText(tz.getCombo()), PojoObservables.observeValue(value, "timeZone")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeSingleSelectionIndex(bSSO), PojoObservables.observeValue(proxy, "sso")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeSelection(bdaterange),
					PojoObservables.observeValue(value, "supportsDateRanges")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeSelection(bUseSoap),
					PojoObservables.observeValue(value, "useOnlySOAP")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeSelection(bSyncDA), PojoObservables.observeValue(value, "syncDA")); //$NON-NLS-1$
			dbc.bindValue(SWTObservables.observeSelection(bLogging), PojoObservables.observeValue(value, "logging")); //$NON-NLS-1$

			dbc.bindValue(SWTObservables.observeText(cversion.getControl()),
					PojoObservables.observeValue(proxy, "jrVersion")); //$NON-NLS-1$

			tpass.loadSecret(JRServerSecretsProvider.SECRET_NODE_ID, Misc.nvl(sprofile.getValue().getPass()));

		} finally {
			refreshing = false;
		}
		showServerInfo();
	}

	protected void createCredentials(Group gr) {
		cmpCredential = new Composite(gr, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cmpCredential.setLayoutData(gd);
		stackLayout = new StackLayout();
		stackLayout.marginWidth = 0;
		cmpCredential.setLayout(stackLayout);

		cmpUP = new Composite(cmpCredential, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmpUP.setLayout(layout);

		new Label(cmpUP, SWT.NONE).setText(Messages.ServerProfilePage_10);
		tuser = new Text(cmpUP, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tuser.setTextLimit(100);
		tuser.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				closeConnection();
			}
		});

		new Label(cmpUP, SWT.NONE).setText(Messages.ServerProfilePage_11);
		tpass = new WSecretText(cmpUP, SWT.BORDER | SWT.PASSWORD);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		cmpCAS = new Composite(cmpCredential, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmpCAS.setLayout(layout);

		new Label(cmpCAS, SWT.NONE).setText(Messages.ServerProfilePage_23);
		ccas = new Combo(cmpCAS, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);

		String v = null;
		v = JasperReportsConfiguration.getDefaultInstance().getPrefStore().getString(CASPreferencePage.CAS);
		for (String line : v.split("\n")) { //$NON-NLS-1$
			if (line.isEmpty())
				continue;
			try {
				SSOServer srv = (SSOServer) CastorHelper.read(new ByteArrayInputStream(Base64.decodeBase64(line)),
						CASListFieldEditor.mapping);
				ssoservers.add(srv);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		final ServerProfile value = sprofile.getValue();
		String[] items = new String[ssoservers.size()];
		int sel = 0;
		for (int i = 0; i < ssoservers.size(); i++) {
			SSOServer srv = ssoservers.get(i);
			items[i] = srv.getUrl();
			if (srv.getUuid().equals(value.getSsoUuid()))
				sel = i;
		}
		ccas.setItems(items);
		ccas.select(sel);
		if (sel >= 0 && sel < ssoservers.size())
			value.setSsoUuid(ssoservers.get(sel).getUuid());
		ccas.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int ind = ccas.getSelectionIndex();
				if (ind >= 0 && ind < ssoservers.size())
					value.setSsoUuid(ssoservers.get(ind).getUuid());
			}
		});

		cmpAsk = new Composite(cmpCredential, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		cmpAsk.setLayout(layout);

		new Label(cmpAsk, SWT.NONE).setText(Messages.ServerProfilePage_10);
		tuserA = new Text(cmpAsk, SWT.BORDER);
		tuserA.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tuserA.setTextLimit(100);
		tuserA.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				closeConnection();
			}
		});

		if (value.isUseSSO()) {
			stackLayout.topControl = cmpCAS;
		} else if (value.isAskPass()) {
			stackLayout.topControl = cmpAsk;
		} else {
			stackLayout.topControl = cmpUP;
		}
	}

	private List<SSOServer> ssoservers = new ArrayList<SSOServer>();
	private Composite cmpUP;
	private Composite cmpCAS;
	private StackLayout stackLayout;
	private Composite cmpCredential;
	private CTabItem drvtab;
	private CTabFolder tabFolder;
	private Button bUpld;
	private Label cstatus;

	private Composite createAdvancedSettings(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_36);

		bSSO = new Combo(cmp, SWT.READ_ONLY);
		bSSO.setItems(new String[] { Messages.ServerProfilePage_38, Messages.ServerProfilePage_45,
				Messages.ServerProfilePage_46 });
		bSSO.setText(Messages.ServerProfilePage_18);
		bSSO.setToolTipText(Messages.ServerProfilePage_20);
		bSSO.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				switch (bSSO.getSelectionIndex()) {
				case 0:
					userValidator.setAllowNull(false);
					stackLayout.topControl = cmpUP;
					bUseSoap.setEnabled(true);
					break;
				case 1:
					userValidator.setAllowNull(true);
					stackLayout.topControl = cmpAsk;
					bUseSoap.setEnabled(true);

					break;
				case 2:
					userValidator.setAllowNull(true);
					stackLayout.topControl = cmpCAS;
					bUseSoap.setSelection(false);
					bUseSoap.setEnabled(false);
					break;
				}
				cmpCredential.layout();
				closeConnection();
				UIUtils.getDisplay().asyncExec(new Runnable() {
					public void run() {
						dbc.updateTargets();
					}
				});
			}
		});
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		bSSO.setLayoutData(gd);

		lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_jrversion);

		cversion = new VersionCombo(cmp);
		((Combo) cversion.getControl()).setItem(0, Messages.ServerProfilePage_25);
		cversion.setVersion(JRXmlWriterHelper.LAST_VERSION);
		gd = new GridData();
		gd.horizontalSpan = 2;
		cversion.getControl().setLayoutData(gd);

		String tt = Messages.ServerProfilePage_24;
		lbl.setToolTipText(tt);
		cversion.getControl().setToolTipText(tt);

		bdaterange = new Button(cmp, SWT.CHECK);
		bdaterange.setText(Messages.ServerProfilePage_daterangeexpression);
		gd = new GridData();
		gd.horizontalSpan = 2;
		bdaterange.setLayoutData(gd);

		tt = Messages.ServerProfilePage_28;
		bdaterange.setToolTipText(tt);

		bSyncDA = new Button(cmp, SWT.CHECK);
		bSyncDA.setText(Messages.ServerProfilePage_14);
		bSyncDA.setToolTipText(Messages.ServerProfilePage_15);

		lbl = new Label(cmp, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		lbl.setLayoutData(gd);

		lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_connectiontimeout);

		ttimeout = new Text(cmp, SWT.BORDER);
		gd = new GridData();
		gd.widthHint = 100;
		ttimeout.setLayoutData(gd);

		tt = Messages.ServerProfilePage_26;
		lbl.setToolTipText(tt);
		ttimeout.setToolTipText(tt);

		bchunked = new Button(cmp, SWT.CHECK);
		bchunked.setText(Messages.ServerProfilePage_chunkedrequest);

		tt = Messages.ServerProfilePage_27;
		bchunked.setToolTipText(tt);

		String ttip = Messages.ServerProfilePage_7;
		lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_12);
		lbl.setToolTipText(ttip);

		bmime = new Combo(cmp, SWT.READ_ONLY);
		bmime.setItems(new String[] { "MIME", "DIME" }); //$NON-NLS-1$ //$NON-NLS-2$
		bmime.setToolTipText(ttip);

		bUseSoap = new Button(cmp, SWT.CHECK);
		bUseSoap.setText(Messages.ServerProfilePage_6);
		tt = Messages.ServerProfilePage_29;
		bUseSoap.setToolTipText(tt);

		bLogging = new Button(cmp, SWT.CHECK);
		bLogging.setText(Messages.ServerProfilePage_34);
		bLogging.setToolTipText(Messages.ServerProfilePage_35);
		gd = new GridData();
		gd.horizontalSpan = 2;
		bLogging.setLayoutData(gd);

		lbl = new Label(cmp, SWT.SEPARATOR | SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 3;
		lbl.setLayoutData(gd);

		ttip = Messages.ServerProfilePage_16;

		lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_17);
		lbl.setToolTipText(ttip);

		Composite c = new Composite(cmp, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		c.setLayout(layout);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);

		lpath = new Text(c, SWT.BORDER | SWT.READ_ONLY);
		lpath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		lpath.setToolTipText(ttip);

		blpath = new Button(c, SWT.PUSH);
		blpath.setText("..."); //$NON-NLS-1$
		blpath.setToolTipText(ttip);

		blpath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IContainer root = ResourcesPlugin.getWorkspace().getRoot();
				ServerProfile sp = sprofile.getValue();
				String ppath = sp.getProjectPath();
				if (!Misc.isNullOrEmpty(ppath)) {
					IResource r = root.findMember(ppath);
					if (r instanceof IContainer)
						root = (IContainer) r;
					else if (r == null) {
						IContainer c = null;
						for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects())
							if (p.isOpen()) {
								String prjName = "/" + p.getName(); //$NON-NLS-1$
								if (ppath.equals(prjName)) {
									c = p;
									break;
								}
								prjName += "/"; //$NON-NLS-1$
								if (ppath.startsWith(prjName)) {
									IFolder f = p.getFolder(ppath.substring(prjName.length()));
									if (f.exists())
										c = f;
									else
										c = p;
									break;
								}
							}
						if (c == null)
							ppath = null;
						else
							root = c;
					} else
						ppath = null;
				}
				if (Misc.isNullOrEmpty(ppath)) {
					try {
						IProject prj = FileUtils.getProject(new NullProgressMonitor());
						if (prj != null)
							root = prj.getFolder(sp.getName().replace(" ", "") + "-" + System.currentTimeMillis()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					} catch (JavaModelException e1) {
						UIUtils.showError(e1);
					} catch (CoreException e1) {
						UIUtils.showError(e1);
					}
				}
				ContainerSelectionDialog csd = new ContainerSelectionDialog(getShell(), root, true, null);
				csd.setValidator(new ISelectionValidator() {

					@Override
					public String isValid(Object selection) {
						if (selection instanceof Path) {
							Path sp = (Path) selection;
							String s0 = sp.segment(0);
							for (IProject p : ResourcesPlugin.getWorkspace().getRoot().getProjects())
								if (p.isOpen() && s0.equals(p.getName()))
									return null;
							return Messages.ServerProfilePage_37;
						}
						return null;
					}
				});
				csd.showClosedProjects(false);
				if (csd.open() == Dialog.OK) {
					Object[] selection = csd.getResult();
					if (selection != null && selection.length > 0 && selection[0] instanceof Path) {
						sprofile.setProjectPath(((Path) selection[0]).toPortableString());
						try {
							refreshing = true;
							dbc.updateTargets();
						} finally {
							refreshing = false;
						}
					}
				}
			}
		});

		lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_21);

		loc = new WLocale(cmp, SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 2;
		loc.setLayoutData(gd);

		tt = Messages.ServerProfilePage_30;
		lbl.setToolTipText(tt);
		loc.setToolTipText(tt);

		lbl = new Label(cmp, SWT.NONE);
		lbl.setText(Messages.ServerProfilePage_22);

		tz = new WTimeZone(cmp, SWT.BORDER);
		gd = new GridData();
		gd.horizontalSpan = 2;
		tz.setLayoutData(gd);

		tt = Messages.ServerProfilePage_31;
		lbl.setToolTipText(tt);
		loc.setToolTipText(tt);

		return cmp;
	}

	private Composite createInfo(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout());

		txtInfo = new Text(cmp, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		txtInfo.setLayoutData(new GridData(GridData.FILL_BOTH));
		txtInfo.setBackground(cmp.getBackground());

		return cmp;
	}

	public void showServerInfo() {
		try {
			txtInfo.setText(sprofile.getConnectionInfo());
			// dbc.updateTargets();
			IConnection c = sprofile.getWsClient();
			if (c != null) {
				ServerInfo si = c.getServerInfo(null);
				// cversion.getControl().setEnabled(Version.isEstimated(si));
				bdaterange.setEnabled(!Version.isDateRangeSupported(si));
				cstatus.setText(Messages.ServerProfilePage_32);
			} else
				cstatus.setText(Messages.ServerProfilePage_33);
			bUseSoap.setEnabled(bSSO.getSelectionIndex() != 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose() {
		pushCursor.dispose();
		super.dispose();
	}

	private Cursor pushCursor = new Cursor(UIUtils.getDisplay(), SWT.CURSOR_HAND);

	public class Proxy {
		private ServerProfile sp;

		public Proxy(ServerProfile sp) {
			this.sp = sp;
		}

		public void setUrl(String url) {
			sp.setUrl(Misc.nvl(url).trim());
		}

		private MouseAdapter mlistener = new MouseAdapter() {
			private long time1;
			private ProgressMonitorDialog pmd;
			private boolean shown;

			@Override
			public void mouseUp(MouseEvent e) {
				if (e.time - time1 > 1000)
					time1 = e.time;
				else
					return;
				ssLabel.removeMouseListener(mlistener);
				try {
					SSLContextBuilder builder = SSLContexts.custom();
					final KeyStore trustStore = CertChainValidator.getDefaultTrustStore();
					builder.loadTrustMaterial(trustStore, new JSSTrustStrategy(trustStore) {
						@Override
						public boolean isTrusted(final X509Certificate[] chain, String authType)
								throws CertificateException {
							getContainer().getShell().getDisplay().syncExec(new Runnable() {
								public void run() {
									if (!shown) {
										new CertificatesDialog(UIUtils.getShell(), "", chain[0], chain) //$NON-NLS-1$
												.open();
										shown = true;
									}
								}
							});

							return true;
						}
					});
					SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),
							new BrowserCompatHostnameVerifier());

					URL targetURL = sp.getURL();

					final Executor exec = Executor
							.newInstance(HttpClientBuilder.create().setSSLSocketFactory(sslsf).build());
					HttpUtils.setupProxy(exec, targetURL.toURI());
					HttpHost proxy = HttpUtils.getUnauthProxy(exec, targetURL.toURI());
					final Request req = Request.Get(targetURL.toString());
					if (proxy != null)
						req.viaProxy(proxy);
					if (pmd != null) {
						pmd.getProgressMonitor().setCanceled(true);
					}
					shown = false;
					pmd = new ProgressMonitorDialog(getContainer().getShell());
					pmd.run(true, true, new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException, InterruptedException {
							monitor.beginTask("Connecting", IProgressMonitor.UNKNOWN);
							Future<Content> future = Async.newInstance().use(exec).execute(req,
									new FutureCallback<Content>() {

										public void failed(final Exception ex) {
											UIUtils.showError(ex);
										}

										public void completed(final Content content) {
										}

										public void cancelled() {
										}

									});
							while (!future.isDone() && !future.isCancelled()) {
								try {
									Thread.sleep(5);
								} catch (InterruptedException e) {
									return;
								}
								if (monitor.isCanceled()) {
									future.cancel(true);
									return;
								}
							}
						}
					});
				} catch (Exception ex) {
					if (ex instanceof SSLHandshakeException)
						return;
					UIUtils.showError(ex);
				} finally {
					ssLabel.addMouseListener(mlistener);
				}
			}
		};

		public String getUrl() throws MalformedURLException, URISyntaxException {
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					try {
						if (sp.getUrl().trim().startsWith("https://")) { //$NON-NLS-1$
							ssLabel.addMouseListener(mlistener);
							setSslIcon();
							ssLabel.setCursor(new Cursor(ssLabel.getDisplay(), SWT.CURSOR_HAND));
							ssLabel.getParent().getParent().layout(true);
							return;
						}
					} catch (MalformedURLException e) {
					} catch (URISyntaxException e) {
					}

					ssLabel.removeMouseListener(mlistener);
					ssLabel.setImage(null);
					ssLabel.setCursor(null);

					ssLabel.getParent().getParent().layout(true);
				}
			});

			return sp.getUrlString();
		}

		private void setSslIcon() {
			if (sprofile.getWsClient() == null)
				ssLabel.setImage(Activator.getDefault().getImage("icons/lock.png")); //$NON-NLS-1$
			else
				ssLabel.setImage(Activator.getDefault().getImage("icons/lock-green.png")); //$NON-NLS-1$
		}

		public void setJrVersion(String v) {
			sp.setJrVersion(VersionCombo.getJrVersion(v));
		}

		public String getJrVersion() {
			return VersionCombo.getLabelVersion(sp.getJrVersion());
		}

		public void setProjectPath(String projectPath) {
			sprofile.setProjectPath(projectPath);
		}

		public String getProjectPath() {
			return sp.getProjectPath();
		}

		public void setMime(String v) {
			sp.setMime(v.equals("MIME")); //$NON-NLS-1$
		}

		public String getMime() {
			return sp.isMime() ? "MIME" : "DIME"; //$NON-NLS-1$ //$NON-NLS-2$
		}

		public int getSso() {
			if (sp.isUseSSO())
				return 2;
			if (sp.isAskPass())
				return 1;
			return 0;
		}

		public void setSso(int indx) {
			switch (indx) {
			case 0:
				sp.setUseSSO(false);
				sp.setAskPass(false);
				break;
			case 1:
				sp.setAskPass(true);
				sp.setUseSSO(false);
				sp.setPass(null);
				break;
			case 2:
				sp.setAskPass(false);
				sp.setUseSSO(true);
				sp.setPass(null);
				break;
			}
		}
	}

	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem().displayHelp("com.jaspersoft.studio.doc.jaspersoftserver"); //$NON-NLS-1$
	}

	@Override
	public void performFinishInvoked() {
		if (JaspersoftStudioPlugin.shouldUseSecureStorage()) {
			tpass.persistSecret();
			sprofile.getValue().setPass(tpass.getUUIDKey());
		}
	}

	@Override
	public void performCancelInvoked() {

	}

	public void connect() {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (drvtab != null)
					drvtab.dispose();
				if (sprofile != null && sprofile.getWsClient() != null
						&& sprofile.getWsClient().getServerProfile() != null)
					sprofile.getWsClient().getServerProfile().setClientUser(null);
			}
		});
	}

	public void connectionOK() {
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (drvtab != null)
					drvtab.dispose();
				createJdbcDrivers(tabFolder);
				try {
					proxy.getUrl();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JdbcDriver driver = new JdbcDriver();
	private Button bLogging;
	private Composite cmpAsk;
	private Text tuserA;
	private Label ssLabel;
	private Proxy proxy;
	private UsernameValidator userValidator;

	private void createJdbcDrivers(CTabFolder tabFolder) {
		if (sprofile.getWsClient() == null || !sprofile.getWsClient().isSupported(Feature.EXPORTMETADATA)
				|| sprofile.getWsClient().getServerProfile() == null
				|| sprofile.getWsClient().getServerProfile().getClientUser() == null)
			return;
		boolean hasPermission = false;
		for (ClientRole r : sprofile.getWsClient().getServerProfile().getClientUser().getRoleSet())
			if (r.getName().equals("ROLE_SUPERUSER")) { //$NON-NLS-1$
				hasPermission = true;
				break;
			}
		if (!hasPermission)
			return;

		drvtab = new CTabItem(tabFolder, SWT.NONE);
		drvtab.setText(Messages.ServerProfilePage_39);

		Composite cmp = new Composite(tabFolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		Label lbl = new Label(cmp, SWT.NONE | SWT.WRAP);
		lbl.setText(Messages.ServerProfilePage_40);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		new Label(cmp, SWT.NONE).setText(Messages.ServerProfilePage_41);
		final Text dname = new Text(cmp, SWT.BORDER);
		dname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dname.setText(Misc.nvl(driver.getClassname()));

		final ClasspathComponent cpath = new ClasspathComponent(cmp) {
			@Override
			protected void handleClasspathChanged() {
				driver.setPaths(getClasspaths());
				bUpld.setEnabled(!Misc.isNullOrEmpty(getClasspaths()) && !Misc.isNullOrEmpty(dname.getText()));
			}
		};
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		cpath.getControl().setLayoutData(gd);
		if (!Misc.isNullOrEmpty(driver.getPaths()))
			cpath.setClasspaths(driver.getPaths());

		bUpld = new Button(cmp, SWT.PUSH);
		bUpld.setText(Messages.ServerProfilePage_42);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		bUpld.setLayoutData(gd);
		bUpld.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					getContainer().run(false, true, new IRunnableWithProgress() {

						@Override
						public void run(IProgressMonitor monitor)
								throws InvocationTargetException, InterruptedException {
							monitor.beginTask(Messages.ServerProfilePage_43, IProgressMonitor.UNKNOWN);
							driver.setClassname(dname.getText());
							driver.setPaths(cpath.getClasspaths());
							try {
								sprofile.getWsClient().uploadJdbcDrivers(driver, monitor);

								UIUtils.showInformation(Messages.ServerProfilePage_44);
							} catch (Exception e) {
								UIUtils.showError(e);
							}
						}
					});
				} catch (InvocationTargetException e1) {
					UIUtils.showError(e1.getCause());
				} catch (InterruptedException e1) {
					UIUtils.showError(e1.getCause());
				}
			}
		});
		bUpld.setEnabled(!Misc.isNullOrEmpty(cpath.getClasspaths()) && !Misc.isNullOrEmpty(dname.getText()));

		dname.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				driver.setClassname(dname.getText());
				bUpld.setEnabled(!Misc.isNullOrEmpty(cpath.getClasspaths()) && !Misc.isNullOrEmpty(dname.getText()));
			}
		});

		drvtab.setControl(cmp);
	}

	protected void closeConnection() {
		if (refreshing)
			return;
		sprofile.setWsClient(null);
		if (drvtab != null)
			drvtab.dispose();
		showServerInfo();
		try {
			proxy.getUrl();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
