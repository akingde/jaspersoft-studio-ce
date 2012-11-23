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
package com.jaspersoft.studio.data.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;

public class DuplicateDataAdapterAction extends Action {
	public static final String ID = "duplicatedataAdapteraction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public DuplicateDataAdapterAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText("Duplicate DataAdapter");
		setDescription("Duplicate DataAdapter");
		setToolTipText("Duplicate DataAdapter");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(ISharedImages.IMG_TOOL_COPY)); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(ISharedImages.IMG_TOOL_COPY)); //$NON-NLS-1
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof MDataAdapter);
	}

	@Override
	public void run() {
		TreeSelection s = (TreeSelection) treeViewer.getSelection();
		TreePath[] p = s.getPaths();
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MDataAdapter) {
				MDataAdapter mDataAdapter = (MDataAdapter) obj;
				ADataAdapterStorage storage = ((MDataAdapters) mDataAdapter.getParent()).getValue();
				DataAdapterDescriptor copyDataAdapter = DataAdapterManager.cloneDataAdapter(mDataAdapter.getDataAdapter());
				String name = "copy_of_" + copyDataAdapter.getName();
				for (int j = 1; j < 1000; j++) {
					if (storage.isDataAdapterNameValid(name))
						break;
					name = "copy_of_" + copyDataAdapter.getName() + j;
				}
				copyDataAdapter.getDataAdapter().setName(name);
				storage.addDataAdapter("", copyDataAdapter);
				treeViewer.refresh(true);
			}
		}
	}
}
