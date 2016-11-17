/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.IRefreshableCellEditor;

/**
 * 
 * DialogCellEditor that support the refresh method to enable of disable the controls
 * when the selected element is not editable 
 * 
 * @author Orlandin Marco
 *
 */
public abstract class EditableDialogCellEditor extends DialogCellEditor implements IRefreshableCellEditor {

	protected Composite editor;

	protected Button button;

	/**	
	 * Creates a new dialog cell editor with no control
	 * @since 2.1
	 */
	public EditableDialogCellEditor() {
		super();
	}

	/**
	 * Creates a new dialog cell editor parented under the given control.
	 * The cell editor value is <code>null</code> initially, and has no
	 * validator.
	 *
	 * @param parent the parent control
	 */
	protected EditableDialogCellEditor(Composite parent) {
		super(parent);
	}

	/**
	 * Creates a new dialog cell editor parented under the given control.
	 * The cell editor value is <code>null</code> initially, and has no
	 * validator.
	 *
	 * @param parent the parent control
	 * @param style the style bits
	 * @since 2.1
	 */
	protected EditableDialogCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Button createButton(Composite parent) {
		button = super.createButton(parent);
		return button;
	}

	@Override
	protected Control createControl(Composite parent) {
		editor = (Composite)super.createControl(parent);
		return editor;
	}

	@Override
	public void refresh(ANode selectedModel) {
		if(editor != null && !editor.isDisposed()){
			editor.setEnabled(selectedModel.isEditable());
		}

		if(button != null && !button.isDisposed()){
			button.setEnabled(selectedModel.isEditable());
		}
	}

}
