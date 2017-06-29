/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.events;

import com.jaspersoft.studio.swt.widgets.WTextExpression;

/**
 * This listener is meant to be used when dealing with expression widgets, like for example the {@link WTextExpression}.
 *  
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see ItemPropertyModifiedEvent
 * @see WTextExpression
 *
 */
public interface ItemPropertyModifiedListener {
	/**
	 * Invoked when an expression is modified.
	 * 
	 * @param event the modification event
	 */
	void itemModified(ItemPropertyModifiedEvent event);
}
