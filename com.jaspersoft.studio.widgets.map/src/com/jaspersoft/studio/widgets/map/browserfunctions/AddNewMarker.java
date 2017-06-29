/*******************************************************************************
 * Copyright (c) 2014 Massimo Rabbi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Massimo Rabbi <mrabbi@users.sourceforge.net> - initial API and implementation
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
