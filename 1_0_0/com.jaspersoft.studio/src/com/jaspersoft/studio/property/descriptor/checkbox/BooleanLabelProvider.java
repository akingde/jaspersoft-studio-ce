package com.jaspersoft.studio.property.descriptor.checkbox;

import org.eclipse.jface.viewers.LabelProvider;

public class BooleanLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		if (element instanceof Boolean)
			return ((Boolean) element).booleanValue() ? "true" : "false";

		return super.getText(element);
	}

}