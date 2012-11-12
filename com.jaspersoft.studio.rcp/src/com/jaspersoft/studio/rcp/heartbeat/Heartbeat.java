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
package com.jaspersoft.studio.rcp.heartbeat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.rcp.Activator;

public class Heartbeat {
	private static final String UUID_PROPERTY;
	public static final String VERSION;
	private static String version;
	private static String optmsg;

	static {
		UUID_PROPERTY = "UUID";
		String ver = "x.x.x - NOT DETECTED"; // $//$NON-NLS-1$
		try{
			// Get JSS version directly from the plugin one:
			// for sure it will be kept in sync with the product one.
			ver = Activator.getDefault().getBundle().getVersion().toString();
		}
		catch(Exception ex){
			// Should never happen...
		}
		finally{
			VERSION = ver;
		}
	}

	public static void run() {
		final PropertiesHelper ph = PropertiesHelper.getInstance();
		String uuid = ph.getString(UUID_PROPERTY, null);
		int newInstallation = 0;
		if (uuid == null || uuid.length() == 0) {
			newInstallation = 1;
			uuid = UUID.randomUUID().toString();
			ph.setString(UUID_PROPERTY, uuid, InstanceScope.SCOPE);
		}

		String urlstr = "http://jasperstudio.sf.net/jsslastversion.php?version="
				+ VERSION + "&uuid=" + uuid + "&new=" + newInstallation;
		System.out.println("Invoking URL: " + urlstr);
		BufferedReader in = null;
		try {
			URL url = new URL(urlstr);
			URLConnection yc = url.openConnection();
			in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

			version = null;
			optmsg = "";
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (version == null)
					version = inputLine.trim();
				else
					optmsg += inputLine;
			}
			if (version != null && version.compareTo(VERSION) > 0) {
				if (ph.getBoolean("show_update_dialog", true)) {
					Display.getDefault().asyncExec(new Runnable() {

						public void run() {
							VersionUpdateDialog ud = new VersionUpdateDialog(
									Display.getDefault().getActiveShell());
							ud.setNewVersion(version);
							ud.setOptionalMessage(optmsg);
							if (ud.open() == Dialog.OK) {
								if (ud.isNotShowAgain()) {
									ph.setBoolean("show_update_dialog", false,
											InstanceScope.SCOPE);
								}
							}
						}
					});
					Thread.sleep(100000);
				}

			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}
