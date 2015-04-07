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
package com.jaspersoft.studio.kpi.dialog;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Abstract configuration page of a KPI
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractKPIConfigurationPage {

	/**
	 * Composite where all the controls of the page are placed
	 */
	protected Composite mainComposite;
	
	/**
	 * The JasperDesign of the handled KPI
	 */
	protected JasperDesign jd;
	
	/**
	 * Create the page
	 * 
	 * @param jd The JasperDesign of the handled KPI
	 */
	public AbstractKPIConfigurationPage(JasperDesign jd){
		this.jd = jd;
	}
	
	/**
	 * Create the content of the current page, only if it wasn't created
	 * before. Otherwise it return the container of the content
	 * 
	 * @param container container where to place the main composite of the page
	 * if it must be created
	 * @return a not null composite where all the content of this page is placed
	 */
	public Composite getComposite(Composite container){
		if (mainComposite == null){	
			mainComposite = createComposite(container);
		}
		return mainComposite;
	}
	
	/**
	 * Return the name of the page shown in the configuration step on the left
	 * 
	 * @return a not null string
	 */
	public abstract String getName();
	
	/**
	 * Return a visual title of the page
	 * 
	 * @return by default it is the same of the name, but it is suggested to override
	 */
	public String getTitle(){
		return getName();
	}
	
	/**
	 * Create the content controls of the page to edit the KPI JasperDesign.
	 * On the parent parameter should be created a single composite and it 
	 * should be used to create inside all the other controls, and then returned
	 * 
	 * @param parent the parent where the main composite, and only it, must be created
	 * @return a not null composite, contaienr of all the other controls of the page
	 */
	protected abstract Composite createComposite(Composite parent);

	// UTILITY METHODS
	
	/**
	 * Return the expression context for the main dataset
	 * 
	 * @return a not null expression context
	 */
	protected ExpressionContext getExpressionContext(JRDesignDataset dataset) {
		JasperReportsConfiguration jConfig = new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null);
		jConfig.setJasperDesign(jd);
		return new ExpressionContext(dataset, jConfig);
	}
	
	
	/**
	 * Return a variable from the JasperDesign with a specific name, if the variable
	 * is not found the it is created and returned. The variable is searched in the specified dataset
	 * 
	 * @param variableName the name of the variable
	 * @param dataset a not null dataset
	 * @return a not null JRDesignVariable
	 */
	protected JRDesignVariable getVariable(String variableName, JRDesignDataset dataset){
		JRVariable variable = dataset.getVariablesMap().get(variableName);
		if (variable == null){
			JRDesignVariable newVariable = new JRDesignVariable();
			newVariable.setName(variableName);
			try {
				dataset.addVariable(newVariable);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newVariable;
		}
		return ((JRDesignVariable)variable);
	}
	
	/**
	 * Return a variable from the JasperDesign with a specific name, if the variable
	 * is not found the it is created and returned. The variable is searched in the main dataset
	 * 
	 * @param variableName the name of the variable
	 * @return a not null JRDesignVariable
	 */
	protected JRDesignVariable getVariable(String variableName){
		return getVariable(variableName, jd.getMainDesignDataset());
	}
	
	/**
	 * Return a parameter from the JasperDesign with a specific name, if the parameter
	 * is not found the it is created and returned. It is searched in the specified dataset
	 * 
	 * @param parameterName the name of the parameter
	 * @parm dataset a not null dataset
	 * @return a not null JRDesignParameter
	 */
	protected JRDesignParameter getParameter(String parameterName, JRDesignDataset dataset){
		JRParameter parameter = dataset.getParametersMap().get(parameterName);
		if (parameter == null){
			JRDesignParameter newParameter = new JRDesignParameter();
			newParameter.setName(parameterName);
			try {
				dataset.addParameter(newParameter);
			} catch (JRException e) {
				e.printStackTrace();
			} 
			return newParameter;
		}
		return ((JRDesignParameter)parameter);
	}
	
	/**
	 * Return a parameter from the JasperDesign with a specific name, if the parameter
	 * is not found the it is created and returned. It is searched in the main dataset
	 * 
	 * @param parameterName the name of the parameter
	 * @return a not null JRDesignParameter
	 */
	protected JRDesignParameter getParameter(String parameterName){
		return getParameter(parameterName, jd.getMainDesignDataset());
	}
}
