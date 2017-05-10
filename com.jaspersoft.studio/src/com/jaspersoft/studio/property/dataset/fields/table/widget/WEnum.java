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

public class WEnum extends AWControl {
	private Combo cmb;
	private Class<?> clazz;

	public WEnum(AWidget aw, Class<?> clazz) {
		super(aw);
		this.clazz = clazz;
	}

	protected void createControl(final Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		cmb = new Combo(cmp, SWT.BORDER);
		cmb.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Object[] obj = clazz.getEnumConstants();
		for (Object item : obj) {
			String eval = item.toString();
			Deprecated dep = obj.getClass().getAnnotation(Deprecated.class);
			if (dep != null)
				eval += " (deprecated)";
			cmb.add(eval);
		}

		cmb.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (refresh)
					return;
				String v = cmb.getText();
				if (v.endsWith(" (deprecated)"))
					v = v.substring(0, v.indexOf(" (deprecated)"));
				aw.setValue(v);
				cmb.setToolTipText(aw.getToolTipText());
			}
		});
	}

	protected boolean refresh = false;

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