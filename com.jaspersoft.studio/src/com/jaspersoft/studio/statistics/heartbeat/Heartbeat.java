/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics.heartbeat;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

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
		//boolean checkHeartbeat = ph.getBoolean(StudioPreferencePage.CHECK_FOR_UPDATE, true);
		//statistics disabled for server shutdown
		boolean checkHeartbeat = false;
		if (checkHeartbeat) {
			final VersionCheckResult versionCheck = JaspersoftStudioPlugin.getInstance().getUsageManager().checkVersion();
			if (versionCheck.canUpdate()) {
				UIUtils.getDisplay().asyncExec(new Runnable() {

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
