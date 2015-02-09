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
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.ui.actions.Clipboard;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;

public class PasteAction extends ACachedSelectionAction {

	public PasteAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		super.init();

		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.common_paste);
		setId(ActionFactory.PASTE.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_PASTE_DISABLED));
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		if (!fresh){
			command = null;
			Object obj = Clipboard.getDefault().getContents();
			if (obj instanceof AbstractPastableObject){
				command = ((AbstractPastableObject)obj).getPasteCommand(getSelectedObjects());
			}
			fresh = true;
		}
		return command != null && command.canExecute();
	}

	@Override
	public void run() {
		execute(command);

		// Select the pasted edit part
		GraphicalViewer viewer = (GraphicalViewer) getWorkbenchPart().getAdapter(GraphicalViewer.class);
		if (viewer != null && command instanceof PasteCommand) {
			PasteCommand standardPasteCommand = (PasteCommand)command;
			viewer.setSelection(new StructuredSelection(getSelectableEditParts(viewer, standardPasteCommand.getPasteParent(),
																																					standardPasteCommand.getCreatedNodesNumber())));
		}
	}

	/**
	 * Return a list of the edit part created because the past operation
	 * 
	 * @param viewer
	 * @param pasteParent
	 *          parent of the pasted elements
	 * @param createdElements
	 *          number of pasted elements
	 * @return the editpart created for the paste operation, so they can be selected
	 */
	@SuppressWarnings("rawtypes")
	private List<EditPart> getSelectableEditParts(GraphicalViewer viewer, IPastable pasteParent, int createdElements) {
		List<EditPart> selectableChildren = new ArrayList<EditPart>();
		if (!(pasteParent instanceof ANode))
			return selectableChildren;

		ANode parentModel = (ANode) pasteParent;
		HashSet<INode> pastedModels = new HashSet<INode>();
		int elementsToInsert = createdElements;
		List<INode> childrens = parentModel.getChildren();
		// the list children can be empty in specific use case
		// for example when the elements were pasted into a node
		// that is not expanded in the main editor (i.e: list component)
		if(childrens.size()>=elementsToInsert) {
			while (elementsToInsert > 0) {
				pastedModels.add(childrens.get(childrens.size() - elementsToInsert));
				elementsToInsert--;
			}
		}

		List children = viewer.getContents().getChildren();
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (child instanceof EditPart) {
				EditPart part = (EditPart) child;
				if (pastedModels.contains(part.getModel()) && part.isSelectable())
					selectableChildren.add(part);
			}
		}
		return selectableChildren;
	}

}
