/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.statistics.heartbeat;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.StudioPreferencePage;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.statistics.VersionCheckResult;

public class Heartbeat {
	
	public static void run() {
		final PropertiesHelper ph = PropertiesHelper.getInstance();
		if (ph.getBoolean(StudioPreferencePage.CHECK_FOR_UPDATE, true)){
			final VersionCheckResult versionCheck = JaspersoftStudioPlugin.getInstance().getUsageManager().checkVersion();
			if (versionCheck.canUpdate()) {
				Display.getDefault().asyncExec(new Runnable() {

					public void run() {
						String version = versionCheck.getServerVersion();
						String optmsg = versionCheck.getOptionalMessage();
						VersionUpdateDialog ud = new VersionUpdateDialog(Display.getDefault().getActiveShell());
						ud.setNewVersion(version);
						ud.setOptionalMessage(optmsg);
						if (ud.open() == Dialog.OK) {
							if (ud.isNotShowAgain()) {
								ph.setBoolean(StudioPreferencePage.CHECK_FOR_UPDATE, false, InstanceScope.SCOPE);
							}
						}
					}
				});
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
	