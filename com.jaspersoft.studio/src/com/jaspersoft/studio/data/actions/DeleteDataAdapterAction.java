/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class DeleteDataAdapterAction extends Action {
	
	public static final String ID = "com.jaspersoft.studio.data.actions.deleteAdapterAction";
	
	private TreeViewer treeViewer;

	public DeleteDataAdapterAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		this.treeViewer = treeViewer;
		setText(Messages.DeleteDataAdapterAction_deleteName);
		setDescription(Messages.DeleteDataAdapterAction_deleteDescription);
		setToolTipText(Messages.DeleteDataAdapterAction_deleteTooltip);
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE)); //$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE)); //$NON-NLS-1

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

		if (!UIUtils.showDeleteConfirmation())
			return;
		ADataAdapterStorage storage = null;
		for (int i = 0; i < p.length; i++) {
			Object obj = p[i].getLastSegment();
			if (obj instanceof MDataAdapter) {
				MDataAdapter mDataAdapter = (MDataAdapter) obj;
				DataAdapterDescriptor m = mDataAdapter.getValue();
				if (storage == null)
					storage = ((MDataAdapters) mDataAdapter.getParent()).getValue();
				if (storage != null)
					storage.removeDataAdapter(m);
				treeViewer.refresh(true);
			}
		}
	}
}
