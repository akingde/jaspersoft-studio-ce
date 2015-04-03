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
package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container to store the informations of a parameter
 * 
 * @author Orlandin Marco
 *
 */
public class ParameterDefinition {

	/**
	 * A list of the available parameter types
	 */
	private static final List<String> parameterTypes = new ArrayList<String>();
	
	/**
	 * An hashmap to bind a parameter type to the associated javatype
	 */
	private static final HashMap<String, String> parameterJavaTypes = new HashMap<String, String>();
	
	/**
	 * Initialize the types
	 */
	static{
		parameterTypes.add("text"); //$NON-NLS-1$
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "java.lang.String"); //$NON-NLS-1$
		parameterTypes.add("date"); //$NON-NLS-1$
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "java.util.Date"); //$NON-NLS-1$
		parameterTypes.add("number"); //$NON-NLS-1$
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "java.lang.Number"); //$NON-NLS-1$
		parameterTypes.add("daterange"); //$NON-NLS-1$
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "net.sf.jasperreports.types.date.DateRange"); //$NON-NLS-1$
	}
	
	/**
	 * The name of the parameter
	 */
	private String name;
	
	/**
	 * The type of the parameter
	 */
	private String type;
	
	/**
	 * The expression of the parameter
	 */
	private String expression;
	
	/**
	 * Flag if the parameter is for prompt or not
	 */
	private boolean isForPrompt;
	
	/**
	 * Create the container of the parameter
	 * 
	 * @param name The name of the parameter, should be not null
	 * @param type The type of the parameter, should be not null
	 * @param expression The expression of the parameter, could be null
	 * @param isForPrompt Flag if the parameter is for prompt or not
	 */
	public ParameterDefinition(String name, String type, String expression, boolean isForPrompt){
		this.name = name;
		this.type = type;
		this.expression = expression;
		this.isForPrompt = isForPrompt;
	}
	
	/**
	 * Return the parameter name
	 * 
	 * @return a not null string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the parameter type
	 * 
	 * @return a not null string
	 */
	public String getType() {
		return type;
	}

	/**
	 * Return the parameter expression
	 * 
	 * @return the expression string, could be null
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Check if the parameter is for prompt or not
	 * 
	 * @return true if it is for prompt, false otherwise
	 */
	public boolean isForPrompt() {
		return isForPrompt;
	}

	/**
	 * Return the parameter types
	 * 
	 * @return a not null list containing the type of the parameter. At the moment
	 * the available types are text, number, date and daterange
	 */
	public static List<String> getParameterTypes(){
		return parameterTypes;
	}
	
	/**
	 * Given a parameter type return the associated java type
	 * 
	 * @param parameterType a not null parameter type
	 * @return the java type of the parameter, if it is not found a specific type the default
	 *  "java.lang.String" is returned
	 */
	public static String getParameterJavaType(String parameterType){
		String javaType =  parameterJavaTypes.get(parameterType);
		if (javaType == null) javaType = "java.lang.String"; //$NON-NLS-1$
		return javaType;
	}
	
	/**
	 * Given a parameter java type return the associated parameter type
	 * 
	 * @param javaType a not null java type
	 * @return the type of the parameter, if the java type dosen't match any parameter
	 * type then the java type is returned
	 */
	public static String getParameterType(String javaType){
		for(Map.Entry<String,String> type : parameterJavaTypes.entrySet()){
			if (type.getValue().equals(javaType)) return type.getKey();
		}
		return javaType;
	}
}
