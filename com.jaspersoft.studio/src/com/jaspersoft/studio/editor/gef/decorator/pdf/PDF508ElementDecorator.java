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
package com.jaspersoft.studio.editor.gef.decorator.pdf;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading1;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading2;
import com.jaspersoft.studio.editor.action.pdf.PdfActionHeading3;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTable;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTableDetail;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTableHeader;
import com.jaspersoft.studio.editor.action.pdf.PdfActionTableRow;
import com.jaspersoft.studio.editor.action.pdf.Position;
import com.jaspersoft.studio.editor.gef.decorator.IDecorator;
import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.messages.Messages;

public class PDF508ElementDecorator implements IElementDecorator {

	private IDecorator decorator = new PDFDecorator();		
	
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		fig.removeDecorator(decorator);
		// check if we should show this decorator
		//J2DScrollingGraphicalViewer
		
		if ((Boolean)editPart.getViewer().getProperty(ShowPDFTagsAction.ID)) {
			fig.addDecorator(decorator);
		}  //else editPart.getViewer().getContents().refresh();
	}
	
	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowPDFTagsAction.ID, Messages.ShowPDFTagsAction_title,
				IAction.AS_CHECK_BOX) };
	}

	private void registerHeading(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions){

		
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
	}
	
	private void registerTable(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions){
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
	
	private void registerTableRow(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions){
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
	
	
	private void registerTableDetail(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions){
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
	
	private void registerTableHeader(ActionRegistry registry, IWorkbenchPart part, List<String> selectionActions){
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
	
	
	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			IWorkbenchPart part) {
		gviewer.setProperty(ShowPDFTagsAction.ID, true);
		IAction action = new ShowPDFTagsAction(gviewer);
		registry.registerAction(action);
		registerHeading(registry, part, selectionActions);
		registerTable(registry, part, selectionActions);
		registerTableRow(registry, part, selectionActions);
		registerTableHeader(registry, part, selectionActions);
		registerTableDetail(registry, part, selectionActions);
		// TODO add pdf actions here
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		MenuManager submenu = new MenuManager(Messages.PDF508ElementDecorator_Menu_PDF508Tags);		
		MenuManager submenuHeading = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading);
		MenuManager submenuHeading1 = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading1);
		MenuManager submenuHeading2 = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading2);
		MenuManager submenuHeading3 = new MenuManager(Messages.PDF508ElementDecorator_Menu_Heading3);
		MenuManager submenuTable = new MenuManager(Messages.PDF508ElementDecorator_Menu_Table);
		MenuManager submenuTableRow = new MenuManager(Messages.PDF508ElementDecorator_Menu_TableRow);
		MenuManager submenuTableHeader = new MenuManager(Messages.PDF508ElementDecorator_Menu_TableHeader);
		MenuManager submenuTableDetails = new MenuManager(Messages.PDF508ElementDecorator_Menu_TableDetails);
		
		submenuHeading.add(submenuHeading1);
		submenuHeading.add(submenuHeading2);
		submenuHeading.add(submenuHeading3);
		submenu.add(submenuHeading);
		submenu.add(submenuTable);
		submenu.add(submenuTableRow);
		submenu.add(submenuTableHeader);
		submenu.add(submenuTableDetails);
		
		IAction action;
		//Adding actions for Heading 1
		action = registry.getAction(PdfActionHeading1.ID_Heading1_Full);
		submenuHeading1.add(action);
		action = registry.getAction(PdfActionHeading1.ID_Heading1_Start);
		submenuHeading1.add(action);
		action = registry.getAction(PdfActionHeading1.ID_Heading1_End);
		submenuHeading1.add(action);
		action = registry.getAction(PdfActionHeading1.ID_Heading1_None);
		submenuHeading1.add(action);
		
		//Adding actions for Heading 2
		action = registry.getAction(PdfActionHeading2.ID_Heading2_Full);
		submenuHeading2.add(action);
		action = registry.getAction(PdfActionHeading2.ID_Heading2_Start);
		submenuHeading2.add(action);
		action = registry.getAction(PdfActionHeading2.ID_Heading2_End);
		submenuHeading2.add(action);
		action = registry.getAction(PdfActionHeading2.ID_Heading2_None);
		submenuHeading2.add(action);
		
		//Adding actions for Heading 3
		action = registry.getAction(PdfActionHeading3.ID_Heading3_Full);
		submenuHeading3.add(action);
		action = registry.getAction(PdfActionHeading3.ID_Heading3_Start);
		submenuHeading3.add(action);
		action = registry.getAction(PdfActionHeading3.ID_Heading3_End);
		submenuHeading3.add(action);
		action = registry.getAction(PdfActionHeading3.ID_Heading3_None);
		submenuHeading3.add(action);
		
		//Adding actions for Table
		action = registry.getAction(PdfActionTable.ID_Table_Full);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_Table_Start);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_Table_End);
		submenuTable.add(action);
		action = registry.getAction(PdfActionTable.ID_Table_None);
		submenuTable.add(action);
		
		//Adding actions for TableRow
		action = registry.getAction(PdfActionTableRow.ID_TableRow_Full);
		submenuTableRow.add(action);
		action = registry.getAction(PdfActionTableRow.ID_TableRow_Start);
		submenuTableRow.add(action);
		action = registry.getAction(PdfActionTableRow.ID_TableRow_End);
		submenuTableRow.add(action);
		action = registry.getAction(PdfActionTableRow.ID_TableRow_None);
		submenuTableRow.add(action);
		
		//Adding actions for TableHeader
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_Full);
		submenuTableHeader.add(action);
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_Start);
		submenuTableHeader.add(action);
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_End);
		submenuTableHeader.add(action);
		action = registry.getAction(PdfActionTableHeader.ID_TableHeader_None);
		submenuTableHeader.add(action);
		
		//Adding actions for TableDetail
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_Full);
		submenuTableDetails.add(action);
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_Start);
		submenuTableDetails.add(action);
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_End);
		submenuTableDetails.add(action);
		action = registry.getAction(PdfActionTableDetail.ID_TableDetail_None);
		submenuTableDetails.add(action);
	
		menu.add(submenu);
	}



	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowPDFTagsAction.ID));
	}
}
