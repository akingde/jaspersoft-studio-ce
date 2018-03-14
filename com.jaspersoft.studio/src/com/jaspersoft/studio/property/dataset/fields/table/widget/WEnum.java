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
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.type.JREnum;
import net.sf.jasperreports.engine.type.NamedValueEnum;

public class WEnum extends AWControl {
	private Combo cmb;
	private Class<? extends Enum<?>> clazz;

	public WEnum(AWidget aw, Class<? extends Enum<?>> clazz) {
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

		cmb = new Combo(cmp, aw.getTColumn().isLabelEditable() ? SWT.BORDER | SWT.READ_ONLY : SWT.BORDER);
		cmb.add(Messages.WEnum_0);
		Enum<?>[] obj = clazz.getEnumConstants();
		for (Enum<?> item : obj) {
			String eval = ""; //$NON-NLS-1$
			if (item instanceof JREnum)
				eval = ((JREnum) item).getName();
			else if (item instanceof NamedValueEnum)
				eval = ((NamedValueEnum<?>) item).getName();
			else
				eval = item.name();

			Deprecated dep = obj.getClass().getAnnotation(Deprecated.class);
			if (dep != null)
				eval += Messages.WEnum_2;

			cmb.add(eval);
		}

		cmb.addModifyListener(e -> {
			if (refresh)
				return;
			UIUtils.getDisplay().asyncExec(() -> cmb.setToolTipText(aw.getToolTipText()));
			String v = cmb.getText();
			if (Misc.isNullOrEmpty(v))
				aw.setValue(null);
			else {
				if (v.endsWith(Messages.WEnum_2))
					v = v.substring(0, v.indexOf(Messages.WEnum_2));
				for (Enum<?> item : obj) {
					if (item instanceof NamedValueEnum && ((NamedValueEnum<?>) item).getName().equals(v)) {
						aw.setValue(((NamedValueEnum<?>) item).getValue());
						return;
					} else if ((item instanceof JREnum && ((JREnum) item).getName().equals(v))
							|| item.name().equals(v)) {
						aw.setValue(item.name());
						return;
					}
				}
				aw.setValue(Enum.valueOf((Class) clazz, v));
			}
		});
	}

	protected boolean refresh = false;

	protected void fillValue() {
		Object obj = aw.getValue();
		String v = "";
		if (obj instanceof PropertyExpressionDTO)
			obj = ((PropertyExpressionDTO) obj).getValue();
		if (obj instanceof String) {
			for (Enum<?> item : clazz.getEnumConstants()) {
				String str = item.name();
				if (item instanceof JREnum)
					str = ((JREnum) item).getName();
				else if (item instanceof NamedValueEnum)
					str = ((NamedValueEnum<?>) item).getName();
				if (item.name().equals(obj)) {
					v = str;
					break;
				}
			}
		}
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

	@Override
	public void setEnabled(boolean en) {
		cmb.setEnabled(en);
	}
}