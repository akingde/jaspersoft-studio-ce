/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.statistics;

import java.io.File;

import org.eclipse.core.runtime.Platform;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.preferences.StudioPreferencePage;

/**
 * Action executed the first time eclipse is started to ask to the user if the
 * want to provide the usage statistics. Actually the statistics are disabled
 * by default for a server shutdown
 * 
 * @author Orlandin Marco
 *
 */
public class EnableUsageStatisticsQuestion implements IFirstStartupAction {
	
	/**
	 * Property that can be used to skip the statistics question
	 */
	public final String skipQuestionProperty = "-com.jaspersoft.studio.skipUsageQuestion";
	
	/**
	 * Return the value of the flag to skip the question, if the flag is not found the question
	 * is not skipped
	 * 
	 * @return true if the question to enable statistics should be skipped, false otherwise
	 */
	protected boolean getFlagValue(){
		String[] args = Platform.getApplicationArgs();
		for(int i=0; i < args.length; i++){
			String arg = args[i];
			if (skipQuestionProperty.equals(arg) && i < (args.length - 1)){
				return Boolean.parseBoolean(args[i + 1]);
			}
		}
		return false;
	}
	
	@Override
	public void executeFirstStartupAction(File configurationDirectory) {
		boolean value = getFlagValue();
		if (!value){
			//boolean enableUsageStatistic = UIUtils.showConfirmation(Messages.EnableUsageStatisticsQuestion_actionTitle, Messages.EnableUsageStatisticsQuestion_actionMessage);
			//statistics disabled for server shutdown
			boolean enableUsageStatistic = false;
			JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS, enableUsageStatistic);
		} else {
			JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS, false);
		}
	}
}
