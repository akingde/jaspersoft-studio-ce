/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.editors.text.TextEditorActionContributor;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.snap.SizeGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGuidesAction;
import com.jaspersoft.studio.editor.gef.ui.actions.RZoomComboContributionItem;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.editor.report.ReportContainer;
import com.jaspersoft.studio.messages.Messages;

/*
 * Manages the installation/deinstallation of global actions for multi-page editors. Responsible for the redirection of
 * global actions to the active editor. Multi-page contributor replaces the contributors for the individual editors in
 * the multi-page editor.
 * 
 * @author Chicu Veaceslav
 */
public class JrxmlEditorContributor extends MultiPageEditorActionBarContributor {

	/** The global action keys. */
	private List<String> globalActionKeys = new ArrayList<String>();

	/** The retarget actions. */
	private List<RetargetAction> retargetActions = new ArrayList<RetargetAction>();

	/** The registry. */
	private ActionRegistry registry = new ActionRegistry();

	/** The zoom combo. */
	private RZoomComboContributionItem zoomCombo;

	/**
	 * Creates a multi-page contributor.
	 */
	public JrxmlEditorContributor() {
		super();
	}

	/**
	 * Initialization.
	 * 
	 * @param bars
	 *          the bars
	 */
	public void init(IActionBars bars) {
		buildActions();
		declareGlobalActionKeys();
		super.init(bars);
	}

	/**
	 * Builds the actions.
	 * 
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
	 */
	protected void buildActions() {
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());

