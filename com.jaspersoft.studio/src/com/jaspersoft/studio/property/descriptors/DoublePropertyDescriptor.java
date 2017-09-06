/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.jface.DoubleCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.text.EditableTextCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPNumber;

/*
 * The Class FloatPropertyDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class DoublePropertyDescriptor extends TextPropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	/**
	 * Flag used to set the widget to accept the null value
	 */
	protected boolean isNullable = true;

	/**
	 * The minimum value accepted
	 */
	protected double minValue = 0;

	/**
	 * The maximum value accepted
	 */
	protected double maxValue = Double.MAX_VALUE;
	
	/**
	 * Number of decimal digits shwon and accepted
	 */
	protected int digitsNumber = 6;
	
	/**
	 * Instantiates a new float property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public DoublePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
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
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new EditableTextCellEditor(parent) {
			@Override
			protected Object doGetValue() {
				String value = (String) super.doGetValue();
				if (value == null || value.equals("")) //$NON-NLS-1$
					return (Double) null;
				return new Double(value);
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					super.doSetValue(""); //$NON-NLS-1$
				else {
					Assert.isTrue(text != null && (value instanceof Double));
					super.doSetValue(((Double) value).toString());
				}
			}
		};
		editor.setValidator(DoubleCellEditorValidator.instance());
		setValidator(DoubleCellEditorValidator.instance());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section) {
		SPNumber spNumber = new SPNumber(parent, section, this);
		spNumber.setNullable(isNullable);
		spNumber.setDigits(2, digitsNumber, Double.class);
		spNumber.setBounds(minValue, maxValue);
		return spNumber;
	}

	private IHelpRefBuilder refBuilder;

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}
	
	/**
	 * Set the flag to enable or disable the acceptance of empty null value
	 * 
	 * @param value true if the null value is accepted, false otherwise
	 */
	public void setNullable(boolean value){
		this.isNullable = value;
	}

	/**
	 * Set the minimum and maximum value accepted by the widget
	 * 
	 * @param min the lower bound
	 * @param max the upper bound
	 */
	public void setBounds(double min, double max) {
		this.minValue = min;
		this.maxValue = max;
	}
	
	/**
	 * Set the number of decimal digits accepted
	 * 
	 * @param digits the number of digits, must be greter than 0
	 */
	public void setDigits(int digits){
		Assert.isTrue(digits > 0, "On a double descriptor the accepted digits must be greater than 0");
		this.digitsNumber = digits;
	}
}
