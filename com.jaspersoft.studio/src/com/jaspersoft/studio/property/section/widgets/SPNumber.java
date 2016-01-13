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

import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.NullableSpinner;
import com.jaspersoft.studio.utils.ValidatedDecimalFormat;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

public class SPNumber extends AHistorySPropertyWidget<IPropertyDescriptor> {
	
	/**
	 * The text widget
	 */
	protected NullableSpinner ftext;
	
	/**
	 * The minimum value accepted
	 */
	private Number min = null;
	
	/**
	 * The maximum value accepted
	 */
	private Number max = null;
	
	/**
	 * The number of digits in the value
	 */
	private int digits = 0;
	
	boolean isRefresh = false;

	public SPNumber(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return ftext;
	}

	@Override
	protected Text getTextControl() {
		return ftext.getTextControl();
	}

	@Override
	protected void createComponent(Composite parent) {
		//Create the widget and set the bounds
		ftext = new NullableSpinner(parent, SWT.BORDER | SWT.RIGHT, 0);
		if (min != null){
			ftext.setMinimum(min.intValue());
		}
		if (max != null){
			ftext.setMaximum(max.intValue());
		}
		ftext.setNullable(true);
		autocomplete = new CustomAutoCompleteField(ftext, new TextContentAdapter(), InputHistoryCache.get(getHistoryKey()));
		ftext.addSelectionListener(new SelectionAdapter(){
		
			public void widgetSelected(SelectionEvent e) {
				if (!isRefresh) {
					Number newValue = null;
					if (digits == 0){
						newValue = ftext.getValueAsInteger();
					} else {
						newValue = ftext.getValueAsFloat();
					}
					if (!section.changeProperty(pDescriptor.getId(), newValue)) {
						setData(section.getElement(), newValue);
					}
				}
			}
		});
		
		ftext.setToolTipText(pDescriptor.getDescription());
		setWidth(parent, 6);
	}

	protected void setWidth(Composite parent, int chars) {
		int w = (getCharWidth(ftext) * chars) + 50;
		if (parent.getLayout() instanceof RowLayout) {
			RowData rd = new RowData();
			rd.width = w;
			ftext.setLayoutData(rd);
		} else if (parent.getLayout() instanceof GridLayout) {
			GridData rd = new GridData();
			rd.widthHint = w;
			ftext.setLayoutData(rd);
		}
	}

	public void setData(APropertyNode pnode, Object b) {
		setData(pnode, b, false); 
	}

	/**
	 * Re-implement the setData with three parameters to show the inherited
	 * values
	 */
	@Override
	public void setData(APropertyNode pnode, Object b, boolean isInherited) {
		ftext.setEnabled(pnode.isEditable());
		Number n = (Number) b;
		isRefresh = true;
		setDataNumber(n, isInherited);
		isRefresh = false;
	}
	
	public void setDataNumber(Number f, boolean isInherited) {
		if (f != null) {
			int oldpos = ftext.getCaretPosition();
			ftext.setInherited(isInherited);
			ftext.setValue(f);
			if (ftext.getText().length() >= oldpos){
				ftext.setSelection(oldpos, oldpos);
			}
		} else {
			ftext.setValue(null);
		}
	}
	
	/**
	 * Set the flag to enable or disable the acceptance of empty null value
	 * 
	 * @param value true if the null value is accepted, false otherwise
	 */
	public void setNullable(boolean value){
		if (ftext != null){
			ftext.setNullable(value);
		}
	}

	/**
	 * Set the number of decimal digits accepted in the widget
	 * 
	 * @param digits a not negative number of digits
	 */
	public void setDigits(int digits){
		if (this.digits != digits){
			this.digits = digits;
			if (ftext != null){
				//regenerate the validator
				ftext.setFormat(new ValidatedDecimalFormat(digits));
			}
		}
	}
	
	/**
	 * Set the minimum and maximum value accepted by the widget
	 * 
	 * @param min the lower bound
	 * @param max the upper bound
	 */
	public void setBounds(Number min, Number max) {
		this.min = min;
		this.max = max;
		if (ftext != null){
			ftext.setMinimum(min.doubleValue());
			ftext.setMaximum(max.doubleValue());
		}
	}
}
