/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.wizard;

import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * Wizard to add the missing parameters to connect a dataset and its datasets run to a domain
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
	public ConnectToDatasetWizard(ConnectToDatasetWizardPage connectionPage) {
		super();
		setWindowTitle("Connect to Main Dataset");
		setNeedsProgressMonitor(true);
		step1 = connectionPage;
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

	/*@Override
	public boolean performFinish() {
		step1.doAction();
		return super.performFinish();
	}*/


}
