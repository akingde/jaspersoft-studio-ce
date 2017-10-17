/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.util;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.jface.DoubleCellEditorValidator;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.descriptor.text.EditableTextCellEditor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPNumber;

/**
 * Extension of the standard descriptor to allow to handle to the property section
 * the frame {@link DefaultValue} container.
 * 
 * @author Orlandin Marco
 */
public class FrameDoublePropertyDescriptor extends DoublePropertyDescriptor {

	private static DoubleCellEditorValidator validator = new DoubleCellEditorValidator(){
		
		@Override
		public String isValid(Object value) {
			if (value instanceof DefaultValue){
				return super.isValid(((DefaultValue)value).getValue());
			} else return super.isValid(value);
		};
	};
	
	public FrameDoublePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section) {
		SPNumber spNumber = new SPNumber(parent, section, this){
			public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
				Object convertedResolvedValue = resolvedValue;
				Object convertedElementValue = elementValue;
				if (resolvedValue instanceof DefaultValue){
					convertedResolvedValue = ((DefaultValue)resolvedValue).getValue();
				}
				if (elementValue instanceof DefaultValue){
					convertedElementValue = ((DefaultValue)elementValue).getValue();
				}
				super.setData(pnode, convertedResolvedValue, convertedElementValue);
			};
		};
		spNumber.setNullable(isNullable);
		spNumber.setDigits(2, digitsNumber, Double.class);
		spNumber.setBounds(minValue, maxValue);
		return spNumber;
	}

	/**
	 * The <code>TextPropertyDescriptor</code> implementation of this <code>IPropertyDescriptor</code> method creates and
	 * returns a new <code>TextCellEditor</code>.
	 * <p>
	 * The editor is configured with the current validator if there is one.
	 * </p>
	 * 
	 * @param parent
	 *          the parent
	 * @return the cell editor
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new EditableTextCellEditor(parent) {

			@Override
			protected void doSetValue(Object value) {
				Object convertedValue = value;
				if (value instanceof DefaultValue){
					convertedValue = ((DefaultValue)value).getValue();
				}
				super.doSetValue(convertedValue);
			}
		};
		editor.setValidator(validator);
		setValidator(validator);
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}
}
