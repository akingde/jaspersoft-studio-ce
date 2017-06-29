/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;
import com.jaspersoft.studio.widgets.map.support.MapSupport;

/**
 * Browser function invoked when a marker is removed from the map.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class RemoveMarker extends GMapEnabledFunction {
	
    public RemoveMarker(Browser browser, String name,
    		JavaMapSupport mapSupport) {
		super(browser, name, mapSupport);
	}

	@Override
    public Object function (Object[] arguments) {
        int markerIdx = ((Double) arguments[0]).intValue();
        removeMarker(markerIdx, getMapSupport());
        return null;
    }
    
    public static void removeMarker(int markerIndex, MapSupport mapSupport) {
    	Assert.isNotNull(mapSupport);
    	Assert.isTrue(markerIndex>=0 && markerIndex<mapSupport.getMarkersNum());
    	mapSupport.removeMarker(markerIndex);
    }
}
