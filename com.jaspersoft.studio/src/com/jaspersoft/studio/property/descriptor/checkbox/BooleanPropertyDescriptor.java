package com.jaspersoft.studio.property.descriptor.checkbox;


import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class BooleanPropertyDescriptor extends  PropertyDescriptor {
	protected static final BooleanLabelProvider sLabelProvider = new BooleanLabelProvider(); // Need only one, they are
																																														// not descriptor specific.

	public BooleanPropertyDescriptor(Object propertyID, String propertyDisplayname) {
		super(propertyID, propertyDisplayname);

		setLabelProvider(sLabelProvider); // The default provider, this can be overridden by just setting in a different
																			// value after creating descriptor.
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new BooleanCellEditor(parent);
		ICellEditorValidator v = getValidator();
		if (v != null)
			editor.setValidator(v);
		return editor;
	}
}
