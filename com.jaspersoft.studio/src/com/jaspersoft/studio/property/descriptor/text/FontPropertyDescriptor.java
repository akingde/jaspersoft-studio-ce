package com.jaspersoft.studio.property.descriptor.text;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class FontPropertyDescriptor extends PropertyDescriptor {
	public FontPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new LabelProvider() {
			@Override
			public String getText(Object element) {
				return "";
			}
		};
	}
}
