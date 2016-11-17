/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.events;
 
import org.eclipse.swt.events.TypedEvent;

import com.jaspersoft.studio.widgets.framework.WItemProperty;

import net.sf.jasperreports.engine.JRExpression;

/**
 * Event launched by a {@link WItemProperty} when an value or an expression is
 * set
 */
public class ItemPropertyModifiedEvent extends TypedEvent {

	private static final long serialVersionUID = -8192762845793694496L;

	/**
	 * The static value set by the property change, can be null
	 */
	public String staticValue;
	
	/**
	 * The value of the expression set by the property change, can be null
	 */
	public JRExpression expressionValue;
	
	/**
	 * The name of the property that changed value
	 */
	public String propertyName;
	
	/**
	 * The item property who generated the event
	 */
	public WItemProperty source;

	/**
	 * Crate the event
	 * 
	 * @param itemPropertyWidget the source of the property change, must be not null
	 */
	public ItemPropertyModifiedEvent(WItemProperty itemPropertyWidget) {
		super(itemPropertyWidget);
		source = itemPropertyWidget;
	}

}
