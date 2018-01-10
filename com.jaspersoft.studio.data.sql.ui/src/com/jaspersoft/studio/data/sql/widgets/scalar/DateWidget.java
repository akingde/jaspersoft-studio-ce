/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets.scalar;

import java.util.Date;

import org.eclipse.nebula.widgets.cdatetime.CDT;
import org.eclipse.nebula.widgets.cdatetime.CDateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class DateWidget extends AScalarWidget {
	private CDateTime date;

	public DateWidget(Composite parent, ScalarOperand<?> operand, AQueryDesigner designer) {
		super(parent, SWT.NONE, operand, designer);
	}

	@Override
	protected void createWidget(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 2;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		date = new CDateTime(this, getDateStyle());
		date.setToolTipText(getValue().toSQLString());
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.widthHint = 150;
		date.setLayoutData(gd);
		date.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				Date sdate = date.getSelection();
				if (sdate != null)
					sdate = convertDate(sdate);
				((ScalarOperand<Date>) getValue()).setValue(sdate);
			}
		});

		date.setSelection((Date) getValue().getValue());
	}

	protected Date convertDate(Date d) {
		if (getValue().getValue() instanceof java.sql.Date)
			return new java.sql.Date(d.getTime());
		return d;
	}

	protected int getDateStyle() {
		return CDT.BORDER | CDT.DATE_SHORT | CDT.DROP_DOWN;
	}
}
