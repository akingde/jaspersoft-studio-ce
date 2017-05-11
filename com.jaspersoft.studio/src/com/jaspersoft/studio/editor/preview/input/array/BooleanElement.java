/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import net.sf.jasperreports.eclipse.util.Misc;

public class BooleanElement extends AWElement {

	private Button bbuton;

	@Override
	public Class<?> getSupportedType() {
		return Boolean.class;
	}

	@Override
	public Control createControl(Composite parent) {
		bbuton = new Button(parent, SWT.CHECK);
		bbuton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setValue(bbuton.getSelection());
				updateLabel();
			}
		});
		if (getValue() != null && getValue() instanceof Boolean)
			bbuton.setSelection((Boolean) Misc.nvl(getValue(), Boolean.FALSE));
		updateLabel();
		return bbuton;
	}

	private void updateLabel() {
		bbuton.setText(new Boolean(bbuton.getSelection()).toString());
	}

}
