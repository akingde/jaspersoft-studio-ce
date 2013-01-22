/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.jrQuery;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

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
		return new JRQueryCellEditor(parent);
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new JRQueryLabelProvider(canBeNull);
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPQueryButton(parent, section, this, buttonText);
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
