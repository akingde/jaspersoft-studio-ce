/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.api.common.domain;

import java.util.Date;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * An event saved in the persistent logging service.
 * 
 * <p>
 * Such events are created and persisted when asynchronous actions, such as
 * scheduled reports, result in errors.
 * The errors are persisted as events and which makes them available at later
 * times.
 * </p>
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @author Ionut Nedelcu
 * @author Yuri Plakosh
 * @version $Id: LogEvent.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @see com.jaspersoft.jasperserver.api.engine.common.service.LoggingService
 * @since 1.0
 */
@JasperServerAPI
public interface LogEvent {

	/**
	 * Error event type.
	 * 
	 * @see #getType()
	 */
	byte TYPE_ERROR = 1;

	/**
	 * Warning event type.
	 * 
	 * @see #getType()
	 */
	byte TYPE_WARNING = 2;

	/**
	 * General information event type.
	 * 
	 * @see #getType()
	 */
	byte TYPE_INFO = 3;

	/**
	 * Unread event state.
	 * 
	 * @see #getState()
	 */
	byte STATE_UNREAD = 1;

	/**
	 * Read event state.
	 * 
	 * @see #getState()
	 */
	byte STATE_READ = 2;
	
	/**
	 * Returns the component in which the event occurred, for instance the 
	 * report scheduler.
	 * 
	 * @return the component in which the event occurred
	 */
	String getComponent();

	/**
	 * Specifies in which component the event occurred. 
	 * 
	 * @param component the component that created the event
	 */
	void setComponent(String component);

	/**
	 * Returns binary data associated with the event.
	 * 
	 * <p>
	 * The data can be anything that the component which logged the event
	 * considers appropriate to save as part of the event.
	 * </p>
	 * 
	 * @return binary data saved for the event, <code>null</code> if no data
	 */
	byte[] getData();

	/**
	 * Sets binary data to be saved as part of the report.
	 * 
	 * @param data binary data for this event
	 */
	void setData(byte[] data);

	/**
	 * Returns the internal ID assigned to a persisted event.
	 * 
	 * @return internal ID of the event
	 */
	long getId();

	/**
	 * Sets the internal ID of this event.
	 * 
	 * <p>
	 * This method should not be called by external code, the ID is automatically
	 * assigned when the event is saved by the logging service.
	 * </p>
	 * 
	 * @param id the event ID
	 */
	void setId(long id);

	/**
	 * Returns the localization key of the main message set for this event.
	 * 
	 * @return the message key for this event
	 */
	String getMessageCode();

	/**
	 * Sets the main event message to be used for the event.
	 * 
	 * <p>
	 * The message is specified using a key of a localization message present in
	 * the resource bundles.
	 * No message arguments can be used for the message.
	 * </p>
	 * 
	 * @param messageCode the localization key of a message
	 */
	void setMessageCode(String messageCode);

	/**
	 * Returns the moment at which this event occurred.
	 * 
	 * @return the moment at which this event occurred
	 */
	Date getOccurrenceDate();

	/**
	 * Sets the moment at which the event occurred.
	 * 
	 * @param occurrenceDate the moment at which the event occurred
	 */
	void setOccurrenceDate(Date occurrenceDate);

	/**
	 * Returns the path of a repository resource associated with the event.
	 * 
	 * <p>
	 * For instance, the report scheduler associates the report unit to events
	 * that occur while a report job executes.
	 * </p>
	 * 
	 * @return the repository path of a resource associated with the event
	 */
	String getResourceURI();

	/**
	 * Associates a repository resource with the event.
	 * 
	 * @param resourceURI the repository path of the resource
	 */
	void setResourceURI(String resourceURI);

	/**
	 * Returns the full text that describes the event.
	 * 
	 * @return the description of the event
	 */
	String getText();

	/**
	 * Sets a text description for the event.
	 * 
	 * <p>
	 * The description should contain any information about the event
	 * that should be presented in text form.
	 * </p>
	 * 
	 * @param text the description of the event
	 */
	void setText(String text);

	/**
	 * Returns the type of the event.
	 * 
	 * @return one of the <code>TYPE_*</code> constants
	 */
	byte getType();

	/**
	 * Sets the type of the event.
	 * 
	 * @param type one of the <code>TYPE_*</code> constants
	 */
	void setType(byte type);

	/**
	 * Determines whether the event has been read by a human user after it was
	 * logged.
	 * 
	 * @return the state of the event
	 * @see #STATE_READ
	 * @see #STATE_UNREAD
	 */
	byte getState();

	/**
	 * Changes the read/unread state of the event.
	 * 
	 * @param state one of the <code>STATE_*</code> constants
	 */
	void setState(byte state);

}
