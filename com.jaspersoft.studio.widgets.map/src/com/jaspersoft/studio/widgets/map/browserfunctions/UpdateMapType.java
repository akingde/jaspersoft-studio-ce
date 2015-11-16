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
