/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Wizard used to edit the properties of a customizer. It uses two different pages
 * if the edited customizer has a configuration or it is a raw class
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerEditWizard extends Wizard {
	
	/**
	 * Page with the controls to edit the customizer with a configuration
	 */
	private EditCustomizerPage editCustomizerPage = null;
	
	/**
	 * Page used to edit a raw class customizer
	 */
	private EditClassPage editClassPage = null;
	
	/**
	 * The {@link JasperReportsConfiguration} of the current report
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The {@link ExpressionContext} used to the expressions control
	 */
	private ExpressionContext ec;
	
	/**
	 * The DTO where the changes are done
	 */
	private CustomizerPropertyExpressionsDTO dto;

	/**
	 * The {@link ChartCustomizerDefinition} of the edited customizer
	 */
	private ChartCustomizerDefinition editedElement;
	
	/**
	 * Create the wizard
	 * 
	 * @param editedElement The {@link ChartCustomizerDefinition} of the edited customizer
	 * @param ec The expression context for the expression control
	 * @param dto the dto where the change will be done, should be a copy to avoid change even when the wizard is closed with cance
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 */
	public CustomizerEditWizard(ChartCustomizerDefinition editedElement, ExpressionContext ec, CustomizerPropertyExpressionsDTO dto, JasperReportsConfiguration jConfig){
		this.editedElement = editedElement;
		this.ec = ec;
		this.dto = dto;
		this.jConfig = jConfig;
	}
	
	/**
	 * Create the correct edit page depending is the edited element is a raw class or 
	 * a customzier with a ui definition
	 */
	@Override
	public void addPages() {
		if (editedElement.isOnlyClass()){
			editClassPage = new EditClassPage(dto, jConfig);
			editClassPage.setRawClass(editedElement.getRawClass());
			addPage(editClassPage);
		} else {
			editCustomizerPage = new EditCustomizerPage(jConfig, ec, dto);
			editCustomizerPage.setEditedElement(editedElement);
			addPage(editCustomizerPage);
		}
	}
	
	/**
	 * Return the change {@link CustomizerPropertyExpressionsDTO}
	 * 
	 * @return a not null object with all the values changed during the wizard
	 */
	public CustomizerPropertyExpressionsDTO getEditedDTO(){
		return dto;
	}
	
	/**
	 * Return the definition update in case the user edited the classname during the wizard
	 * 
	 * @return a not null {@link ChartCustomizerDefinition}
	 */
	public ChartCustomizerDefinition getDefinition(){
		if (editedElement.isOnlyClass()){
			//the class could be changed
			String key = editedElement.getKey();
			ChartCustomizerDefinition result = new ChartCustomizerDefinition(editClassPage.getRawClass(), key, editedElement.isPropertiesCustomizer());
			return result;
		} else {
			return editedElement;
		}
	}
	
	/**
	 * The wizard can finish when the user has inserted valid data for the definition
	 */
	@Override
	public boolean canFinish() {
		if (editedElement.isOnlyClass()){
			return editClassPage.isPageComplete();
		} else {
			return editCustomizerPage.isPageComplete();	
		}
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
}