		// addRetargetAction(new RetargetAction(BringToFrontAction.ID, "Bring To Front"));
		// addRetargetAction(new RetargetAction(BringForwardAction.ID, "Bring Forward"));
		// addRetargetAction(new RetargetAction(BringBackwardAction.ID, "Bring Backward"));
		// addRetargetAction(new RetargetAction(BringToBackAction.ID, "Bring To Back"));
		//
		// addRetargetAction(new AlignmentRetargetAction(PositionConstants.LEFT));
		// addRetargetAction(new AlignmentRetargetAction(PositionConstants.CENTER));
		// addRetargetAction(new AlignmentRetargetAction(PositionConstants.RIGHT));
		// addRetargetAction(new AlignmentRetargetAction(PositionConstants.TOP));
		// addRetargetAction(new AlignmentRetargetAction(PositionConstants.MIDDLE));
		// addRetargetAction(new AlignmentRetargetAction(PositionConstants.BOTTOM));

		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());

		// addRetargetAction(new MatchWidthRetargetAction());
		// addRetargetAction(new MatchHeightRetargetAction());
		// GEFMessages.ToggleRulerVisibility_Label
		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY,
				Messages.JrxmlEditorContributor_show_ruler, IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(SnapToGuidesAction.ID, Messages.common_snap_to_guides, IAction.AS_CHECK_BOX));

		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY, Messages.common_show_grid,
				IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY, Messages.common_snap_to_geometry,
				IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(SnapToGridAction.ID, Messages.common_snap_to_grid, IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(SizeGridAction.ID, Messages.JrxmlEditorContributor_grid_size));

		List<RetargetAction> ractions = JaspersoftStudioPlugin.getDecoratorManager().buildMenuActions();
		for (RetargetAction r : ractions)
			addRetargetAction(r);
	}

	/**
	 * Adds the retarded actions.
	 * 
	 * @param action
	 *          The action to add
	 */
	protected void addRetargetAction(RetargetAction action) {
		addAction(action);
		retargetActions.add(action);
		getPage().addPartListener(action);
		addGlobalActionKey(action.getId());
	}

	private List<String> glRetargetAction = new ArrayList<String>();

	protected void addGlobaRetargetAction(Action action) {
		addAction(action);
		glRetargetAction.add(action.getId());
		addGlobalActionKey(action.getId());
	}

	/**
	 * Adds global action key.
	 * 
	 * @param key
	 *          The key to add
	 */
	protected void addGlobalActionKey(String key) {
		globalActionKeys.add(key);
	}

	/**
	 * Adds to action registry an action.
	 * 
	 * @param action
	 *          The action to add
	 */
	protected void addAction(IAction action) {
		getActionRegistry().registerAction(action);
	}

	/**
	 * Gets the registry.
	 * 
	 * @return ActionRegistry The registry
	 */
	protected ActionRegistry getActionRegistry() {
		return registry;
	}

	/**
	 * Declares the global action keys.
	 * 
	 * @see org.eclipse.gef.ui.actions.ActionBarContributor#declareGlobalActionKeys()
	 */
	protected void declareGlobalActionKeys() {
		addGlobalActionKey(ActionFactory.CUT.getId());
		addGlobalActionKey(ActionFactory.COPY.getId());
		addGlobalActionKey(ActionFactory.PASTE.getId());
		addGlobalActionKey(ActionFactory.SELECT_ALL.getId());
		addGlobalActionKey(ActionFactory.DELETE.getId());
	}

	/**
	 * Gets the action.
	 * 
	 * @param id
	 *          the id
	 * @return the action
	 */
	protected IAction getAction(String id) {
		return getActionRegistry().getAction(id);
	}

	/**
	 * Sets the page to active status.
	 * 
	 * @param activeEditor
	 *          The active editor
	 */
	public void setActivePage(IEditorPart activeEditor) {
		IActionBars bars = getActionBars();
		removeZoom(bars.getToolBarManager());
		bars.clearGlobalActionHandlers();

		addGlobal(bars);
		if (activeEditor instanceof ITextEditor) {
			if (textEditorContributor == null) {
				textEditorContributor = new TextEditorActionContributor();
				textEditorContributor.init(bars, activeEditor.getSite().getPage());
			}
			textEditorContributor.setActiveEditor(activeEditor);
		} else if (activeEditor instanceof ReportContainer || activeEditor instanceof AbstractVisualEditor) {
			// NO LONGER AVAILABLE IN GLOBAL TOOLBAR SINCE
			// THEY WILL BE VISIBLE IN THE ReportContainer toolbar.
//			addZoom(bars.getToolBarManager());
			if (activeEditor instanceof AbstractVisualEditor) {
				GraphicalViewer graphicalViewer = ((AbstractVisualEditor) activeEditor).getGraphicalViewer();
				ZoomManager property = (ZoomManager) graphicalViewer.getProperty(ZoomManager.class.toString());
				if (property != null)
					zoomCombo.setZoomManager(property);
			}
			ActionRegistry registry = (ActionRegistry) activeEditor.getAdapter(ActionRegistry.class);
			if (registry != null)
				for (String id : globalActionKeys) {
					bars.setGlobalActionHandler(id, registry.getAction(id));
				}
		}
		bars.updateActionBars();
	}

	/**
	 * Returns the action registed with the given text editor.
	 * 
	 * @param editor
	 *          the editor
	 * @param actionID
	 *          the action id
	 * @return IAction or null if editor is null.
	 */
	protected IAction getAction(ITextEditor editor, String actionID) {
		return (editor == null ? null : editor.getAction(actionID));
	}

	/**
	 * Adds the undo and redo items to the toolbar.
	 * 
	 * @param tbm
	 *          The IToolBarManager
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(IToolBarManager)
	 */
	public void contributeToToolBar(IToolBarManager tbm) {
		tbm.add(getAction(ActionFactory.UNDO.getId()));
		tbm.add(getAction(ActionFactory.REDO.getId()));

		// tbm.add(new Separator());
		// tbm.add(getAction(GEFActionConstants.ALIGN_LEFT));
		// tbm.add(getAction(GEFActionConstants.ALIGN_CENTER));
		// tbm.add(getAction(GEFActionConstants.ALIGN_RIGHT));
		// tbm.add(new Separator());
		// tbm.add(getAction(GEFActionConstants.ALIGN_TOP));
		// tbm.add(getAction(GEFActionConstants.ALIGN_MIDDLE));
		// tbm.add(getAction(GEFActionConstants.ALIGN_BOTTOM));
		//
		// tbm.add(new Separator());
		// tbm.add(getAction(GEFActionConstants.MATCH_WIDTH));
		// tbm.add(getAction(GEFActionConstants.MATCH_HEIGHT));
		tbm.add(new Separator());

		addZoom(tbm);
	}

	private void addZoom(IToolBarManager tbm) {
		tbm.add(getAction(GEFActionConstants.ZOOM_IN));
		tbm.add(getAction(GEFActionConstants.ZOOM_OUT));
		if (zoomCombo == null)
			zoomCombo = new RZoomComboContributionItem(getPage());
		zoomCombo.setEnabled(true);
		tbm.add(zoomCombo);
		tbm.update(true);
	}

	private void removeZoom(IToolBarManager tbm) {
		tbm.remove(GEFActionConstants.ZOOM_IN);
		tbm.remove(GEFActionConstants.ZOOM_OUT);
		tbm.remove(zoomCombo.getId());
		zoomCombo.setEnabled(false);
		tbm.update(true);
	}

	private void addGlobal(IActionBars bars) {
		IToolBarManager tbm = bars.getToolBarManager();
		for (String s : glRetargetAction) {
			tbm.remove(s);
			IAction action = getAction(s);
			bars.setGlobalActionHandler(s, action);
			tbm.add(action);
		}
	}

	private void removeGlobal(IToolBarManager tbm) {
		for (String s : glRetargetAction)
			tbm.remove(s);
		tbm.update(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void contributeToMenu(IMenuManager manager) {
		super.contributeToMenu(manager);

		MenuManager viewMenu = new MenuManager(Messages.JrxmlEditorContributor_view);
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(new Separator());
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY));
		viewMenu.add(getAction(SnapToGuidesAction.ID));
		viewMenu.add(new Separator());
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		viewMenu.add(getAction(SnapToGridAction.ID));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
		viewMenu.add(getAction(SizeGridAction.ID));

		JaspersoftStudioPlugin.getDecoratorManager().contribute2Menu(getActionRegistry(), viewMenu);

		manager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);
	}

	private TextEditorActionContributor textEditorContributor = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.EditorActionBarContributor#contributeToStatusLine(org.eclipse.jface.action.IStatusLineManager)
	 */
	@Override
	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		super.contributeToStatusLine(statusLineManager);
		if (textEditorContributor != null)
			textEditorContributor.contributeToStatusLine(statusLineManager);

		statusLineManager.setMessage(""); //$NON-NLS-1$
	}

	/**
	 * Disposes the contributor. Removes all {@link RetargetAction}s that were {@link org.eclipse.ui.IPartListener}s on
	 * the {@link org.eclipse.ui.IWorkbenchPage} and disposes them. Also disposes the action registry.
	 * <P>
	 * Subclasses may extend this method to perform additional cleanup.
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#dispose()
	 */
	public void dispose() {
		for (int i = 0; i < retargetActions.size(); i++) {
			RetargetAction action = retargetActions.get(i);
			getPage().removePartListener(action);
			action.dispose();
		}
		registry.dispose();
		retargetActions = null;
		registry = null;
	}

}
