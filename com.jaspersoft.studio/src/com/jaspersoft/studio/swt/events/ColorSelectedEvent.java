/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.swt.widgets.WColorPicker;
import com.jaspersoft.studio.utils.AlfaRGB;

/**
 * The event that should be notified/received when dealing with color selection widgets, like for example the {@link WColorPicker}.
 * 
 * @author mrabbi
 * 
 * @see ColorSelectionListener
 * @see WColorPicker
 *
 */
public class ColorSelectedEvent extends TypedEvent{

	private static final long serialVersionUID = 1849191962501917969L;
	public AlfaRGB selectedColor = null;

	public ColorSelectedEvent(Widget widget){
		super(widget);
	}
	
}
