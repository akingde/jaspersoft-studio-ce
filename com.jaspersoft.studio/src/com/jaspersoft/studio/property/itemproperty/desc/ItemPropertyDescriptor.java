/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.itemproperty.celleditor.ItemPropertyCellEditor;
import com.jaspersoft.studio.property.itemproperty.sp.SPItemProperty;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;

public class ItemPropertyDescriptor extends NTextPropertyDescriptor
		implements IPropertyDescriptorWidget, IExpressionContextSetter {
	private ExpressionContext expContext;
	private SPItemProperty expEditor;
	private ADescriptor descriptor;

	public ItemPropertyDescriptor(Object id, ADescriptor descriptor) {
		super(id, descriptor.getDescription((String) id).getLabel());
		this.descriptor = descriptor;
		setDescription(descriptor.getDescription((String) id).getDescription());
		setLabelProvider(new DescriptorPropertyLabelProvider(descriptor));
	}

	public ADescriptor getDescriptor() {
		return descriptor;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new ItemPropertyCellEditor(parent, expContext, (String) getId(), descriptor);
		if (getValidator() != null)
			cellEditor.setValidator(getValidator());
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
