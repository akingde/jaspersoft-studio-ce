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
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.wizard.DataAdapterWizard;
import com.jaspersoft.studio.data.wizard.DataAdapterWizardDialog;

public class EditDataAdapterAction extends Action {
	public static final String ID = "editdataAdapteraction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public EditDataAdapterAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText("Edit DataAdapter");
		setDescription("Edit DataAdapter");
		setToolTipText("Edit DataAdapter");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/properties_view.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/properties_view.gif")); //$NON-NLS-1
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		return firstElement != null && (firstElement instanceof MDataAdapter);
	}

	@Override
	public void run() {
		Object obj = ((TreeSelection) treeViewer.getSelection()).getFirstElement();
		if (obj instanceof MDataAdapter) {
			DataAdapter dataAdapter = ((MDataAdapter) obj).getDataAdapter();
			DataAdapterWizard wizard = new DataAdapterWizard(dataAdapter);
			DataAdapterWizardDialog dialog = new DataAdapterWizardDialog(Display.getCurrent().getActiveShell(), wizard);
			wizard.setWizardDialog(dialog);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				
				DataAdapter modifiedDataAdapter = wizard.getDataAdapter();
				DataAdapterManager.saveDataAdapter(modifiedDataAdapter);
				treeViewer.refresh(true);
			}
		}
	}
}
