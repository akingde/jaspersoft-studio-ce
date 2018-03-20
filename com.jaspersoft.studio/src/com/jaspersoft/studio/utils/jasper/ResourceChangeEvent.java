/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jasper;

import java.beans.PropertyChangeEvent;

/**
 * Event used to notify to a {@link JasperReportsConfiguration} that the resource 
 * are changed and should be reloaded. It is possible to specify which is the 
 * type of the changed resource, in this way it is possible to reload only
 * that type
 */
public class ResourceChangeEvent extends PropertyChangeEvent {

	private static final long serialVersionUID = 8556756822844222137L;
	
	/**
	 * Key of the event that must be fired on the {@link JasperReportsConfiguration}
	 * to notify that a physical resource not available before was loaded and can be
	 * used. Doing this we can refresh some resources on the editor (ie image &
	 * styles) when new resource are available
	 */
	public static final String RESOURCE_LOADED = "RESOURCE_LOADED";
	
	public enum RESOURCE_TYPE{IMAGE, TEMPLATE_STYLE, CHART_STYLE, FONT, ALL};
	
	private RESOURCE_TYPE type = RESOURCE_TYPE.ALL;
	
	public ResourceChangeEvent(Object source, Object newValue, RESOURCE_TYPE type) {
		super(source, RESOURCE_LOADED, null, newValue);
		this.type = type;
	}
	
	public RESOURCE_TYPE getResourceType() {
		return type;
	}


}
