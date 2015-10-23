/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.list.decorator;

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

import com.jaspersoft.studio.components.list.messages.Messages;
import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.gef.decorator.pdf.PDF508ElementDecorator;
import com.jaspersoft.studio.editor.gef.decorator.pdf.ShowPDFTagsAction;
import com.jaspersoft.studio.editor.gef.decorator.text.TextDecorator;
import com.jaspersoft.studio.editor.gef.decorator.text.TextElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Show an additional action on report and table to set the tag of the list item element
 * 
 * @author Orlandin Marco
 *
 */
public class PDF508ListItemElementDecorator extends TextElementDecorator{

	/**
	 * Decorator used for this tag
	 */
	private PDFListItemDecorator decorator = new PDFListItemDecorator();

	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		TextDecorator textDecorator = getDecorator(fig);
		textDecorator.removeDecorator(decorator);
		if (editPart.getjConfig().getPropertyBooleanDef(ShowPDFTagsAction.ID, false)) {
			textDecorator.addDecorator(decorator);
		}
	}

	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {
		IAction action = new PdfActionListItem(part, PdfActionListItem.TYPE.END);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionListItem(part, PdfActionListItem.TYPE.FULL);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionListItem(part, PdfActionListItem.TYPE.START);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionListItem(part, PdfActionListItem.TYPE.NONE);
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
		
		MenuManager submenuTable = new MenuManager(Messages.PDF508ListItemElementDecorator_actionName);

		submenu.add(submenuTable);

		IAction action;
		action = registry.getAction(PdfActionListItem.ID_FULL);
		submenuTable.add(action);
		action = registry.getAction(PdfActionListItem.ID_START);
		submenuTable.add(action);
		action = registry.getAction(PdfActionListItem.ID_END);
		submenuTable.add(action);
		action = registry.getAction(PdfActionListItem.ID_NONE);
		submenuTable.add(action);

		menu.add(submenu);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.getFirstElement() instanceof EditPart) {
			EditPart ep = (EditPart) sel.getFirstElement();
			if (ep.getModel() instanceof MGraphicElement){
				MGraphicElement gElement = (MGraphicElement)ep.getModel();
				if (!(gElement.getParent() instanceof MList)){
					return;
				}
			} else {
				return;
			}
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
