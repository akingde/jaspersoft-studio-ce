/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.2.1/jasperreports-6.2.1-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.2.0/jasperreports-6.2.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.1.1/jasperreports-6.1.1-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.1.0/jasperreports-6.1.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.0.4/jasperreports-6.0.4-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.0.3/jasperreports-6.0.3-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.0.2/jasperreports-6.0.2-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%206.0.0/jasperreports-6.0.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.6.1/jasperreports-5.6.1-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.6.0/jasperreports-5.6.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.5.2/jasperreports-5.5.2-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.5.1/jasperreports-5.5.1-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.5.0/jasperreports-5.5.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.2.0/jasperreports-5.2.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.1.2/jasperreports-5.1.2-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.0.4/jasperreports-5.0.4-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%205.0.1/jasperreports-5.0.1-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.8.0/jasperreports-4.8.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.7.0/jasperreports-4.7.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.6.0/jasperreports-4.6.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.5.0/jasperreports-4.5.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.1.3/jasperreports-4.1.3-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.1.1/jasperreports-4.1.1-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.0.2/jasperreports-4.0.2-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%204.0.0/jasperreports-4.0.0-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%203.7.5/jasperreports-3.7.5-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%203.7.4/jasperreports-3.7.4-project.zip/download", //$NON-NLS-1$
						"http://sourceforge.net/projects/jasperreports/files/jasperreports/JasperReports%203.7.2/jasperreports-3.7.2-project.zip/download" }); //$NON-NLS-1$
				curl.setText(d.getResourceURL());
			}

		});
	}

}
