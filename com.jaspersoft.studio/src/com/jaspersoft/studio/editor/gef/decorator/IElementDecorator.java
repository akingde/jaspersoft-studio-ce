/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator;

import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * @author slavic
 * 
 */
public interface IElementDecorator {
	
	/**
	 * create RetargetActions that we show in global menu
	 * 
	 * @return
	 */
	public RetargetAction[] buildMenuActions();
	
	/**
	 * register and create all decorator actions in editors ActionRegistry
	 * 
	 * @param registry
	 * @param selectionActions
	 * @param gviewer
	 * @param part
	 */
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer, AbstractVisualEditor part);
	
	/**
	 * Returns the list of Action IDs.
	 * 
	 * @return list of id
	 */
	public List<String> getActionIDs();
	
	/**
	 * add actions to global menu
	 * 
	 * @param registry
	 * @param menuManager
	 */
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager);
	
	/**
	 * decide and add action to context menu, right click on an element
	 * 
	 * @param registry
	 * @param viewer
	 * @param menu
	 */
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu);

	/**
	 * Build a context menu basing it on the passed selection
	 */
	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel);
	
	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part);
	
	/**
	 * method to setup decorator on setup figure in edit part, when figure parameters are changed
	 * 
	 */
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart);
	
	/**
	 * Paint a global decorator at page level, this decorator will be visible only on the main report page
	 * 
	 * @param g graphics of the page, can be used to paint the decorator
	 * @param figure the figure of the page
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 */
	public void paintGlobal(Graphics g, IFigure figure, JasperReportsConfiguration jConfig);

}
