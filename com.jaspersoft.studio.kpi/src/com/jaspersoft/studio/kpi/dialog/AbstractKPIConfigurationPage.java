package com.jaspersoft.studio.kpi.dialog;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.widgets.Composite;

public abstract class AbstractKPIConfigurationPage {

	protected Composite mainComposite;
	
	protected JasperDesign jd;
	
	public Composite getComposite(Composite container, JasperDesign jd){
		if (mainComposite == null){
			this.jd = jd;
			mainComposite = createComposite(container);
		}
		return mainComposite;
	}
	
	public abstract String getName();
	
	public String getTitle(){
		return getName();
	}
	
	protected abstract Composite createComposite(Composite parent);

}
