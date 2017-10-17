/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.decorator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableDecorator;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableElementDecorator;
import com.jaspersoft.studio.editor.gef.decorator.pdf.PDF508ElementDecorator;
import com.jaspersoft.studio.editor.gef.decorator.pdf.ShowPDFTagsAction;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.MReport;

/**
 * Show an additional action on report and table to set the tag Autotag table
 * 
 * @author Orlandin Marco
 *
 */
public class PDF508TableElementDecorator extends ChainableElementDecorator{

	/**
	 * Decorator used for this tag
	 */
	private PDFTableDecorator decorator = new PDFTableDecorator();

	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		ChainableDecorator textDecorator = getDecorator(fig);
		textDecorator.removeDecorator(decorator);
		if (editPart.getjConfig().getPropertyBooleanDef(ShowPDFTagsAction.ID, false)) {
			textDecorator.addDecorator(decorator);
		}
	}

	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {
		IAction action = new PdfActionTable(part, PdfActionTable.TYPE.DEFAULT);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTable(part, PdfActionTable.TYPE.ENABLED);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionTable(part, PdfActionTable.TYPE.DISABLED);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,AbstractVisualEditor part) {
		registerActions(registry, selectionActions, part);
	}

	@Override
	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel) {
		//Check if the menu item of the PDF tags is already present, if it is then add this item to 
		//it otherwise create a new one
		MenuManager submenu = null;
		for(IContributionItem item : menu.getItems()){
			if (item.getId() != null && item.getId().equals(PDF508ElementDecorator.PDF_MENU_ID)){
				submenu = (MenuManager)item;
				break;
			}
		}
		if (submenu == null){
			 submenu = new MenuManager(com.jaspersoft.studio.messages.Messages.PDF508ElementDecorator_Menu_PDF508Tags, 
					 					null, PDF508ElementDecorator.PDF_MENU_ID); //$NON-NLS-1$
		}
		
		MenuManager submenuTable = new MenuManager(com.jaspersoft.studio.components.table.messages.Messages.PDF508TableElementDecorator_0);

		submenu.add(submenuTable);

		IAction action;
		// Adding actions for Heading 1
		action = registry.getAction(PdfActionTable.ID_DEFAULT);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_ENABLED);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_DISABLED);
		submenuTable.add(action);

		menu.add(submenu);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.getFirstElement() instanceof EditPart) {
			EditPart ep = (EditPart) sel.getFirstElement();
			if (!(ep.getModel() instanceof MTable) && !(ep.getModel() instanceof MReport))
				return;
		}
		fillContextMenu(registry, menu, sel);
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
	}

	@Override
	public List<String> getActionIDs() {
		return new ArrayList<String>();
	}
	
	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[]{};
	}
}
