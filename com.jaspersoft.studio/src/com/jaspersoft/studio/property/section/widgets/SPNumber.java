/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.draw2d.ColorConstants;
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

import com.jaspersoft.studio.messages.Messages;
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
	 * The number of digits always shown in the value
	 */
	private int minDigitsShown = 0;
	
	/**
	 * The number of digits accepted in the value
	 */
	private int maxDigitsAccepted = 0;
	
	private Class<? extends Number> outputType = Integer.class;

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
		ftext = new NullableSpinner(parent, SWT.BORDER | SWT.RIGHT, 0, 0);
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
				changeValue();
			}
		});
		
		ftext.setToolTipText(pDescriptor.getDescription());
		setWidth(parent, 6);
	}
	
	protected void changeValue(){
		Number newValue = getValue();
		boolean valueChanged = section.changeProperty(pDescriptor.getId(), newValue);
		if (valueChanged){
			setData(section.getElement(), section.getElement().getPropertyActualValue(pDescriptor.getId()), newValue);
		}
	}
	
	protected Number getValue(){
		if (outputType == Integer.class){
			return ftext.getValueAsInteger();
		} else if (outputType == Long.class){
			return ftext.getValueAsLong();
		} else if (outputType == Float.class){
			return ftext.getValueAsFloat();
		} else if (outputType == Double.class){
			return ftext.getValueAsDouble();
		} else {
			throw new RuntimeException("Format " + outputType.getName() + " not supported by the SPNumber conversion");
		}
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

	@Override
	public void setData(APropertyNode pnode, Object b) {
		setData(pnode, b, b); 
	}

	/**
	 * Re-implement the setData with three parameters to show the inherited
	 * values
	 */
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		createContextualMenu(pnode);
		ftext.setEnabled(pnode.isEditable());
		Number resolvedNumber = (Number) resolvedValue;
		Number ownNumber = (Number)elementValue;
		setDataNumber(resolvedNumber, ownNumber);
		if (elementValue == null) {
			ftext.setForeground(ColorConstants.gray);
			ftext.setToolTipText(Messages.common_inherited_attribute + pDescriptor.getDescription());
		} else {
			ftext.setForeground(ColorConstants.black);
			ftext.setToolTipText(pDescriptor.getDescription());
		}
	}
	
	public void setDataNumber(Number resolvedNumber, Number ownNumber) {
		if (resolvedNumber != null) {
			int oldpos = ftext.getCaretPosition();
			if (ownNumber == null) {
				ftext.setDefaultValue(resolvedNumber);
			}
			ftext.setValue(ownNumber);
			if (ftext.getText().length() >= oldpos){
				ftext.setSelection(oldpos, oldpos);
			}
		} else if (ownNumber != null){
			int oldpos = ftext.getCaretPosition();
			ftext.setValue(ownNumber);
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
	 * @param minDigitsShown the minimum number of decimal digits displayed when formatting the value, must be not negative
	 * @param maxDigitsAccepted maximum number of decimal digits accepted. Set this to 0 mean no decimal digits, must be greater or equal of 
	 * minDigitsShown
	 */
	public void setDigits(int minDigitsShown, int maxDigitsAccepted, Class<? extends Number> outputType){
		this.outputType = outputType;
		if (this.minDigitsShown != minDigitsShown || this.maxDigitsAccepted != maxDigitsAccepted){
			this.minDigitsShown = minDigitsShown;
			this.maxDigitsAccepted = maxDigitsAccepted;
			if (ftext != null){
				//regenerate the validator
				ftext.setFormat(new ValidatedDecimalFormat(minDigitsShown, maxDigitsAccepted));
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
