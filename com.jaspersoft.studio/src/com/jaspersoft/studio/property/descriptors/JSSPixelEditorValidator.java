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
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Validator for the size and position of an element. It check to 
 * avoid to insert a negative size or a position\size that exceed the
 * available space
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPixelEditorValidator extends AbstractJSSCellEditorValidator {
	
	/**
	 * The id of the checked property
	 */
	public String propertyID;
	
	/**
	 * Create the validator
	 * 
	 * @param propertyID The id of the checked property
	 */
	public JSSPixelEditorValidator(String propertyID) {
		this.propertyID = propertyID;
	}
	
	@Override
	public String isValid(Object value) {
		try {
			if (value instanceof Integer)
				return null;
			if (value instanceof String)
				value = new Integer((String) value);
		} catch (NumberFormatException exc) {
			return Messages.common_this_is_not_an_integer_number; 
		}
		Object newValue = checkValid(getTarget(), value, propertyID);
		if (newValue != value){
			return "The input value is not valid, the used value will be "+newValue;
		}
		return null;
	}

	/**
	 * Check if the value for the propertyID is valid on the node
	 * 
	 * @param node the node
	 * @param value the value of the property to set
	 * @param propertyID the property to set
	 * @return a valid value of the property, can be the same object of 
	 * the parameter value if no changes were needed or a new value
	 */
	public Object checkValid(ANode node, Object value, Object propertyID){
		if (node == null) return value;
		if (JRDesignElement.PROPERTY_HEIGHT.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MGraphicElement gElement = (MGraphicElement)node;
			JRDesignElement jrElement = (JRDesignElement)gElement.getValue();

			int newHeight = (Integer)value;
			if (newHeight < 0){
				return 0;
			} else if (Math.abs(jrElement.getY() + newHeight)>maximumSize.y){
				int delta = maximumSize.y - jrElement.getY();
				return delta;
			} else return value;
		} else if (JRDesignElement.PROPERTY_WIDTH.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MGraphicElement gElement = (MGraphicElement)node;
			JRDesignElement jrElement = (JRDesignElement)gElement.getValue();

			int newWidth = (Integer)value;
			if (newWidth < 0){
				return 0;
			} else if (Math.abs(jrElement.getX() + newWidth)>maximumSize.x){
				int delta = maximumSize.x - jrElement.getX();
				return delta;
			} else return value;
		} else if (JRDesignElement.PROPERTY_X.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MGraphicElement gElement = (MGraphicElement)node;
			JRDesignElement jrElement = (JRDesignElement)gElement.getValue();

			int newX = (Integer)value;
			if (Math.abs(jrElement.getWidth() + newX)>maximumSize.x){
				if (newX >= 0){
					if (Math.abs(jrElement.getWidth())+newX>maximumSize.x){
						int delta = maximumSize.x - jrElement.getWidth();
						return delta;
					}
				} else {
					if (Math.abs(newX)>maximumSize.x){
						int delta = -maximumSize.x;
						return delta;
					}
				}
			}
		} else if (JRDesignElement.PROPERTY_Y.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MGraphicElement gElement = (MGraphicElement)node;
			JRDesignElement jrElement = (JRDesignElement)gElement.getValue();

			int newY = (Integer)value;
			if (Math.abs(jrElement.getHeight() + newY)>maximumSize.y){
				if (newY >= 0){
					if (Math.abs(jrElement.getHeight())+newY>maximumSize.y){
						int delta = maximumSize.y - jrElement.getHeight();
						return delta;
					}
				} else {
					if (Math.abs(newY)>maximumSize.y){
						int delta = -maximumSize.y;
						return delta;
					}
				}
			}
		}
		return value;
	}
}
