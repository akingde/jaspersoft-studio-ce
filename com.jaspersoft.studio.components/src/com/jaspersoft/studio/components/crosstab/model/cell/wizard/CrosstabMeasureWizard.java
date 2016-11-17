/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
