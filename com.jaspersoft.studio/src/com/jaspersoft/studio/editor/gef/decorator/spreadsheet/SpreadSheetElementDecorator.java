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

public class SpreadSheetElementDecorator extends ChainableElementDecorator{

	private SpreadsheetDecorator decorator = new SpreadsheetDecorator();
	
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		ChainableDecorator textDecorator = getDecorator(fig);
		textDecorator.removeDecorator(decorator);
		textDecorator.addDecorator(decorator);
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { };
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
	}

	@Override
	public List<String> getActionIDs() {
		return new ArrayList<String>();
	}

	@Override
	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel) {
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {		
	}
}
