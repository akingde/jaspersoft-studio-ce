/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards.obj2text;

import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.wizard.Wizard;

public class Obj2TextWizard extends Wizard {
	private CalculationEnum calculation;
	private String[] names;
	private Obj2TextPage step1;

	public Obj2TextWizard(String[] names) {
		super();
		this.names = names;
		setWindowTitle("TextField Wizard");
	}

	public CalculationEnum getCalculation() {
		return calculation;
	}

	@Override
	public void addPages() {
		step1 = new Obj2TextPage(names);
		addPage(step1);
	}

	@Override
	public boolean performFinish() {
		calculation = step1.getCalculation();
		return true;
	}

}
