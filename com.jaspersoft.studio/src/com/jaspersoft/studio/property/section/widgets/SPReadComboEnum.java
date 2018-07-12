/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboItemAction;
import com.jaspersoft.studio.property.combomenu.WritableComboTableViewer;
import com.jaspersoft.studio.property.descriptors.IEnumDescriptors;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPReadComboEnum<T extends IPropertyDescriptor & IEnumDescriptors> extends ASPropertyWidget<T> {
	
	/**
	 * The combo popup
	 */
	protected WritableComboTableViewer combo;

	public SPReadComboEnum(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return combo.getControl();
	}
	
	protected List<ComboItem> getItems(String[] enumItems){
		List<ComboItem> result = new ArrayList<>();
		int index = 0;
		for(String enumItem : enumItems) {
			result.add(new ComboItem(enumItem, true, index, enumItem, index));
			index++;
		}
		return result;
	}

	protected void createComponent(Composite parent) {
		combo = new WritableComboTableViewer(parent, SWT.FLAT | SWT.READ_ONLY | WritableComboTableViewer.NO_IMAGE);
		combo.setItems(getItems(pDescriptor.getEnumItems()));
		combo.getControl().getParent().layout(true, true);
		combo.addSelectionListener(new ComboItemAction() {
			
			@Override
			public void exec() {
				int index = (int)combo.getSelectionValue();
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
		combo.getControl().layout(true, true);
	}

}
