/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.imageprovider;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.resource.NResourceCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

public class ImageProviderPropertyDescriptor extends NTextPropertyDescriptor {

	public ImageProviderPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new ImageProviderCellEditor(parent);
		editor.setValidator(NResourceCellEditorValidator.instance());
		setValidator(NResourceCellEditorValidator.instance());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section) {
		return new SPImageProvider(parent, section, this);
	}
}
