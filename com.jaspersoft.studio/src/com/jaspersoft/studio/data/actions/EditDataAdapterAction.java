/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.actions;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.wizard.DataAdapterWizard;
import com.jaspersoft.studio.data.wizard.DataAdapterWizardDialog;
import com.jaspersoft.studio.messages.Messages;

public class EditDataAdapterAction extends Action {
	public static final String ID = "editdataAdapteraction"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public EditDataAdapterAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText(Messages.EditDataAdapterAction_editName);
		setDescription(Messages.EditDataAdapterAction_editDescription);
		setToolTipText(Messages.EditDataAdapterAction_editTooltip);
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/properties_view.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/properties_view.gif")); //$NON-NLS-1 //$NON-NLS-1$
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

			DataAdapterDescriptor dataAdapter = mDataAdapter.getValue();
			String oldName = dataAdapter.getName();
			DataAdapterWizard wizard = new DataAdapterWizard(dataAdapter, storage);
			DataAdapterWizardDialog dialog = new DataAdapterWizardDialog(UIUtils.getShell(), wizard);
			wizard.setWizardDialog(dialog);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				DataAdapterDescriptor modifiedDataAdapter = wizard.getDataAdapter();
				storage.editDataAdapter(oldName, modifiedDataAdapter);
				treeViewer.refresh(true);
			}
		}
	}
}
