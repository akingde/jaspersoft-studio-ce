/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import java.text.MessageFormat;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.editor.layout.FreeLayout;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Validator for the size and position of an element. It check to 
 * avoid to insert a negative size or a position\size that exceed the
 * available space
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPixelLocationValidator extends AbstractJSSCellEditorValidator {
	
	/**
	 * The id of the checked property
	 */
	public String propertyID;
	
	/**
	 * Create the validator
	 * 
	 * @param propertyID The id of the checked property
	 */
	public JSSPixelLocationValidator(String propertyID) {
		this.propertyID = propertyID;
	}
	
	@Override
	public String isValid(Object value) {
		try {
			if (value == null){
				return Messages.JSSPixelEditorValidator_errorNull;
			}
			if (value instanceof String){
				value = new Integer((String) value);
			}
			if (isSizeNegative(value, propertyID)){
				return Messages.JSSPixelEditorValidator_errorNegative;
			}
				
			//Check the size
			Object newValue = checkValid(getTarget(), value, propertyID);
			if (newValue != value){
				return MessageFormat.format(Messages.JSSPixelEditorValidator_errorExceed, new Object[]{newValue});
			}
			
			//check the layout
			if (!checkLayoutValidity(value)){
				return Messages.JSSPixelLocationValidator_errorLayout;
			}
		} catch (NumberFormatException exc) {
			return Messages.common_this_is_not_an_integer_number; 
		}
		return null;
	}
	
	/**
	 * Check if the new value is valid according to the layout of the parent
	 * 
	 * @param value the new value
	 * @return true if the operation is allowed by the layout, false otherwise
	 */
	private boolean checkLayoutValidity(Object value){
		ANode parent = getTarget().getParent();
		JRDesignElement jrElement = (JRDesignElement)getTarget().getValue();
		Class<? extends ILayout> currentLayout = LayoutManager.getContainerLayout(parent);
		//Avoid further calculation when using the free layout
		if (currentLayout == FreeLayout.class) return true;
		
		int currentHeight = jrElement.getHeight();
		int currentWidth = jrElement.getWidth();
		int currentX = jrElement.getX();
		int currentY = jrElement.getY();
		Rectangle oldBounds = new Rectangle(currentX, currentY, currentWidth, currentHeight);
		
		if (JRDesignElement.PROPERTY_HEIGHT.equals(propertyID)){
			currentHeight = (Integer)value;
		} else if (JRDesignElement.PROPERTY_WIDTH.equals(propertyID)){
			currentWidth= (Integer)value;
		} else if (JRDesignElement.PROPERTY_X.equals(propertyID)){
			currentX = (Integer)value;
		} else if (JRDesignElement.PROPERTY_Y.equals(propertyID)){
			currentY = (Integer)value;
		}
		
		Rectangle newBounds = new Rectangle(currentX, currentY, currentWidth, currentHeight);
		return LayoutManager.getLayout(currentLayout.getName()).allowChildBoundChange(getTarget(), oldBounds, newBounds);
	}
	
	/**
	 * Check if the value is a size of an element and if it is negative
	 * 
	 * @param value the value of the property to set
	 * @param propertyId the property to set
	 * @return true if the property is a negative size, false otherwise
	 */
	protected boolean isSizeNegative(Object value, Object propertyId){
		if (JRDesignElement.PROPERTY_HEIGHT.equals(propertyID)){
			int newHeight = (Integer)value;
			if (newHeight<0) return true;
		} else if (JRDesignElement.PROPERTY_WIDTH.equals(propertyID)){
			int newWidth = (Integer)value;
			if (newWidth<0) return true;
		}
		return false;
	}

	/**
	 * Check if the value for the propertyID is valid on the node. Essentially
	 * it check if the value exceed the page size or it the size is negative
	 * 
	 * @param node the node
	 * @param value the value of the property to set
	 * @param propertyID the property to set
	 * @return a valid value of the property, can be the same object of 
	 * the parameter value if no changes were needed or a new value
	 */
	protected Object checkValid(ANode node, Object value, Object propertyID){
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
