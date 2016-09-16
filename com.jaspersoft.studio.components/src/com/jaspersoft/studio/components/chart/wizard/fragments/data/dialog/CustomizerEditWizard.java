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
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Wizard used to edit the properties of a customizer
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerEditWizard extends Wizard {
	
	/**
	 * Page with the controls to edit the customizer
	 */
	private EditCustomizerPage editPage;
	
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
	
	@Override
	public void addPages() {
		editPage = new EditCustomizerPage(jConfig, ec, dto){
			
			@Override
			protected boolean isUsingCustomDefinition() {
				return editedElement.isOnlyClass();
			}
			
			@Override
			protected ChartCustomizerDefinition getCurrentDefinition() {
				return editedElement;
			}
		};
		if (editedElement.isOnlyClass()){
			editPage.setRawClass(editedElement.getRawClass());
		}
		addPage(editPage);
	}
	
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
			ChartCustomizerDefinition result = new ChartCustomizerDefinition(editPage.getRawClass(), key);
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
		return editPage.isPageComplete();
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
}
