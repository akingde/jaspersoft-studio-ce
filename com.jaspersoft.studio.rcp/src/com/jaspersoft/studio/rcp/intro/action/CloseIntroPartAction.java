/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp.intro.action;

import java.util.Properties;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;
import org.eclipse.ui.intro.config.IIntroAction;

public class CloseIntroPartAction implements IIntroAction {

	public void run(IIntroSite site, Properties params) {
		final IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro(); 
        PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);  
    }

}
