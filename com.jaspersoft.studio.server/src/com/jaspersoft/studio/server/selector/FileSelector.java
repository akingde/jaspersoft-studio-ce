/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.selector;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ResourceMediaType;
import com.jaspersoft.studio.jface.IFileSelection;
import com.jaspersoft.studio.jface.dialogs.ImageSelectionDialog;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.properties.dialog.RepositoryDialog;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.wizard.find.FindResourceJob;
import com.jaspersoft.studio.utils.Callback;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JasperDesign;

public class FileSelector implements IFileSelection {
	private Composite cmpExpr;
	private ImageSelectionDialog dialog;
	private Text txtURL;
	private JasperDesign jd;

	@Override
	public void createRadioButton(Composite parent, ImageSelectionDialog d, JasperDesign jd) {
		this.dialog = d;
		this.jd = jd;
		Button btnExpression = new Button(parent, SWT.RADIO);
		btnExpression.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialog.changeImageSelectionMode(cmpExpr);
			}
		});
		btnExpression.setText("Select a resource from JasperReports Server");
	}

	@Override
	public void changeSelectionMode(Control newTopControl) {
		txtURL.setText("");
	}

	@Override
	public void createFileSelectionContainer(Composite parent) {
		cmpExpr = new Composite(parent, SWT.NONE);
		cmpExpr.setLayout(new GridLayout(2, false));

		Label lbl = new Label(cmpExpr, SWT.NONE);
		lbl.setText("Select a resource from JasperReports Server");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		txtURL = new Text(cmpExpr, SWT.BORDER);
		txtURL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtURL.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dialog.loadImagePreview();
			}
		});

		Button btn = new Button(cmpExpr, SWT.PUSH);
		btn.setText("...");
		btn.addSelectionListener(new SelectionAdapter() {
			private MServerProfile msp;

			@Override
			public void widgetSelected(SelectionEvent e) {
				String servURL = jd.getProperty(AExporter.PROP_SERVERURL);
				String servUser = jd.getProperty(AExporter.PROP_USER);

				msp = ServerManager.getServerByUrl(servURL, servUser);
				if (msp == null) {
					SelectServerWizard wizard = new SelectServerWizard();
					WizardDialog w = new WizardDialog(UIUtils.getShell(), wizard);
					if (w.open() == Dialog.OK) {
						msp = wizard.getValue();
						try {
							jd.setProperty(AExporter.PROP_SERVERURL, msp.getValue().getUrl());
							jd.setProperty(AExporter.PROP_USER,
									msp.getValue().getUser() + (msp.getValue().getOrganisation() != null
											? "|" + msp.getValue().getOrganisation() : ""));
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (msp != null)
					showFindDialog(msp);
			}

			protected void showFindDialog(MServerProfile msp) {
				if (msp.isSupported(Feature.SEARCHREPOSITORY)) {
					String[] incl = null;
					if (dialog instanceof ImageSelectionDialog)
						incl = new String[] { ResourceMediaType.FILE_CLIENT_TYPE };
					ResourceDescriptor rd = FindResourceJob.doFindResource(msp, incl, null);
					if (rd != null) {
						dialog.setImageExpressionText("repo:" + rd.getUriString());
						txtURL.setText(rd.getUriString());
					}
				} else {
					RepositoryDialog rd = new RepositoryDialog(UIUtils.getShell(), msp) {

						@Override
						public boolean isResourceCompatible(AMResource r) {
							if (dialog instanceof ImageSelectionDialog)
								return r.getValue().getWsType().equals(ResourceDescriptor.TYPE_IMAGE);
							return true;
						}
					};
					if (rd.open() == Dialog.OK) {
						AMResource rs = rd.getResource();
						if (rs != null) {
							dialog.setImageExpressionText("repo:" + rs.getValue().getUriString());
							txtURL.setText(rs.getValue().getUriString());
						}
					}
				}
			}
		});

	}

}
