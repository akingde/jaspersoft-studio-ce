/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.book.model.commands.RemoveChildrenCommand;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

public class DeleteBookPartAction extends ACachedSelectionAction {

	public static final String ID = "delete_book_part"; //$NON-NLS-1$
	
	public DeleteBookPartAction(IWorkbenchPart part) {
		super(part);
	}

	@Override
	protected void init() {
		super.init();
		setText(Messages.DeleteBookPartAction_actionTitle);
		setToolTipText(Messages.DeleteBookPartAction_actionTooltip);
		setId(ID);
		setImageDescriptor(JRBookActivator.getDefault().getImageDescriptor("/icons/delete.gif")); //$NON-NLS-1$
		setEnabled(false);
	}
	
	@Override
	protected Command createCommand() {
		List<Object> parts = editor.getSelectionCache().getSelectionModelForType(MReportPart.class);
		CompoundCommand cc = new CompoundCommand();
		for(Object rawPart : parts){
			MReportPart part = (MReportPart)rawPart;
			RemoveChildrenCommand removeCommand = new RemoveChildrenCommand((MReportPartContainer)part.getParent(), part);
			cc.add(removeCommand);
		}
		return cc.isEmpty() ? null : cc;
	}

}
