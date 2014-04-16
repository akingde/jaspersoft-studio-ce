/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Action to paste the appearance of an element on a set of 
 * other elements 
 * 
 * @author Orlandin Marco
 *
 */
public class PasteFormatAction extends ACachedSelectionAction {

	public static final String ID = "PasteFormatAction"; //$NON-NLS-1$
	
	public PasteFormatAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.PasteFormatAction_title);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/paste_format.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		if (!CopyFormatAction.hasCopiedValues()) return false;
		List<?> selection = getSelectedObjects();
		//Search if there is at least one target node
		boolean found = false;
		for (Object it : selection) {
			if (it instanceof EditPart) {
				EditPart ep = (EditPart) it;
				Object modelObj = ep.getModel();
				if (modelObj instanceof APropertyNode){
					found = true;
					//Nod found we can break the search
					break;
				}
			}
		}
		return found;
	}

	@Override
	public void run() {
		List<APropertyNode> selectedNodes = getNodes(getSelectedObjects());
		PasteFormatCommand command = new PasteFormatCommand(selectedNodes);
		execute(command);
	}

	/**
	 * Return the list of APropertyNode inside the selection
	 * 
	 * @param selectedObjects the actual selection
	 * @return a not null list of APropertyNode
	 */
	protected List<APropertyNode> getNodes(List<?> selectedObjects) {
		List<APropertyNode> result = new ArrayList<APropertyNode>();
		if (selectedObjects.isEmpty())
			return result;
		for (Object it : selectedObjects) {
			if (it instanceof EditPart) {
				EditPart ep = (EditPart) it;
				Object modelObj = ep.getModel();
				// Before to add an element it is checked if its nested, this is done to avoid to copy twice an element because
				// it is also directly selected with also its container (ie a frame) selected
				if (modelObj instanceof APropertyNode){
					result.add((APropertyNode)modelObj);
				}
			}
		}
		return result;
	}
}
