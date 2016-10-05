/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * This custom version of the standard {@link WItemProperty} is meant to be used
 * with boolean widgets where it is not possible to rely on the standard value string
 * for the property value.
 * <p>
 * 
 * In this case a custom expression (true/false) is generated whenever the simple
 * mode interface is used to set the property value itself.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class WBooleanItemProperty extends WItemProperty {

	public WBooleanItemProperty(Composite parent, int style, ItemPropertyDescription<?> widgetDescriptor,
			IPropertyEditor editor) {
		super(parent, style, widgetDescriptor, editor);
	}

	public WBooleanItemProperty(Composite parent, int style, WidgetPropertyDescriptor descriptor,
			ItemPropertyDescription<?> widgetDescriptor, IPropertyEditor editor) {
		super(parent, style, descriptor, widgetDescriptor, editor);
	}
	
	@Override
	public boolean isExpressionMode() {
		if(isCustomSimpleMode()){
			return false;
		}
		else {
			return getPropertyEditor().getPropertyValueExpression(getPropertyName()) != null;			
		}
	}
	
	@Override
	public String getStaticValue() {
		if(isCustomSimpleMode()){
			JRExpression expressionValue = getExpressionValue();
			if(expressionValue!=null && "false".equals(expressionValue.getText())){
				return "false";
			}
			if(expressionValue!=null && "true".equals(expressionValue.getText())){
				return "true";
			}
		}
		return super.getStaticValue();
	}
	
	private boolean isCustomSimpleMode(){
		String customSimpleMode = getPropertyEditor().getPropertyValue(getPropertyName()+".customSimpleMode");
		return "true".equals(customSimpleMode);
	}

	@Override
	public void setValue(String staticValue, JRExpression expressionValue) {
		setRefresh(true);
		try {
			if(staticValue!=null){
				String simpleMode = getPropertyName() + ".customSimpleMode";
				getPropertyEditor().createUpdateProperty(simpleMode, "true", null);
				expressionValue=new JRDesignExpression(staticValue);
				staticValue=null;
			}
			else {
				getPropertyEditor().removeProperty(getPropertyName() + ".customSimpleMode");
			}
			getPropertyEditor().createUpdateProperty(getPropertyName(), staticValue, expressionValue);
			updateWidget();
			// Notifies the listeners of the new expression
			fireModifyEvent(staticValue, expressionValue);
		} finally {
			setRefresh(false);
		}
	}
}
