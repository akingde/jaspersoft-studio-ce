/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.NullableSpinner;

/**
 * 
 * An ASProperty widget that provide a spinner control
 * 
 * @author Orlandin Marco
 *
 */
public class SPSpinner<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {

	/**
	 * The spinner control
	 */
	private NullableSpinner controlSpinner;
	
	public SPSpinner(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}
	
	@Override
	protected void createComponent(Composite parent) {
		controlSpinner = new NullableSpinner(parent, SWT.BORDER);
		controlSpinner.setNullable(false);
		controlSpinner.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				section.changeProperty(pDescriptor.getId(), controlSpinner.getValueAsInteger());
			}
		});
	}

	/**
	 * Set the data inside the spinner control, the value must be an integer 
	 * or a string that can be converted to integer
	 */
	@Override
	public void setData(APropertyNode pnode, Object value) {
		createContextualMenu(pnode);
		if (value != null){
			int intValue = Integer.parseInt(value.toString());
			controlSpinner.setValue(intValue);
		} 

	}

	/**
	 * Return the spinner control
	 */
	@Override
	public Control getControl() {
		return controlSpinner;
	}

}
