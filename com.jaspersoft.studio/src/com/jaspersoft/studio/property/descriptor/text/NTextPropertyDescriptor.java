/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.text;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;

/**
 * Similar to the JSSTextPropertyDescriptor but the cell editor in the advanced
 * view support the null value for the text
 */
public class NTextPropertyDescriptor extends JSSTextPropertyDescriptor {

	public NTextPropertyDescriptor(Object id, String displayName, int style) {
		super(id, displayName, style);
	}

	public NTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new EditableTextCellEditor(parent) {
			@Override
			protected Object doGetValue() {
				String doGetValue = (String) super.doGetValue();
				if (doGetValue.equals("")) //$NON-NLS-1$
					doGetValue = null;
				else
					doGetValue = doGetValue.trim();
				return doGetValue;
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					value = ""; //$NON-NLS-1$
				else
					value = ((String) value).trim();
				super.doSetValue(value);
			}
		};
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}
}
