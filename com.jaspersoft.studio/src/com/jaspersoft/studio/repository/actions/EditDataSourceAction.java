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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.repository.RepositoryManager;
import com.jaspersoft.studio.repository.wizzard.DatasourceEditor;

public class EditDataSourceAction extends Action {
	public static final String ID = "editdatasourceaction";
	private TreeViewer treeViewer;

	public EditDataSourceAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText("Edit ...");
		setDescription("Edit Datasource");
		setToolTipText("Edi datasource");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/data_source.gif"));
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/data_source.gif"));

	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof AMDatasource);
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		if (obj instanceof AMDatasource) {
			AMDatasource value = (AMDatasource) obj;
			ANode parent = (ANode) value.getParent();
			// clone
			DatasourceEditor wizard = new DatasourceEditor();
			wizard.setValue(value.getCopy());
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				AMDatasource n = wizard.getValue();
				value.setParent(null, -1);
				n.setParent(parent, -1);
				// delete the old one
				// create physical file
				RepositoryManager.saveResource(value);
			}
			treeViewer.refresh(true);
		}

	}
}
