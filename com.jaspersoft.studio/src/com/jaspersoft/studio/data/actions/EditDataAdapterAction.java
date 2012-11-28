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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
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
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/properties_view.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/properties_view.gif")); //$NON-NLS-1
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
			MDataAdapter mDataAdapter = (MDataAdapter) obj;
			ADataAdapterStorage storage = ((MDataAdapters) mDataAdapter.getParent()).getValue();

			DataAdapterDescriptor dataAdapter = mDataAdapter.getDataAdapter();
			String key = storage.getUrl(dataAdapter);
			DataAdapterWizard wizard = new DataAdapterWizard(dataAdapter, storage);
			DataAdapterWizardDialog dialog = new DataAdapterWizardDialog(Display.getCurrent().getActiveShell(), wizard);
			wizard.setWizardDialog(dialog);
			dialog.create();
			if (dialog.open() == Dialog.OK) {

				DataAdapterDescriptor modifiedDataAdapter = wizard.getDataAdapter();

				storage.addDataAdapter(key, modifiedDataAdapter);
				treeViewer.refresh(true);
			}
		}
	}
}
