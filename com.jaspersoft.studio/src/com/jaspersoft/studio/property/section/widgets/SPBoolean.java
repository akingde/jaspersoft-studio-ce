/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPBoolean<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {
	
	protected Button cmb3Bool;
	
	public SPBoolean(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return cmb3Bool;
	}

	public void createComponent(Composite parent) {
		String buttonText = pDescriptor.getDisplayName();
		cmb3Bool = section.getWidgetFactory().createButton(parent, buttonText, SWT.CHECK);
		cmb3Bool.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkboxSelected();
			}
		});
		cmb3Bool.setToolTipText(pDescriptor.getDescription());
	}
	
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		if (elementValue == null) {
			cmb3Bool.setForeground(ColorConstants.gray);
			cmb3Bool.setToolTipText(Messages.common_inherited_attribute + pDescriptor.getDescription());
		} else {
			cmb3Bool.setForeground(ColorConstants.black);
			cmb3Bool.setToolTipText(pDescriptor.getDescription());
		}
		setData(pnode, resolvedValue);
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		cmb3Bool.setEnabled(pnode.isEditable());
		cmb3Bool.setSelection(b != null ? (Boolean) b : false);
	}
	
	protected void checkboxSelected(){
		section.changeProperty(pDescriptor.getId(), cmb3Bool.getSelection());
	}
}
