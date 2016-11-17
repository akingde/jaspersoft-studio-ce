/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;


/**
 * 
 * Simple event to notify a change
 * 
 * @author gtoffoli
 *
 */
public class ChangeEvent {
	
	private Object source;
	
	public ChangeEvent(Object source)
	{
		this.setSource(source);
	}

	/**
	 * @return the source
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Object source) {
		this.source = source;
	}

}
