/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.eclipse.util.Misc;

public class StringElement extends AWElement {

	protected Text text;

	@Override
	public Class<?> getSupportedType() {
		return String.class;
	}

	protected int getStyle() {
		return SWT.BORDER;
	}

	@Override
	public Control createControl(Composite parent) {
		text = new Text(parent, getStyle());
		text.setText(Misc.nvl(getValue(), ""));
		text.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				setValue(convertString(text.getText()));
			}
		});

		return text;
	}

	protected Object convertString(String str) {
		return Misc.nvl(str);
	}

}
