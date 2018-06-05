/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
 * Wizard to crate a new chart customizer definition. It uses different pages
 * to edit the customizer if it has a UI definition or if it is a raw class
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
	 * Page where the properties of the customizer can be edited if it has a UI definition
	 */
	private EditCustomizerPage editCustomizerPage = null;

	/**
	 * Page where the properties of the customizer can be edited if it is a raw class
	 */
	private EditClassPage editClassPage = null;
	
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
	
	/**
	 * The plot of the edited chart
	 */
	private JRChartPlot selectedChartPlot;
	
	/**
	 * Create the new customizer wizard
	 *  
	 * @param newDefinitionKey the unique key used for the new customizer
	 * @param ec the expression context
	 * @param dto the dto changed by the new operation, should be a copy in case the user press cancel
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 * @param selectedChartPlot the plot of the modified chart
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
		editCustomizerPage = new EditCustomizerPage(jConfig, ec, dto);
		editClassPage = new EditClassPage(dto, jConfig);
		addPage(selectionPage);
		addPage(editCustomizerPage);
		addPage(editClassPage);
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
			ChartCustomizerDefinition result = new ChartCustomizerDefinition(editClassPage.getRawClass(), key, true);
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
			return editClassPage.getRawClass() != null && !editClassPage.getRawClass().trim().isEmpty();
		} else {
			if (selectionPage.isPageComplete()){
				if (selectionPage.getSelectedDefinition().getDescriptor().hasWidgets()){
					//force the dialog to advance to the configuration page
					if (getContainer().getCurrentPage() == editCustomizerPage){
						return editCustomizerPage.isPageComplete();
					} else return false;
				} else return true;		
			} else return false;
		}
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
	
	/**
	 * Return the page used to edit a customizer trough a UI defined by it
	 * 
	 * @return a not null {@link EditCustomizerPage}
	 */
	protected EditCustomizerPage getEditCustomizerPage(){
		return editCustomizerPage;
	}

	/**
	 * Return the page used to edit a customizer class name
	 * 
	 * @return a not null {@link EditClassPage}
	 */
	protected EditClassPage getEditClassPage(){
		return editClassPage;
	}
}
