/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.parameter.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDatasetParameter;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGenericElementParameter;
import net.sf.jasperreports.engine.JRHyperlinkParameter;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignDatasetParameter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignGenericElementParameter;
import net.sf.jasperreports.engine.design.JRDesignHyperlinkParameter;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;

/**
 * A parameter type that can be used as warpper for every JasperReports parameter.
 * This allow to using a single type of dialog to be used to handle each type of 
 * parameter. This class offer also static methods to allow the conversion between
 * this wrapper and each type of parameter
 * 
 * @author Orlandin Marco
 *
 */
public class GenericJSSParameter {
	
	/**
	 * The name of the parameter
	 */
	private String name;
	
	/**
	 * The expression of the parameter
	 */
	private JRExpression expression;
	
	/**
	 * Build an instance of the class with empty name and expression
	 */
	public GenericJSSParameter(){
		name = "";
		expression = new JRDesignExpression();
	}
	
	/**
	 * Build the wrapper for a JRDatasetParameter
	 * 
	 * @param parameter a not null JRDataset parameter
	 */
	public GenericJSSParameter(JRDatasetParameter parameter){
		this.name = parameter.getName();
		this.expression = parameter.getExpression();
	}

	/**
	 * Build the wrapper for a JRHyperlinkParameter
	 * 
	 * @param parameter a not null JRHyperlinkParameter
	 */
	public GenericJSSParameter(JRHyperlinkParameter parameter){
		this.name = parameter.getName();
		this.expression = parameter.getValueExpression();
	}
	
	/**
	 * Build the wrapper for a JRGenericElementParameter
	 * 
	 * @param parameter a not null JRGenericElementParameter
	 */
	public GenericJSSParameter(JRGenericElementParameter parameter){
		this.name = parameter.getName();
		this.expression = parameter.getValueExpression();
	}
	
	/**
	 * Build the wrapper for a JRSubreportParameter
	 * 
	 * @param parameter a not null JRSubreportParameter
	 */
	public GenericJSSParameter(JRSubreportParameter parameter){
		this.name = parameter.getName();
		this.expression = parameter.getExpression();
	}
	
	/**
	 * Convert the current generic parameter to a JRDatasetParameter
	 * 
	 * @return a not null JRDatasetParameter
	 */
	public JRDatasetParameter getDatasetValue(){
		JRDesignDatasetParameter result = new JRDesignDatasetParameter();
		result.setName(name);
		result.setExpression(expression);
		return result;
	}
	
	/**
	 * Convert the current generic parameter to a JRHyperlinkParameter
	 * 
	 * @return a not null JRHyperlinkParameter
	 */
	public JRHyperlinkParameter getHyperlinkValue(){
		JRDesignHyperlinkParameter result = new JRDesignHyperlinkParameter();
		result.setName(name);
		result.setValueExpression(expression);
		return result;
	}
	
	/**
	 * Convert the current generic parameter to a JRGenericElementParameter
	 * 
	 * @return a not null JRGenericElementParameter
	 */
	public JRGenericElementParameter getGenericValue(){
		JRDesignGenericElementParameter result = new JRDesignGenericElementParameter();
		result.setName(name);
		result.setValueExpression(expression);
		return result;
	}
	
	/**
	 * Convert the current generic parameter to a JRSubreportParameter
	 * 
	 * @return a not null JRSubreportParameter
	 */
	public JRSubreportParameter getSubreportValue(){
		JRDesignSubreportParameter result = new JRDesignSubreportParameter();
		result.setName(name);
		result.setExpression(expression);
		return result;
	}

	/**
	 * Return the name of the current parameter
	 * 
	 * @return the name of the parameter, can be null
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the parameter
	 * 
	 * @param name a name for the parameter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the expression of the parameter
	 * 
	 * @return the expression, can be null
	 */
	public JRExpression getExpression() {
		return expression;
	}

	/**
	 * Set the expression of the parameter
	 * 
	 * @param expression the expression for the parameter
	 */
	public void setExpression(JRExpression expression) {
		this.expression = expression;
	}
	
	/**
	 * Clone the current parameter. The expression object,
	 * if it's different from null it is duplicated as well
	 * 
	 * @return a not null copy of the current parameter. 
	 */
	public GenericJSSParameter clone() {
		GenericJSSParameter result = new GenericJSSParameter();
		result.setName(name);
		if (expression != null){
			result.setExpression((JRExpression)expression.clone());
		} else {
			result.setExpression(null);
		}
		return result;
	}
	
