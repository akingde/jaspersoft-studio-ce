package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class ClassnamePropertyDescriptor extends NTextPropertyDescriptor {

	public ClassnamePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		return new ClassTypeCellEditor(parent);
	}
}
