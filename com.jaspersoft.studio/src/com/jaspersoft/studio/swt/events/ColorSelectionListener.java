/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;

import java.util.EventListener;

import com.jaspersoft.studio.swt.widgets.WColorPicker;

/**
 * This listener is meant to be used when dealing with color selection widgets, like for example the {@link WColorPicker}.
 *  
 * @author mrabbi
 * 
 * @see ColorSelectedEvent
 * @see WColorPicker
 *
 */
public interface ColorSelectionListener extends EventListener{
	void changed(ColorSelectedEvent e);
}
