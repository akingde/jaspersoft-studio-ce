/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;

import net.sf.jasperreports.engine.JRExpression;

/**
 * Properties editor of a chart customizer dynamic property, used to store the property inside the element
 * 
 * @author Orlandin Marco
 *
 */
public class DtoPropertyEditor implements IPropertyEditor {
	
	/**
	 * The edited {@link CustomizerPropertyExpressionsDTO}
	 */
	private CustomizerPropertyExpressionsDTO propertiesDTO;
	
	/**
	 * The prefix key of the customizer handled by this editor
	 */
	private String keyPrefix;

	public DtoPropertyEditor(String keyPrefix, CustomizerPropertyExpressionsDTO propertiesDTO) {
		this.propertiesDTO = propertiesDTO;
		this.keyPrefix = keyPrefix;
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
	}

	/**
	 * Remove a property from the element, both expression and static and store the new dto on the element
	 */
	@Override
	public void removeProperty(String propertyName) {
		propertiesDTO.removeProperty(keyPrefix + propertyName, false);
		propertiesDTO.removeProperty(keyPrefix + propertyName, true);	
	}

}
