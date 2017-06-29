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
