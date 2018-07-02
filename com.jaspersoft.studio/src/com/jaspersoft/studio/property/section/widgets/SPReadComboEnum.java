/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptors.IEnumDescriptors;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPReadComboEnum<T extends IPropertyDescriptor & IEnumDescriptors> extends ASPropertyWidget<T> {
	private CCombo combo;

	public SPReadComboEnum(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return combo;
	}

	protected void createComponent(Composite parent) {
		combo = new CCombo(parent, SWT.FLAT | SWT.READ_ONLY | SWT.BORDER);
		combo.setItems(pDescriptor.getEnumItems());
		combo.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo.getSelectionIndex();
				handleChange(index);
			}
		});
		combo.setToolTipText(pDescriptor.getDescription());
	}
	
	protected void handleChange(int selectionIndex) {
		section.changeProperty(pDescriptor.getId(), selectionIndex);
	}
	
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		if (elementValue == null) {
			combo.setForeground(ColorConstants.gray);
			combo.setToolTipText(Messages.common_inherited_attribute + pDescriptor.getDescription());
		} else {
			combo.setForeground(ColorConstants.black);
			combo.setToolTipText(pDescriptor.getDescription());
		}
		setData(pnode, resolvedValue);
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		int index = 0;
		if (b != null)
			index = ((Number) b).intValue();
		combo.select(index);
		combo.setEnabled(pnode.isEditable());
	}

}
