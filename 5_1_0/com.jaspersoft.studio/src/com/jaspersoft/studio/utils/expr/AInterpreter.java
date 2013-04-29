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
package com.jaspersoft.studio.utils.expr;

import java.util.List;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import com.jaspersoft.studio.plugin.IEditorContributor;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AInterpreter {
	protected IJavaProject javaProject;
	protected JRDesignDataset dataset;
	protected JasperDesign jasperDesign;
	protected JasperReportsConfiguration jConfig;
	protected ClassLoader classLoader;

	public void prepareExpressionEvaluator(JRDesignDataset dataset, JasperDesign jasperDesign,
			JasperReportsConfiguration jConfig) throws Exception {
		this.dataset = dataset;
		this.jasperDesign = jasperDesign;
		this.jConfig = jConfig;
		try {
			IFile file = (IFile) jConfig.get(IEditorContributor.KEY_FILE);
			if (file != null) {
				IProject project = file.getProject();
				if (project.getNature(JavaCore.NATURE_ID) != null)
					javaProject = JavaCore.create(project);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}

		classLoader = jConfig.getClassLoader();
		if (classLoader == null) {
			if (jasperDesign != null)
				classLoader = jasperDesign.getClass().getClassLoader();
			else
				classLoader = Thread.currentThread().getContextClassLoader();
		}
	}

	protected abstract Object eval(String expression) throws Exception;

	protected abstract void set(String key, Object val) throws Exception;

	protected abstract Object get(String key) throws Exception;

	public Object interpretExpression(String expression) {
		try {
			expression = prepareExpression(expression);
			return eval(expression);
		} catch (Exception e) {
		}
		return null;
	}

	protected String prepareExpression(String expression) throws Exception {
		if (dataset != null)
			for (JRParameter parameter : dataset.getParametersList()) {
				String p1 = "$P{" + parameter.getName() + "}";
				// String p2 = "$P!{" + parameter.getName() + "}";

				// evaluate the Default expression value

				// Integer expID = (Integer)parameterNameToExpressionID.get(parameter.getName());

				int ip1 = expression.indexOf(p1);

				if (ip1 < 0)
					continue;

				Object defValue;
				if (parameter.getDefaultValueExpression() != null && !parameter.getDefaultValueExpression().equals("")) {
					String expText = "";
					if (parameter.getDefaultValueExpression() != null) {
						expText = parameter.getDefaultValueExpression().getText();
					}
					defValue = recursiveInterpreter(expText, dataset.getParametersList(), 0, parameter.getName());
					// interpreter.eval("bshCalculator.evaluate(" + expID.intValue() + ")");
				} else {
					// this param does not have a default value.
					defValue = null;
					if (isConvertNullParams()) {
						if (parameter.getValueClassName().equals("java.lang.String")) {
							defValue = "";
						}
					}
				}

				while (ip1 != -1) {
					// String replacement...
					// if( defValue==null ) {
					// return null;
					// }

					String before = expression.substring(0, ip1);
					String after = expression.substring(ip1 + p1.length());

					String param_name_literal = "param_"
							+ net.sf.jasperreports.engine.util.JRStringUtil.escapeJavaStringLiteral(parameter.getName());

					expression = before + param_name_literal + after;
					// set the value...
					set(param_name_literal, defValue);

					/*
					 * if (parameter.getValueClassName().equals("java.lang.String")) { String stt = defValue.toString(); //stt =
					 * Misc.string_replace("''","'", stt); expression = before + stt + after; } else expression = before + "" +
					 * defValue.toString() + "" + after;
					 */
					ip1 = expression.indexOf(p1);
				}

				/*
				 * int ip2 = expression.indexOf(p2); while( ip2!=-1 ) { // String replacement, Altering the SQL statement. if(
				 * defValue==null ) { throw new IllegalArgumentException("Please set a " + "default value for the parameter '" +
				 * parameter.getName() + "'" ); }
				 * 
				 * String before = expression.substring(0, ip2); String after = expression.substring(ip2+p2.length());
				 * expression = before + "" + defValue.toString() + "" + after; ip2 = expression.indexOf(p2); }
				 */
			}
		return expression;
	}

	public Object recursiveInterpreter(String expression, List<JRParameter> parameters) throws Exception {
		return recursiveInterpreter(expression, parameters, 0);
	}

	public Object recursiveInterpreter(String expression, List<JRParameter> parameters, int recursion_level)
			throws Exception {
		return recursiveInterpreter(expression, parameters, 0, null);
	}

	public Object recursiveInterpreter(String expression, List<JRParameter> parameters, int recursion_level,
			String this_param_name) throws Exception {
		++recursion_level;

		if (expression == null || expression.length() == 0)
			return null;

		// System.out.println("Valuto ["+ recursion_level +"]: " + expression);
		if (recursion_level > 100)
			return null;
		if (expression != null && expression.trim().length() > 0) {
			// for each parameter, we have to calc the real value...
			while (expression.indexOf("$P{") >= 0) {
				int start_index = expression.indexOf("$P{") + 3;
				String param_name = expression.substring(start_index, expression.indexOf("}", start_index));
				String param_expression = "";
				for (int i = 0; i < parameters.size(); ++i) {
					JRDesignParameter p = (JRDesignParameter) parameters.get(i);
					if (p.getName().equals(param_name)) {
						param_expression = p.getDefaultValueExpression().getText();
						break;
					}
				}

				String param_name_literal = "param_"
						+ net.sf.jasperreports.engine.util.JRStringUtil.escapeJavaStringLiteral(param_name);

				expression = Misc.strReplace(param_name_literal, "$P{" + param_name + "}", expression);
				// interpreter.set( param_name_literal, recursiveInterpreter(interpreter, param_expression, parameters,
				// recursion_level));

				// If the parameter was never evaluated before, that can happen is some cases,
				// evaluate it now!
				if (get(param_name_literal) == null) {
					Object paramValue = recursiveInterpreter(param_expression, parameters, recursion_level, this_param_name);
					set(param_name_literal, paramValue);
				}
			}

			String this_param_name_literal = "param_unknow";

			if (this_param_name != null) {
				this_param_name_literal = "param_"
						+ net.sf.jasperreports.engine.util.JRStringUtil.escapeJavaStringLiteral(this_param_name);
			}
			// System.out.println("interpreto ["+ recursion_level +"]: " + expression);
			// System.out.flush();
			Object res = eval(expression);
			set(this_param_name_literal, res);
			// System.out.println("Result: " + res);
			// System.out.flush();
			return res;
		}
		return null;
	}

	private boolean convertNullParams = false;

	/**
	 * @return the convertNullParams
	 */
	public boolean isConvertNullParams() {
		return convertNullParams;
	}

	/**
	 * @param convertNullParams
	 *          the convertNullParams to set
	 */
	public void setConvertNullParams(boolean convertNullParams) {
		this.convertNullParams = convertNullParams;
	}
}
