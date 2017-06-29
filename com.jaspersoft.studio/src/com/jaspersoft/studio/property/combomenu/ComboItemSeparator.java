/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.combomenu;

/**
 * Item for a combo popup that represent a separator into the menu
 * @author Orlandin Marco
 *
 */
public class ComboItemSeparator extends ComboItem {

	/**
	 * Position of the separator, since the list is ordered this field is necessary
	 * to put the separator in the right position.
	 * @param order
	 */
	public ComboItemSeparator(int order){
		super(null, true, order, null, null);
	}
	
	/**
	 * Return always true because this element represent a separator
	 */
	@Override
	public boolean isSeparator() {
		return true;
	}
}
