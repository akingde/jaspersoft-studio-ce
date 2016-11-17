/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.checkbox;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.jface.BooleanCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.NullEnum;

/**
 * Represent a checkbox in the property view, but when viewed in tabular mode in advanced view is a 
 * combo box with the value null
 * @author Orlandin Marco
 *
 */
public class NullCheckBoxPropertyDescriptor extends CheckBoxPropertyDescriptor{
	
	public NullCheckBoxPropertyDescriptor(Object id, String displayName, NullEnum canBeNull) {
		super(id,displayName,canBeNull);
		setValidator(new BooleanCellEditorValidator(NullEnum.INHERITED));
	}
	
	public NullCheckBoxPropertyDescriptor(Object id, String displayName) {
		this(id, displayName, NullEnum.NOTNULL);
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
			CellEditor editor = new BCheckBoxCellEditor(parent, NullEnum.INHERITED);
			if (getValidator() != null)
				editor.setValidator(getValidator());
			return editor;
	}
	
}
