/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.pdf;

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

import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading1;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading2;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading3;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading4;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading5;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading6;
import com.jaspersoft.studio.editor.action.pdf.PdfActionList;
import com.jaspersoft.studio.editor.action.pdf.PdfActionListItem;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTable;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTableDetail;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTableHeader;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTableRow;
import com.jaspersoft.studio.editor.action.pdf.Position;
import com.jaspersoft.studio.editor.gef.decorator.text.TextDecorator;
import com.jaspersoft.studio.editor.gef.decorator.text.TextElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;

public class PDF508ElementDecorator extends TextElementDecorator{

	private PDFDecorator decorator = new PDFDecorator();
	
	private List<String> actionIDs;

	public static final String PDF_MENU_ID = "com.jaspersoft.studio.editor.gef.decorator.pdf.menu"; //$NON-NLS-1$
	
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		TextDecorator textDecorator = getDecorator(fig);
		textDecorator.removeDecorator(decorator);
		if (editPart.getjConfig().getPropertyBooleanDef(ShowPDFTagsAction.ID, false)) {
			textDecorator.addDecorator(decorator);
		}
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowPDFTagsAction.ID, Messages.ShowPDFTagsAction_title,
				IAction.AS_CHECK_BOX) };
	}

	private void registerHeading(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new PdfActionHeading1(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading1(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading1(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading1(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading2(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading2(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading2(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading2(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading3(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading3(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading3(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionHeading3(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading4(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading4(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading4(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading4(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading5(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading5(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading5(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading5(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading6(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading6(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading6(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionHeading6(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerTable(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new PdfActionTable(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTable(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTable(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTable(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerTableRow(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new PdfActionTableRow(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableRow(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableRow(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableRow(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerTableDetail(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new PdfActionTableDetail(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableDetail(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableDetail(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableDetail(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	private void registerTableHeader(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new PdfActionTableHeader(part, Position.Full);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableHeader(part, Position.Start);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableHeader(part, Position.End);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionTableHeader(part, Position.None);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	public void registerListItemActions(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
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

	public void registerListActions(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions) {
		IAction action = new PdfActionList(part, PdfActionList.TYPE.END);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new PdfActionList(part, PdfActionList.TYPE.FULL);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionList(part, PdfActionList.TYPE.START);
		registry.registerAction(action);
		selectionActions.add(action.getId());
		
		action = new PdfActionList(part, PdfActionList.TYPE.NONE);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}
	
	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {
		registerHeading(registry, part, selectionActions);
		registerTable(registry, part, selectionActions);
		registerTableRow(registry, part, selectionActions);
		registerTableHeader(registry, part, selectionActions);
		registerTableDetail(registry, part, selectionActions);
		registerListActions(registry, part, selectionActions);
		registerListItemActions(registry, part, selectionActions);
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		gviewer.setProperty(ShowPDFTagsAction.ID, true);
		IAction action = new ShowPDFTagsAction(gviewer, part.getJrContext());
		registry.registerAction(action);
		registerActions(registry, selectionActions, part);
	}

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
			 submenu = new MenuManager(Messages.PDF508ElementDecorator_Menu_PDF508Tags, null, PDF508ElementDecorator.PDF_MENU_ID);
		}

		MenuManager submenuHeading = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading);
		MenuManager submenuHeading1 = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading1);
		MenuManager submenuHeading2 = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading2);
		MenuManager submenuHeading3 = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading3);
		MenuManager submenuHeading4 = new MenuManager(Messages.PDF508ElementDecorator_Heading4);
		MenuManager submenuHeading5 = new MenuManager(Messages.PDF508ElementDecorator_Heading5);
		MenuManager submenuHeading6 = new MenuManager(Messages.PDF508ElementDecorator_Heading6);
		MenuManager submenuTable = new MenuManager(Messages.PDF508ElementDecorator_Menu_Table);
		MenuManager submenuTableRow = new MenuManager(Messages.PDF508ElementDecorator_Menu_TableRow);
		MenuManager submenuTableHeader = new MenuManager(Messages.PDF508ElementDecorator_Menu_TableHeader);
		MenuManager submenuTableDetails = new MenuManager(Messages.PDF508ElementDecorator_Menu_TableDetails);
		MenuManager submenuList = new MenuManager(Messages.PDF508ElementDecorator_listLabel);
		MenuManager submenuListItem = new MenuManager(Messages.PDF508ElementDecorator_listItemLabel);

		submenuHeading.add(submenuHeading1);
		submenuHeading.add(submenuHeading2);
		submenuHeading.add(submenuHeading3);
		submenuHeading.add(submenuHeading4);
		submenuHeading.add(submenuHeading5);
		submenuHeading.add(submenuHeading6);
		submenu.add(submenuHeading);
		submenu.add(submenuTable);
		submenu.add(submenuTableRow);
		submenu.add(submenuTableHeader);
		submenu.add(submenuTableDetails);
		submenu.add(submenuList);
		submenu.add(submenuListItem);

		IAction action;
		// Adding actions for Heading 1
		action = registry.getAction(PdfActionHeading1.ID_Heading1_Full);
		submenuHeading1.add(action);
		action = registry.getAction(PdfActionHeading1.ID_Heading1_Start);
		submenuHeading1.add(action);
		action = registry.getAction(PdfActionHeading1.ID_Heading1_End);
		submenuHeading1.add(action);
		action = registry.getAction(PdfActionHeading1.ID_Heading1_None);
		submenuHeading1.add(action);

		// Adding actions for Heading 2
		action = registry.getAction(PdfActionHeading2.ID_Heading2_Full);
		submenuHeading2.add(action);
		action = registry.getAction(PdfActionHeading2.ID_Heading2_Start);
		submenuHeading2.add(action);
		action = registry.getAction(PdfActionHeading2.ID_Heading2_End);
		submenuHeading2.add(action);
		action = registry.getAction(PdfActionHeading2.ID_Heading2_None);
		submenuHeading2.add(action);

		// Adding actions for Heading 3
		action = registry.getAction(PdfActionHeading3.ID_Heading3_Full);
		submenuHeading3.add(action);
		action = registry.getAction(PdfActionHeading3.ID_Heading3_Start);
		submenuHeading3.add(action);
		action = registry.getAction(PdfActionHeading3.ID_Heading3_End);
		submenuHeading3.add(action);
		action = registry.getAction(PdfActionHeading3.ID_Heading3_None);
		submenuHeading3.add(action);

		// Adding actions for Heading 4
		action = registry.getAction(PdfActionHeading4.ID_Heading4_Full);
		submenuHeading4.add(action);
		action = registry.getAction(PdfActionHeading4.ID_Heading4_Start);
		submenuHeading4.add(action);
		action = registry.getAction(PdfActionHeading4.ID_Heading4_End);
		submenuHeading4.add(action);
		action = registry.getAction(PdfActionHeading4.ID_Heading4_None);
		submenuHeading4.add(action);
		
		// Adding actions for Heading 5
		action = registry.getAction(PdfActionHeading5.ID_Heading5_Full);
		submenuHeading5.add(action);
		action = registry.getAction(PdfActionHeading5.ID_Heading5_Start);
		submenuHeading5.add(action);
		action = registry.getAction(PdfActionHeading5.ID_Heading5_End);
		submenuHeading5.add(action);
		action = registry.getAction(PdfActionHeading5.ID_Heading5_None);
		submenuHeading5.add(action);
		
		// Adding actions for Heading 6
		action = registry.getAction(PdfActionHeading6.ID_Heading6_Full);
		submenuHeading6.add(action);
		action = registry.getAction(PdfActionHeading6.ID_Heading6_Start);
		submenuHeading6.add(action);
		action = registry.getAction(PdfActionHeading6.ID_Heading6_End);
		submenuHeading6.add(action);
		action = registry.getAction(PdfActionHeading6.ID_Heading6_None);
		submenuHeading6.add(action);
		
		// Adding actions for Table
		action = registry.getAction(PdfActionTable.ID_Table_Full);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_Table_Start);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_Table_End);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_Table_None);
		submenuTable.add(action);

		// Adding actions for TableRow
		action = registry.getAction(PdfActionTableRow.ID_TableRow_Full);
		submenuTableRow.add(action);
		action = registry.getAction(PdfActionTableRow.ID_TableRow_Start);
		submenuTableRow.add(action);
		action = registry.getAction(PdfActionTableRow.ID_TableRow_End);
		submenuTableRow.add(action);
		action = registry.getAction(PdfActionTableRow.ID_TableRow_None);
		submenuTableRow.add(action);

		// Adding actions for TableHeader
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_Full);
		submenuTableHeader.add(action);
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_Start);
		submenuTableHeader.add(action);
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_End);
		submenuTableHeader.add(action);
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_None);
		submenuTableHeader.add(action);

		// Adding actions for TableDetail
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_Full);
		submenuTableDetails.add(action);
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_Start);
		submenuTableDetails.add(action);
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_End);
		submenuTableDetails.add(action);
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_None);
		submenuTableDetails.add(action);
		
		//adding actions for list
		action = registry.getAction(PdfActionList.ID_FULL);
		submenuList.add(action);
		action = registry.getAction(PdfActionList.ID_START);
		submenuList.add(action);
		action = registry.getAction(PdfActionList.ID_END);
		submenuList.add(action);
		action = registry.getAction(PdfActionList.ID_NONE);
		submenuList.add(action);
		
		//adding actions for the list item
		action = registry.getAction(PdfActionListItem.ID_FULL);
		submenuListItem.add(action);
		action = registry.getAction(PdfActionListItem.ID_START);
		submenuListItem.add(action);
		action = registry.getAction(PdfActionListItem.ID_END);
		submenuListItem.add(action);
		action = registry.getAction(PdfActionListItem.ID_NONE);
		submenuListItem.add(action);

		menu.add(submenu);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		if (sel.getFirstElement() instanceof EditPart) {
			EditPart ep = (EditPart) sel.getFirstElement();
			if (!(ep.getModel() instanceof MGraphicElement))
				return;
		}
		fillContextMenu(registry, menu, sel);
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowPDFTagsAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(1);
			actionIDs.add(ShowPDFTagsAction.ID);
		}
		return actionIDs;
	}
}
