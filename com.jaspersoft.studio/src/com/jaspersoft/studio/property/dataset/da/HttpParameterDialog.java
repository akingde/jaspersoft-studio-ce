/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.da;

import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.data.http.HttpLocationParameter;
import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class HttpParameterDialog extends ATitledDialog {
	private JRDesignDataset dataset;
	private List<HttpLocationParameter> properties;
	private String name;
	private JRDesignParameter prm;
	private String pname;

	public String getName() {
		return name;
	}

	public JRDesignParameter getPrm() {
		return prm;
	}

	protected HttpParameterDialog(Shell parentShell, JRDesignDataset dataset, List<HttpLocationParameter> properties,
			String pname) {
		super(parentShell);
		this.dataset = dataset;
		this.properties = properties;
		this.pname = pname;
		setTitle("Parameter");
		setSaveSettings(false);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText("Name");

		final Text tname = new Text(cmp, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 300;
		tname.setLayoutData(gd);

		new Label(cmp, SWT.NONE).setText("Parameter");

		final Combo cprm = new Combo(cmp, SWT.READ_ONLY);
		for (JRParameter p : dataset.getParameters())
			if (!p.isSystemDefined() && !p.getPropertiesMap().containsProperty(pname)) {
				if (!p.getValueClass().isAssignableFrom(String.class))
					continue;
				cprm.add(p.getName());
			}
		if (cprm.getItemCount() > 0)
			cprm.select(0);
		else
			UIUtils.getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					setError("Compatible parameters is empty. Cancel this dialog and create some parameters.");
					getButton(IDialogConstants.OK_ID).setEnabled(false);
				}
			});

		prm = (JRDesignParameter) dataset.getParametersMap().get(cprm.getText());
		cprm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				prm = (JRDesignParameter) dataset.getParametersMap().get(cprm.getText());
			}
		});

		String n = "prm";
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			if (validateName(n + i) == null) {
				tname.setText(n + i);
				name = n + i;
				break;
			}
		}
		tname.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String error = validateName(tname.getText());
				setError(error);
				getButton(IDialogConstants.OK_ID).setEnabled(error == null);
				if (error == null)
					name = tname.getText();
			}
		});
		return cmp;
	}

	private String validateName(String name) {
		if (Misc.isNullOrEmpty(name))
			return "Name can't be empty";
		for (HttpLocationParameter p : properties)
			if (p.getName().equals(name))
				return "This name is already used";
		return null;
	}
}
