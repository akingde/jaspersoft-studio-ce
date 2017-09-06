/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.util;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.BCheckBoxCellEditor;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPBoolean;

/**
 * Extension of the standard descriptor to allow to handle to the property section
 * the frame {@link DefaultValue} container.
 * 
 * @author Orlandin Marco
 */
public class FrameCheckBoxPropertyDescriptor extends CheckBoxPropertyDescriptor {

	public FrameCheckBoxPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	
	@Override
	public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPBoolean<CheckBoxPropertyDescriptor>(parent, section, this){
			
			@Override
			public void setData(APropertyNode pnode, Object b) {
				Object convertedValue = b;
				if (b instanceof DefaultValue){
					convertedValue = ((DefaultValue)b).getValue();
				}
				super.setData(pnode, convertedValue);
			}
		};
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new BCheckBoxCellEditor(parent, NullEnum.NOTNULL){
			
			@Override
			protected void doSetValue(Object value) {
				Object convertedValue = value;
				if (value instanceof DefaultValue){
					convertedValue = ((DefaultValue)value).getValue();
				}
				super.doSetValue(convertedValue);
			}
		};
		if (getValidator() != null)
			editor.setValidator(getValidator());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;

	}
}
