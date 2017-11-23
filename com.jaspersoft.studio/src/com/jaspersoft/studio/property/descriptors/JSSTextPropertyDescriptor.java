/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.property.descriptor.text.EditableTextCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPText;

public class JSSTextPropertyDescriptor extends TextPropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	/**
	 * Field to check if the widget should be read only
	 */
	protected boolean readOnly;

	private int style = SWT.BORDER;

	public JSSTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		readOnly = false;
	}

	public JSSTextPropertyDescriptor(Object id, String displayName, int style) {
		super(id, displayName);
		readOnly = false;
		this.style = style;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		if (!readOnly) {
			CellEditor editor = new EditableTextCellEditor(parent);
			if (getValidator() != null) {
				editor.setValidator(getValidator());
			}
			HelpSystem.bindToHelp(this, editor.getControl());
			return editor;
		}
		return new TextCellEditor(parent, SWT.READ_ONLY);
	}

	public void setReadOnly(boolean value) {
		readOnly = value;
	}

	public int getStyle() {
		return style;
	}

	public ASPropertyWidget<? extends IPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget<IPropertyDescriptor> textWidget = new SPText<IPropertyDescriptor>(parent, section, this);
		textWidget.setReadOnly(readOnly);
		return textWidget;
	}

	private IHelpRefBuilder refBuilder;

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}
}
