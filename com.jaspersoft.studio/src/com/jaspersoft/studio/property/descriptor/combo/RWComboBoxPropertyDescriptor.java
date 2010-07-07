package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

public class RWComboBoxPropertyDescriptor extends ComboBoxPropertyDescriptor {
	private String[] labels;

	public RWComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray) {
		super(id, displayName, labelsArray);
		labels = labelsArray;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new RWComboBoxCellEditor(parent, labels);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		return editor;
	}

	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new RWComboBoxLabelProvider(labels);
	}
}
