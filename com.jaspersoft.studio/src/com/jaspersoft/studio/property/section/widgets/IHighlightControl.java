/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.graphics.Color;

/**
 * Interface to declare that a class has the capability to graphically highlight
 * in some way a control
 * 
 * @author Orlandin Marco
 *
 */
public interface IHighlightControl {
	
	/**
	 * highlight the control
	 */
	public void highLightControl();
	
	/**
	 * Restore the highlighted control to its original status
	 */
	public void deHighLightControl();
	
	public void deHighLightControl(Color oldColor);
}
