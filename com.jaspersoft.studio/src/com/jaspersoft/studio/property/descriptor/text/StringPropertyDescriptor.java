package com.jaspersoft.studio.property.descriptor.text;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class StringPropertyDescriptor extends PropertyDescriptor {
	public StringPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		return new InputDialogCellEditor(parent, getDisplayName());
	}
}
