/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.jrQuery;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPQueryButton;

/**
 * Descriptor for a button to edit a query of a data adapter
 * 
 * @author Orlandin Marco
 * 
 */
public class JRQueryButtonPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	private NullEnum canBeNull;

	/**
	 * Text on the button
	 */
	private String buttonText;

	public JRQueryButtonPropertyDescriptor(Object id, String displayName, NullEnum canBeNull, String buttonText) {
		super(id, displayName);
		this.canBeNull = canBeNull;
		this.buttonText = buttonText;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		JRQueryCellEditor editor = new JRQueryCellEditor(parent);
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new JRQueryLabelProvider(canBeNull);
	}

	public ASPropertyWidget<JRQueryButtonPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new SPQueryButton<JRQueryButtonPropertyDescriptor>(parent, section, this, buttonText);
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
