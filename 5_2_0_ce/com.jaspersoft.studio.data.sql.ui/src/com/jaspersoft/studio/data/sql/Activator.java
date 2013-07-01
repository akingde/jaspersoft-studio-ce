/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql;

import net.sf.jasperreports.eclipse.AbstractJRUIPlugin;

public class Activator extends AbstractJRUIPlugin {

	public static final String PLUGIN_ID = "com.jaspersoft.studio.data.sql.ui";

	@Override
	public String getPluginID() {
		return PLUGIN_ID;
	}

	private static Activator plugin;

	public Activator() {
		plugin = this;
	}

	public static Activator getDefault() {
		if (plugin == null)
			plugin = new Activator();
		return plugin;
	}
}
