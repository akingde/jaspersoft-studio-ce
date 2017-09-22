/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.swt.widgets.Composite;

/*
 * A cell editor that presents a list of items in a combo box. The cell editor's value is the zero-based index of the
 * selected item. <p> This class may be instantiated; it is not intended to be subclassed. </p>
 * 
 * @noextend This class is not intended to be subclassed by clients.
 */
public class RWComboBoxCellEditor extends EditableComboBoxCellEditor {

	/**
	 * Creates a new cell editor with no control and no st of choices. Initially, the cell editor has no cell validator.
	 * 
	 * @since 2.1
	 * @see CellEditor#setStyle
	 * @see CellEditor#create
	 * @see ComboBoxCellEditor#setItems
	 * @see CellEditor#dispose
	 */
	public RWComboBoxCellEditor() {
		super();
	}

	/**
	 * Creates a new cell editor with a combo containing the given list of choices and parented under the given control.
	 * The cell editor value is the zero-based index of the selected item. Initially, the cell editor has no cell
	 * validator and the first item in the list is selected.
	 * 
	 * @param parent
	 *          the parent control
	 * @param items
	 *          the list of strings for the combo box
	 */
	public RWComboBoxCellEditor(Composite parent, String[] items) {
		super(parent, items);
	}

	/**
	 * Creates a new cell editor with a combo containing the given list of choices and parented under the given control.
	 * The cell editor value is the zero-based index of the selected item. Initially, the cell editor has no cell
	 * validator and the first item in the list is selected.
	 * 
	 * @param parent
	 *          the parent control
	 * @param items
	 *          the list of strings for the combo box
	 * @param style
	 *          the style bits
	 * @since 2.1
	 */
	public RWComboBoxCellEditor(Composite parent, String[] items, int style) {
		super(parent, items, style);
	}

	/**
	 * The <code>ComboBoxCellEditor</code> implementation of this <code>CellEditor</code> framework method returns the
	 * zero-based index of the current selection.
	 * 
	 * @return the zero-based index of the current selection wrapped as an <code>Integer</code>
	 */
	@Override
	protected Object doGetValue() {
		if (getItems().length > 0) {
			int selectionIndex = comboBox.getSelectionIndex();
			if (selectionIndex < 0) {
				getItems()[0] = comboBox.getText().trim();
				selectionIndex = 0;
				comboBox.setItems(getItems());
				comboBox.select(selectionIndex);
			}
			return getItems()[selectionIndex];
		}
		return null;
	}

	/**
	 * The <code>ComboBoxCellEditor</code> implementation of this <code>CellEditor</code> framework method accepts a
	 * zero-based index of a selection.
	 * 
	 * @param value
	 *          the zero-based index of the selection wrapped as an <code>Integer</code>
	 */
	@Override
	protected void doSetValue(Object value) {
		if (comboBox != null) {
			if (value instanceof String) {
				String[] items = getItems();
				for (int i = 0; i < items.length; i++) {
					if (items[i].equals((String) value)) {
						super.doSetValue(new Integer(i));
						return;
					}
				}
				// No element found
				String[] newListItems = new String[items.length + 1];
				System.arraycopy(items, 0, newListItems, 0, items.length);
	
				newListItems[items.length] = (String) value;
				setItems(newListItems);
				super.doSetValue(items.length);
			} else if (value == null) {
				String[] items = getItems();
				// No element found
				String[] newListItems = new String[items.length + 1];
				System.arraycopy(items, 0, newListItems, 0, items.length);
	
				newListItems[items.length] = "";
				setItems(newListItems);
				super.doSetValue(items.length);
				setItems(items);
			}
		}

	}

	@Override
	public void setItems(String[] items) {
		String old = comboBox.getText();
		super.setItems(items);
		for (String s : items)
			if (s.equals(old)) {
				comboBox.setText(old);
				break;
			}
	}
}
