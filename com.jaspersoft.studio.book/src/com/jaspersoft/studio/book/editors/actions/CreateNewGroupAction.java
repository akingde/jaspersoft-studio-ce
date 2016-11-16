/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.commands.CreateGroupForBookCommand;
import com.jaspersoft.studio.editor.outline.actions.ACreateAction;
import com.jaspersoft.studio.model.MReport;

public class CreateNewGroupAction extends ACreateAction {

	public static final String ID = "book.create_group"; //$NON-NLS-1$

	public CreateNewGroupAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateNewGroupAction_actionTitle);
		setToolTipText(Messages.CreateNewGroupAction_actionTooltip);
		setId(ID);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		setDisabledImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD_DISABLED));
		setEnabled(false);
	}
	
	@Override
	protected boolean calculateEnabled() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if(obj instanceof AbstractEditPart && ((AbstractEditPart)obj).getModel() instanceof MReport) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void run() {
		ISelection s = getSelection();
		if (s instanceof StructuredSelection) {
			Object obj = ((StructuredSelection) s).getFirstElement();
			if(obj instanceof AbstractEditPart) {
				Object model = ((AbstractEditPart)obj).getModel();
				if (model instanceof MReport) {
					MReport report = (MReport) model;
					CreateGroupForBookCommand cmd = new CreateGroupForBookCommand(report);
					if(cmd.canExecute() && cmd.openDialog()==Dialog.OK){
						getCommandStack().execute(cmd);
					}
				}
			}
		}		
	}
}
