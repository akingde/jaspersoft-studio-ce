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
package com.jaspersoft.studio.editor.gef.decorator;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * @author slavic
 * 
 */
public interface IElementDecorator {
	/**
	 * method to setup decorator on setup figure in edit part, when figure parameters are changed
	 * 
	 * @param fig
	 * @param jConfig
	 */
	public void setupFigure(ComponentFigure fig, JasperReportsConfiguration jConfig);

	/**
	 * register and create all decorator actions in editors ActionRegistry
	 * 
	 * @param registry
	 * @param selectionActions
	 * @param gviewer
	 * @param part
	 */
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part);

	/**
	 * decide and add action to context menu, right click on an element
	 * 
	 * @param registry
	 * @param viewer
	 * @param menu
	 */
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu);

	/**
	 * create RetargetActions that we show in global menu
	 * 
	 * @return
	 */
	public RetargetAction[] buildMenuActions();

	/**
	 * add actions to global menu
	 * 
	 * @param registry
	 * @param menuManager
	 */
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager);
}
