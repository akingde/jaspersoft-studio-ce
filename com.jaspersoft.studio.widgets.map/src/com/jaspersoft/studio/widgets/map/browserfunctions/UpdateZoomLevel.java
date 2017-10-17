/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * Browser function invoked when the zoom level is updated.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class UpdateZoomLevel extends GMapEnabledFunction {

	public UpdateZoomLevel(Browser browser, String name,
			JavaMapSupport mapSupport) {
		super(browser, name, mapSupport);
	}

	@Override
	public Object function(Object[] arguments) {
		int newZoomLevel = ((Double)arguments[0]).intValue();
		getMapSupport().setZoomLevel(newZoomLevel);		
		return null;
	}
}
