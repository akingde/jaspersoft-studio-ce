/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPExpression;

public class JRExpressionPropertyDescriptor extends NTextPropertyDescriptor implements IPropertyDescriptorWidget,
		IExpressionContextSetter {

	protected ExpressionContext expContext;

	protected SPExpression expEditor;

	public JRExpressionPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		setLabelProvider(new JRExpressionLabelProvider());
	}

	public CellEditor createPropertyEditor(Composite parent) {
		return new JRExpressionCellEditor(parent, expContext);
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new JRExpressionLabelProvider();
	}

	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section) {
		expEditor = new SPExpression(parent, section, this);
		expEditor.setTraverseOnTab(true);
		expEditor.setExpressionContext(expContext);
		return expEditor;
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		if (expEditor != null)
			expEditor.setExpressionContext(expContext);
	}
}
