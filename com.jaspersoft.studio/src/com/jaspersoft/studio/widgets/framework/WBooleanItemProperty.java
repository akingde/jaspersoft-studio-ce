/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
		String customSimpleMode = getPropertyEditor().getPropertyValue(getPropertyName()+WItemProperty.CUSTOM_SIMPLE_MODE_SUFFIX);
		return "true".equals(customSimpleMode);
	}

	@Override
	public void setValue(String staticValue, JRExpression expressionValue) {
		setRefresh(true);
		try {
			if(staticValue!=null){
				String simpleMode = getPropertyName() + WItemProperty.CUSTOM_SIMPLE_MODE_SUFFIX;
				getPropertyEditor().createUpdateProperty(simpleMode, "true", null);
				expressionValue=new JRDesignExpression(staticValue);
				staticValue=null;
			}
			else {
				getPropertyEditor().removeProperty(getPropertyName() + WItemProperty.CUSTOM_SIMPLE_MODE_SUFFIX);
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
