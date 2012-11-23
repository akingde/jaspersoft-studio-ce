/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IPastable;

public class PasteAction extends SelectionAction {

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

	private Command createPasteCommand(List<?> selectedObjects) {
		for (Object selection : selectedObjects) {
			Command cmd = getPasteComand(selection);
			if (cmd != null)
				return cmd;
			if (selection instanceof StructuredSelection) {
				StructuredSelection s = (StructuredSelection) selection;
				for (Iterator<?> it = s.iterator(); it.hasNext();) {
					Object o = it.next();
					cmd = getPasteComand(o);
					if (cmd != null)
						return cmd;
				}
			}
		}
		return null;
	}

	private Command getPasteComand(Object selection) {
		if (selection instanceof EditPart) {
			ANode n = (ANode) ((EditPart) selection).getModel();
			IPastable past = getParent2Paste(n);
			if (past != null)
				return new PasteCommand(past);
		} else if (selection instanceof ANode) {
			IPastable past = getParent2Paste((ANode) selection);
			if (past != null)
				return new PasteCommand(past);
		}
		return null;
	}

	private IPastable getParent2Paste(ANode n) {
		while (n != null) {
			if (n instanceof IPastable)
				return (IPastable) n;
			n = (ANode) n.getParent();
		}
		return null;
	}

	@Override
	protected boolean calculateEnabled() {
		Command command = createPasteCommand(getSelectedObjects());
		return command != null && command.canExecute();
	}

	@Override
	public void run() {
		Command command = createPasteCommand(getSelectedObjects());
		if (command != null && command.canExecute())
			execute(command);
	}

}
