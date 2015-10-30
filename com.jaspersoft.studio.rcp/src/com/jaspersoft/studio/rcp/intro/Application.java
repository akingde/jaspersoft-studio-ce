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
package com.jaspersoft.studio.rcp.intro;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.rcp.Activator;
import com.jaspersoft.studio.rcp.OpenDocumentEventProcessor;
import com.jaspersoft.studio.rcp.messages.Messages;
import com.jaspersoft.studio.rcp.workspace.PickWorkspaceDialog;
import com.jaspersoft.studio.rcp.workspace.WorkspaceUtils;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

    private static final String PROP_EXIT_CODE = "eclipse.exitcode"; //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) throws Exception{
		OpenDocumentEventProcessor openDocProcessor = new OpenDocumentEventProcessor();
		//org.eclipse.ui.internal.misc.Policy.DEBUG_SWT_GRAPHICS = true;
		//org.eclipse.ui.internal.misc.Policy.DEBUG_SWT_DEBUG = true;
		Display display = PlatformUI.createDisplay();
		display.addListener(SWT.OpenDocument, openDocProcessor);
		
		try {
			Location instanceLoc = Platform.getInstanceLocation();
			
			if(instanceLoc == null) {
				// FIXME - raise exception?!
			}
			else {
				if(!instanceLoc.isSet()) {
					// Location is not set let's proceed using the default one
					instanceLoc.set(new URL("file", null, PickWorkspaceDialog.getLastSetWorkspaceDirectory()), false); //$NON-NLS-1$
				}
				// Checks if workspace exists
				boolean workspaceExists = WorkspaceUtils.checkWorkspaceExists(instanceLoc);
				if(workspaceExists) {
					boolean isLegacyWS = WorkspaceUtils.isLegacyWorkspace(instanceLoc);
					if(isLegacyWS){
						MessageDialog warningDialog = WorkspaceUtils.getLegacyWorkspaceWarningDialog(
								FileLocator.toFileURL(instanceLoc.getURL()).getPath());
						if(warningDialog.open()==Window.OK) {
							// Show chooser dialog
				            PickWorkspaceDialog pwd = new PickWorkspaceDialog(false,Activator.getDefault().getImage("icons/jss_icon_64.png"),true);  //$NON-NLS-1$
				            int pick = pwd.open(); 
				 
				            // if the user cancelled, we can't do anything as we need a workspace, so in this case, we tell them and exit 
				            if (pick == Window.CANCEL) { 
					            if (pwd.getSelectedWorkspaceLocation()  == null) { 
					                MessageDialog.openError(display.getActiveShell(), Messages.Application_ErrorTitle, 
					                    Messages.Application_WorkspaceErrorMsg); 
					                try { 
					                	PlatformUI.getWorkbench().close(); 
					                } catch (Exception err) { 
					 
					                } 
					                System.exit(0); 
					                return IApplication.EXIT_OK; 
					            } 
				            } 
				            else { 
				            	// Let's restart since the new location has been chosen
				            	// and we can not set twice the location once already set.
				            	return IApplication.EXIT_RESTART;
				            } 
						}
					}
				}
			}
			
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor(openDocProcessor));
		    if (returnCode !=  PlatformUI.RETURN_RESTART) return EXIT_OK;
		    return EXIT_RELAUNCH.equals(Integer.getInteger(PROP_EXIT_CODE)) ? EXIT_RELAUNCH : EXIT_RESTART;
		} finally {
			display.dispose();
		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
