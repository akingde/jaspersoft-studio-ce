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

import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.jaspersoft.studio.rcp.messages.Messages;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public ApplicationWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new ApplicationActionBarAdvisor(configurer);
	}

	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(700, 550));
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		configurer.setShowFastViewBars(true);
		configurer.setShowMenuBar(true);
		configurer.setShowProgressIndicator(true);
		configurer.setShowPerspectiveBar(true);
		// Try to get the title from the Product name
		IProduct product = Platform.getProduct();
		if(product!=null && product.getName()!=null){
			configurer.setTitle(product.getName());
		}
		else{
			// Fallback solution
			configurer.setTitle(
					Messages.ApplicationWorkbenchWindowAdvisor_jasper_open_studio);
		}
		
		IPartService service = (IPartService) configurer.getWindow().getService(IPartService.class);
    	service.addPartListener(new IPartListener() {

			public void partActivated(IWorkbenchPart part) {
			}

			public void partBroughtToTop(IWorkbenchPart part) {
			}

			public void partClosed(IWorkbenchPart part) {
			}

			public void partDeactivated(IWorkbenchPart part) {
			try {
				String name = part.getSite().getId();

				if ("org.eclipse.ui.internal.introview".equals(name)
						&& part != null && part instanceof IViewPart) {
					final IViewPart thePart = (IViewPart) part;
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							IWorkbenchWindow activeWorkbenchWindow = PlatformUI
									.getWorkbench().getActiveWorkbenchWindow();
							if (activeWorkbenchWindow != null) {
								IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
								if(activePage != null)
								activePage.hideView(thePart);
							}
						}
					});
				}
			} catch (Exception ex)
			{
			ex.printStackTrace();	
			}
			}

			public void partOpened(IWorkbenchPart part) {
			}
    	});
	}
	
	
}
