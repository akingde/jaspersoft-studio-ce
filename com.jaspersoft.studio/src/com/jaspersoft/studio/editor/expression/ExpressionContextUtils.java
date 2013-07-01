/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.design.JRDesignDataset;

/**
 * Utility methods related to the {@link ExpressionContext} object.
 * <p>
 * 
 * <b>Implementation details</b> - Right now the following methods {@link #getAllDatasetsFields(ExpressionContext)},
 * {@link #getAllDatasetsParameters(ExpressionContext)}, {@link #getAllDatasetsVariables(ExpressionContext)}
 * return a list of "unique" elements using as key the name.<br/> 
 * This could not be the most wise solution.<br/>
 * However since we are presenting this information in the Expression Editor UI, the element name that will be
 * used in the expression is important.<br/>
 * An enhancement would be to present in Expression Editor UI also the Dataset nodes like we do for Crosstabs.
 * So we will have something like:
 * 
 * <pre>
 * 	&lt;DATASET_1&gt; 
 * 	- Parameters
 * 	- Fields
 * 	- Variables
 *	...
 * 	&lt;DATASET_n&gt; 
 * 	- Parameters
 * 	- Fields
 * 	- Variables
 * 	CROSSTAB(1) 
 *	...
 *	CROSSTAB(n)
 * </pre>
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public final class ExpressionContextUtils {

	private ExpressionContextUtils(){
		// PREVENT INSTANTIATION 
	}
	
	/**
	 * Returns the list of JasperReports parameters that can be used 
	 * in the specified expression context. Only dataset instances
	 * associated to the context are considered.
	 * 
	 * @param exprContext the expression context
	 * @return the list of parameters
	 */
	public static List<JRParameter> getAllDatasetsParameters(ExpressionContext exprContext){
		if(exprContext.hasDatasets()){
			List<JRParameter> elements = new ArrayList<JRParameter>();
			for(JRDesignDataset d : exprContext.getDatasets()) {
				elements.addAll(d.getParametersList());
			}
			return getUniqueParameters(elements);
		}
		return new ArrayList<JRParameter>(0);
	}
	
	/**
	 * Returns the list of JasperReports variables that can be used 
	 * in the specified expression context. Only dataset instances
	 * associated to the context are considered.
	 * 
	 * @param exprContext the expression context
	 * @return the list of variables
	 */
	public static List<JRVariable> getAllDatasetsVariables(ExpressionContext exprContext){
		if(exprContext.hasDatasets()){
			List<JRVariable> elements = new ArrayList<JRVariable>();
			for(JRDesignDataset d : exprContext.getDatasets()) {
				elements.addAll(d.getVariablesList());
			}
			return getUniqueVariables(elements);
		}
		return new ArrayList<JRVariable>(0);
	}
	
	/**
	 * Returns the list of JasperReports fields that can be used 
	 * in the specified expression context. Only dataset instances
	 * associated to the context are considered. 
	 * 
	 * @param exprContext the expression context
	 * @return the list of fields
	 */
	public static List<JRField> getAllDatasetsFields(ExpressionContext exprContext){
		if(exprContext.hasDatasets()){
			List<JRField> elements = new ArrayList<JRField>();
			for(JRDesignDataset d : exprContext.getDatasets()) {
				elements.addAll(d.getFieldsList());
			}
			return getUniqueFields(elements);
		}
		return new ArrayList<JRField>(0);
	}
	
	/**
	 * Returns the list of JasperReports variables that can be used 
	 * in the specified expression context. Only crosstab instances
	 * associated to the context are considered. 
	 * 
	 * @param exprContext the expression context
	 * @return the list of variables
	 */
	public static List<JRVariable> getAllCrosstabsVariables(ExpressionContext exprContext) {
		if(exprContext.hasCrosstabs()) {
			List<JRVariable> elements = new ArrayList<JRVariable>();
			for(JRDesignCrosstab c : exprContext.getCrosstabs()) {
				elements.addAll(Arrays.asList(c.getVariables()));
			}
			return elements;
		}
		return new ArrayList<JRVariable>(0);
	}
	
	/**
	 * Returns the list of JasperReports parameters that can be used 
	 * in the specified expression context. Only dataset instances
	 * associated to the context are considered.
	 * 
	 * @param exprContext the expression context
	 * @return the list of parameters
	 */
	public static List<JRParameter> getAllCrosstabsParameters(ExpressionContext exprContext) {
		if(exprContext.hasCrosstabs()) {
			List<JRParameter> elements = new ArrayList<JRParameter>();
			for(JRDesignCrosstab c : exprContext.getCrosstabs()) {
				elements.addAll(Arrays.asList(c.getParameters()));
			}
			return elements;
		}
		return new ArrayList<JRParameter>(0);
	}

	/*
	 * Returns a list of "unique" variables. From the input collection we get rid of the
	 * "duplicate" variables. We use as key the variable name.
	 */
	private static List<JRVariable> getUniqueVariables(List<JRVariable> variables){
		Set<JRVariable> results = new TreeSet<JRVariable>(new Comparator<JRVariable>() {
			@Override
			public int compare(JRVariable o1, JRVariable o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		results.addAll(variables);
		return new ArrayList<JRVariable>(results);
	}
	
	/*
	 * Returns a list of "unique" parameters. From the input collection we get rid of the
	 * "duplicate" parameters. We use as key the parameter name.
	 */
	private static List<JRParameter> getUniqueParameters(List<JRParameter> parameters){
		Set<JRParameter> results = new TreeSet<JRParameter>(new Comparator<JRParameter>() {
			@Override
			public int compare(JRParameter o1, JRParameter o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		results.addAll(parameters);
		return new ArrayList<JRParameter>(results);
	}
	
	/*
	 * Returns a list of "unique" fields. From the input collection we get rid of the
	 * "duplicate" fields. We use as key the field name.
	 */
	private static List<JRField> getUniqueFields(List<JRField> fields){
		Set<JRField> results = new TreeSet<JRField>(new Comparator<JRField>() {
			@Override
			public int compare(JRField o1, JRField o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		results.addAll(fields);
		return new ArrayList<JRField>(results);
	}
}
