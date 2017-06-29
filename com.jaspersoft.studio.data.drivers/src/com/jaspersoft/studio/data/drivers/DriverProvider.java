/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.drivers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.osgi.framework.Bundle;

import com.jaspersoft.studio.utils.jasper.IDriverProvider;

public class DriverProvider implements IDriverProvider {

	private static List<URL> urlist;

	@Override
	public URL[] getDriversURL() {
		if (urlist == null) {
			urlist = new ArrayList<URL>();

			Bundle bundle = Activator.getDefault().getBundle();
			Enumeration<URL> urls = bundle.findEntries("lib/", "*.jar", true);
			while (urls.hasMoreElements())
				urlist.add(urls.nextElement());
		}
		return urlist.toArray(new URL[urlist.size()]);
	}

}
