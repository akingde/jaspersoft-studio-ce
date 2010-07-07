package com.jaspersoft.studio.property.descriptor.jrQuery;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;

public class JRQueryPropertyDescriptor extends PropertyDescriptor {
	private NullEnum canBeNull;

	public JRQueryPropertyDescriptor(Object id, String displayName, NullEnum canBeNull) {
		super(id, displayName);
		this.canBeNull = canBeNull;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new JRQueryCellEditor(parent);
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new JRQueryLabelProvider(canBeNull);
	}
}
