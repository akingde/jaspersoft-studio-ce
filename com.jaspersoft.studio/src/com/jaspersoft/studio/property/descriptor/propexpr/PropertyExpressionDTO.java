/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr;

import com.jaspersoft.studio.property.descriptor.properties.dialog.PropertyDTO;

/**
 * Container for the definition of both a Standard property or an expression property
 * 
 * @author Orlandin Marco
 */
public class PropertyExpressionDTO extends PropertyDTO {

	/**
	 * Flag to know if the property is an expression property or a standard property
	 */
	private boolean isExpression;

	/**
	 * Create a container for a standard property definition or a expression property definition
	 * 
	 * @param isExpression
	 *          true if it is an expression property, false otherwise
	 * @param name
	 *          The name of the property, must be not null
	 * @param value
	 *          The property value as string
	 */
	public PropertyExpressionDTO(boolean isExpression, String name, String value) {
		super(name, value);
		this.isExpression = isExpression;
	}

	/**
	 * Used to know if the property is a standard one or an expression property.
	 * 
	 * @return false if the property is standard, true if it is an expression property
	 */
	@Override
	public boolean isExpression() {
		return isExpression;
	}

	/**
	 * Set if the current property is a standard property or an expression property
	 * 
	 * @param isExpression
	 *          false if the property is standard, true if it is an expression property
	 */
	public void setExpression(boolean isExpression) {
		this.isExpression = isExpression;
	}

	/**
	 * Clone the current property and return a copy of it
	 * 
	 * @return a not null copy of the current property
	 */
	@Override
	public PropertyExpressionDTO clone() {
		String v = this.getValue();
		PropertyExpressionDTO result = new PropertyExpressionDTO(this.isExpression(), new String(this.getName()),
				v == null ? v : new String(v));
		result.setPnode(getPnode());
		return result;
	}

	@Override
	public String toString() {
		return getValue();
	}
}