	/**
	 * Convert an array of JRDatasetParameter to a List of GenericJSSParameter
	 * 
	 * @param input the value to convert, if null the result list will be empty
	 * @return a not null list of GenericJSSParameter
	 */
	public static List<GenericJSSParameter> convertFrom(JRDatasetParameter[] input){
		ArrayList<GenericJSSParameter> result = new ArrayList<GenericJSSParameter>();
		if (input != null){
			for(JRDatasetParameter param : input){
				result.add(new GenericJSSParameter(param));
			}
		}
		return result;
	}
	
	/**
	 * Convert an array of JRGenericElementParameter to a List of GenericJSSParameter
	 * 
	 * @param input the value to convert, if null the result list will be empty
	 * @return a not null list of GenericJSSParameter
	 */
	public static List<GenericJSSParameter> convertFrom(JRGenericElementParameter[] input){
		ArrayList<GenericJSSParameter> result = new ArrayList<GenericJSSParameter>();
		if (input != null){
			for(JRGenericElementParameter param : input){
				result.add(new GenericJSSParameter(param));
			}
		}
		return result;
	}
	
	/**
	 * Convert an array of JRHyperlinkParameter to a List of GenericJSSParameter
	 * 
	 * @param input the value to convert, if null the result list will be empty
	 * @return a not null list of GenericJSSParameter
	 */
	public static List<GenericJSSParameter> convertFrom(JRHyperlinkParameter[] input){
		ArrayList<GenericJSSParameter> result = new ArrayList<GenericJSSParameter>();
		if (input != null){
			for(JRHyperlinkParameter param : input){
				result.add(new GenericJSSParameter(param));
			}
		}
		return result;
	}
	
	/**
	 * Convert an array of JRSubreportParameter to a List of GenericJSSParameter
	 * 
	 * @param input the value to convert, if null the result list will be empty
	 * @return a not null list of GenericJSSParameter
	 */
	public static List<GenericJSSParameter> convertFrom(JRSubreportParameter[] input){
		ArrayList<GenericJSSParameter> result = new ArrayList<GenericJSSParameter>();
		if (input != null){
			for(JRSubreportParameter param : input){
				result.add(new GenericJSSParameter(param));
			}
		}
		return result;
	}
	
	/**
	 * Convert a List of GenericJSSParameter to an array of JRDatasetParameter
	 * 
	 * @param input the values to convert, it must be not null
	 * @return a not null array of JRDatasetPArameter
	 */
	public static JRDatasetParameter[] convertToDataset(List<GenericJSSParameter> input){
		JRDatasetParameter[] result = new JRDatasetParameter[input.size()];
		int index = 0;
		for(GenericJSSParameter param : input){
			result[index] = param.getDatasetValue();
			index ++;
		}
		return result;
	}
	
	/**
	 * Convert a List of GenericJSSParameter to an array of JRGenericElementParameter
	 * 
	 * @param input the values to convert, it must be not null
	 * @return a not null array of JRGenericElementParameter
	 */
	public static JRGenericElementParameter[] convertToGeneric(List<GenericJSSParameter> input){
		JRGenericElementParameter[] result = new JRGenericElementParameter[input.size()];
		int index = 0;
		for(GenericJSSParameter param : input){
			result[index] = param.getGenericValue();
			index ++;
		}
		return result;
	}
	
	/**
	 * Convert a List of GenericJSSParameter to an array of JRHyperlinkParameter
	 * 
	 * @param input the values to convert, it must be not null
	 * @return a not null array of JRHyperlinkParameter
	 */
	public static JRHyperlinkParameter[] convertToHyperlink(List<GenericJSSParameter> input){
		JRHyperlinkParameter[] result = new JRHyperlinkParameter[input.size()];
		int index = 0;
		for(GenericJSSParameter param : input){
			result[index] = param.getHyperlinkValue();
			index ++;
		}
		return result;
	}
	
	/**
	 * Convert a List of GenericJSSParameter to an array of JRSubreportParameter
	 * 
	 * @param input the values to convert, it must be not null
	 * @return a not null array of JRSubreportParameter
	 */
	public static JRSubreportParameter[] convertToSubreport(List<GenericJSSParameter> input){
		JRSubreportParameter[] result = new JRSubreportParameter[input.size()];
		int index = 0;
		for(GenericJSSParameter param : input){
			result[index] = param.getSubreportValue();
			index ++;
		}
		return result;
	}
}
