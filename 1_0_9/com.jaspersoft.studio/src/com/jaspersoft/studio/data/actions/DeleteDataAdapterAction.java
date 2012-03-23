/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.actions.ActionFactory;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.utils.UIUtils;

public class DeleteDataAdapterAction extends Action {
	private TreeViewer treeViewer;

	public DeleteDataAdapterAction(TreeViewer treeViewer) {
		super();
		setId(ActionFactory.DELETE.getId());
		this.treeViewer = treeViewer;
		setText("Delete DataAdapter");
		setDescription("Delete DataAdapter");
		setToolTipText("Delete DataAdapter");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE)); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE)); //$NON-NLS-1

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
				DataAdapterDescriptor m = mDataAdapter.getDataAdapter();
				if (storage == null)
					storage = ((MDataAdapters) mDataAdapter.getParent()).getValue();
				if (storage != null)
					storage.removeDataAdapter(m);
				treeViewer.refresh(true);
			}
		}
	}
}
