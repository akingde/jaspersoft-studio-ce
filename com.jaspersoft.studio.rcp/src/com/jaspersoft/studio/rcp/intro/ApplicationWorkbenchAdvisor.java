/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.rcp.intro;

import java.net.URL;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.ide.IDEInternalWorkbenchImages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.osgi.framework.Bundle;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.ReportDesignPerspective;
import com.jaspersoft.studio.rcp.Activator;
import com.jaspersoft.studio.rcp.OpenDocumentEventProcessor;
import com.jaspersoft.studio.rcp.p2.P2Util;
import com.jaspersoft.studio.utils.BrandingInfo;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private OpenDocumentEventProcessor openDocProcessor;

	public ApplicationWorkbenchAdvisor(
			OpenDocumentEventProcessor openDocProcessor) {
		this.openDocProcessor = openDocProcessor;
	}

	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	@Override
	public void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);

		final String ICONS_PATH = "icons/full/";
		final String PATH_OBJECT = ICONS_PATH + "obj16/";
		Bundle ideBundle = Platform.getBundle(IDEWorkbenchPlugin.IDE_WORKBENCH);
		declareWorkbenchImage(configurer, ideBundle,
				IDE.SharedImages.IMG_OBJ_PROJECT, PATH_OBJECT + "prj_obj.gif",
				true);
		declareWorkbenchImage(configurer, ideBundle,
				IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, PATH_OBJECT
						+ "cprj_obj.gif", true);
		declareWorkbenchImage(configurer, ideBundle,
				IDEInternalWorkbenchImages.IMG_DLGBAN_SAVEAS_DLG, PATH_OBJECT
						+ "saveas_wiz.png", false);
		declareWorkbenchImage(configurer, ideBundle,
				IDE.SharedImages.IMG_OBJ_PROJECT, PATH_OBJECT + "prj_obj.gif",
				true);
		declareWorkbenchImage(configurer, ideBundle,
				IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, PATH_OBJECT
						+ "cprj_obj.gif", true);

		// Force the default setting for the help (tray) button in dialogs.
		// It seems that in Windows and Linux platforms as default this value is set to true.
		// However in Mac OS X the button does not shown as default behavior.
		TrayDialog.setDialogHelpAvailable(true);
		
		// Sets the branding information
		BrandingInfo info = new BrandingInfo();
		info.setProductName("Jaspersoft Studio Community Edition");
		info.setProductVersion(Activator.getDefault().getBundle().getVersion().toString());
		info.setProductMainBundleID(Activator.PLUGIN_ID);
		JaspersoftStudioPlugin.getInstance().setBrandingInformation(info);
	}

	private void declareWorkbenchImage(IWorkbenchConfigurer configurer_p,
			Bundle ideBundle, String symbolicName, String path, boolean shared) {
		URL url = ideBundle.getEntry(path);
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		configurer_p.declareImage(symbolicName, desc, shared);
	}

	@Override
	public IAdaptable getDefaultPageInput() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return workspace.getRoot();
	}

	@Override
	public String getInitialWindowPerspectiveId() {
		return ReportDesignPerspective.ID;// FIXME export this from studio
											// plugin?
	}

	@Override
	public void preStartup() {
		super.preStartup();
		IDE.registerAdapters();
		setRepositories();
	}
	
	/**
	 * Sets the list of default repositories that will be
	 * used for the JSS product update. 
	 */
	protected void setRepositories(){
		P2Util.setRepositories();		
	}

	/**
	 * Added to process SWT.OpenDocument events. Here we actually process the
	 * OpenDocument events.
	 */
	@Override
	public void eventLoopIdle(Display display) {
		openDocProcessor.openFiles();
		super.eventLoopIdle(display);
	}
}
