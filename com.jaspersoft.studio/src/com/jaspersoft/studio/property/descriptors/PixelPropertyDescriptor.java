/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.text.EditableTextCellEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPPixel;

/**
 * 
 * @author Orlandin Marco
 * 
 */
public class PixelPropertyDescriptor extends TextPropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	private boolean readOnly = false;
	
	/**
	 * Default validator for the cell editor, used when a specific one is not provided
	 */
	protected static final AbstractJSSCellEditorValidator DEFAULT_VALIDATOR = new AbstractJSSCellEditorValidator() {
		
		public String isValid(Object value) {
			try {
				if (value instanceof Integer)
					return null;
				if (value instanceof String)
					new Integer((String) value);
				return null;
			} catch (NumberFormatException exc) {
				return Messages.common_this_is_not_an_integer_number; 
			}
		}
	};
	
	/**
	 * Instantiates a new integer property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public PixelPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}
	
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public boolean isReadOnly() {
		return readOnly;
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
					return (Integer) null;
				return new Integer(value);
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					super.doSetValue(""); //$NON-NLS-1$
				else {
					Assert.isTrue(text != null && (value instanceof Integer));
					super.doSetValue(((Integer) value).toString());
				}
			}
		};
		if (getValidator() == null){
			setValidator(DEFAULT_VALIDATOR);
		}
		editor.setValidator(getValidator());

		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public ASPropertyWidget<PixelPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		SPPixel spNumber = new SPPixel(parent, section, this);
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
	
	@Override
	public AbstractJSSCellEditorValidator getValidator() {
		return (AbstractJSSCellEditorValidator)super.getValidator();
	}
	
	/**
	 * Set the validator for the current element, must be of type AbstractJSSCellEditorValidator
	 * 
	 * @param validator the validator, must be of type AbstractJSSCellEditorValidator
	 */
	@Override
	public void setValidator(ICellEditorValidator validator) {
		Assert.isTrue(validator instanceof AbstractJSSCellEditorValidator);
		super.setValidator(validator);
	}
}
