/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.internal.handlers.IActionCommandMappingService;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.menus.CommandContributionItem;
import org.eclipse.ui.menus.CommandContributionItemParameter;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;

import com.jaspersoft.studio.editor.action.snap.SnapToGridAction;
import com.jaspersoft.studio.editor.action.snap.SnapToGuidesAction;
import com.jaspersoft.studio.editor.gef.ui.actions.RZoomComboContributionItem;

/**
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

	/** The sample action. */
	private Action sampleAction;

	/** The zoom combo. */
	private RZoomComboContributionItem zoomCombo;

	/**
	 * Creates a multi-page contributor.
	 */
	public JrxmlEditorContributor() {
		super();
		createActions();
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
		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY, "Show Ruler", IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(SnapToGuidesAction.ID, "Snap To Guides", IAction.AS_CHECK_BOX));

		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY, "Show Grid", IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY, "Snap To Geometry",
				IAction.AS_CHECK_BOX));
		addRetargetAction(new RetargetAction(SnapToGridAction.ID, "Snap To Grid", IAction.AS_CHECK_BOX));
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
		if (zoomCombo != null)
			zoomCombo.setEnabled(false);
		bars.clearGlobalActionHandlers();
		if (activeEditor instanceof ITextEditor) {

			ITextEditor editor = (ITextEditor) activeEditor;
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), getAction(editor, ITextEditorActionConstants.DELETE));
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(), getAction(editor, ITextEditorActionConstants.UNDO));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(), getAction(editor, ITextEditorActionConstants.REDO));
			bars.setGlobalActionHandler(ActionFactory.CUT.getId(), getAction(editor, ITextEditorActionConstants.CUT));
			bars.setGlobalActionHandler(ActionFactory.COPY.getId(), getAction(editor, ITextEditorActionConstants.COPY));
			bars.setGlobalActionHandler(ActionFactory.PASTE.getId(), getAction(editor, ITextEditorActionConstants.PASTE));
			bars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), getAction(editor,
					ITextEditorActionConstants.SELECT_ALL));
			bars.setGlobalActionHandler(ActionFactory.FIND.getId(), getAction(editor, ITextEditorActionConstants.FIND));
			bars.setGlobalActionHandler(IDEActionFactory.BOOKMARK.getId(), getAction(editor, IDEActionFactory.BOOKMARK
					.getId()));
		} else if (activeEditor instanceof IAdaptable) {
			if (zoomCombo != null)
				zoomCombo.setEnabled(true);
			ActionRegistry registry = (ActionRegistry) activeEditor.getAdapter(ActionRegistry.class);
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
	 * Creates the actions.
	 */
	private void createActions() {
		sampleAction = new Action() {
			public void run() {
				MessageDialog.openInformation(null, "Jasper Studio", "Sample Action Executed");
			}
		};
		sampleAction.setText("Sample Action");
		sampleAction.setToolTipText("Sample Action tool tip");
		sampleAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(
				IDE.SharedImages.IMG_OBJS_TASK_TSK));
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
		tbm.add(getAction(GEFActionConstants.ZOOM_IN));
		tbm.add(getAction(GEFActionConstants.ZOOM_OUT));
		zoomCombo = new RZoomComboContributionItem(getPage());
		tbm.add(zoomCombo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void contributeToMenu(IMenuManager manager) {
		super.contributeToMenu(manager);
		if (manager.findUsingPath(IWorkbenchActionConstants.M_EDIT) == null) {
			MenuManager editMenu = createEditMenu();
			manager.insertAfter(IWorkbenchActionConstants.M_FILE, editMenu);
		}

		MenuManager viewMenu = new MenuManager("View");
		viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
		viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
		viewMenu.add(new Separator());
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_RULER_VISIBILITY));
		viewMenu.add(getAction(SnapToGuidesAction.ID));

		viewMenu.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		viewMenu.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));
		viewMenu.add(getAction(SnapToGridAction.ID));

		manager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);

		IMenuManager menu = new MenuManager("Editor &Menu");
		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
		menu.add(sampleAction);

	}

	/**
	 * Creates and returns the Edit menu.
	 * 
	 * @return the menu manager
	 */
	private MenuManager createEditMenu() {
		MenuManager menu = new MenuManager(IDEWorkbenchMessages.Workbench_edit, IWorkbenchActionConstants.M_EDIT);
		menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));

		menu.add(getAction(ActionFactory.UNDO.getId()));
		menu.add(getAction(ActionFactory.REDO.getId()));
		menu.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));
		menu.add(new Separator());

		menu.add(getCutItem());
		menu.add(getCopyItem());
		menu.add(getPasteItem());
		menu.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));
		menu.add(new Separator());

		menu.add(getAction(ActionFactory.DELETE.getId()));
		menu.add(getSelectAllItem());
		menu.add(new Separator());

		menu.add(getFindItem());
		menu.add(new GroupMarker(IWorkbenchActionConstants.FIND_EXT));
		menu.add(new Separator());

		menu.add(getBookmarkItem());
		menu.add(getTaskItem());
		menu.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));

		menu.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));
		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		return menu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.EditorActionBarContributor#contributeToStatusLine(org.eclipse.jface.action.IStatusLineManager)
	 */
	@Override
	public void contributeToStatusLine(IStatusLineManager statusLineManager) {
		statusLineManager.setMessage("hello");
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
			RetargetAction action = (RetargetAction) retargetActions.get(i);
			getPage().removePartListener(action);
			action.dispose();
		}
		registry.dispose();
		retargetActions = null;
		registry = null;
	}

	/**
	 * Gets the cut item.
	 * 
	 * @return the cut item
	 */
	private IContributionItem getCutItem() {
		return getItem(ActionFactory.CUT.getId(), ActionFactory.CUT.getCommandId(), ISharedImages.IMG_TOOL_CUT,
				ISharedImages.IMG_TOOL_CUT_DISABLED, WorkbenchMessages.Workbench_cut, WorkbenchMessages.Workbench_cutToolTip,
				null);
	}

	/**
	 * Gets the copy item.
	 * 
	 * @return the copy item
	 */
	private IContributionItem getCopyItem() {
		return getItem(ActionFactory.COPY.getId(), ActionFactory.COPY.getCommandId(), ISharedImages.IMG_TOOL_COPY,
				ISharedImages.IMG_TOOL_COPY_DISABLED, WorkbenchMessages.Workbench_copy,
				WorkbenchMessages.Workbench_copyToolTip, null);
	}

	/**
	 * Gets the paste item.
	 * 
	 * @return the paste item
	 */
	private IContributionItem getPasteItem() {
		return getItem(ActionFactory.PASTE.getId(), ActionFactory.PASTE.getCommandId(), ISharedImages.IMG_TOOL_PASTE,
				ISharedImages.IMG_TOOL_PASTE_DISABLED, WorkbenchMessages.Workbench_paste,
				WorkbenchMessages.Workbench_pasteToolTip, null);
	}

	/**
	 * Gets the select all item.
	 * 
	 * @return the select all item
	 */
	private IContributionItem getSelectAllItem() {
		return getItem(ActionFactory.SELECT_ALL.getId(), ActionFactory.SELECT_ALL.getCommandId(), null, null,
				WorkbenchMessages.Workbench_selectAll, WorkbenchMessages.Workbench_selectAllToolTip, null);
	}

	/**
	 * Gets the find item.
	 * 
	 * @return the find item
	 */
	private IContributionItem getFindItem() {
		return getItem(ActionFactory.FIND.getId(), ActionFactory.FIND.getCommandId(), null, null,
				WorkbenchMessages.Workbench_findReplace, WorkbenchMessages.Workbench_findReplaceToolTip, null);
	}

	/**
	 * Gets the bookmark item.
	 * 
	 * @return the bookmark item
	 */
	private IContributionItem getBookmarkItem() {
		return getItem(IDEActionFactory.BOOKMARK.getId(), IDEActionFactory.BOOKMARK.getCommandId(), null, null,
				IDEWorkbenchMessages.Workbench_addBookmark, IDEWorkbenchMessages.Workbench_addBookmarkToolTip, null);
	}

	/**
	 * Gets the task item.
	 * 
	 * @return the task item
	 */
	private IContributionItem getTaskItem() {
		return getItem(IDEActionFactory.ADD_TASK.getId(), IDEActionFactory.ADD_TASK.getCommandId(), null, null,
				IDEWorkbenchMessages.Workbench_addTask, IDEWorkbenchMessages.Workbench_addTaskToolTip, null);
	}

	/**
	 * Gets the item.
	 * 
	 * @param actionId
	 *          the action id
	 * @param commandId
	 *          the command id
	 * @param image
	 *          the image
	 * @param disabledImage
	 *          the disabled image
	 * @param label
	 *          the label
	 * @param tooltip
	 *          the tooltip
	 * @param helpContextId
	 *          the help context id
	 * @return the item
	 */
	private IContributionItem getItem(String actionId, String commandId, String image, String disabledImage,
			String label, String tooltip, String helpContextId) {
		ISharedImages sharedImages = getWindow().getWorkbench().getSharedImages();

		IActionCommandMappingService acms = (IActionCommandMappingService) getWindow().getService(
				IActionCommandMappingService.class);
		acms.map(actionId, commandId);

		CommandContributionItemParameter commandParm = new CommandContributionItemParameter(getWindow(), actionId,
				commandId, null, sharedImages.getImageDescriptor(image), sharedImages.getImageDescriptor(disabledImage), null,
				label, null, tooltip, CommandContributionItem.STYLE_PUSH, null, false);
		return new CommandContributionItem(commandParm);
	}

	/** The window. */
	private IWorkbenchWindow window;

	/**
	 * Returns the window to which this action builder is contributing.
	 * 
	 * @return the window
	 */
	private IWorkbenchWindow getWindow() {
		if (this.window == null)
			this.window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		return window;
	}
}
