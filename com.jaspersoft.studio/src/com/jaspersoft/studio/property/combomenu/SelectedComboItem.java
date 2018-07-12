/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.combomenu;

/**
 * A ComboItem to represent the element currently selected
 */
public class SelectedComboItem extends ComboItem {

	/**
	 * Flag used to know if the currently selected item was selected from the combo
	 * or typed
	 */
	private boolean isTyped;
	
	public SelectedComboItem(String label, boolean isTyped, Object item, Object value) {
		super(label, true, -1, item, value);
		this.isTyped = isTyped;
	}

	public boolean isTyped() {
		return isTyped;
	}

}
