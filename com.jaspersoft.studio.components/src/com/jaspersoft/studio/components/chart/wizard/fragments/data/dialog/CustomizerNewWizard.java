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
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRChartPlot;

/**
 * Wizard to crate a new chart customizer definition
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerNewWizard extends Wizard {
	
	/**
	 * Page where the customizer can be selected
	 */
	private SelectCustomizerPage selectionPage;
	
	/**
	 * Page where the properties of the customizer can be edited
	 */
	private EditCustomizerPage editPage;
	
	/**
	 * The current {@link JasperReportsConfiguration}
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The {@link ExpressionContext} used in the expression widget
	 */
	private ExpressionContext ec;
	
	/**
	 * The DTO changed by the edit operation
	 */
	private CustomizerPropertyExpressionsDTO dto;
	
	/**
	 * The key used by the new customizer
	 */
	private String definitionKey;
	
	private JRChartPlot selectedChartPlot;
	
	/**
	 * Create the new customizer wizard
	 *  
	 * @param newDefinitionKey the unique key used for the new customizer
	 * @param ec the expression context
	 * @param dto the dto changed by the new operation, should be a copy in case the user press cancel
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 * @param selectedChartPlot the plot of the modfied chart
	 */
	public CustomizerNewWizard(String newDefinitionKey, ExpressionContext ec, CustomizerPropertyExpressionsDTO dto, JasperReportsConfiguration jConfig, JRChartPlot selectedChartPlot){
		definitionKey = newDefinitionKey;
		this.selectedChartPlot = selectedChartPlot;
		this.ec = ec;
		this.dto = dto;
		this.jConfig = jConfig;
	}
	
	@Override
	public void addPages() {
		selectionPage = new SelectCustomizerPage(jConfig, definitionKey, dto, selectedChartPlot);
		editPage = new EditCustomizerPage(jConfig, ec, dto){
			
			protected boolean isUsingCustomDefinition() {
				return selectionPage.isUsingCustomDefinition();
			}
			
			@Override
			protected ChartCustomizerDefinition getCurrentDefinition() {
				return selectionPage.getSelectedDefinition();
			}
			
		};
		addPage(selectionPage);
		addPage(editPage);
	}
	
	/**
	 * Return the modified DTO
	 * 
	 * @return a not null {@link PropertyExpressionDTO}
	 */
	public CustomizerPropertyExpressionsDTO getEditedDTO(){
		return dto;
	}
	
	/**
	 * Get the definition selected by the user
	 * 
	 * @return a not null {@link ChartCustomizerDefinition}
	 */
	public ChartCustomizerDefinition getDefinition(){
		if (selectionPage.isUsingCustomDefinition()){
			String key = selectionPage.getSelectedDefinition().getKey();
			ChartCustomizerDefinition result = new ChartCustomizerDefinition(editPage.getRawClass(), key);
			return result;
		} else {
			return selectionPage.getSelectedDefinition();
		}
	}
	
	/**
	 * Can finish if the user has selected at least a definition and, in case the selected definition
	 * is the raw class name, if he inserted a not empty classname
	 * 
	 * @return true if the wizard can finish, false otherwise
	 */
	@Override
	public boolean canFinish() {
		if (selectionPage.isUsingCustomDefinition()){
			return editPage.getRawClass() != null && !editPage.getRawClass().trim().isEmpty();
		} else {
			return selectionPage.isPageComplete();
		}
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}

}
