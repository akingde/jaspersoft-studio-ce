/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.wizard;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.engine.type.CalculationEnum;
import net.sf.jasperreports.engine.type.NamedEnum;

/**
 * Wizard page where the user can define the name and the expression and calculation of a crosstab measure.
 * 
 * @author Orlandin Marco
 *
 */
public class WizardCrosstabMeasurePage extends WizardCrosstabGroupPage {
	
	/**
	 * The combo to select the calculatin
	 */
	private Combo calculationTypeCombo;
	
	/**
	 * The available calculation types
	 */
	private NamedEnum[] calculationTypes = (NamedEnum[]) CalculationEnum.AVERAGE.getDeclaringClass().getEnumConstants();
	
	/**
	 * The currently selected calculation
	 */
	private CalculationEnum selectedEnum = (CalculationEnum)calculationTypes[0];

	public WizardCrosstabMeasurePage(MCrosstab crosstab, List<String> alreadyUsedNames) {
		super(crosstab, alreadyUsedNames);
		setTitle(Messages.common_measure);
		setDescription(Messages.WizardCrosstabMeasurePage_dialogDescription);
	}
	
	/**
	 * Create a combo control for the calculation
	 */
	@Override
	protected void createAdditionalControls(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout containerLayout = new GridLayout(2, false);
		containerLayout.marginWidth = 0;
		container.setLayout(containerLayout);
		
		Label comboLabel = new Label(container, SWT.NONE);
		comboLabel.setText(Messages.common_calculation);
		
		calculationTypeCombo = new Combo(container, SWT.READ_ONLY);
		calculationTypeCombo.setItems(NamedEnumPropertyDescriptor.getEnumItems(calculationTypes, NullEnum.NOTNULL));
		calculationTypeCombo.select(0);
		calculationTypeCombo.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = calculationTypeCombo.getSelectionIndex();
				selectedEnum = (CalculationEnum)calculationTypes[selectedIndex];
			}
		});
	}
	
	/**
	 * Return the calculation for the measure
	 * 
	 * @return a not null calculation for the measure
	 */
	public CalculationEnum getSelectedCalculation(){
		return selectedEnum;
	}
	
	//Return the strings used in the dialog, to allow it to use in more context
	
	protected String getGroupNameLabel(){
		return Messages.common_measure;
	}
	
	protected String getReportObjectLabel(){
		return Messages.WizardCrosstabMeasurePage_optionReportObjectText;
	}
	
	protected String getExpressionLabel(){
		return Messages.WizardCrosstabMeasurePage_optionExpressionText;
	}
	
	protected String getEmptyNameError(){
		return Messages.WizardCrosstabMeasurePage_errorEmptyName;
	}
}
