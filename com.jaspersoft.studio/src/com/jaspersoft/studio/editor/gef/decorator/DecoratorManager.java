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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class DecoratorManager {
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "decorators"); //$NON-NLS-1$  
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IElementDecorator)
					nodeFactory.add((IElementDecorator) o);
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private List<IElementDecorator> nodeFactory = new ArrayList<IElementDecorator>();

	public void setupFigure(ComponentFigure fig, JasperReportsConfiguration jConfig) {
		for (IElementDecorator f : nodeFactory)
			f.setupFigure(fig, jConfig);
	}

	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part) {
		for (IElementDecorator f : nodeFactory)
			f.registerActions(registry, selectionActions, gviewer, part);
	}

	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		for (IElementDecorator f : nodeFactory)
			f.buildContextMenu(registry, viewer, menu);
	}

	public List<RetargetAction> buildMenuActions() {
		List<RetargetAction> actions = new ArrayList<RetargetAction>();
		for (IElementDecorator f : nodeFactory)
			actions.addAll(Arrays.asList(f.buildMenuActions()));
		return actions;
	}

	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		for (IElementDecorator f : nodeFactory)
			f.contribute2Menu(registry, menuManager);
	}
}
