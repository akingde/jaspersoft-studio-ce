/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.studio.editor.expression.ExpressionContext;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;

/**
 * Container used to represent all the standard properties and the expression properties of an element
 * 
 * @author Orlandin Marco
 *
 */
public class PropertyExpressionsDTO {

	/**
	 * The list of the element properties
	 */
	protected List<PropertyExpressionDTO> properties = new ArrayList<PropertyExpressionDTO>();

	private Object jrElement;
	private ExpressionContext eContext;

	/**
	 * Build the class with some properties inside
	 * 
	 * @param propExpressions
	 *          list of the expression properties of the element, can be null if empty
	 * @param propMap
	 *          map of the standard properties of the element, can be null if empty
	 * @param pnode
	 *          the element
	 */
	public PropertyExpressionsDTO(JRPropertyExpression[] propExpressions, JRPropertiesMap propMap, Object jrElement,
			ExpressionContext eContext) {
		this(jrElement, eContext);
		if (propExpressions != null)
			for (JRPropertyExpression prop : propExpressions) {
				PropertyExpressionDTO newProp = new PropertyExpressionDTO(true, prop.getName(),
						prop.getValueExpression().getText());
				newProp.seteContext(eContext);
				newProp.setJrElement(jrElement);
				properties.add(newProp);
			}
		if (propMap != null)
			for (String prop : propMap.getPropertyNames()) {
				PropertyExpressionDTO newProp = new PropertyExpressionDTO(false, prop, propMap.getProperty(prop));
				newProp.seteContext(eContext);
				newProp.setJrElement(jrElement);
				properties.add(newProp);
			}
	}

	/**
	 * Create the class with no properties inside
	 * 
	 * @param pnode
	 *          the element
	 */
	public PropertyExpressionsDTO(Object jrElement, ExpressionContext eContext) {
		super();
		this.jrElement = jrElement;
		this.eContext = eContext;
	}

	/**
	 * Create the dto with the properties and the node from another dto
	 * 
	 * @param properties
	 *          the properties
	 * @param pnode
	 *          the node
	 */
	public PropertyExpressionsDTO(List<PropertyExpressionDTO> properties, Object jrElement, ExpressionContext eContext) {
		this(jrElement, eContext);
		this.properties = properties;
	}

	public Object getJrElement() {
		return jrElement;
	}

	public void setJrElement(Object jrElement) {
		this.jrElement = jrElement;
	}

	public ExpressionContext geteContext() {
		return eContext;
	}

	public void seteContext(ExpressionContext eContext) {
		this.eContext = eContext;
	}

	/**
	 * Return a not null list of the element properties
	 * 
	 * @return a not null list of the element expressions and standard properties
	 */
	public List<PropertyExpressionDTO> getProperties() {
		return properties;
	}

	/**
	 * Remove a properties from the properties list
	 * 
	 * @param propertyName
	 *          name of the property to remove
	 * @param isExpression
	 *          true if the property to remove is an expression property, false if it is a standard one
	 * @return true if the properties was found and removed, false if it was not found
	 */
	public boolean removeProperty(String propertyName, boolean isExpression) {
		for (PropertyExpressionDTO prop : properties)
			if (isExpression == prop.isExpression() && propertyName.equals(prop.getName())) {
				properties.remove(prop);
				return true;
			}
		return false;
	}

	/**
	 * Check if a specific property is in the properties list
	 * 
	 * @param propertyName
	 *          name of the property
	 * @param isExpression
	 *          true if the property is an expression property, false if it is a standard one
	 * @return true if the properties was found, false otherwise
	 */
	public boolean hasProperty(String propertyName, boolean isExpression) {
		for (PropertyExpressionDTO prop : properties)
			if (isExpression == prop.isExpression() && propertyName.equals(prop.getName()))
				return true;
		return false;
	}

	/**
	 * Add a property to the list, only if a property with the same name and of the same type is not already present
	 * 
	 * @param name
	 *          the name of the property
	 * @param value
	 *          the value of the property
	 * @param isExpression
	 *          true if the property is an expression property, false if it is a standard property
	 * @return true if a property with the same name\type was not found and the new one was inserted, false otherwise
	 */
	public boolean addProperty(String name, String value, boolean isExpression) {
		if (!hasProperty(name, isExpression)) {
			PropertyExpressionDTO newProp = new PropertyExpressionDTO(isExpression, name, value);
			newProp.seteContext(eContext);
			newProp.setJrElement(jrElement);
			properties.add(newProp);
			return true;
		}
		return false;
	}

	/**
	 * Add a property to the list into a specific position, only if a property with the same name and of the same type is
	 * not already present
	 * 
	 * @param name
	 *          the name of the property
	 * @param value
	 *          the value of the property
	 * @param isExpression
	 *          true if the property is an expression property, false if it is a standard property
	 * @param position
	 *          the position where the property should be inserted
	 * @return true if a property with the same name\type was not found and the new one was inserted, false otherwise
	 */
	public boolean addProperty(String name, String value, boolean isExpression, int position) {
		if (!hasProperty(name, isExpression)) {
			PropertyExpressionDTO newProp = new PropertyExpressionDTO(isExpression, name, value);
			newProp.seteContext(eContext);
			newProp.setJrElement(jrElement);
			properties.add(position, newProp);
			return true;
		}
		return false;
	}

	/**
	 * Set a property to the list, if there is a property with the same name and type then the value of that property is
	 * changed with the passed value parameter. Otherwise a new property is created.
	 * 
	 * @param name
	 *          the name of the property
	 * @param value
	 *          the value of the property
	 * @param isExpression
	 *          true if the property is an expression property, false if it is a standard property
	 */
	public void setProperty(String name, String value, boolean isExpression) {
		PropertyExpressionDTO prop = getProperty(name, isExpression);
		if (prop != null)
			prop.setValue(value);
		else
			addProperty(name, value, isExpression);
	}

	/**
	 * Return a property defined inside the list with a specific name and type
	 * 
	 * @param propertyName
	 *          the name of the property
	 * @param isExpression
	 *          true if the property is an expression property, false if it is a standard property
	 * @return the property if it was found, null otherwise
	 */
	public PropertyExpressionDTO getProperty(String propertyName, boolean isExpression) {
		for (PropertyExpressionDTO prop : properties)
			if (isExpression == prop.isExpression() && propertyName.equals(prop.getName()))
				return prop;
		return null;
	}

	/**
	 * Deep copy the list of properties
	 * 
	 * @return a copy of the current object
	 */
	@Override
	public PropertyExpressionsDTO clone() {
		PropertyExpressionsDTO copy = new PropertyExpressionsDTO(getJrElement(), geteContext());
		for (PropertyExpressionDTO prop : getProperties())
			copy.addProperty(prop.getName(), prop.getValue(), prop.isExpression());
		return copy;
	}
}
