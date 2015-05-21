/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;

public class ItemPropertyDescriptor extends NTextPropertyDescriptor implements IPropertyDescriptorWidget,
		IExpressionContextSetter {
	private ExpressionContext expContext;
	private SPItemProperty expEditor;
	private ADescriptor descriptor;

	public ItemPropertyDescriptor(Object id, ADescriptor descriptor) {
		super(id, descriptor.getDescription((String) id).getLabel());
		this.descriptor = descriptor;
		setDescription(descriptor.getDescription((String) id).getDescription());
		setLabelProvider(new ItemPropertyLabelProvider(descriptor));
	}

	public ADescriptor getDescriptor() {
		return descriptor;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new ItemPropertyCellEditor(parent, expContext, (String) getId(), descriptor);
		return cellEditor;
	}

	public ASPropertyWidget<ItemPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		expEditor = new SPItemProperty(parent, section, this);
		expEditor.setExpressionContext(expContext);
		return expEditor;
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		if (expEditor != null)
			expEditor.setExpressionContext(expContext);
		if (cellEditor != null)
			cellEditor.setExpressionContext(expContext);
	}

	private List<ElementDescription> elementDescriptions;

	private ItemPropertyCellEditor cellEditor;

	public void setElementDescriptions(List<ElementDescription> propertiesPath) {
		this.elementDescriptions = propertiesPath;
	}

	public List<ElementDescription> getElementDescriptions() {
		return elementDescriptions;
	}
}
