/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.actions;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.MDataAdapter;
import com.jaspersoft.studio.data.MDataAdapters;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.wizard.DataAdapterWizard;
import com.jaspersoft.studio.data.wizard.DataAdapterWizardDialog;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.statistics.UsageStatisticsIDs;

public class CreateDataAdapterAction extends Action implements ICheatSheetAction {
	public static final String ID = "createdataAdapteraction"; //$NON-NLS-1$

	public CreateDataAdapterAction() {
		this(null);
	}

	private TreeViewer treeViewer;

	public CreateDataAdapterAction(TreeViewer treeViewer) {
		super();
		this.treeViewer = treeViewer;
		setId(ID);
		setText(Messages.CreateDataAdapterAction_actionName);
		setDescription(Messages.CreateDataAdapterAction_actionDescription);
		setToolTipText(Messages.CreateDataAdapterAction_actionToolTip);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/data_source_add.png")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/data_source_add.png")); //$NON-NLS-1$
	}

	@Override
	public void run() {
		ADataAdapterStorage storage = null;
		if (treeViewer != null) {
			TreeSelection s = (TreeSelection) treeViewer.getSelection();
			TreePath[] p = s.getPaths();
			for (int i = 0; i < p.length;) {
				Object obj = p[i].getLastSegment();
				if (obj instanceof MDataAdapters) {
					storage = ((MDataAdapters) obj).getValue();
				} else if (obj instanceof MDataAdapter) {
					storage = ((MDataAdapters) ((MDataAdapter) obj).getParent()).getValue();
				}
				break;
			}
		}
		if (storage == null)
			storage = DataAdapterManager.getPreferencesStorage();

		DataAdapterWizard wizard = new DataAdapterWizard(storage);
		DataAdapterWizardDialog dialog = new DataAdapterWizardDialog(UIUtils.getShell(), wizard);
		wizard.setWizardDialog(dialog);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			newDataAdapter = wizard.getDataAdapter();
			DataAdapterManager.getPreferencesStorage().addDataAdapter(newDataAdapter); //$NON-NLS-1$
			JaspersoftStudioPlugin.getInstance().getUsageManager().audit(newDataAdapter.getClass().getName(), UsageStatisticsIDs.CATEGORY_DA);
		}
	}

	private DataAdapterDescriptor newDataAdapter;

	public DataAdapterDescriptor getNewDataAdapter() {
		return newDataAdapter;
	}

	public void run(String[] params, ICheatSheetManager manager) {
		run();
		notifyResult(true);
	}

}
