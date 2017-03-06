/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields;

import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.dataset.fields.table.TColumnFactory;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;

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

		for (TColumn c : tcolumns)
			TColumnFactory.addWidget(c, cmp, element, jConfig);

		return cmp;
	}
}
