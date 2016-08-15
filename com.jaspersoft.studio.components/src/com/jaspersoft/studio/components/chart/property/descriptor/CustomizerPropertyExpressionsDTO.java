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
package com.jaspersoft.studio.components.chart.property.descriptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jaspersoft.jasperreports.chartcustomizers.ProxyChartCustomizer;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerDefinitionManager;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Utility class used to extend the properties container of an element adding
 * some utility method to handle the chart customizers
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerPropertyExpressionsDTO extends PropertyExpressionsDTO {

	/**
	 * Since calculating the customizer number has an overhead but it could be necessary
	 * when using the advanced view the number is cached, and the cache is discarded when
	 * the properties set is modified
	 */
	private Integer customizerNumber = null;
	
	/**
	 * Build the class from a standard {@link PropertyExpressionsDTO}
	 * 
	 * @param dto a not null {@link PropertyExpressionsDTO}
	 */
	public CustomizerPropertyExpressionsDTO(PropertyExpressionsDTO dto) {
		super(dto.getProperties(), dto.getPnode());
	}
	
	/**
	 * Return the number of customizers defined in the properties of the element 
	 * 
	 * @return an integer >=0
	 */
	public int getCustomizersNumber(){
		if (customizerNumber == null){
			int count = 0;
			String classAttribute = CustomizerPropertyDescriptor.CUSTOMIZER_CLASS_ATTRIUBUTE;
			for(PropertyExpressionDTO property: getProperties()){
				if (!property.isExpression()){
					String name = property.getName().trim().toLowerCase();
					//Search the properties related to a customizer
					if (name.startsWith(CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX)){
						String[] splitString = name.split("\\.");
						if (splitString.length > 2 && splitString[2].equals(classAttribute)){
							count++;
						}
					}
				}
			}
			
			//If it is an old report with an old customizer set as property simply ad it to the count
			String customizerClass = getPnode().getValue().getCustomizerClass();
			if (customizerClass != null && !customizerClass.equals(ProxyChartCustomizer.class.getName())){
				count ++;
			}
			customizerNumber = count;
		}
		return customizerNumber;
	}
	
	/**
	 * Create a class entry for a chart customizer with an unique key
	 * 
	 * @param className the customizer classname
	 * @param headPosition true if the new entry should be placed in the head of the properties, false if
	 * it should be placed in the tails
	 */
	public void createCustomizerEntry(String className, boolean headPosition){
		String key = getUniqueKey();
		String classAttribute = key + CustomizerPropertyDescriptor.CUSTOMIZER_CLASS_ATTRIUBUTE;
		if (headPosition){
			addProperty(classAttribute, className, false, 0);
		} else {
			addProperty(classAttribute, className, false);
		}
	}
	
	/**
	 * Return the list of the defined customizers in the properties of an element
	 * 
	 * @return a not null list of a {@link ChartCustomizerDefinition}
	 */
	public List<ChartCustomizerDefinition> getDefinedCustomizers(){
		List<ChartCustomizerDefinition> selectedCustomizers = new ArrayList<ChartCustomizerDefinition>();
		JasperReportsConfiguration jConfig = getPnode().getJasperConfiguration();
		String classAttribute = CustomizerPropertyDescriptor.CUSTOMIZER_CLASS_ATTRIUBUTE;
		String attributeSeparator = CustomizerPropertyDescriptor.CUSTOMIZER_ATTRIBUTE_SEPARATOR;
		for (PropertyExpressionDTO prop : getProperties()) {
			if (!prop.isExpression()){
				String propName = prop.getName().trim().toLowerCase();
				if (propName.startsWith(CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX)){
					String[] parts = prop.getName().split("\\.");
					if (parts.length > 2 && parts[2].equals(classAttribute)) {
						// We have found a chart customizer, build the key prefix
						String key = parts[0] + attributeSeparator + parts[1] + attributeSeparator;
						String customizerClass = prop.getValue();
						ChartCustomizerDefinition definition = CustomizerDefinitionManager.getCustomizerDefinition(customizerClass, key, jConfig);
						if (definition != null){
							selectedCustomizers.add(definition);
						}
					}
				}
			}
		}
		
		//If it is an old report with an old customizer set as property simply ad it to the list
		String customizerClass = getPnode().getValue().getCustomizerClass();
		if (customizerClass != null && !customizerClass.equals(ProxyChartCustomizer.class.getName())){
			selectedCustomizers.add(new ChartCustomizerDefinition(customizerClass, getUniqueKey()));
		}
		return selectedCustomizers;
	}

	/**
	 * Return an unique key for a new chart customizer, still not used by other customizers on the element
	 * 
	 * @return a not null string that will be the valid and unique prefix for any customizer property. The 
	 * String will be like chartcustomizer.customizerX. where X is an unique number not used by any of the others
	 * customizers
	 */
	public String getUniqueKey(){
		int index = 0;
		String attributeSeparator = CustomizerPropertyDescriptor.CUSTOMIZER_ATTRIBUTE_SEPARATOR;
		String currentKey = CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX + index + attributeSeparator;
		HashSet<String> usedKeys = getUsedKeys();
		boolean existing = usedKeys.contains(currentKey);
		while(existing){
			index++;
			currentKey = CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX + index + attributeSeparator;
			existing = usedKeys.contains(currentKey);
		}
		return currentKey;
	}
	
	/**
	 * Return an hashset of all the currently used key
	 * 
	 * @return a not null hashset of string
	 */
	private HashSet<String> getUsedKeys(){
		HashSet<String> result = new HashSet<String>();
		String attributeSeparator = CustomizerPropertyDescriptor.CUSTOMIZER_ATTRIBUTE_SEPARATOR;
		for (PropertyExpressionDTO prop : new ArrayList<PropertyExpressionDTO>(getProperties())) {
			if (!prop.isExpression()){
				String propName = prop.getName().trim().toLowerCase();
				if (propName.startsWith(CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX)){
					String[] parts = prop.getName().split("\\.");
					if (parts.length > 2) {
						// We have found a chart customizer, build the key prefix
						String key = parts[0] + attributeSeparator + parts[1] + attributeSeparator;
						result.add(key);
					}
				}
			}
		}	
		return result;
	}
	
	/**
	 * Check if there are properties set for a specific customizer, excluding the class
	 * 
	 * @param customizerKey the key of the customizer
	 * @return true if there are properties stored for the specified customizer beside the class,
	 * flase othwrwise
	 */
	public boolean hasCustomizerProperties(String customizerKey){
		String attributeSeparator = CustomizerPropertyDescriptor.CUSTOMIZER_ATTRIBUTE_SEPARATOR;
		String classAttribute = CustomizerPropertyDescriptor.CUSTOMIZER_CLASS_ATTRIUBUTE;
		for (PropertyExpressionDTO prop : new ArrayList<PropertyExpressionDTO>(getProperties())) {
			if (!prop.isExpression()){
				String propName = prop.getName().trim().toLowerCase();
				if (propName.startsWith(CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX)){
					String[] parts = prop.getName().split("\\.");
					if (parts.length > 2) {
						// We have found a chart customizer, build the key prefix
						String key = parts[0] + attributeSeparator + parts[1] + attributeSeparator;
						if (key.equals(customizerKey)  && !parts[2].equals(classAttribute)){
							return true;
						}
					}
				}
			}
		}	
		return false;
	}
	
	/**
	 * Delete all the properties related to a customizer
	 * 
	 * @param keyToRemove key of the customizer to remove
	 * @param removeProperties true if only the class attribute should be removed, false if all the 
	 * properties should be removed
	 */
	public void deleteCustomizer(String customizerKey, boolean removeProperties){
		String attributeSeparator = CustomizerPropertyDescriptor.CUSTOMIZER_ATTRIBUTE_SEPARATOR;
		String classAttribute = CustomizerPropertyDescriptor.CUSTOMIZER_CLASS_ATTRIUBUTE;
		for (PropertyExpressionDTO prop : new ArrayList<PropertyExpressionDTO>(getProperties())) {
			if (!prop.isExpression()){
				String propName = prop.getName().trim().toLowerCase();
				if (propName.startsWith(CustomizerPropertyDescriptor.CUSTOMIZER_KEY_PREFIX)){
					String[] parts = prop.getName().split("\\.");
					if (parts.length > 2) {
						// We have found a chart customizer, build the key prefix
						String key = parts[0] + attributeSeparator + parts[1] + attributeSeparator;
						if (key.equals(customizerKey)){
							if (parts[2].equals(classAttribute)){
								removeProperty(prop.getName(), prop.isExpression());
								if (!removeProperties){
									//I've removed the class, break the cycle if the attributes could stay
									break;
								}
							} else if (removeProperties){
								removeProperty(prop.getName(), prop.isExpression());
							}
						}
					}
				}
			}
		}	
	}
	
	/**
	 * When the properties set changes discard the cache
	 */
	@Override
	public boolean addProperty(String name, String value, boolean isExpression) {
		customizerNumber = null;
		return super.addProperty(name, value, isExpression);
	}
	
	/**
	 * When the properties set changes discard the cache
	 */
	@Override
	public void setProperty(String name, String value, boolean isExpression) {
		customizerNumber = null;
		super.setProperty(name, value, isExpression);
	}
	
	/**
	 * When the properties set changes discard the cache
	 */
	@Override
	public boolean removeProperty(String propertyName, boolean isExpression) {
		customizerNumber = null;
		return super.removeProperty(propertyName, isExpression);
	}
	
	@Override
	public MChart getPnode() {
		return (MChart)super.getPnode();
	}
}
