/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.cell.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * Wizard used to define a new group (column or row) of the crosstab
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabGroupWizard extends Wizard implements IExpressionContextSetter {

	/**
	 * The expression context when the expression editor is opened
	 */
	private ExpressionContext expContext;

	/**
	 * Step where the user define the group name and its expression
	 */
	protected WizardCrosstabGroupPage step1;
	
	/**
	 * Crosstab where the group is added
	 */
	private MCrosstab crosstab;
	
	/**
	 * Create the wizard
	 * 
	 * @param crosstab crosstab where the group is added, must be not null
	 */
	public CrosstabGroupWizard(MCrosstab crosstab) {
		super();
		this.crosstab = crosstab;
		JRDatasetRun datasetRun = crosstab.getValue().getDataset().getDatasetRun();
		if (datasetRun != null){
			String datasetName = datasetRun.getDatasetName();
			JRDataset dataset = crosstab.getJasperDesign().getDatasetMap().get(datasetName);
			expContext = new ExpressionContext((JRDesignDataset)dataset, crosstab.getJasperConfiguration());
		} else {
			expContext = new ExpressionContext(crosstab.getJasperDesign().getMainDesignDataset(), crosstab.getJasperConfiguration());
		}
		setWindowTitle(Messages.BandGroupWizard_group_band);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		List<String> namesInUse = new ArrayList<String>();
		for(JRCrosstabRowGroup rowGroup : crosstab.getValue().getRowGroups()){
			namesInUse.add(rowGroup.getName());
		}
		for(JRCrosstabColumnGroup colGroup : crosstab.getValue().getColumnGroups()){
			namesInUse.add(colGroup.getName());
		}
		for(JRCrosstabMeasure measure : crosstab.getValue().getMeasures()){
			namesInUse.add(measure.getName());
		}
		step1 = createPage(crosstab, namesInUse);
		addPage(step1);
		if (expContext != null) {
			step1.setExpressionContext(expContext);
		}
	}
	
	protected WizardCrosstabGroupPage createPage(MCrosstab crosstab, List<String> namesInUse){
		return new WizardCrosstabGroupPage(crosstab, namesInUse);
	}

	@Override
	public boolean performFinish() {
		return true;
	}
	
	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
		if (step1 != null) {
			step1.setExpressionContext(expContext);
		}
	}

	/**
	 * Return the name of the new group
	 * 
	 * @return a not null and unique name for the group
	 */
	public String getGroupName(){
		return step1.getGroupName();
	}

	/**
	 * Return the expression of the new group
	 * 
	 * @return an expression for the new group
	 */
	public String getGroupExpression(){
		return step1.getGroupExpression();
	}
	
	/**
	 * Return the type of the selected element
	 * 
	 * @return a string defining the qualified type of the selected element, if
	 * if used an expression the type returned will be java.lang.Object
	 */
	public String getGroupValueClass(){
		return step1.getGroupValueClass();
	}
}
