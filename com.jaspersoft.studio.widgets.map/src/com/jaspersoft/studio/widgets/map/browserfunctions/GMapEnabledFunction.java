/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * A browser function that maintains a reference to the map support
 * component (Java based). This because request coming from Javascript
 * invocation should reflect on Java components, most of the times UI ones.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public abstract class GMapEnabledFunction extends BrowserFunction {

	private JavaMapSupport mapSupport;
	private String jsName;
	
	public GMapEnabledFunction(Browser browser, String name, JavaMapSupport mapSupport) {
		super(browser, name);
		this.mapSupport = mapSupport;
		this.jsName = name;
	}
	
	public JavaMapSupport getMapSupport() {
		return mapSupport;
	}
	
	public String getJavascriptName(){
		return jsName;
	}
}
