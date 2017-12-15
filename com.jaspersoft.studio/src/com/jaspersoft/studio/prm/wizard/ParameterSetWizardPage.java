/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.prm.wizard;

import java.io.IOException;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.prm.ParameterSet;
import com.jaspersoft.studio.prm.ParameterSetProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class ParameterSetWizardPage extends WizardPage {
	private JasperReportsConfiguration jConfig;
	private boolean override = false;
	private List lst;
	private String selected;

	public ParameterSetWizardPage(JasperReportsConfiguration jConfig) {
		super("parametersetpage"); //$NON-NLS-1$
		setTitle(Messages.ParameterSetWizardPage_1);
		setDescription(Messages.ParameterSetWizardPage_2);
		this.jConfig = jConfig;
	}

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout());
		setControl(cmp);

		lst = new List(cmp, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL);
		lst.setLayoutData(new GridData(GridData.FILL_BOTH));
		lst.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selected = lst.getItem(lst.getSelectionIndex());
			}
		});

		final Button btn = new Button(cmp, SWT.CHECK);
		btn.setText(Messages.ParameterSetWizardPage_3);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				override = btn.getSelection();
			}
		});

		String str = jConfig.getProperty(ParameterSet.PARAMETER_SETS);
		if (str != null) {
			try {
				str = net.sf.jasperreports.eclipse.util.Misc.decodeBase64String(str, FileUtils.LATIN1_ENCODING);
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			String[] sets = str.split("\n"); //$NON-NLS-1$

			for (String key : sets) {
				lst.add(key);
				if (lst.getSelectionIndex() != 0) {
					lst.setSelection(0);
					selected = key;
				}
			}
		}
	}

	public boolean isOverride() {
		return override;
	}

	public ParameterSet getValue() {
		String str = selected;
		if (!Misc.isNullOrEmpty(str))
			return ParameterSetProvider.getParameterSet(str, jConfig.getPrefStore());
		return null;
	}

}
