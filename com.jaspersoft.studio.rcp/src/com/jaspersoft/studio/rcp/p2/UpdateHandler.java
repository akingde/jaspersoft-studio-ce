/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.rcp.p2;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.equinox.p2.operations.RepositoryTracker;
import org.eclipse.equinox.p2.operations.UpdateOperation;
import org.eclipse.equinox.p2.ui.LoadMetadataRepositoryJob;
import org.eclipse.jface.dialogs.MessageDialog;

import com.jaspersoft.studio.rcp.messages.Messages;

/**
 * UpdateHandler invokes the check for updates UI
 * 
 * @since 3.4
 */
public class UpdateHandler extends PreloadingRepositoryHandler {

	boolean hasNoRepos = false;
	UpdateOperation operation;

	protected void doExecute(LoadMetadataRepositoryJob job) {
		if (hasNoRepos) {
			if (getProvisioningUI().getPolicy().getRepositoriesVisible()) {
				boolean goToSites = MessageDialog.openQuestion(getShell(), Messages.UpdateHandler_NoUpdatesTitle, Messages.UpdateHandler_NoUpdatesMessage);
				if (goToSites) {
					getProvisioningUI().manipulateRepositories(getShell());
				}
			}
			return;
		}
		// Report any missing repositories.
		job.reportAccumulatedStatus();
		if (getProvisioningUI().getPolicy().continueWorkingWithOperation(operation, getShell())) {
			getProvisioningUI().openUpdateWizard(false, operation, job);
		}
	}

	protected void doPostLoadBackgroundWork(IProgressMonitor monitor) throws OperationCanceledException {
		operation = getProvisioningUI().getUpdateOperation(null, null);
		// check for updates
		IStatus resolveStatus = operation.resolveModal(monitor);
		if (resolveStatus.getSeverity() == IStatus.CANCEL)
			throw new OperationCanceledException();
	}

	protected boolean preloadRepositories() {
		hasNoRepos = false;
		RepositoryTracker repoMan = getProvisioningUI().getRepositoryTracker();
		if (repoMan.getKnownRepositories(getProvisioningUI().getSession()).length == 0) {
			hasNoRepos = true;
			return false;
		}
		return super.preloadRepositories();
	}

	@Override
	protected String getProgressTaskName() {
		return Messages.UpdateHandler_TaskName;
	}
}
