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
package com.jaspersoft.studio.statistics;

import java.io.File;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;

/**
 * Action executed the first time eclipse is started to ask to the user if the
 * want to provide the usage statistics
 * 
 * @author Orlandin Marco
 *
 */
public class EnableUsageStatisticsQuestion implements IFirstStartupAction {
	
	@Override
	public void executeFirstStartupAction(File configurationDirectory) {
		boolean enableUsageStatistic = UIUtils.showConfirmation(Messages.EnableUsageStatisticsQuestion_actionTitle, Messages.EnableUsageStatisticsQuestion_actionMessage);
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS, enableUsageStatistic);
	}
}
