/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;

public class DRDateTime extends CDateTime {
	public static final String NULLTEXT = "<choose date>";

	public DRDateTime(Composite parent, int style) {
		super(parent, style);

		text.getControl().addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				expr = text.getControl().getText();
				if (expr != null && expr.trim().isEmpty())
					expr = null;
				else if (supportDateRange) {
					if (getSelection() != null)
						setSelection(null);
				}
			}
		});
	}

	private boolean supportDateRange = true;

	public void setSupportDateRange(boolean supportDateRange) {
		this.supportDateRange = supportDateRange;
	}

	@Override
	public void setSelection(Date selection) {
		super.setSelection(selection);
		if (supportDateRange)
			removeTextListener();
	}

	@Override
	protected void addTextListener() {
		if (supportDateRange)
			removeTextListener();
		else
			super.addTextListener();
	}

	public void addModifyListener(ModifyListener listener) {
		text.getControl().addModifyListener(listener);
	}

	public void removeModifyListener(ModifyListener listener) {
		text.getControl().removeModifyListener(listener);
	}

	private String expr;

	public void setText(String expr) {
		if (expr != null && expr.trim().isEmpty())
			expr = null;
		this.expr = expr;
		setSelection(null);
		text.getControl().setText(expr);
	}

	@Override
	public String getNullText() {
		if (hasSelection() || expr == null)
			return NULLTEXT;
		return expr;
	}
}
