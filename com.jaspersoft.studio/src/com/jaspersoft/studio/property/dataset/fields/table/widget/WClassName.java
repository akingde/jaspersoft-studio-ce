/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeComboCellEditor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

public class WClassName extends AWControl {
	private Combo cmb;

	public WClassName(AWidget aw) {
		super(aw);
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
		cmb.setItems(ClassTypeComboCellEditor.DEFAULT_ITEMS);
		cmb.addModifyListener(e -> {
			if (refresh)
				return;
			aw.setValue(cmb.getText());
			cmb.setToolTipText(aw.getToolTipText());
		});

		Button button = new Button(cmp, SWT.PUSH);
		button.setText("...");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aw.setValue(ClassTypeCellEditor.getJavaClassDialog(UIUtils.getShell(), null));
				fillValue();
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

	@Override
	public void setEnabled(boolean en) {
		cmb.setEnabled(en);
	}
}