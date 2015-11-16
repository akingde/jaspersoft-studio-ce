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
package com.jaspersoft.studio.server.action.server;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class DeleteServerAction extends Action {
	
	public static final String ID = "com.jaspersoft.studio.server.action.resource.deleteServerAction";
	
	private TreeViewer treeViewer;

	public DeleteServerAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText(com.jaspersoft.studio.messages.Messages.common_delete);
		setDescription(Messages.DeleteServerAction_desc);
		setToolTipText(Messages.DeleteServerAction_desc); //$NON-NLS-1$
		setImageDescriptor(Activator.getDefault().getImageDescriptor(
				"icons/server--minus.png")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		return firstElement != null && (firstElement instanceof MServerProfile);
	}

	@Override
	public void run() {
		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		if (!UIUtils.showDeleteConfirmation())
			return;
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MServerProfile) {
				ServerManager.removeServerProfile((MServerProfile) obj);
				treeViewer.refresh(true);
			}
		}
	}
}
