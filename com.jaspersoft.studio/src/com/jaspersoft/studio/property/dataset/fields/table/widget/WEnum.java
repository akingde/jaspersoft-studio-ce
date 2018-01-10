/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.type.JREnum;
import net.sf.jasperreports.engine.type.NamedValueEnum;

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
		cmb.add(Messages.WEnum_0);
		Object[] obj = clazz.getEnumConstants();
		for (Object item : obj) {
			String eval = ""; //$NON-NLS-1$
			if (item instanceof JREnum)
				eval = ((JREnum) item).getName();
			else if (item instanceof NamedValueEnum)
				eval = ((NamedValueEnum<?>) item).getName();
			else
				eval = ((Enum<?>) item).name();

			Deprecated dep = obj.getClass().getAnnotation(Deprecated.class);
			if (dep != null)
				eval += Messages.WEnum_2;

			cmb.add(eval);
		}

		cmb.addModifyListener(e -> {
			if (refresh)
				return;
			String v = cmb.getText();
			if (Misc.isNullOrEmpty(v))
				aw.setValue(null);
			else {
				if (v.endsWith(Messages.WEnum_2))
					v = v.substring(0, v.indexOf(Messages.WEnum_2));

				aw.setValue(Enum.valueOf((Class) clazz, v));
			}
			cmb.setToolTipText(aw.getToolTipText());
		});
	}

	protected boolean refresh = false;

	protected void fillValue() {
		String v = getText();
		try {
			refresh = true;
			cmb.setText(Misc.nvl(v, "")); //$NON-NLS-1$
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