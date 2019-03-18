/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.sp;

import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.DescriptorPropertyLabelProvider;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.AHistorySPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.CustomAutoCompleteField;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedListener;
import com.jaspersoft.studio.widgets.framework.manager.DoubleControlComposite;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.engine.design.JRDesignElement;

public class SPItemProperty extends AHistorySPropertyWidget<ItemPropertyDescriptor> implements IExpressionContextSetter {
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
		ItemPropertyDescription<?> ipd = d.getDescription((String) pDescriptor.getId());
		expr = new WItemProperty(parent, SWT.NONE, ipd, d.getPropertyEditor());
		DoubleControlComposite control = (DoubleControlComposite)expr.getControl();
		Control simpleControl = control.getSimpleControlToHighlight();
		if (simpleControl instanceof Combo || simpleControl instanceof Text) {
			/**
			 * Assuring that the width has an hint in case of grid layout, doing this will force the
			 * text to not grow too much depending on the text content 
			 */
			
			Object layoutData = simpleControl.getLayoutData();
			if (layoutData != null && layoutData.getClass().equals(GridData.class)) {
				GridData oldData = (GridData)layoutData;
				if (oldData.grabExcessHorizontalSpace && oldData.horizontalAlignment == SWT.FILL && oldData.widthHint == SWT.DEFAULT);
				GridData newGridData = new GridData(oldData.horizontalAlignment, oldData.verticalAlignment, oldData.grabExcessHorizontalSpace, 
														oldData.grabExcessVerticalSpace, oldData.horizontalSpan, oldData.verticalSpan);
				int w = getCharWidth(simpleControl) * 15;
				if (w > 50) w = 50;
				newGridData.widthHint = w;
				simpleControl.setLayoutData(newGridData);
			}
		}
		expr.setLabelProvider(new DescriptorPropertyLabelProvider(d));
		
		expr.addModifyListener(new ItemPropertyModifiedListener() {
			@Override
			public void itemModified(ItemPropertyModifiedEvent event) {
				StandardItemProperty exp = new StandardItemProperty(expr.getPropertyName(), expr.getStaticValue(), expr.getExpressionValue());
				section.changeProperty(pDescriptor.getId(), exp != null ? exp.clone() : null);
			}
		});
		if (parent.getLayout() instanceof GridLayout)
			expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expr.getControl().addFocusListener(focusListener);
		Text textControl = getTextControl();
		if (textControl != null)
			autocomplete = new CustomAutoCompleteField(textControl, new TextContentAdapter(),
					InputHistoryCache.get(getHistoryKey()));
	}

	public void setData(APropertyNode pnode, Object b) {
		if (b == null)
			b = new StandardItemProperty((String) pDescriptor.getId(), null, null);
		StandardItemProperty prop = (StandardItemProperty)b;
		expr.setValue(prop.getValue(), prop.getValueExpression());
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

	@Override
	protected Text getTextControl() {
		if (expr.getControl() != null && expr.getControl() instanceof Text)
			return (Text) expr.getControl();
		return null;
	}

}
