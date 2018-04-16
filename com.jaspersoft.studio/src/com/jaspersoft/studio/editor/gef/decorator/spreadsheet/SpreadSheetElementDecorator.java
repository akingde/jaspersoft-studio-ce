/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableDecorator;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.messages.Messages;

public class SpreadSheetElementDecorator extends ChainableElementDecorator{

	private SpreadsheetDecorator decorator = new SpreadsheetDecorator();
	
	private List<String> actionIDs;
	
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		ChainableDecorator textDecorator = getDecorator(fig);
		textDecorator.removeDecorator(decorator);
		if (editPart.getjConfig().getPropertyBooleanDef(ShowSpreadsheetTagsAction.ID, false)) {
			textDecorator.addDecorator(decorator);
		}
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] {new RetargetAction(ShowSpreadsheetTagsAction.ID, Messages.SpreadSheetElementDecorator_actionTitle, IAction.AS_CHECK_BOX) };
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		IAction action = new ShowSpreadsheetTagsAction(gviewer, part.getJrContext());
		registry.registerAction(action);
		registerActions(registry, selectionActions, part);	
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowSpreadsheetTagsAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(1);
			actionIDs.add(ShowSpreadsheetTagsAction.ID);
		}
		return actionIDs;
	}

	@Override
	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel) {
		
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {		

	}
}
