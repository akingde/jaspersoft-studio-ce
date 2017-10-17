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
 * Browser function invoked when a marker on the map gets a newer position (i.e: while being moved).
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class UpdateMarkerPosition extends GMapEnabledFunction {

    public UpdateMarkerPosition(Browser browser, String name,
    		JavaMapSupport mapSupport) {
		super(browser, name, mapSupport);
	}

	@Override
    public Object function (Object[] arguments) {
		LatLng newPosition = GMapUtils.getPosition(arguments);
        int markerIdx = ((Double) arguments[2]).intValue();
        getMapSupport().updateMarkerPosition(markerIdx, newPosition);
        return null;
    }
}
