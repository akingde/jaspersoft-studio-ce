/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.error;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.RetargetAction;

import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Error decorator, used to validate and show errors on the elements with an informative tooltip message on the error
 * 
 * @author Orlandin Marco
 *
 */
public class ElementErrorDecorator implements IElementDecorator {

	/**
	 * List of action provided by this decorator. The action provided allow only to show or hide the error decorator
	 */
	private List<String> actionIDs;

	@Override
	public void setupFigure(final ComponentFigure fig, FigureEditPart editPart) {
		// Remove the previous error decorator
		fig.removeDecorator(ErrorDecorator.class);
		// check if we should show this decorator
		if (editPart.getjConfig().getPropertyBooleanDef(ShowErrorsAction.ID, false)) {
			List<ValidationError> errorMessages = ((ANode) editPart.getModel()).validate();
			// check if the element has validation errors and in case read the decorator
			if (errorMessages != null && !errorMessages.isEmpty()) {
				ErrorDecorator eDecorator = new ErrorDecorator();
				eDecorator.setErrorMessages(errorMessages);
				fig.addDecorator(eDecorator);
			}
		}
	}

	// Create the action to show or hide the decorator

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		gviewer.setProperty(ShowErrorsAction.ID, true);
		IAction action = new ShowErrorsAction(gviewer, part.getJrContext());
		registry.registerAction(action);
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] {
				new RetargetAction(ShowErrorsAction.ID, Messages.ShowErrorsAction_title, IAction.AS_CHECK_BOX) };
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

	@Override
	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel) {

	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {
	}

	/**
	 * By default a global feedback is not provided
	 */
	@Override
	public void paintGlobal(Graphics g, IFigure figure, JasperReportsConfiguration jConfig) {
		
	}
}
