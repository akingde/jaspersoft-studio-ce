/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * This custom version of the standard {@link WItemProperty} is meant to be used
 * with colors palette widgets where it is not possible to rely on the standard value string
 * for the property value.
 * <p>
 * 
 * In this case a custom expression (arrays of string colors) is generated whenever the simple
 * mode interface is used to set the property value itself.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class WColorsItemProperty extends WItemProperty {

	public WColorsItemProperty(Composite parent, int style, ItemPropertyDescription<?> widgetDescriptor,
			IPropertyEditor editor) {
		super(parent, style, widgetDescriptor, editor);
	}

	public WColorsItemProperty(Composite parent, int style, WidgetPropertyDescriptor descriptor,
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
			if(expressionValue!=null){
				String[] colors = Colors.decodeHexColorsArray(expressionValue.getText());
				return Colors.encodeHexColorsAsArray(colors);
			}
			return "";
		}
		else {
			return super.getStaticValue();
		}
	}
	
	public void setCustomSimpleModeUsage() {
		String simpleMode = getPropertyName() + WItemProperty.CUSTOM_SIMPLE_MODE_SUFFIX;
		getPropertyEditor().createUpdateProperty(simpleMode, "true", null);
	}
	
	public void removeCustomSimpleModeUsage() {
		getPropertyEditor().removeProperty(getPropertyName() + WItemProperty.CUSTOM_SIMPLE_MODE_SUFFIX);
	}
	
	public boolean isCustomSimpleMode(){
		String customSimpleMode = getPropertyEditor().getPropertyValue(getPropertyName()+WItemProperty.CUSTOM_SIMPLE_MODE_SUFFIX);
		return "true".equals(customSimpleMode);
	}

	@Override
	public void setValue(String staticValue, JRExpression expressionValue) {
		setRefresh(true);
		try {
			if(staticValue!=null){
				setCustomSimpleModeUsage();
				String[] colors = Colors.decodeHexColorsArray(staticValue);
				StringBuffer colorsSB = new StringBuffer("Arrays.asList(");
				for(int i=0;i<colors.length;i++){
					colorsSB.append("\"");
					colorsSB.append(colors[i]);
					colorsSB.append("\"");
					if(i!=colors.length-1){
						colorsSB.append(",");
					}
				}
				colorsSB.append(")");
				staticValue=null;
				expressionValue=new JRDesignExpression(colorsSB.toString());
			}
			else {
				removeCustomSimpleModeUsage();
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
