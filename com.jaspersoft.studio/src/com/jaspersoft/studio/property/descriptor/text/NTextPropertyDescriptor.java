package com.jaspersoft.studio.property.descriptor.text;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class NTextPropertyDescriptor extends TextPropertyDescriptor {

	public NTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new TextCellEditor(parent) {
			@Override
			protected Object doGetValue() {
				String doGetValue = (String) super.doGetValue();
				if (doGetValue.equals(""))
					doGetValue = null;
				else
					doGetValue.trim();
				return doGetValue;
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					value = "";
				else
					value = ((String) value).trim();
				super.doSetValue(value);
			}
		};
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}
}
