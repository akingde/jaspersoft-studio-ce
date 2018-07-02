/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SP3Boolean<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {
	private Combo cmb3Bool;

	public SP3Boolean(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return cmb3Bool;
	}

	public void createComponent(Composite parent) {
		cmb3Bool = section.getWidgetFactory().createCombo(parent, SWT.READ_ONLY);
		cmb3Bool.setItems(new String[] { Messages.SP3Boolean_Undefined_Value, Messages.SP3Boolean_True_Value, Messages.SP3Boolean_False_Value });
		cmb3Bool.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Boolean bval = null;
				switch (cmb3Bool.getSelectionIndex()) {
				case 1:
					bval = Boolean.TRUE;
					break;
				case 2:
					bval = Boolean.FALSE;
					break;
				}

				section.changeProperty(pDescriptor.getId(), bval);
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
		if (b == null)
			cmb3Bool.select(0);
		else if ((Boolean) b)
			cmb3Bool.select(1);
		else
			cmb3Bool.select(2);
	}
}
