/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.jface.FloatCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWFloatCombo;

/**
 * Property descriptor used to input float number
 * 
 * @author Orlandin Marco
 *
 */
public class RWFloatComboBoxPropertyDescriptor extends RWComboBoxPropertyDescriptor implements IPropertyDescriptorWidget,
		IHelp {

	public RWFloatComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull) {
		super(id, displayName, labelsArray, canBeNull, true);
	}

	public RWFloatComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, NullEnum canBeNull,
			boolean caseSensitive) {
		super(id, displayName, labelsArray, canBeNull, caseSensitive);
	}


	public ASPropertyWidget<RWComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPRWFloatCombo<RWComboBoxPropertyDescriptor>(parent, section, this);
	}

	/**
	 * Create a cell editor that accept and return float numbers. The float number validity
	 * is assured by the validator. If something different from a validator is inserted the default
	 * value null is returned
	 * 
	 * @param parent
	 *          the parent
	 * @return the cell editor
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new RWComboBoxCellEditor(parent, labels){
			@Override
			protected Object doGetValue() {
				String value = comboBox.getText().trim();
				if (value == null || value.equals("")) {
					getItems()[0] = "";
					int selectionIndex = 0;
					comboBox.setItems(getItems());
					comboBox.select(selectionIndex);
					return null;
				}  else if (isCorrect(value)){
					if (getItems().length > 0) {
						int selectionIndex = comboBox.getSelectionIndex();
						if (selectionIndex < 0) {
							getItems()[0] = value;
							selectionIndex = 0;
							comboBox.setItems(getItems());
							comboBox.select(selectionIndex);
							value = getItems()[selectionIndex];
						}
					} 
					return new Float(value);
				}
				return null;
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					super.doSetValue(""); //$NON-NLS-1$
				else {
					Assert.isTrue((value instanceof Float));
					super.doSetValue(((Float) value).toString());
				}
			}
		};
		cellEditor.setValidator(FloatCellEditorValidator.instance());
		HelpSystem.bindToHelp(this, cellEditor.getControl());
		return cellEditor;
	}
}
