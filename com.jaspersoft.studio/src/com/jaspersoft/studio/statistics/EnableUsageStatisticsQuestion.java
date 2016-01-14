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

import org.eclipse.core.runtime.Platform;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.StudioPreferencePage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Action executed the first time eclipse is started to ask to the user if the
 * want to provide the usage statistics
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
			boolean enableUsageStatistic = UIUtils.showConfirmation(Messages.EnableUsageStatisticsQuestion_actionTitle, Messages.EnableUsageStatisticsQuestion_actionMessage);
			JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS, enableUsageStatistic);
		} else {
			JaspersoftStudioPlugin.getInstance().getPreferenceStore().setValue(StudioPreferencePage.JSS_SEND_USAGE_STATISTICS, false);
		}
	}
}
