package com.jaspersoft.studio.kpi.dialog.pages.range;

import org.eclipse.jface.wizard.Wizard;

public class RangeWizard extends Wizard{
	
	private RangeDefinition element;
	
	private RangeWizardPage page0;
	
	public RangeWizard(RangeDefinition element){
		this.element = element;
	}
	
	public RangeWizard(){
		this(null);
	}
	
	@Override
	public void addPages() {
		page0 = new RangeWizardPage(element);
		addPage(page0);
	}
	
	@Override
	public boolean performFinish() {
		element = page0.regenerateRange();
		return true;
	}
	
	public RangeDefinition getRange(){
		return element;
	}

}
