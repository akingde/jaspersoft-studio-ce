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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.jaspersoft.studio.rcp.messages.Messages;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IWorkbenchAction saveAction;

	private IContributionItem reopenEditorsContributionItem;

	// private IWorkbenchAction undoAction;
	// private IWorkbenchAction redoAction;

	private IContributionItem viewsShortlistContributionItem;

	private IWorkbenchAction preferencesAction;

	private IWorkbenchAction introAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);

		reopenEditorsContributionItem = ContributionItemFactory.REOPEN_EDITORS.create(window);

		viewsShortlistContributionItem = ContributionItemFactory.VIEWS_SHORTLIST.create(window);

		preferencesAction = ActionFactory.PREFERENCES.create(window);
		register(preferencesAction);

		introAction = ActionFactory.INTRO.create(window);
		register(introAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {

		MenuManager fileMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_file, IWorkbenchActionConstants.M_FILE);
		menuBar.add(fileMenu);

		fileMenu.add(saveAction);
		fileMenu.add(reopenEditorsContributionItem);

		MenuManager windowMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_window, IWorkbenchActionConstants.M_WINDOW);
		menuBar.add(windowMenu);

		// Window
		windowMenu.add(viewsShortlistContributionItem);
		windowMenu.add(preferencesAction);

		MenuManager helpMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_help, IWorkbenchActionConstants.M_HELP);
		menuBar.add(helpMenu);

		// Help
		helpMenu.add(introAction);
	}

}
