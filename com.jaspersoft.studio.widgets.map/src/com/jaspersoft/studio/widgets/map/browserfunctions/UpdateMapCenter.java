/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * Browser function invoked when the map center is updated.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class UpdateMapCenter extends GMapEnabledFunction {

	public UpdateMapCenter(Browser browser, String name,
			JavaMapSupport mapSupport) {
		super(browser, name, mapSupport);
	}

	@Override
	public Object function(Object[] arguments) {
		LatLng newPosition = GMapUtils.getPosition(arguments);
		getMapSupport().setMapCenter(newPosition);
		return null;
	}
}
