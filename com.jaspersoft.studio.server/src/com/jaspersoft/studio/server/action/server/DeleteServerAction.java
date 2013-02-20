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
package com.jaspersoft.studio.server.action.server;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class DeleteServerAction extends Action {
	private TreeViewer treeViewer;

	public DeleteServerAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ActionFactory.DELETE.getId());
		setText(Messages.common_delete);
		setDescription("Delete JasperServer Connection");
		setToolTipText("Delete JasperServer connection");
		setImageDescriptor(
				Activator.getDefault().getImageDescriptor("icons/server--minus.png")); //$NON-NLS-1$
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
