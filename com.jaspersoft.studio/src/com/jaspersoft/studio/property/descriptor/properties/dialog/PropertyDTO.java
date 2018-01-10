/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import com.jaspersoft.studio.editor.expression.ExpressionContext;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/**
 * Container for the definition of a Standard property
 */
public class PropertyDTO implements Cloneable {

	private Object jrElement;
	private ExpressionContext eContext;

	/**
	 * The name of the property, must be not null
	 */
	private String name;

	/**
	 * The property value as string
	 */
	private String value;

	/**
	 * Create a container for a standard property definition
	 * 
	 * @param name
	 *          The name of the property, must be not null
	 * @param value
	 *          The property value as string
	 */
	public PropertyDTO(String name, String value) {
		this.name = name;
		this.value = value;
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
	 * Used to know if the property is a standard one or an expression property. since the property DTO represent a
	 * standard property this return always false, but can be overridden
	 * 
	 * @return false if the property is standard, true if it is an expression property
	 */
	public boolean isExpression() {
		return false;
	}

	/**
	 * Return the name of the property
	 * 
	 * @return a not null property name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the value of the property
	 * 
	 * @return the value of the property, can be null
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Return the value of the property encapsulated inside an expression
	 * 
	 * @return a not null JRDesignExpression
	 */
	public JRExpression getValueAsExpression() {
		return new JRDesignExpression(getValue());
	}

	/**
	 * Set the name of the property
	 * 
	 * @param name
	 *          the name of the property, must be not null
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the value of the property
	 * 
	 * @param value
	 *          the value of the property represented as string, can be null
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Clone the property and return a copy of it
	 * 
	 * @return a not null copy of the current property
	 */
	@Override
	public PropertyDTO clone() {
		return new PropertyDTO(new String(this.getName()), new String(this.getValue()));
	}
}
