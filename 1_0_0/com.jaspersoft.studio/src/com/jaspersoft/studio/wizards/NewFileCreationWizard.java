package com.jaspersoft.studio.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class NewFileCreationWizard extends WizardNewFileCreationPage {

	public NewFileCreationWizard(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}

	@Override
	public boolean validatePage() {
		return super.validatePage();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		validatePage();
	}
}
