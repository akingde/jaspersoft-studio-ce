/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.editor.preview.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class ExportMenuAction extends AReportViewerAction implements IMenuCreator {

	private static final ImageDescriptor ICON = JaspersoftStudioPlugin.getImageDescriptor("icons/resources/save.GIF"); //$NON-NLS-1$
	private static final ImageDescriptor DISABLED_ICON = JaspersoftStudioPlugin
			.getImageDescriptor("icons/resources/save.GIF"); //$NON-NLS-1$

	private MenuManager menuManager = new MenuManager();
	private Menu menu;
	private IAction defaultAction;

	/**
	 * @see AReportViewerAction#AbstractReportViewerAction(IReportViewer)
	 */
	public ExportMenuAction(IReportViewer viewer) {
		super(viewer, AS_DROP_DOWN_MENU);

		setText(Messages.ExportMenuAction_title);
		setToolTipText(Messages.ExportMenuAction_tooltip);
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);
		setMenuCreator(this);
	}

	@Override
	protected boolean calculateEnabled() {
		return getReportViewer().hasDocument();
	}

	@Override
	public void run() {
		if (defaultAction != null && defaultAction.isEnabled())
			defaultAction.run();
	}

	public MenuManager getMenuManager() {
		return menuManager;
	}

	@Override
	public void dispose() {
		menuManager.dispose();
	}

	public Menu getMenu(Control parent) {
		if (menu == null)
			menu = menuManager.createContextMenu(parent);
		return menu;
	}

	public Menu getMenu(Menu parent) {
		return null;
	}

	public IAction getDefaultAction() {
		return defaultAction;
	}

	public void setDefaultAction(IAction defaultAction) {
		if (this.defaultAction != null)
			this.defaultAction.setChecked(false);
		this.defaultAction = defaultAction;
		this.defaultAction.setChecked(true);
	}
}
