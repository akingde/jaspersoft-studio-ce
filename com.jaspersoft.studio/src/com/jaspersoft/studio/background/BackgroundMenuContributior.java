/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.background;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

import com.jaspersoft.studio.messages.Messages;

/**
 * Provide the menu to show some configuring option for the image
 * 
 * @author Orlandin Marco
 *
 */
public class BackgroundMenuContributior extends ExtensionContributionFactory {

	@Override
	public void createContributionItems(IServiceLocator serviceLocator, IContributionRoot additions) {
    additions.addContributionItem(getBackgroundMenu(), new Expression() {
			
			@Override
			public EvaluationResult evaluate(IEvaluationContext context) throws CoreException {
				return EvaluationResult.TRUE;
			}
		});
	}

	/**
	 * Build the menu with the action to use a background image and return it
	 * 
	 * @return a not null menu manager with all the action inside
	 */
	public static MenuManager getBackgroundMenu(){
	   MenuManager submenu = new MenuManager(Messages.MBackgroundImage_menuLabel, "com.jaspersoft.studio.background.backgroundmenu"); //$NON-NLS-2$
	    IContributionItem dynamicItem = new CompoundContributionItem("org.eclipse.ui.views.problems.groupBy.items") { //$NON-NLS-1$
	        protected IContributionItem[] getContributionItems() {
	          IContributionItem[] list = new IContributionItem[4];
	          list[0] = new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench(), "SelectBackgroundHandler", "com.jaspersoft.studio.background.commands.SelectBackgroundHandler", CommandContributionItem.STYLE_PUSH)); //$NON-NLS-1$ //$NON-NLS-2$
	          list[1] = new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench(), "TransformBackgroundCommand",  "com.jaspersoft.studio.background.commands.TransformBackgroundCommand", CommandContributionItem.STYLE_CHECK)); //$NON-NLS-1$ //$NON-NLS-2$
	          list[2] = new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench(), "ShowBackgroundHandler",  "com.jaspersoft.studio.background.commands.ShowBackgroundHandler", CommandContributionItem.STYLE_CHECK)); //$NON-NLS-1$ //$NON-NLS-2$
	          list[3] = new CommandContributionItem(new CommandContributionItemParameter(PlatformUI.getWorkbench(), "DeleteBackgroundHandler",  "com.jaspersoft.studio.background.commands.DeleteBackgroundHandler", CommandContributionItem.STYLE_PUSH)); //$NON-NLS-1$ //$NON-NLS-2$
	        	return list;
	        }
	    };
	    submenu.add(dynamicItem);
	    return submenu;
	}
}
