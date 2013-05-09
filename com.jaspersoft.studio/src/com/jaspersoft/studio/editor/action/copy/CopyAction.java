/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.INode;

/**
 * Create the command to execute a copy action
 * 
 * @author Slavic & Orlandin Marco
 *
 */
public class CopyAction extends SelectionAction {

	public CopyAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		super.init();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setText(Messages.common_copy);
		setId(ActionFactory.COPY.getId());
		setHoverImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_COPY_DISABLED));
		setEnabled(false);
	}

	@Override
	public void run() {
		execute(createCopyCommand(getSelectedObjects()));
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createCopyCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}
	
	/**
	 * Take a container and add its children to the nested object, if one of its children it is a container 
	 * even its children are explored and added to the hashset
	 * 
	 * @param elementToExplore container to explore for the nested elements
	 * @param nestedFound has set where the references to the nested element are inserted
	 */
	private void getNestedElementsRecursive(INode elementToExplore, HashSet<INode> nestedFound){
		List<INode> selectedObjects = elementToExplore.getChildren();
		for(INode element : selectedObjects){
				if (element instanceof IContainer) getNestedElementsRecursive(element,nestedFound);
				nestedFound.add(element);
		}
	}
	
	/**
	 * Return an hashset that contains a reference for every model that is nested into a container 
	 * (or in its hierarchy) of the selection
	 * 
	 * @param selectedObjects the objects in the selection
	 * @return an hashset containing the references to the nested elements into the selected object
	 */
	private HashSet<INode> getNotNestedSelection(List<?> selectedObjects){
		HashSet<INode> nestedElements = new HashSet<INode>();
		for (Object it : selectedObjects) {
			if (it instanceof EditPart) {
				EditPart ep = (EditPart) it;
				if (ep.getModel() instanceof IContainer) getNestedElementsRecursive((INode)ep.getModel(),nestedElements);
			}
		}
		return nestedElements;
	}

	private Command createCopyCommand(List<?> selectedObjects) {
		if (selectedObjects.isEmpty())
			return null;
		CopyCommand cmd = new CopyCommand();
		HashSet<INode> nestedElements = getNotNestedSelection(selectedObjects);
		for (Object it : selectedObjects) {
			if (it instanceof EditPart) {
				EditPart ep = (EditPart) it;
				ANode node = (ANode) ep.getModel();
				//Before to add an element it is checked if its nested, this is done to avoid to copy twice an element because
				//it is also directly selected with also its container (ie a frame) selected
				if (cmd.isCopyableNode(node) && !nestedElements.contains(node))
					cmd.addElement(node);
			}
		}
		return cmd;
	}
}
