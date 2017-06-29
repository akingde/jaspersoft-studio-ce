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
package com.jaspersoft.studio.components.crosstab.model.cell.wizard;

import java.util.List;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;

import net.sf.jasperreports.engine.type.CalculationEnum;

/**
 * Wizard used to define a new measure of the crosstab
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabMeasureWizard extends CrosstabGroupWizard {

	public CrosstabMeasureWizard(MCrosstab crosstab) {
		super(crosstab);
	}

	@Override
	protected WizardCrosstabGroupPage createPage(MCrosstab crosstab, List<String> namesInUse) {
		return new WizardCrosstabMeasurePage(crosstab, namesInUse);
	}
	
	/**
	 * Return the calculation for the measure
	 * 
	 * @return a not null calculation for the measure
	 */
	public CalculationEnum getCalculation(){
		return ((WizardCrosstabMeasurePage)step1).getSelectedCalculation();
	}
}
