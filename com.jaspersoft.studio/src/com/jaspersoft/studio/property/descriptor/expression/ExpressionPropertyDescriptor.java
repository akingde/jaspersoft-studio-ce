/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * Property descriptor used to provide a widget to edit a textual property or 
 * an expression property of an element. The type of the widget depend from
 * the property expected value, so string properties will have a text widget,
 * numeric properties a numeric widget and so on. The widget come from the 
 * widget framework
 * 
 * @author Orlandin Marco
 */
public class ExpressionPropertyDescriptor extends JSSTextPropertyDescriptor {
	
	/**
	 * The name of the edited property
	 */
	private String propertyName;

	/**
	 * Create the descriptor
	 * 
	 * @param propertyName the name of the edited property
	 */
	public ExpressionPropertyDescriptor(Object id, String displayName, String propertyName) {
		super(id, displayName);
		this.propertyName = propertyName;
	}

	/**
	 * The properties are not shown in the advanced section, since in the advanced section there
	 * is a dialog to edit all this
	 */
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return null;
	}
	
	
	@Override
	public ASPropertyWidget<? extends IPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		return new ASPropertyExpressionWidget(parent, section, this, propertyName);
	}
	
}
