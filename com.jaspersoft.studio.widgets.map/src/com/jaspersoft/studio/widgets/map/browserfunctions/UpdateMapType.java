/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * Browser function invoked when the type of the map is changed.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class UpdateMapType extends GMapEnabledFunction {

	public UpdateMapType(Browser browser, String name, JavaMapSupport mapSupport) {
		super(browser, name, mapSupport);
	}
	
	@Override
    public Object function (Object[] arguments) {
		MapType newMapType = MapType.fromStringID((String) arguments[0]);
        getMapSupport().setMapType(newMapType);
        return null;
    }

}
