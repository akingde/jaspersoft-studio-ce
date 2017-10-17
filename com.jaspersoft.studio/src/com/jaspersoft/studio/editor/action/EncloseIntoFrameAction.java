/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.command.EncloseIntoFrameCommand;

/**
 * Action to enclose the selected elements into a frame. All
 * the selected elements must have the same parent
 * 
 * @author Orlandin Marco
 * 
 */
public class EncloseIntoFrameAction extends ACachedSelectionAction {
  
  public static final String ID = "EncloseIntoFrame"; //$NON-NLS-1$
	
	public EncloseIntoFrameAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(true);
	}
	
	@Override
	protected void init() {
		super.init();
		setText(Messages.EncloseIntoFrameAction_actionTitle);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/enclose_into_frame.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	protected Command createCommand() {
		List<Object> mGraphElements = editor.getSelectionCache().getSelectionModelForType(MGraphicElement.class);
		if (mGraphElements.isEmpty()) return null;
		ANode parentCheck = ((MGraphicElement)mGraphElements.get(0)).getParent();
		EncloseIntoFrameCommand command = new EncloseIntoFrameCommand(parentCheck);
		for(Object rawNode : mGraphElements){
			MGraphicElement node = (MGraphicElement)rawNode;
			if (parentCheck == node.getParent()){
				command.addNode(node);
			} else {
				return null;
			}
		}
		return command;
	}
}
