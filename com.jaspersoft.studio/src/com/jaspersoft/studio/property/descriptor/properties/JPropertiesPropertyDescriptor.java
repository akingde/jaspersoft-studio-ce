/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JPropertiesPropertyDescriptor extends NTextPropertyDescriptor {
	private JasperReportsConfiguration jConfig;
	private Object jrElement;

	public JPropertiesPropertyDescriptor(Object id, String displayName, JasperReportsConfiguration jConfig,
			Object jrElement) {
		super(id, displayName);
		this.jConfig = jConfig;
		this.jrElement = jrElement;
	}

	public void setJrElement(Object jrElement) {
		this.jrElement = jrElement;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new JPropertiesCellEditor(parent, jConfig, jrElement);
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet())
			return super.getLabelProvider();
		return new JPropertiesLabelProvider();
	}
}
