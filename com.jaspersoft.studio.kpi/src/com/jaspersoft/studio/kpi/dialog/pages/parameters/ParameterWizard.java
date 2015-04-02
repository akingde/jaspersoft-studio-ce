package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.Wizard;

public class ParameterWizard extends Wizard{

	private JasperDesign jd;
	
	private ParameterDefinition param;
	
	private ParameterWizardPage page0;
	
	public ParameterWizard(ParameterDefinition param, JasperDesign jd){
		this.param = param;
		this.jd = jd;
	}
	
	public ParameterWizard(JasperDesign jd){
		this(null, jd);
	}
	
	@Override
	public void addPages() {
		page0 = new ParameterWizardPage(param, jd);
		addPage(page0);
	}
	
	@Override
	public boolean performFinish() {
		param = page0.regenerateParameter();
		return true;
	}
	
	public ParameterDefinition getParameter(){
		return param;
	}

}
