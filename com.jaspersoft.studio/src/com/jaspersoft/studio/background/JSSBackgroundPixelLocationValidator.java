/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background;

import org.eclipse.swt.graphics.Point;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptors.AbstractJSSCellEditorValidator;

/**
 * Validator for the size and position of the background. It check to 
 * avoid to insert a negative size
 * 
 * @author Orlandin Marco
 *
 */
public class JSSBackgroundPixelLocationValidator extends AbstractJSSCellEditorValidator {

	/**
	 * The id of the checked property
	 */
	public String propertyID;
	
	/**
	 * Create the validator
	 * 
	 * @param propertyID The id of the checked property
	 */
	public JSSBackgroundPixelLocationValidator(String propertyID) {
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
			//The check of the size is not done for the background
			/*Object newValue = checkValid(getTarget(), value, propertyID);
			if (newValue != value){
				return MessageFormat.format(Messages.JSSPixelEditorValidator_errorExceed, new Object[]{newValue});
			}*/
		} catch (NumberFormatException exc) {
			return Messages.common_this_is_not_an_integer_number; 
		}
		return null;
	}
	
	/**
	 * Check if the value is a size of an element and if it is negative
	 * 
	 * @param value the value of the property to set
	 * @param propertyId the property to set
	 * @return true if the property is a negative size, false otherwise
	 */
	protected boolean isSizeNegative(Object value, Object propertyId){
		if (MBackgrounImage.PROPERTY_HEIGHT.equals(propertyID)){
			int newHeight = (Integer)value;
			if (newHeight<0) return true;
		} else if (MBackgrounImage.PROPERTY_WIDTH.equals(propertyID)){
			int newWidth = (Integer)value;
			if (newWidth<0) return true;
		}
		return false;
	}
	
	/**
	 * Check if the value for the propertyID is valid on the node. Essentially
	 * it check if the value exceed the page size or it the size is negative.
	 * Currently it is not used for the background, it up to the user select
	 * its size
	 * 
	 * @param node the node
	 * @param value the value of the property to set
	 * @param propertyID the property to set
	 * @return a valid value of the property, can be the same object of 
	 * the parameter value if no changes were needed or a new value
	 */
	protected Object checkValid(ANode node, Object value, Object propertyID){
		if (node == null) return value;
		if (MBackgrounImage.PROPERTY_HEIGHT.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MBackgrounImage background = (MBackgrounImage)node;

			int newHeight = (Integer)value;
			if (newHeight < 0){
				return 0;
			} else if (Math.abs(background.getDefaultY() + newHeight)>maximumSize.y){
				int delta = maximumSize.y - background.getDefaultY();
				return delta;
			} else return value;
		} else if (MBackgrounImage.PROPERTY_WIDTH.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MBackgrounImage background = (MBackgrounImage)node;

			int newWidth = (Integer)value;
			if (newWidth < 0){
				return 0;
			} else if (Math.abs(background.getDefaultX() + newWidth)>maximumSize.x){
				int delta = maximumSize.x - background.getDefaultX();
				return delta;
			} else return value;
		} else if (MBackgrounImage.PROPERTY_X.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MBackgrounImage background = (MBackgrounImage)node;

			int newX = (Integer)value;
			if (Math.abs(background.getDefaultWidth() + newX)>maximumSize.x){
				if (newX >= 0){
					if (Math.abs(background.getDefaultWidth())+newX>maximumSize.x){
						int delta = maximumSize.x - background.getDefaultWidth();
						return delta;
					}
				} else {
					if (Math.abs(newX)>maximumSize.x){
						int delta = -maximumSize.x;
						return delta;
					}
				}
			}
		} else if (MBackgrounImage.PROPERTY_Y.equals(propertyID)){
			Point maximumSize = node.getAvailableSize();
			MBackgrounImage background = (MBackgrounImage)node;

			int newY = (Integer)value;
			if (Math.abs(background.getDefaultHeight() + newY)>maximumSize.y){
				if (newY >= 0){
					if (Math.abs(background.getDefaultHeight())+newY>maximumSize.y){
						int delta = maximumSize.y - background.getDefaultHeight();
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
