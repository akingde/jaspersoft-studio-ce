package com.jaspersoft.studio.wizards;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardPage;

public abstract class JSSWizardPage extends WizardPage {

	protected JSSWizardPage(String pageName) {
		super(pageName);
	}

	@Override
	public boolean canFlipToNextPage() {
		IWizard wizard = getWizard();
		if (wizard != null && wizard instanceof JSSWizard)
			return isPageComplete() && ((JSSWizard) wizard).hasNextPage(this) != null;
		return super.canFlipToNextPage();
	}
}
