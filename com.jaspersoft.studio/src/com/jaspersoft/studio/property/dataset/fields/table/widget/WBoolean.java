/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;

import net.sf.jasperreports.eclipse.util.Misc;

public class WBoolean extends AWControl {
	public WBoolean(AWidget aw) {
		super(aw);
	}

	protected boolean refresh = false;
	private Button btn;

	@Override
	protected void createControl(Composite parent) {
		btn = new Button(parent, SWT.CHECK);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		btn.setLayoutData(gd);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (refresh)
					return;
				aw.setValue(btn.getSelection());
				btn.setToolTipText(aw.getToolTipText());
			}
		});
		btn.setText(Misc.nvl(aw.getTColumn().getLabel(), ""));
	}

	@Override
	protected void createLabel(Composite parent, TColumn c) {
	}

	@Override
	protected void fillValue() {
		try {
			refresh = true;
			btn.setSelection(Misc.nvl((Boolean) aw.getValue(), Boolean.FALSE));
		} finally {
			refresh = false;
		}
		btn.setToolTipText(aw.getToolTipText());
	}

	@Override
	public void addDisposeListener(DisposeListener dlistener) {
		btn.addDisposeListener(dlistener);
	}
}
