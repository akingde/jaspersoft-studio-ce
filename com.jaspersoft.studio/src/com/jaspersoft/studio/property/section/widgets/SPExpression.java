/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.ibm.icu.text.MessageFormat;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.events.ExpressionModifiedEvent;
import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.swt.widgets.WTextExpression;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.inputhistory.InputHistoryCache;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class SPExpression extends AHistorySPropertyWidget<IPropertyDescriptor> implements IExpressionContextSetter {
	
	protected WTextExpression expr;

	public SPExpression(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return expr;
	}

	@Override
	protected Text getTextControl() {
		return expr.getTextControl();
	}
	
	@Override
	public Control getControlToBorder() {
		return getTextControl();
	}
	
	protected void createComponent(Composite parent) {
		expr = new WTextExpression(parent, SWT.NONE, 1);
		expr.addModifyListener(new ExpressionModifiedListener() {
			@Override
			public void expressionModified(ExpressionModifiedEvent event) {
				JRDesignExpression exp = expr.getExpression();
				section.changeProperty(pDescriptor.getId(), exp != null ? exp.clone() : null);
			}
		});
		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			expr.setLayoutData(gd);
		}
		expr.getTextControl().addFocusListener(focusListener);
		autocomplete = new CustomAutoCompleteField(expr.getTextControl(), new TextContentAdapter(),
				InputHistoryCache.get(getHistoryKey()));
	}

	public void setData(APropertyNode pnode, Object b) {
		createContextualMenu(pnode);
		expr.setExpression((JRDesignExpression) b);
		if (b != null && expr.getTextControl() != null){
			String expressionText = ((JRDesignExpression)b).getText();
			if (expr.isTraverseOnTab()){
				String tooltip = MessageFormat.format(Messages.SPExpression_tooltipHint, expressionText);
				expr.getTextControl().setToolTipText(tooltip);
			} else {
				expr.getTextControl().setToolTipText(expressionText);
			}
		}
		
		JRDesignElement designEl = null;
		if (pnode.getValue() instanceof JRDesignElement) {
			designEl = (JRDesignElement) pnode.getValue();
		}
		// Try to get an expression context for the node if any
		Object expContextAdapter = pnode.getAdapter(ExpressionContext.class);
		if(expContextAdapter!=null){
			expr.setExpressionContext((ExpressionContext)expContextAdapter);
		}
		else{
			expr.setExpressionContext(ModelUtils.getElementExpressionContext(designEl, pnode));
		}
	}

	public void setEnabled(boolean enabled) {
		expr.setEnabled(enabled);
	}

	public void setExpressionContext(ExpressionContext exprContext) {
		expr.setExpressionContext(exprContext);
	}

	/**
	 * Set if the widget should traverse on tab or not
	 * 
	 * @param value true if on tab the widget should change, false to 
	 * add a tab as text as content of the expression
	 */
	public void setTraverseOnTab(boolean value){
		if (expr != null){
			expr.setTraverseOnTab(value);
		}
	}
}
