/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;

public class DecoratorManager {
	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry().getConfigurationElementsFor(
				JaspersoftStudioPlugin.PLUGIN_ID, "decorators"); //$NON-NLS-1$  
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IElementDecorator){
					if (JaspersoftStudioPlugin.PLUGIN_ID.equalsIgnoreCase(e.getContributor().getName())){
						elementDecorators.add(0, (IElementDecorator)o);
					} else {
						elementDecorators.add((IElementDecorator) o);
					}
				} 
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private List<IElementDecorator> elementDecorators = new ArrayList<IElementDecorator>();

	
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		for (IElementDecorator f : elementDecorators)
			f.setupFigure(fig, editPart);
	}

	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		for (IElementDecorator f : elementDecorators) {
			f.registerActions(registry, selectionActions, gviewer, part);
		}
	}
	
	public List<IElementDecorator> getDecorators(){
		return elementDecorators;
	}

	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		for (IElementDecorator f : elementDecorators)
			f.buildContextMenu(registry, viewer, menu);
	}

	public List<RetargetAction> buildMenuActions() {
		List<RetargetAction> actions = new ArrayList<RetargetAction>();
		for (IElementDecorator f : elementDecorators)
			actions.addAll(Arrays.asList(f.buildMenuActions()));
		return actions;
	}

	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		for (IElementDecorator f : elementDecorators)
			f.contribute2Menu(registry, menuManager);
	}

	public List<String> getActionIDs() {
		List<String> ids = new ArrayList<String>();
		for (IElementDecorator f : elementDecorators) {
			ids.addAll(f.getActionIDs());
		}
		return ids;
	}
}
