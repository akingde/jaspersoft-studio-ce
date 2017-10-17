/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.browserfunctions;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * A simple function to test the Java and Javascript bidirectional communication.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class TestJavaCallSupport extends GMapEnabledFunction {

	public TestJavaCallSupport(Browser browser, String name, JavaMapSupport mapSupport) {
		super(browser, name, mapSupport);
	}
	
	@Override
	public Object function(Object[] arguments) {
		getMapSupport().getBrowserControl().evaluate("alert('Test message: Java<->Javascript communitcation supported'");//$NON-NLS-1$
		return null;
	}

}
