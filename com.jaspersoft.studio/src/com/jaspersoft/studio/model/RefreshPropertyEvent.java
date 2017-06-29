/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.beans.PropertyChangeEvent;
import java.util.HashSet;

import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;

/**
 * Event used to trigger a graphical refresh, it offers the structure to keep track
 * of which node were refreshed as side effect of another refresh, to avoid to
 * refresh more time the same nodes as the event propagates
 * 
 * @author Orlandin Marco
 *
 */
public class RefreshPropertyEvent extends PropertyChangeEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3554451514858603281L;
	
	/**
	 * {@link HashSet} of the {@link JRChangeEventsSupport} on which this event is going
	 * to be fired
	 */
	private HashSet<JRChangeEventsSupport> eventFireNodes = new HashSet<JRChangeEventsSupport>();
	
	/**
	 * Create the {@link RefreshPropertyEvent}, this has always as property source the value
	 * MGraphicElement.FORCE_GRAPHICAL_REFRESH
	 * 
	 * @param source the source of the event
	 */
	public RefreshPropertyEvent(Object source) {
		super(source, MGraphicElement.FORCE_GRAPHICAL_REFRESH, null, null);
	}
	
	/**
	 * Check if the passed {@link JRChangeEventsSupport} has already received
	 * this event
	 * 
	 * @param node a not null {@link JRChangeEventsSupport}
	 * @return true if the node has already received the event, false otherwise
	 */
	public boolean hasElementTriggeredEvent(JRChangeEventsSupport node){
		return eventFireNodes.contains(node);
	}

	/**
	 * Store the passed {@link JRChangeEventsSupport} to mark it has already received
	 * this event
	 * 
	 * @param node a not null {@link JRChangeEventsSupport}
	 */
	public void setElementTriggeredEvent(JRChangeEventsSupport node){
		eventFireNodes.add(node);
	}
}
