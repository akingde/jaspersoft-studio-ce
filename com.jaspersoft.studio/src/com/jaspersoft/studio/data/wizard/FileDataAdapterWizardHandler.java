/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.NewWizardAction;
import org.eclipse.ui.wizards.IWizardRegistry;

import com.jaspersoft.studio.wizards.JSSWizardHandler;

/**
 * Custom wizard handler for the command that launches the wizard that creates 
 * a new data-adapter from file.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class FileDataAdapterWizardHandler extends JSSWizardHandler{

	@Override
	protected void createAndOpenWizardDialog(Shell parent, IWizard wizard) {
		if(wizard instanceof NewFileDataAdapterWizard){
			DataAdapterWizardDialog dialog = new DataAdapterWizardDialog(parent, wizard);
			((NewFileDataAdapterWizard)wizard).setWizardDialog(dialog);
			dialog.open();
		}
		else{
			super.createAndOpenWizardDialog(parent, wizard);
		}
	}

	@Override
	protected IAction createWizardChooserDialogAction(IWorkbenchWindow window) {
		return new NewWizardAction(window);
	}

	@Override
	protected String getWizardIdParameterId() {
		return NewFileDataAdapterWizard.WIZARD_ID; 
	}

	@Override
	protected IWizardRegistry getWizardRegistry() {
		return PlatformUI.getWorkbench().getNewWizardRegistry();
	}
}
