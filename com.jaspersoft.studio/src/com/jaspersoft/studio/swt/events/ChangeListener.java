/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;


/**
 * A  listener to listen to change events.
 * 
 * @author gtoffoli
 *
 */
public interface ChangeListener {

	/**
	 * The method invoked when a change occurs.
	 * 
	 * @param event
	 */
	public void changed(ChangeEvent event);
	
}
