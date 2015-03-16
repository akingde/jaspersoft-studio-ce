/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.property.itemproperty.event.ItemPropertyModifiedListener;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.utils.ModelUtils;

public class SPItemProperty extends ASPropertyWidget<ItemPropertyDescriptor> implements IExpressionContextSetter {
	private WItemProperty expr;

	public SPItemProperty(Composite parent, AbstractSection section, ItemPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return expr;
	}

	@Override
	public Control getControlToBorder() {
		return expr.getControl();
	}

	protected void createComponent(Composite parent) {
		ADescriptor d = pDescriptor.getDescriptor();
		ItemPropertyDescription<?> ipd =	d.getDescription((String) pDescriptor.getId());
		expr = new WItemProperty(parent, SWT.NONE, 1, d, ipd);
		expr.addModifyListener(new ItemPropertyModifiedListener() {
			@Override
			public void itemModified(ItemPropertyModifiedEvent event) {
				StandardItemProperty exp = expr.getValue();
				section.changeProperty(pDescriptor.getId(), exp != null ? exp.clone() : null);
			}
		});
		if (parent.getLayout() instanceof GridLayout)
			expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expr.getControl().addFocusListener(focusListener); 
	}

	public void setData(APropertyNode pnode, Object b) {
		if (b == null)
			b = new StandardItemProperty((String) pDescriptor.getId(), null, null);
		expr.setValue((StandardItemProperty) b);
		JRDesignElement designEl = null;
		if (pnode.getValue() instanceof JRDesignElement)
			designEl = (JRDesignElement) pnode.getValue();
		// Try to get an expression context for the node if any
		Object expContextAdapter = pnode.getAdapter(ExpressionContext.class);
		if (expContextAdapter != null)
			expr.setExpressionContext((ExpressionContext) expContextAdapter);
		else
			expr.setExpressionContext(ModelUtils.getElementExpressionContext(designEl, pnode));
	}

	public void setEnabled(boolean enabled) {
		expr.setEnabled(enabled);
	}

	public void setExpressionContext(ExpressionContext exprContext) {
		expr.setExpressionContext(exprContext);
	}

}
