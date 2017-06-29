/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
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
