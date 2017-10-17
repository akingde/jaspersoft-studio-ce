/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.export.JsonMetadataReportConfiguration;

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

import com.jaspersoft.studio.editor.action.json.JSONEscapeMembersAction;
import com.jaspersoft.studio.editor.action.json.JSONPathDataAction;
import com.jaspersoft.studio.editor.action.json.JSONSchemaAction;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableDecorator;
import com.jaspersoft.studio.editor.gef.decorator.chainable.ChainableElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.text.MTextElement;

/**
 * Define the action related to the JSON export, it extends a TextElementDecorator to print the textual tag on the
 * elements
 * 
 * @author Veaceslav Chicu
 * 
 */
public class JSONElementDecorator extends ChainableElementDecorator {

	/**
	 * The XSL contributor for the text decoration
	 */
	private JSONDecorator decorator = new JSONDecorator();

	private List<String> actionIDs;

	/**
	 * Add or remove the XSL contributor from the text element decorator
	 */
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		super.setupFigure(fig, editPart);
		ChainableDecorator textDecorator = getDecorator(fig);
		textDecorator.removeDecorator(decorator);
		if (editPart.getjConfig().getPropertyBooleanDef(ShowJSONTagsAction.ID, false)) {
			textDecorator.addDecorator(decorator);
		}
	}

	/**
	 * Create the action related to the JSON exporting
	 * 
	 * @param registry
	 * @param part
	 * @param selectionActions
	 */
	public void registerActions(ActionRegistry registry, List<String> selectionActions, IWorkbenchPart part) {
		IAction action = new JSONPathDataAction(part);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new JSONSchemaAction(part);
		registry.registerAction(action);
		selectionActions.add(action.getId());

		action = new JSONEscapeMembersAction(part);
		registry.registerAction(action);
		selectionActions.add(action.getId());
	}

	@Override
	public void registerActions(ActionRegistry registry, List<String> selectionActions, GraphicalViewer gviewer,
			AbstractVisualEditor part) {
		gviewer.setProperty(ShowJSONTagsAction.ID, true);
		IAction action = new ShowJSONTagsAction(gviewer, part.getJrContext());
		registry.registerAction(action);
		registerActions(registry, selectionActions, part);
	}

	public void fillContextMenu(ActionRegistry registry, IMenuManager menu, IStructuredSelection sel) {
		if (sel.getFirstElement() instanceof EditPart) {
			EditPart ep = (EditPart) sel.getFirstElement();
			if (ep.getModel() instanceof MTextElement) {
				MenuManager submenu = new MenuManager(Messages.JSONElementDecorator_0);
				IAction action;
				action = registry.getAction(JSONPathDataAction.JSON_EXPORTER_PATH_PROPERTY);
				submenu.add(action);
				menu.add(submenu);
			}
			if (ep.getModel() instanceof MReport) {
				MenuManager submenu = new MenuManager(Messages.JSONElementDecorator_0);
				IAction action = registry.getAction(JsonMetadataReportConfiguration.JSON_EXPORTER_JSON_SCHEMA);
				submenu.add(action);

				action = registry.getAction(JsonMetadataReportConfiguration.JSON_EXPORTER_ESCAPE_MEMBERS);
				submenu.add(action);

				menu.add(submenu);
			}
		}
	}

	@Override
	public void buildContextMenu(ActionRegistry registry, EditPartViewer viewer, IMenuManager menu) {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		fillContextMenu(registry, menu, sel);
	}

	@Override
	public RetargetAction[] buildMenuActions() {
		return new RetargetAction[] { new RetargetAction(ShowJSONTagsAction.ID, Messages.JSONElementDecorator_2, IAction.AS_CHECK_BOX) };
	}

	@Override
	public void contribute2Menu(ActionRegistry registry, MenuManager menuManager) {
		menuManager.add(registry.getAction(ShowJSONTagsAction.ID));
	}

	@Override
	public List<String> getActionIDs() {
		if (actionIDs == null) {
			actionIDs = new ArrayList<String>(1);
			actionIDs.add(ShowJSONTagsAction.ID);
		}
		return actionIDs;
	}

}
