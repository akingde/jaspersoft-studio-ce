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
package com.jaspersoft.studio.rcp.intro;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.ReportDesignPerspective;
import com.jaspersoft.studio.rcp.OpenDocumentEventProcessor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	
	private OpenDocumentEventProcessor openDocProcessor;
	 	
	public ApplicationWorkbenchAdvisor(OpenDocumentEventProcessor openDocProcessor) {
	 	this.openDocProcessor = openDocProcessor;
	}
	
    @Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    
    @Override
	public void initialize(IWorkbenchConfigurer configurer) {
        super.initialize(configurer);
        configurer.setSaveAndRestore(true);
    }

	@Override
	public String getInitialWindowPerspectiveId() {
		return ReportDesignPerspective.ID;//FIXME export this from studio plugin? 
	}
	
	@Override
	public void preStartup() {
		super.preStartup();
		
		IDE.registerAdapters(); 
	}
	
	/**
	  * Added to process SWT.OpenDocument events.
	  * Here we actually process the OpenDocument events.
	  */
	public void eventLoopIdle(Display display) {
		openDocProcessor.openFiles();
		super.eventLoopIdle(display);
	}
}
