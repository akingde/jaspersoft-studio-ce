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
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;

/**
 * Error decorator, used to validate and show errors on the elements with an informative
 * tooltip message on the error
 * 
 * @author Orlandin Marco
 *
 */
public class ElementErrorDecorator implements IElementDecorator {
	
	/**
	 * List of action provided by this decorator. The action provided allow only 
	 * to show or hide the error decorator
	 */
	private List<String> actionIDs;
	
	/**
	 * Standard line separator char
	 */
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		//Remove the previous error decorator
		fig.removeDecorator(ErrorDecorator.class);
		// check if we should show this decorator
		if (editPart.getjConfig().getPropertyBooleanDef(ShowErrorsAction.ID, false)) {
			List<String> errorMessages = ((ANode)editPart.getModel()).validate(); 
			//check if the element has validation errors and in case read the decorator
			if (errorMessages != null && !errorMessages.isEmpty()) {
				StringBuilder errorMessage = new StringBuilder();
				int size = errorMessages.size();
				for(String error : errorMessages){
					errorMessage.append(error);
					if (size != 1) errorMessage.append(LINE_SEPARATOR);
					size--;
				}
				ErrorDecorator eDecorator = new ErrorDecorator();
				eDecorator.setErrorTooltip(errorMessage.toString());
				fig.addDecorator(eDecorator);
			}
		}
	}
	
	//Create the action to show or hide the decorator

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,AbstractVisualEditor part) {
		gviewer.setProperty(ShowErrorsAction.ID, true);
		IAction action = new ShowErrorsAction(gviewer, part.getJrContext());
		registry.registerAction(action);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowErrorsAction.ID, Messages.ShowErrorsAction_title,
				IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowErrorsAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(1);
			actionIDs.add(ShowErrorsAction.ID);
		}
		return actionIDs;
	}

}
