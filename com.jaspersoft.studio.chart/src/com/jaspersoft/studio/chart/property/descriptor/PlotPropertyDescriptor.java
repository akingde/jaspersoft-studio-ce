package com.jaspersoft.studio.chart.property.descriptor;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class PlotPropertyDescriptor extends PropertyDescriptor {
	public PlotPropertyDescriptor(Object id, String displayName) {
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
