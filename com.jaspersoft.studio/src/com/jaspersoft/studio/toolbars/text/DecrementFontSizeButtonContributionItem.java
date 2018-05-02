/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.text;

/**
 * Toolitem to decrease the font size of the selected elements. This implementation simply return a boolean value
 * but it can't be converted to a field of the class initialized upon creation. This because the toolitem
 * are instantiated trough the plugin.xml definition and no parameter or special setting can be done. For this
 * reason for this class we need to provide two different implementation
 * 
 * @author Orlandin Marco
 */
public class DecrementFontSizeButtonContributionItem extends AbstractFontSizeButtonsContributionItem {
	
	@Override
	protected boolean isIncrement() {
		return false;
	}
	
}
