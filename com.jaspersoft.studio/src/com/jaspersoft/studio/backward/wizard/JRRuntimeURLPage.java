/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.backward.wizard;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.builder.JRDefinition;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class JRRuntimeURLPage extends WizardPage {
	private JRDefinition d;

	protected JRRuntimeURLPage(JRDefinition d) {
		super("jrRuntimeURLPage"); //$NON-NLS-1$
		setTitle(Messages.JRRuntimeURLPage_1);
		setDescription(Messages.JRRuntimeURLPage_2);
		this.d = d;
	}

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout());
		setControl(cmp);

		new Label(cmp, SWT.NONE).setText(Messages.JRRuntimeURLPage_3);

		final Combo curl = new Combo(cmp, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 800;
		curl.setLayoutData(gd);
		curl.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				try {
					if (!curl.getText().isEmpty()) {
						new URL(curl.getText());
						d.setResourceURL(curl.getText());
						setErrorMessage(null);
						setPageComplete(true);
						return;
					}
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
				setPageComplete(false);
				setErrorMessage(Messages.JRRuntimeURLPage_4);
			}
		});
		UIUtils.getDisplay().asyncExec(new Runnable() {
			public void run() {
				curl.setItems(new String[] {
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.6.0/jasperreports-3.6.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.6.1/jasperreports-3.6.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.6.2/jasperreports-3.6.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.0/jasperreports-3.7.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.1/jasperreports-3.7.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.2/jasperreports-3.7.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.3/jasperreports-3.7.3.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.4/jasperreports-3.7.4.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.5/jasperreports-3.7.5.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/3.7.6/jasperreports-3.7.6.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.0.0/jasperreports-4.0.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.0.1/jasperreports-4.0.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.0.2/jasperreports-4.0.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.1.1/jasperreports-4.1.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.1.2/jasperreports-4.1.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.1.3/jasperreports-4.1.3.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.5.0/jasperreports-4.5.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.5.1/jasperreports-4.5.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.6.0/jasperreports-4.6.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.7.0/jasperreports-4.7.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.7.1/jasperreports-4.7.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/4.8.0/jasperreports-4.8.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.0.0/jasperreports-5.0.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.0.1/jasperreports-5.0.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.0.4/jasperreports-5.0.4.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.1.0/jasperreports-5.1.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.1.2/jasperreports-5.1.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.2.0/jasperreports-5.2.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.5.0/jasperreports-5.5.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.5.1/jasperreports-5.5.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.5.2/jasperreports-5.5.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.6.0/jasperreports-5.6.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/5.6.1/jasperreports-5.6.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.0.0/jasperreports-6.0.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.0.2/jasperreports-6.0.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.0.3/jasperreports-6.0.3.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.0.4/jasperreports-6.0.4.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.1.0/jasperreports-6.1.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.1.1/jasperreports-6.1.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.2.0/jasperreports-6.2.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.2.1/jasperreports-6.2.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.2.2/jasperreports-6.2.2.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.3.0/jasperreports-6.3.0.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.3.1/jasperreports-6.3.1.jar",
						"http://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.4.0/jasperreports-6.4.0.jar" }); //$NON-NLS-1$
				curl.setText(d.getResourceURL());
			}

		});
	}

}
