/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.util.HashMap;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRExpressionUtil;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ExpressionUtil {
	
	/**
	 * Cache of the expression interpreter for every dataset, the key is the reference to the dataset for whose the interpreter was created
	 */
	private static HashMap<JRDesignDataset, ExpressionInterpreter> datasetsIntepreters = new HashMap<JRDesignDataset, ExpressionInterpreter>();
	
	/**
	 * Resolve an expression and return its value or null if it can not be resolve. First it will try to use a simple evaluation
	 * since it is much faster. If this can't resolve the expression then an interpreter for the current report is created 
	 * and cached (since create and interpreter is very slow) 
	 * 
	 * @param exp expression to resolve
	 * @param project project of the report
	 * @param jConfig Configuration of the report to evaluate the expression
	 * @param dataset the context of the expression resolution
	 * @return resolved expression or null it it can't be resolved
	 */
	public static String cachedExpressionEvaluation(JRExpression exp, JasperReportsConfiguration jConfig, JRDesignDataset dataset){	
		String evaluatedExpression = null;
		String expString = exp != null ? exp.getText() : "";
		try{
			evaluatedExpression = JRExpressionUtil.getSimpleExpressionText(exp);
			if (evaluatedExpression == null){
				//Unable to interpret the expression, lets try with a more advanced (and slow, so its cached) interpreter
				JasperDesign jd = jConfig.getJasperDesign();
				ExpressionInterpreter interpreter = datasetsIntepreters.get(dataset);
				if (interpreter == null || !interpreter.isCorrectInterpreter(jd)){
					if (exp != null && jd != null){
						interpreter = new ExpressionInterpreter(dataset, jd, jConfig);
						datasetsIntepreters.put(dataset, interpreter);
					}
				}
				if (interpreter != null){
					Object expressionValue = interpreter.interpretExpression(expString);
					if (expressionValue != null) evaluatedExpression = expressionValue.toString();
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return evaluatedExpression;
	}
	
	/**
	 * Resolve an expression and return its value or null if it can not be resolve. First it will try to use a simple evaluation
	 * since it is much faster. If this can't resolve the expression then an interpreter for the current report is created 
	 * and cached (since create and interpreter is very slow) 
	 * 
	 * @param exp expression to resolve
	 * @param jConfig Configuration of the report to evaluate the expression
	 * @return resolved expression or null it it can't be resolved
	 */
	public static String cachedExpressionEvaluation(JRExpression exp, JasperReportsConfiguration jConfig){	
		return cachedExpressionEvaluation(exp, jConfig, jConfig.getJasperDesign().getMainDesignDataset());
	}
	
	/**
	 * Remove an expression interpreter from the cache. An intepreter must be removed when something change in the dataset
	 * that has generated it
	 * 
	 * @param dataset dataset for whose the intepreter was created
	 */
	public static void removeCachedInterpreter(JRDesignDataset dataset){
		datasetsIntepreters.remove(dataset);
	}
	
	/**
	 * Remove an expression interpreter from the cache
	 * 
	 * @param jConfig JasperReportConfiguration project for which the interpreter should be removed
	 */
	public static void removeCachedInterpreter(JasperReportsConfiguration jConfig){
		if (jConfig != null){
			removeCachedInterpreter(jConfig.getJasperDesign().getMainDesignDataset());
		}
	}
	
	public static final String eval(JRExpression expr, JasperReportsConfiguration jConfig, JasperDesign jd) {
		if (expr == null)
			return null;
		if (jd == null)
			return JRExpressionUtil.getSimpleExpressionText(expr);

		Object eval = eval(expr, jd.getMainDesignDataset(), jConfig, jd);
		if (eval != null)
			return eval.toString();
		return null;
	}

	public static final String eval(JRExpression expr, JasperReportsConfiguration jConfig) {
		return eval(expr, jConfig, jConfig.getJasperDesign());
	}

	public static final Object eval(JRExpression expr, JRDataset jrd, JasperReportsConfiguration jConfig) {
		return eval(expr, jrd, jConfig, jConfig.getJasperDesign());
	}

	public static final Object eval(JRExpression expr, JRDataset jrd, JasperReportsConfiguration jConfig, JasperDesign jd) {
		if (expr == null || jrd == null || jd == null)
			return null;
		return getInterpreter((JRDesignDataset) jrd, jConfig, jd).interpretExpression(expr.getText());
	}

	public static final ExpressionInterpreter getInterpreter(JRDesignDataset jrd, JasperReportsConfiguration jConfig,
			JasperDesign jd) {
		if (jrd == null || jd == null)
			return null;
		return new ExpressionInterpreter(jrd, jd, jConfig);
	}

	/**
	 * @return an expression with empty string
	 */
	public static final JRDesignExpression getEmptyStringExpression() {
		return new JRDesignExpression("\"\"");
	}
}
