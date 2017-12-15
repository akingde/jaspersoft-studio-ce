/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.descriptor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerDefinitionManager;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.NamedChartCustomizer;

/**
 * Utility class used to extend the properties container of an element adding
 * some utility method to handle the chart customizers
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerPropertyExpressionsDTO extends PropertyExpressionsDTO {

	/**
	 * Since calculating the customizer number has an overhead but it could be
	 * necessary when using the advanced view the number is cached, and the
	 * cache is discarded when the properties set is modified
	 */
	private Integer customizerNumber = null;

	/**
	 * Customizer set in the class field of the chart, not inside the properties
	 * map
	 */
	private String customizerClass = null;
	private MChart pnode;

	/**
	 * Build the class from a standard {@link PropertyExpressionsDTO}
	 * 
	 * @param dto
	 *            a not null {@link PropertyExpressionsDTO}
	 */
	public CustomizerPropertyExpressionsDTO(PropertyExpressionsDTO dto, MChart pnode) {
		super(dto.getProperties(), dto.getJrElement(), dto.geteContext());
		customizerClass = pnode.getValue().getCustomizerClass();
		this.pnode = pnode;
	}

	public MChart getPnode() {
		return pnode;
	}

	/**
	 * Return the number of customizers defined in the properties of the
	 * element.
	 * 
	 * @return an integer >=0
	 */
	public int getCustomizersNumber() {
		if (customizerNumber == null) {
			int count = 0;
			String classAttribute = NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX;
			for (PropertyExpressionDTO property : getProperties()) {
				if (!property.isExpression()) {
					String name = property.getName().trim().toLowerCase();
					// Search the properties related to a customizer
					if (name.startsWith(classAttribute)) {
						count++;
					}
				}
			}

			if (customizerClass != null && !customizerClass.trim().isEmpty()) {
				count++;
			}
			customizerNumber = count;
		}
		return customizerNumber;
	}

	/**
	 * Create a class entry for a chart customizer with an unique key
	 * 
	 * @param className
	 *            the customizer classname
	 * @param headPosition
	 *            true if the new entry should be placed in the head of the
	 *            properties, false if it should be placed in the tails
	 */
	public void createCustomizerEntry(String className, boolean headPosition) {
		String key = getUniqueKey();
		String classAttribute = NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX + key;
		if (headPosition) {
			addProperty(classAttribute, className, false, 0);
		} else {
			addProperty(classAttribute, className, false);
		}
	}

	/**
	 * Return the list of the defined customizers in the properties of an
	 * element
	 * 
	 * @return a not null list of a {@link ChartCustomizerDefinition}
	 */
	public List<ChartCustomizerDefinition> getDefinedCustomizers() {
		List<ChartCustomizerDefinition> selectedCustomizers = new ArrayList<ChartCustomizerDefinition>();
		JasperReportsConfiguration jConfig = pnode.getJasperConfiguration();
		for (PropertyExpressionDTO prop : getProperties()) {
			if (!prop.isExpression()) {
				String propName = prop.getName().trim();
				if (propName.toLowerCase().startsWith(NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX)) {
					// We have found a chart customizer, build the key prefix
					String key = propName.substring(NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX.length());
					String customizerClass = prop.getValue();
					ChartCustomizerDefinition definition = CustomizerDefinitionManager
							.getCustomizerDefinition(customizerClass, key, jConfig);
					if (definition != null) {
						selectedCustomizers.add(definition);
					}
				}
			}
		}

		// add the customizer class
		if (customizerClass != null && !customizerClass.trim().isEmpty()) {
			selectedCustomizers.add(new ChartCustomizerDefinition(customizerClass, getUniqueKey(), false));
		}
		return selectedCustomizers;
	}

	/**
	 * Return an unique key for a new chart customizer, still not used by other
	 * customizers on the element
	 * 
	 * @return a not null string that will be the valid and unique prefix for
	 *         any customizer property. The String will be like
	 *         chartcustomizer.customizerX. where X is an unique number not used
	 *         by any of the others customizers
	 */
	public String getUniqueKey() {
		int index = 0;
		HashSet<String> usedKeys = getUsedKeys();
		boolean existing = usedKeys.contains(String.valueOf(index));
		while (existing) {
			index++;
			existing = usedKeys.contains(String.valueOf(index));
		}
		return String.valueOf(index);
	}

	/**
	 * Return an hashset of all the currently used key
	 * 
	 * @return a not null hashset of string
	 */
	private HashSet<String> getUsedKeys() {
		HashSet<String> result = new HashSet<String>();
		for (PropertyExpressionDTO prop : new ArrayList<PropertyExpressionDTO>(getProperties())) {
			if (!prop.isExpression()) {
				String propName = prop.getName().trim();
				if (propName.toLowerCase().startsWith(NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX)) {
					// We have found a chart customizer, get the key
					String key = propName.substring(NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX.length());
					result.add(key);
				}
			}
		}
		return result;
	}

	/**
	 * Delete a customizer and all its properties if it is defined in the
	 * properties map, otherwise the customizer class value on the customizer
	 * chart property will be set to null
	 * 
	 * @param definition
	 *            the customizer to remove
	 * @param removeProperties
	 *            true if only the class attribute should be removed, false if
	 *            all the properties should be removed
	 */
	public void deleteCustomizer(ChartCustomizerDefinition definition, boolean removeProperties) {
		customizerNumber = null;
		if (definition.isPropertiesCustomizer()) {
			String customizerKey = definition.getKey();
			for (PropertyExpressionDTO prop : new ArrayList<PropertyExpressionDTO>(getProperties())) {
				if (!prop.isExpression()) {
					String propName = prop.getName().trim();
					if (propName.toLowerCase().startsWith(NamedChartCustomizer.CUSTOMIZER_PROPERTY_PREFIX)) {
						// it is customizer property and a class property
						if (propName.toLowerCase().startsWith(NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX)) {
							String key = propName
									.substring(NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX.length());
							if (customizerKey.equalsIgnoreCase(key)) {
								removeProperty(prop.getName(), prop.isExpression());
								if (!removeProperties) {
									// I've removed the class, break the cycle
									// if the attributes could stay
									break;
								}
							}
						} else {
							if (removeProperties) {
								String key = propName
										.substring(NamedChartCustomizer.CUSTOMIZER_PROPERTY_PREFIX.length());
								// remove the suffix
								int suffixIndex = key.indexOf('.');
								if (suffixIndex != -1) {
									key = key.substring(0, suffixIndex);
								}
								// it is customizer property but not a class
								// property
								if (customizerKey.equalsIgnoreCase(key)) {
									removeProperty(prop.getName(), prop.isExpression());
								}
							}
						}
					}
				}
			}
		} else {
			customizerClass = null;
		}
	}

	/**
	 * Add a customizer to the properties map
	 * 
	 * @param definition
	 *            the customizer to add, must be not null
	 */
	public void addCustomizer(ChartCustomizerDefinition definition) {
		String classProperty = NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX + definition.getKey();
		addProperty(classProperty, definition.getCustomizerClass(), false);
	}

	/**
	 * Update the class value of a customizer. If it is customizer used as
	 * direct class on the chart then that value is updated. If the passed
	 * customizer is not a raw class this doesn't do anything
	 * 
	 * @param editElement
	 *            the element to edit
	 */
	public void updateCustomizerClass(ChartCustomizerDefinition editElement) {
		if (!editElement.isPropertiesCustomizer()) {
			customizerClass = editElement.getCustomizerClass();
		} else if (editElement.isOnlyClass()) {
			String classProp = NamedChartCustomizer.CUSTOMIZER_CLASS_PROPERTY_PREFIX + editElement.getKey();
			setProperty(classProp, editElement.getCustomizerClass(), false);
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

}
