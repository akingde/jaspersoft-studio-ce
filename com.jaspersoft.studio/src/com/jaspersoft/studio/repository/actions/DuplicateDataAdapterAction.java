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
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.repository.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.ISharedImages;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.MDataAdapter;

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
				DataAdapter copyDataAdapter = DataAdapterManager.cloneDataAdapter(((MDataAdapter) obj).getDataAdapter());
				copyDataAdapter.setName("copy_of_"+copyDataAdapter.getName());
				DataAdapterManager.addDataAdapter(copyDataAdapter);
				treeViewer.refresh(true);
			}
		}
	}
}
