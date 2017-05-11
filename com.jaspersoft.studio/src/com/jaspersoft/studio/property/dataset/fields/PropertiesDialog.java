/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.Misc;

public class PropertiesDialog<T> extends ATitledDialog {
	private T element;
	private List<TColumn> tcolumns;
	private JasperReportsConfiguration jConfig;

	public PropertiesDialog(Shell shell, T field, List<TColumn> tcolumns, String title,
			JasperReportsConfiguration jConfig) {
		super(shell, false);
		setTitle(Misc.nvl(title));
		this.element = field;
		this.tcolumns = tcolumns;
		this.jConfig = jConfig;
	}

	public T getElement() {
		return element;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		String type = null;
		for (TColumn c : tcolumns) {
			if (type != null && !type.equals(c.getType())) {
				Label lbl = new Label(cmp, SWT.SEPARATOR | SWT.HORIZONTAL);
				GridData gd = new GridData(GridData.FILL_HORIZONTAL);
				gd.horizontalSpan = 2;
				lbl.setLayoutData(gd);
			}
			type = c.getType();
			TColumnFactory.addWidget(c, cmp, element, jConfig);
		}

		return cmp;
	}
}
