/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.internal;

import org.eclipse.swt.widgets.Control;

/**
 * Interface to implement if a widget can be highlighted by the search widget function
 * 
 * @author Orlandin Marco
 *
 */
public interface IHighlightPropertyWidget {
	
	/**
	 * Highlight the widget, for a specific time
	 * 
	 * @param ms time to wait to have the widget return to normal
	 */
	public void highLightWidget(long ms);
	
	/**
	 * Return the control that will be highlighted
	 */
	public Control getControlToBorder();
}
