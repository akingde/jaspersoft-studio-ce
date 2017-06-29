/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.combomenu;

import org.eclipse.swt.widgets.Menu;

/**
 * Interface used to declare that a class can provide a menu where will be attached and 
 * help listener 
 * 
 * @author Orlandin Marco
 *
 */
public interface IMenuProvider {
	
	/**
	 * Menu where the help listener will be attached
	 * @return a menu
	 */
	public Menu getMenu();
	
}
