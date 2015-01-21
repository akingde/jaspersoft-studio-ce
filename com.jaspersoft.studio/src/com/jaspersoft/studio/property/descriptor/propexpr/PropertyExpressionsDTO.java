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
package com.jaspersoft.studio.property.descriptor.propexpr;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;

import com.jaspersoft.studio.model.ANode;

/**
 * Container used to represent all the standard properties and the expression properties of
 * an element
 * 
 * @author Orlandin Marco
 *
 */
public class PropertyExpressionsDTO {
	
	/**
	 * The list of the element properties
	 */
	private List<PropertyExpressionDTO> properties = new ArrayList<PropertyExpressionDTO>();
	
	/**
	 * The element from where the properties came from
	 */
	private ANode pnode;

	/**
	 * Build the class with some properties inside
	 * 
	 * @param propExpressions list of the expression properties of the element, can be null if empty
	 * @param propMap map of the standard properties of the element, can be null if empty
	 * @param pnode the element
	 */
	public PropertyExpressionsDTO(JRPropertyExpression[] propExpressions, JRPropertiesMap propMap, ANode pnode) {
		this(pnode);
		if (propExpressions != null){
			for(JRPropertyExpression prop : propExpressions){
				PropertyExpressionDTO newProp = new PropertyExpressionDTO(true, prop.getName(), prop.getValueExpression().getText());
				newProp.setPnode(pnode);
				properties.add(newProp);
			}
		}
		if (propMap != null){
			for(String prop : propMap.getPropertyNames()){
				PropertyExpressionDTO newProp = new PropertyExpressionDTO(false, prop, propMap.getProperty(prop));
				newProp.setPnode(pnode);
				properties.add(newProp);
			}
		}
	}
	
	/**
	 * Create the class with no properties inside
	 * 
	 * @param pnode the element
	 */
	public PropertyExpressionsDTO(ANode pnode) {
		super();
		this.pnode = pnode;
	}
	
	/**
	 * Return the element from where the properties 
	 * came
	 * 
	 * @return a node
	 */
	public ANode getPnode() {
		return pnode;
	}

	/**
	 * Set the element from where the properties 
	 * came
	 * 
	 * @return a node
	 */
	public void setPnode(ANode pnode) {
		this.pnode = pnode;
	}

	/**
	 * Return a not null list of the element properties
	 * 
	 * @return a not null list of the element expressions and standard properties
	 */
	public List<PropertyExpressionDTO> getProperties(){
		return properties;
	}
	
	/**
	 * Remove a properties from the properties list
	 * 
	 * @param propertyName name of the property to remove
	 * @param isExpression true if the property to remove is an expression property, false if it is a standard one
	 * @return true if the properties was found and removed, false if it was not found
	 */
	public boolean removeProperty(String propertyName, boolean isExpression){
		for(PropertyExpressionDTO prop : properties){
			if (isExpression == prop.isExpression() && propertyName.equals(prop.getName())){
				properties.remove(prop);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if a specific property is in the properties list
	 * 
	 * @param propertyName name of the property
	 * @param isExpression true if the property is an expression property, false if it is a standard one
	 * @return true if the properties was found, false otherwise
	 */
	public boolean hasProperty(String propertyName, boolean isExpression){
		for(PropertyExpressionDTO prop : properties){
			if (isExpression == prop.isExpression() && propertyName.equals(prop.getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Add a property to the list, only if a property with the same name and 
	 * of the same type is not already present
	 * 
	 * @param name the name of the property
	 * @param value the value of the property
	 * @param isExpression true if the property is an expression property, false if it is a standard property
	 * @return true if a property with the same name\type was not found and the new one was inserted, false otherwise
	 */
	public boolean addProperty(String name, String value, boolean isExpression){
		if (!hasProperty(name, isExpression)){
			PropertyExpressionDTO newProp = new PropertyExpressionDTO(isExpression, name,value);
			newProp.setPnode(pnode);
			properties.add(newProp);
			return true;
		}
		return false;
	}
	
	/**
	 * Set a property to the list, if there is a property with the same name and type then the value
	 * of that property is changed with the passed value parameter. Otherwise a new property is created.
	 * 
	 * @param name the name of the property
	 * @param value the value of the property
	 * @param isExpression true if the property is an expression property, false if it is a standard property
	 */
	public void setProperty(String name, String value, boolean isExpression){
		PropertyExpressionDTO prop = getProperty(name, isExpression);
		if (prop != null){
			prop.setValue(value);
		} else {
			addProperty(name, value, isExpression);
		}
	}
	
	/**
	 * Return a property defined inside the list with a specific name and type
	 * 
	 * @param propertyName the name of the property
	 * @param isExpression true if the property is an expression property, false if it is a standard property
	 * @return the property if it was found, null otherwise
	 */
	public PropertyExpressionDTO getProperty(String propertyName, boolean isExpression){
		for(PropertyExpressionDTO prop : properties){
			if (isExpression == prop.isExpression() && propertyName.equals(prop.getName())){
				return prop;
			}
		}
		return null;
	}
	
	/**
	 * Deep copy the list of properties
	 * 
	 * @return a copy of the current object
	 */
	@Override
	public PropertyExpressionsDTO clone(){
		PropertyExpressionsDTO copy = new PropertyExpressionsDTO(getPnode());
		for(PropertyExpressionDTO prop : getProperties()){
			copy.addProperty(prop.getName(), prop.getValue(), prop.isExpression());
		}
		return copy;
	}
}
