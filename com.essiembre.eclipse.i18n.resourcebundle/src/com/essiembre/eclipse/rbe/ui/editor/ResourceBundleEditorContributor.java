/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.ui.editor;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

import com.essiembre.eclipse.rbe.ui.editor.i18n.I18nPageEditor;

/**
 * Manages the installation/deinstallation of global actions for multi-page 
 * editors. Responsible for the redirection of global actions to the active 
 * editor.
 * Multi-page contributor replaces the contributors for the individual editors
 * in the multi-page editor.
 */
public class ResourceBundleEditorContributor extends MultiPageEditorActionBarContributor {
    private IEditorPart activeEditorPart;
    /**
     * Creates a multi-page contributor.
     */
    public ResourceBundleEditorContributor() {
        super();
        createActions();
    }
    /**
     * Returns the action registed with the given text editor.
     * @param editor eclipse text editor
     * @param actionID action id
     * @return IAction or null if editor is null.
     */
    protected IAction getAction(ITextEditor editor, String actionID) {
        return (editor == null ? null : editor.getAction(actionID));
    }

   @Override
   public void dispose() {
      activeEditorPart = null;
   }

    /**
     * @see MultiPageEditorActionBarContributor
     *         #setActivePage(org.eclipse.ui.IEditorPart)
     */
    public void setActivePage(IEditorPart part) {
        if (activeEditorPart == part)
            return;

        activeEditorPart = part;

        IActionBars actionBars = getActionBars();
        if (actionBars != null) {

            ITextEditor editor = (part instanceof ITextEditor) 
                               ? (ITextEditor) part : null;
                               
             if(editor instanceof I18nPageEditor) {
                actionBars.clearGlobalActionHandlers();
                
                actionBars.setGlobalActionHandler(
                   ActionFactory.FIND.getId(),
                   ((I18nPageEditor)editor).getFindReplaceAction());
                actionBars.setGlobalActionHandler(
                   IWorkbenchActionDefinitionIds.FIND_NEXT,
                   ((I18nPageEditor)editor).getFindNextAction());
                actionBars.setGlobalActionHandler(
                   IWorkbenchActionDefinitionIds.FIND_PREVIOUS,
                   ((I18nPageEditor)editor).getFindPreviousAction());
                   
                actionBars.updateActionBars();
                return;
             }

            actionBars.setGlobalActionHandler(
                ActionFactory.DELETE.getId(),
                getAction(editor, ITextEditorActionConstants.DELETE));
            actionBars.setGlobalActionHandler(
                ActionFactory.UNDO.getId(),
                getAction(editor, ITextEditorActionConstants.UNDO));
            actionBars.setGlobalActionHandler(
                ActionFactory.REDO.getId(),
                getAction(editor, ITextEditorActionConstants.REDO));
            actionBars.setGlobalActionHandler(
                ActionFactory.CUT.getId(),
                getAction(editor, ITextEditorActionConstants.CUT));
            actionBars.setGlobalActionHandler(
                ActionFactory.COPY.getId(),
                getAction(editor, ITextEditorActionConstants.COPY));
            actionBars.setGlobalActionHandler(
                ActionFactory.PASTE.getId(),
                getAction(editor, ITextEditorActionConstants.PASTE));
            actionBars.setGlobalActionHandler(
                ActionFactory.SELECT_ALL.getId(),
                getAction(editor, ITextEditorActionConstants.SELECT_ALL));
            actionBars.setGlobalActionHandler(
               ActionFactory.FIND.getId(),
               getAction(editor, ITextEditorActionConstants.FIND));
            actionBars.setGlobalActionHandler(
               IWorkbenchActionDefinitionIds.FIND_NEXT,
               getAction(editor, ITextEditorActionConstants.FIND_NEXT));
            actionBars.setGlobalActionHandler(
               IWorkbenchActionDefinitionIds.FIND_PREVIOUS,
               getAction(editor, ITextEditorActionConstants.FIND_PREVIOUS));
            actionBars.setGlobalActionHandler(
                IDEActionFactory.BOOKMARK.getId(),
                getAction(editor, IDEActionFactory.BOOKMARK.getId()));
            actionBars.updateActionBars();
        }
    }
    private void createActions() {
//		sampleAction = new Action() {
//			public void run() {
//				MessageDialog.openInformation(null,
//        "ResourceBundle Editor Plug-in", "Sample Action Executed");
//			}
//		};
//		sampleAction.setText("Sample Action");
//		sampleAction.setToolTipText("Sample Action tool tip");
//		sampleAction.setImageDescriptor(
//        PlatformUI.getWorkbench().getSharedImages().
//				getImageDescriptor(IDE.SharedImages.IMG_OBJS_TASK_TSK));
    }
    /**
     * @see org.eclipse.ui.part.EditorActionBarContributor
     *         #contributeToMenu(org.eclipse.jface.action.IMenuManager)
     */
    public void contributeToMenu(IMenuManager manager) {
//		IMenuManager menu = new MenuManager("Editor &Menu");
//		manager.prependToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
//		menu.add(sampleAction);
    }
    /**
     * @see org.eclipse.ui.part.EditorActionBarContributor
     *         #contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    public void contributeToToolBar(IToolBarManager manager) {
//		manager.add(new Separator());
//		manager.add(sampleAction);
    }
}
