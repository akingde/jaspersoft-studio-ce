/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class AWTextButton extends WText {

	protected Button btn;

	public AWTextButton(AWidget aw) {
		super(aw);
	}

	@Override
	public void setEnabled(boolean en) {
		super.setEnabled(en);
		btn.setEnabled(en);
	}

	@Override
	protected void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(cmp);

		createButton(cmp);
	}

	protected void createButton(Composite cmp) {
		btn = new Button(cmp, SWT.PUSH);
		btn.setText("...");
	}

}
