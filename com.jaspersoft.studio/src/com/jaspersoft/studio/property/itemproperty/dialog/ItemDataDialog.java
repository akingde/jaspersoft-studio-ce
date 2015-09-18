/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.dialog;

import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRElementDataset;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ItemDataDialog extends ElementDatasetDialog {
	private StandardItemData itemData;
	private Button bhasds;
	private boolean hasDS;

	public ItemDataDialog(Shell parentShell, String title, String message,
			StandardItemData itemData, JasperReportsConfiguration jConfig) {
		super(parentShell, title, message, itemData.getDataset(), jConfig);
		this.itemData = itemData;
		hasDS = itemData.getDataset() != null;
	}

	@Override
	protected void preElementDataset(Composite parent) {
		bhasds = new Button(parent, SWT.CHECK);
		bhasds.setText(Messages.ItemDataDialog_0);
		bhasds.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hasDS = bhasds.getSelection();
				UIUtils.setEnabled(compositeDatasetInfo, hasDS);
			}
		});
		bhasds.setSelection(itemData.getDataset() != null);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Control ctrl = super.createDialogArea(parent);
		UIUtils.setEnabled(compositeDatasetInfo, hasDS);
		return ctrl;
	}

	@Override
	public JRElementDataset getDataset() {
		return hasDS ? super.getDataset() : null;
	}
}
