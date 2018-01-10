/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.celleditor.ItemListCellEditor;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.property.itemproperty.sp.SPItemDataList;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

public abstract class AItemDataListPropertyDescriptor extends NTextPropertyDescriptor
		implements IExpressionContextSetter {
	protected APropertyNode pNode;

	public AItemDataListPropertyDescriptor(Object id, String displayName, APropertyNode pNode) {
		super(id, displayName);
		this.pNode = pNode;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		ItemListCellEditor ce = new ItemListCellEditor(parent, expContext, getDescriptor(), this, pNode);
		ce.setLabelProvider(getLabelProvider());
		return ce;
	}

	@Override
	public ItemLabelProvider getLabelProvider() {
		return new ItemLabelProvider(descriptor);
	}

	@Override
	public ASPropertyWidget<AItemDataListPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget<AItemDataListPropertyDescriptor> sp = createSPWidget(parent, section);
		((IExpressionContextSetter) sp).setExpressionContext(expContext);
		return sp;
	}

	protected ASPropertyWidget<AItemDataListPropertyDescriptor> createSPWidget(Composite parent,
			AbstractSection section) {
		return new SPItemDataList(parent, section, this);
	}

	protected ADescriptor descriptor;

	public ADescriptor getDescriptor() {
		if (descriptor == null)
			initShowColumns();
		setLabelProvider(getLabelProvider());
		return descriptor;
	}

	protected abstract void initShowColumns();

	private ExpressionContext expContext;

	@Override
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
}
