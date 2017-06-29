/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.IRefreshableCellEditor;

/**
 * 
 * ComboBoxCellEditor that support the refresh method to enable of disable the controls
 * when the selected element is not editable 
 * 
 * @author Orlandin Marco
 *
 */
public class EditableComboBoxCellEditor extends ComboBoxCellEditor  implements IRefreshableCellEditor {

	/**
	 * The custom combo box control.
	 */
	protected CCombo comboBox;

	/**
	 * Creates a new cell editor with a combo containing the given list of
	 * choices and parented under the given control. The cell editor value is
	 * the zero-based index of the selected item. Initially, the cell editor has
	 * no cell validator and the first item in the list is selected.
	 *
	 * @param parent
	 *            the parent control
	 * @param items
	 *            the list of strings for the combo box
	 */
	public EditableComboBoxCellEditor(Composite parent, String[] items) {
		super(parent, items);
	}

	public EditableComboBoxCellEditor(Composite parent, String[] items, int style) {
		super(parent, items, style);
	}

	/**
	 * Creates a new cell editor with no control and no st of choices.
	 * Initially, the cell editor has no cell validator.
	 *
	 * @since 2.1
	 * @see CellEditor#setStyle
	 * @see CellEditor#create
	 * @see ComboBoxCellEditor#setItems
	 * @see CellEditor#dispose
	 */
	public EditableComboBoxCellEditor() {
		super();
	}

	@Override
	public void refresh(ANode selectedModel) {
		if (!getComboBox().isDisposed()){
			getComboBox().setEnabled(selectedModel.isEditable());
		}
	}

	/*
	 * (non-Javadoc) Method declared on CellEditor.
	 */
	@Override
	protected Control createControl(Composite parent) {
		comboBox = (CCombo) super.createControl(parent);
		return comboBox;
	}

	public CCombo getComboBox() {
		return comboBox;
	}
}
