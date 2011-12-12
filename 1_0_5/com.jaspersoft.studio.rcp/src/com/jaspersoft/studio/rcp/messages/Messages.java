/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.rcp.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.jaspersoft.studio.rcp.messages.messages"; //$NON-NLS-1$
	public static String ApplicationActionBarAdvisor_file;
	public static String ApplicationActionBarAdvisor_help;
	public static String ApplicationActionBarAdvisor_window;
	public static String ApplicationWorkbenchWindowAdvisor_jasper_open_studio;
	public static String Startup_jss_project;
	public static String VersionUpdateDialog_0;
	public static String VersionUpdateDialog_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
