/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPClassType;

public class NClassTypePropertyDescriptor extends ClassTypePropertyDescriptor implements IPropertyDescriptorWidget {

	/**
	 * Field to check if the widget should be read only
	 */
	protected boolean readOnly;

	public NClassTypePropertyDescriptor(Object id, String displayName) {
		super(id, displayName, new String[0]);
		readOnly = false;
	}

	public NClassTypePropertyDescriptor(Object id, String displayName, String[] items) {
		super(id, displayName, items);
		readOnly = false;
	}

	public void setReadOnly(boolean value) {
		readOnly = value;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new ClassTypeCellEditor(parent);
		editor.setValidator(ClassTypeCellEditorValidator.instance());
		setValidator(ClassTypeCellEditorValidator.instance());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		SPClassType<RWComboBoxPropertyDescriptor> classNameWidget = new SPClassType<RWComboBoxPropertyDescriptor>(parent,
				section, this);
		classNameWidget.setClassesOfType(classes);
		classNameWidget.setReadOnly(readOnly);
		return classNameWidget;
	}
}
