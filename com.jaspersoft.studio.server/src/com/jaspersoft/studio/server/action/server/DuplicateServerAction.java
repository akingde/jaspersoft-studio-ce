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
package com.jaspersoft.studio.server.action.server;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ServerManager;
import com.jaspersoft.studio.server.model.server.MServerProfile;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.utils.UIUtils;

public class DuplicateServerAction extends Action {
	public static final String ID = "duplicateServerAction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public DuplicateServerAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText("Duplicate JasperServer Connection");
		setDescription("Duplicate JasperServer Connection");
		setToolTipText("Duplicate JasperServer connection");
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor(ISharedImages.IMG_TOOL_COPY)); //$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor(ISharedImages.IMG_TOOL_COPY)); //$NON-NLS-1
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
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MServerProfile) {
				try {
					ServerProfile copy = (ServerProfile) ((MServerProfile) obj)
							.getValue().clone();
					copy.setName("Copy_Of_" + copy.getName());

					MServerProfile copyDataAdapter = new MServerProfile(
							(ANode) ((MServerProfile) obj).getParent(), copy);
					ServerManager.addServerProfile(copyDataAdapter);
					treeViewer.refresh(true);
				} catch (CloneNotSupportedException e) {
					UIUtils.showError(e);
				}

			}
		}
	}
}
