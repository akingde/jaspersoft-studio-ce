/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.checkbox;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.jface.BooleanCellEditorValidator;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SP3Boolean;
import com.jaspersoft.studio.property.section.widgets.SPBoolean;
import com.jaspersoft.studio.property.section.widgets.SPBooleanNoText;

/*
 * @author Chicu Veaceslav
 */
public class CheckBoxPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	private NullEnum canBeNull;

	/**
	 * Flag to show or not the label on the checkbox button
	 */
	private boolean showTextOnButton = true;

	public CheckBoxPropertyDescriptor(Object id, String displayName, NullEnum canBeNull) {
		super(id, displayName);
		setValidator(new BooleanCellEditorValidator(canBeNull));
		this.canBeNull = canBeNull;
	}

	public CheckBoxPropertyDescriptor(Object id, String displayName) {
		this(id, displayName, NullEnum.NOTNULL);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new BCheckBoxCellEditor(parent, canBeNull);
		if (getValidator() != null)
			editor.setValidator(getValidator());
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;

	}

	/**
	 * Set if the text should be shown on the checkbox button, this must be called
	 * before the creation of the button itself
	 * 
	 * @param value
	 *            true if the label on the button should be shown, false otherwise
	 */
	public void setShowTextOnButton(boolean value) {
		this.showTextOnButton = value;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new CheckBoxLabelProvider(canBeNull, false);
	}

	public ASPropertyWidget<CheckBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		if (!showTextOnButton && canBeNull == NullEnum.NOTNULL) {
			return new SPBooleanNoText<CheckBoxPropertyDescriptor>(parent, section, this);
		} else if (canBeNull == NullEnum.NOTNULL) {
			return new SPBoolean<CheckBoxPropertyDescriptor>(parent, section, this);
		} else {
			return new SP3Boolean<CheckBoxPropertyDescriptor>(parent, section, this);
		}
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
}
