/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import org.eclipse.ui.views.properties.IPropertySource;

/**
 * Interface to provide additional methods for the advanced property view
 */
public interface IJSSPropertySource extends IPropertySource {

	/**
	 * Called when a property is reset, this receive the ID and return 
	 * true if the children of the property must be reset to, otherwise
	 * it return false
	 */
	public boolean forcePropertyChildrenReset(Object id);
	
}
