/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import net.sf.jasperreports.eclipse.util.Misc;

public class WBoolean extends AWControl {
	public WBoolean(AWidget aw) {
		super(aw);
	}

	protected boolean refresh = false;
	protected Combo cmb;

	@Override
	protected void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		cmb = new Combo(cmp, SWT.BORDER | SWT.READ_ONLY);
		cmb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cmb.setItems(getValues());
		cmb.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (refresh)
					return;
				handleValueChanged();
			}

		});
	}

	protected void handleValueChanged() {
		if (cmb.getSelectionIndex() == 0)
			aw.setValue(null);
		else if (cmb.getSelectionIndex() == 1)
			aw.setValue(true);
		else if (cmb.getSelectionIndex() == 2)
			aw.setValue(false);
		cmb.setToolTipText(aw.getToolTipText());
	}

	protected String[] getValues() {
		return new String[] { "", "true", "false" };
	}

	@Override
	protected void fillValue() {
		String v = getText();
		try {
			refresh = true;
			cmb.setText(Misc.nvl(v, ""));
		} finally {
			refresh = false;
		}
		cmb.setToolTipText(aw.getToolTipText());
	}

	@Override
	public void addDisposeListener(DisposeListener dlistener) {
		cmb.addDisposeListener(dlistener);
	}
}
