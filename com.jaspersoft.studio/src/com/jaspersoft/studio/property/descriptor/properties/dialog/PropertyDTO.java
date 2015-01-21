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
package com.jaspersoft.studio.property.descriptor.properties.dialog;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import com.jaspersoft.studio.model.ANode;

/**
 * Container for the definition of a Standard property
 */
public class PropertyDTO implements Cloneable {
	
	/**
	 * The node where the property is or will be
	 */
	private ANode pnode;
	
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
	 * @param name The name of the property, must be not null
	 * @param value The property value as string
	 */
	public PropertyDTO(String name, String value){
		this.name = name;
		this.value = value;
	}
	
	/**
	 * Return the node where the property is defined or will
	 * be defined
	 * 
	 * @return ANode, can be null
	 */
	public ANode getPnode() {
		return pnode;
	}

	/**
	 * Set the node where the property is defined or will
	 * be defined
	 * 
	 * @param pnode, the node, should be not null
	 */
	public void setPnode(ANode pnode) {
		this.pnode = pnode;
	}

	/**
	 * Used to know if the property is a standard one
	 * or an expression property. since the property DTO represent
	 * a standard property this return always false, but can be overridden
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
	 * Return the value of the property encapsulated inside
	 * an expression
	 * 
	 * @return a not null JRDesignExpression
	 */
	public JRExpression getValueAsExpression(){
		return new JRDesignExpression(getValue());
	}

	/**
	 * Set the name of the property
	 * 
	 * @param name the name of the property, must be not null
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the value of the property
	 * 
	 * @param value the value of the property represented as string,
	 * can be null
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
	public PropertyDTO clone(){
		return new PropertyDTO(new String(this.getName()), new String(this.getValue()));
	}
}
