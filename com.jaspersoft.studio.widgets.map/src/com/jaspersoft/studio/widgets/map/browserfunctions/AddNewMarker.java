/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * Browser function invoked when a new marker is added on the map.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class AddNewMarker extends GMapEnabledFunction {
	
    public AddNewMarker (Browser browser, String name, JavaMapSupport mapSupport) {
        super (browser, name, mapSupport);
    }
    
    @Override
    public Object function (Object[] arguments) {
    	Marker newMarker = new Marker(GMapUtils.getMarkerOptions(arguments));
        getMapSupport().addNewMarker(newMarker);
        return null;
    }
}
