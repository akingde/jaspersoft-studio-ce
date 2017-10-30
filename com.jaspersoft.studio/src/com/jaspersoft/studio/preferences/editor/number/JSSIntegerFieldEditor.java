/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor.number;

import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class JSSIntegerFieldEditor extends IntegerFieldEditor {
	private int minValidValue = 0;
	private int maxValidValue = Integer.MAX_VALUE;

	public JSSIntegerFieldEditor() {
		super();
	}

	public JSSIntegerFieldEditor(String name, String labelText, Composite parent, int textLimit) {
		super(name, labelText, parent, textLimit);
	}

	public JSSIntegerFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, parent);
	}

	@Override
	public void setValidRange(int min, int max) {
		minValidValue = min;
		maxValidValue = max;
		super.setValidRange(min, max);
	}

	@Override
	protected boolean checkState() {

		Text text = getTextControl();

		if (text == null) {
			return false;
		}

		String numberString = text.getText();
		if (numberString.isEmpty() && isEmptyStringAllowed()) {
			return false;
		}
		try {
			int number = Integer.valueOf(numberString).intValue();
			if (number >= minValidValue && number <= maxValidValue) {
				clearErrorMessage();
				return true;
			}

			showErrorMessage();
			return false;

		} catch (NumberFormatException e1) {
			showErrorMessage();
		}

		return false;
	}
}
