/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.gef.decorator.xls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.action.xls.XLSAction;
import com.jaspersoft.studio.editor.action.xls.XLSActionList;
import com.jaspersoft.studio.editor.gef.decorator.text.TextElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Define the action related to the XLS export, it extends a TextElementDecorator to print the textual 
 * tag on the elements
 * @author Orlandin Marco
 *
 */
public class XLSElementDecorator extends TextElementDecorator {

	/**
	 * The XSL contributor for the text decoration
	 */
	private XLSDecorator decorator = new XLSDecorator();
	
	private List<String> actionIDs;

	/**
	 * Add or remove the XSL contributor from the text element decorator
	 */
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		getDecorator().removeDecorator(decorator);
		if ((Boolean) editPart.getViewer().getProperty(ShowXLSTagsAction.ID)) {
			getDecorator().addDecorator(decorator);
		}
	}
	
	private void registerFit(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {		
		IAction action = new XLSAction(part, XLSAction.FIT_ROW_ID,"true","Fit Row");
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.FIT_COL_ID,"true","Fit Column");
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part, "XLS_Fit_None",new String[]{XLSAction.FIT_ROW_ID, XLSAction.FIT_COL_ID}, new String[]{null, null},"None" );
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	private void registerAutoFilter(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSAction(part, XLSAction.AUTOFILTER_ID.concat("start"), XLSAction.AUTOFILTER_ID,"start","Start");
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.AUTOFILTER_ID.concat("end"), XLSAction.AUTOFILTER_ID,"end","End");
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part,  XLSAction.AUTOFILTER_ID.concat("none"), XLSAction.AUTOFILTER_ID, null, "None");
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	private void registerBreak(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSActionList(part, XLSAction.BREAK_AFTER_ROW_ID, new String[]{XLSAction.BREAK_AFTER_ROW_ID, XLSAction.BREAK_BEFORE_ROW_ID}, 
																						new String[]{"true", null},"Break After Row" );
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part, XLSAction.BREAK_BEFORE_ROW_ID, new String[]{XLSAction.BREAK_AFTER_ROW_ID, XLSAction.BREAK_BEFORE_ROW_ID}, 
																						new String[]{null, "true"},"Break Before Row" );
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part, "XSL_Break_None", new String[]{XLSAction.BREAK_AFTER_ROW_ID, XLSAction.BREAK_BEFORE_ROW_ID}, 
																						new String[]{null, null},"None" );
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	private void registerCellProperties(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new XLSAction(part, XLSAction.CELL_HIDDEN_ID, XLSAction.CELL_HIDDEN_ID, "true","Cell Hidden");
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.CELL_LOCKED_ID, XLSAction.CELL_LOCKED_ID, "true","Cell Locked");
		
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSActionList(part, "XSL_Cell_None", new String[]{XLSAction.CELL_HIDDEN_ID, XLSAction.CELL_LOCKED_ID}, 
																						new String[]{null, null},"None" );
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	private void registerFreezeProperties(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		
		IAction action = new XLSAction(part, XLSAction.FREEZE_COL_ID.concat("Left"), XLSAction.FREEZE_COL_ID, "Left","Left");
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new XLSAction(part, XLSAction.FREEZE_COL_ID.concat("Right"), XLSAction.FREEZE_COL_ID, "Right","Right");
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new XLSAction(part, XLSAction.FREEZE_COL_ID.concat("None"), XLSAction.FREEZE_COL_ID, null, "None");
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new XLSAction(part, XLSAction.FREEZE_ROW_ID.concat("Top"), XLSAction.FREEZE_ROW_ID, "Top","Top");
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new XLSAction(part, XLSAction.FREEZE_ROW_ID.concat("Bottom"), XLSAction.FREEZE_ROW_ID, "Bottom","Bottom");
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new XLSAction(part, XLSAction.FREEZE_ROW_ID.concat("None"), XLSAction.FREEZE_ROW_ID, null, "None");
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part) {
		gviewer.setProperty(ShowXLSTagsAction.ID, true);
		IAction action = new ShowXLSTagsAction(gviewer);
		registry.registerAction(action);
		registerFit(registry, part, selectionActions);
		registerAutoFilter(registry, part, selectionActions);
		registerBreak(registry, part, selectionActions);
		registerCellProperties(registry, part, selectionActions);
		registerFreezeProperties(registry, part, selectionActions);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.getFirstElement() instanceof EditPart) {
			EditPart ep = (EditPart) sel.getFirstElement();
			if (!(ep.getModel() instanceof MGraphicElement))
				return;
		}
		MenuManager submenu = new MenuManager("XLS Tags");
		MenuManager fitMenu = new MenuManager("Fit");
		MenuManager autoFilterMenu = new MenuManager("Autofilter");
		MenuManager breakMenu = new MenuManager("Break");
		MenuManager freezeMenu = new MenuManager("Freeze");
		MenuManager freezeRowMenu = new MenuManager("Rows");
		MenuManager freezeColMenu = new MenuManager("Columns");
		MenuManager propertiesMenu = new MenuManager("Cell Properties");
		
		submenu.add(fitMenu);
		submenu.add(autoFilterMenu);
		submenu.add(breakMenu);
		submenu.add(propertiesMenu);
		freezeMenu.add(freezeRowMenu);
		freezeMenu.add(freezeColMenu);
		submenu.add(freezeMenu);

		IAction action;
		// Adding actions for the Fit
		action = registry.getAction(XLSAction.FIT_ROW_ID);
		fitMenu.add(action);
		action = registry.getAction(XLSAction.FIT_COL_ID);
		fitMenu.add(action);
		action = registry.getAction("XLS_Fit_None");
		fitMenu.add(action);
		
		//Adding actions for the autofilter
		action = registry.getAction(XLSAction.AUTOFILTER_ID.concat("start"));
		autoFilterMenu.add(action);
		action = registry.getAction(XLSAction.AUTOFILTER_ID.concat("end"));
		autoFilterMenu.add(action);
		action = registry.getAction(XLSAction.AUTOFILTER_ID.concat("none"));
		autoFilterMenu.add(action);
		
		//Adding actions for the break
		action = registry.getAction(XLSAction.BREAK_BEFORE_ROW_ID);
		breakMenu.add(action);
		action = registry.getAction(XLSAction.BREAK_AFTER_ROW_ID);
		breakMenu.add(action);
		action = registry.getAction("XSL_Break_None");
		breakMenu.add(action);
		
		//Adding actions for the cell properties
		action = registry.getAction(XLSAction.CELL_HIDDEN_ID);
		propertiesMenu.add(action);
		action = registry.getAction(XLSAction.CELL_LOCKED_ID);
		propertiesMenu.add(action);
		action = registry.getAction("XSL_Cell_None");
		propertiesMenu.add(action);
		
		//Adding the freeze properties
		action = registry.getAction(XLSAction.FREEZE_ROW_ID.concat("Top"));
		freezeRowMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_ROW_ID.concat("Bottom"));
		freezeRowMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_ROW_ID.concat("None"));
		freezeRowMenu.add(action);
		
		action = registry.getAction(XLSAction.FREEZE_COL_ID.concat("Left"));
		freezeColMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_COL_ID.concat("Right"));
		freezeColMenu.add(action);
		action = registry.getAction(XLSAction.FREEZE_COL_ID.concat("None"));
		freezeColMenu.add(action);

		menu.add(submenu);
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowXLSTagsAction.ID, "Show XLS Tags", IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowXLSTagsAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(1);
			actionIDs.add(ShowXLSTagsAction.ID);
		}
		return actionIDs;
	}

}
