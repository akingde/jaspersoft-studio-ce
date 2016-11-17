/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.wizard;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * Wizard to add the missing parameters to connect a dataset and its datasets run to the main dataset. The
 * wizard by itself doesn't do anything when the finish button is pressed but it can provide the command to
 * do the update
 * 
 * @author Orlandin Marco
 *
 */
public class ConnectToDatasetWizard extends JSSWizard {

	/**
	 * First page of the wizard
	 */
	private ConnectToDatasetWizardPage step1;
	
	/**
	 * Create the wizard
	 * 
	 * @param connectedDataset the selected dataset
	 */
	public ConnectToDatasetWizard(MDataset connectedDataset) {
		super();
		setWindowTitle("Connect to Main Dataset");
		setNeedsProgressMonitor(true);
		step1 = new ConnectToDatasetWizardPage(connectedDataset);
	}


	@Override
	public void addPages() {
		addPage(step1);
	}


	/**
	 * This method force the user to go next and not finish if the selection in the first step is not an empty dataset...
	 */
	@Override
	public boolean canFinish() {
		return step1.canFinish();
	}
	
	/**
	 * Return the command to execute to synchronize the dataset parameters
	 * 
	 * @return The command
	 */
	public Command getCommand(){
		return step1.getCommand();
	}

	/*@Override
	public boolean performFinish() {
		step1.doAction();
		return super.performFinish();
	}*/
}
