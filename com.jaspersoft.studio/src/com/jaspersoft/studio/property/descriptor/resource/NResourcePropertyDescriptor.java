/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.resource;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPResourceType;

public class NResourcePropertyDescriptor extends NTextPropertyDescriptor {

	public NResourcePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new ResourceCellEditor(parent);
		editor.setValidator(NResourceCellEditorValidator.instance());
		setValidator(NResourceCellEditorValidator.instance());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public SPResourceType<?> createWidget(Composite parent, AbstractSection section) {
		SPResourceType<?> textWidget = new SPResourceType<NResourcePropertyDescriptor>(parent, section, this);
		textWidget.setReadOnly(readOnly);
		return textWidget;
	}
}
