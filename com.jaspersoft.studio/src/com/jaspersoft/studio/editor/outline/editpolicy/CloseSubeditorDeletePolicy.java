/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.editpolicy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;

import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.command.CloseSubeditorsCommand;

/**
 * Special policy used when the delete of an element is requested, it will use a custom delete command
 * to close eventually opened editors for a children (or descendant ) element of the deleted one
 * 
 * @author Orlandin Marco
 *
 */
public class CloseSubeditorDeletePolicy extends ElementEditPolicy{
	
	protected Command createDeleteCommand(GroupRequest request) {
		if (getHost() != null && getHost().getParent() != null) {
			Object parent = ((ANode)getHost().getModel()).getParent();
			Command deleteCommand = null;
			if (parent != null && parent instanceof ANode) {
				deleteCommand = OutlineTreeEditPartFactory.getDeleteCommand((ANode) parent, (ANode) getHost().getModel());
			}
			
			if (deleteCommand != null){
				deleteCommand = new CloseSubeditorsCommand(deleteCommand, (ANode) getHost().getModel());
			}
			return deleteCommand;
		}
		return null;
	}
	
	public CloseSubeditorDeletePolicy(){
		super();
	}
	
};
