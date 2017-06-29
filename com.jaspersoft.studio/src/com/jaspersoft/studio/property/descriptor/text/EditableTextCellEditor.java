/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.text;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.IRefreshableCellEditor;

/**
 * 
 * TextCellEditor that support the refresh method to enable of disable the controls
 * when the selected element is not editable 
 * 
 * @author Orlandin Marco
 *
 */
public class EditableTextCellEditor extends TextCellEditor implements IRefreshableCellEditor {

	/**
	 * Creates a new text string cell editor with no control
	 * The cell editor value is the string itself, which is initially the empty
	 * string. Initially, the cell editor has no cell validator.
	 *
	 * @since 2.1
	 */
	public EditableTextCellEditor() {
		super();
	}

	/**
	 * Creates a new text string cell editor parented under the given control.
	 * The cell editor value is the string itself, which is initially the empty string.
	 * Initially, the cell editor has no cell validator.
	 *
	 * @param parent the parent control
	 */
	public EditableTextCellEditor(Composite parent) {
		super(parent);
	}

	/**
	 * Creates a new text string cell editor parented under the given control.
	 * The cell editor value is the string itself, which is initially the empty string.
	 * Initially, the cell editor has no cell validator.
	 *
	 * @param parent the parent control
	 * @param style the style bits
	 * @since 2.1
	 */
	public EditableTextCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	public void refresh(ANode selectedModel) {
		if(text != null && !text.isDisposed()){
			text.setEditable(selectedModel.isEditable());
		}
	}

}
