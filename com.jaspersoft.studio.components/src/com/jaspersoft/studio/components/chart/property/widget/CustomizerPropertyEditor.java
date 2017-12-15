/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.ChartCustomizerWidget;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Properties editor of a chart customizer dynamic property, used to store the property inside the element
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerPropertyEditor implements IPropertyEditor {
	
	/**
	 * The edited {@link CustomizerPropertyExpressionsDTO}
	 */
	private CustomizerPropertyExpressionsDTO propertiesDTO;
	
	/**
	 * The prefix key of the customizer handled by this editor
	 */
	private String keyPrefix;
	
	/**
	 * The customizer widget
	 */
	private ChartCustomizerWidget customizerWidget;
	
	public CustomizerPropertyEditor(ChartCustomizerWidget customizerWidget, String keyPrefix, CustomizerPropertyExpressionsDTO propertiesDTO) {
		this.propertiesDTO = propertiesDTO;
		this.keyPrefix = keyPrefix;
		this.customizerWidget = customizerWidget;
	}

	/**
	 * Read the static value from the properties of the element and return it
	 */
	@Override
	public String getPropertyValue(String propertyName) {
		PropertyExpressionDTO prop = propertiesDTO.getProperty(keyPrefix + propertyName, false);
		if (prop != null){
			return prop.getValue();
		} 
		return null;
	}

	/**
	 * Read the expression value from the properties expressions of the element and return it
	 */
	@Override
	public JRExpression getPropertyValueExpression(String propertyName) {
		PropertyExpressionDTO prop = propertiesDTO.getProperty(keyPrefix + propertyName, true);
		if (prop != null){
			return prop.getValueAsExpression();
		}
		return null;
	}

	/**
	 * change the value of a property and store the new dto on an element
	 */
	@Override
	public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
		String fullPropertyName = keyPrefix + propertyName;
		if (valueExpression != null){
			if (valueExpression.getText().isEmpty()){
				propertiesDTO.removeProperty(fullPropertyName, true);
			} else {
				propertiesDTO.setProperty(fullPropertyName, valueExpression.getText(), true);
			}
		} else if (value != null){
			if (value.isEmpty()){
				propertiesDTO.removeProperty(fullPropertyName, false);
			} else {
				propertiesDTO.setProperty(fullPropertyName, value, false);
			}
		}
		
		if (value == null){
			propertiesDTO.removeProperty(fullPropertyName, false);
		}
		if (valueExpression == null){
			propertiesDTO.removeProperty(fullPropertyName, true);
		}

		customizerWidget.changePropertyOn(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, propertiesDTO, (APropertyNode)propertiesDTO.getPnode());
	}

	/**
	 * Remove a property from the element, both expression and static and store the new dto on the element
	 */
	@Override
	public void removeProperty(String propertyName) {
		propertiesDTO.removeProperty(keyPrefix + propertyName, false);
		propertiesDTO.removeProperty(keyPrefix + propertyName, true);
		customizerWidget.changePropertyOn(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, propertiesDTO, (APropertyNode)propertiesDTO.getPnode());	
	}

}
