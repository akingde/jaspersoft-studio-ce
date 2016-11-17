/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.descriptor.seriescolor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.property.widget.SPColorSeries;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SeriesColorPropertyDescriptor extends NTextPropertyDescriptor {

	public SeriesColorPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new SeriesColorCellEditor(parent);
		return editor;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new SeriesColorLabelProvider();
	}

	public SPColorSeries createWidget(Composite parent,
			AbstractSection section) {
		return new SPColorSeries(parent, section, this);
	}
}
