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
package com.jaspersoft.studio.property.descriptor.returnvalue;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRSubreportReturnValue;
import net.sf.jasperreports.engine.ReturnValue;
import net.sf.jasperreports.engine.design.DesignReturnValue;
import net.sf.jasperreports.engine.design.JRDesignSubreportReturnValue;
import net.sf.jasperreports.engine.type.CalculationEnum;

/**
 * Container used to keep the information of a generic return value. Offer the methods 
 * to convert this return value container to a return value for a subreport
 * or a dataset run
 * 
 * @author Orlandin Marco
 *
 */
public class ReturnValueContainer {

	/**
	 * The name of the variable to be copied.
	 */
	protected String fromVariable;

	/**
	 * The name of the variable where the value should be copied.
	 */
	protected String toVariable;
	
	/**
	 * The calculation type.
	 */
	protected CalculationEnum calculation = CalculationEnum.NOTHING;
	
	/**
	 * The incrementer factory class name.
	 */
	protected String incrementerFactoryClassName;

	
	// START OF GETTER AND SETTERS
	
	public String getFromVariable() {
		return fromVariable;
	}

	public void setFromVariable(String fromVariable) {
		this.fromVariable = fromVariable;
	}

	public String getToVariable() {
		return toVariable;
	}

	public void setToVariable(String toVariable) {
		this.toVariable = toVariable;
	}

	public CalculationEnum getCalculation() {
		return calculation;
	}

	public void setCalculation(CalculationEnum calculation) {
		this.calculation = calculation;
	}

	public String getIncrementerFactoryClassName() {
		return incrementerFactoryClassName;
	}

	public void setIncrementerFactoryClassName(String incrementerFactoryClassName) {
		if (incrementerFactoryClassName != null && incrementerFactoryClassName.trim().isEmpty()){
			this.incrementerFactoryClassName = null;
 		} else {
 			this.incrementerFactoryClassName = incrementerFactoryClassName;
 		}
	}
	
	// END OF GETTERS AND SETTER
	
	/**
	 * Clone the actual value and return it as a new instance of a return value
	 * container
	 * 
	 * @return a not null return value container, copy of the instance where this
	 * method is called
	 */
	public ReturnValueContainer clone(){
		ReturnValueContainer result = new ReturnValueContainer();
		result.setCalculation(getCalculation());
		result.setFromVariable(getFromVariable());
		result.setIncrementerFactoryClassName(getIncrementerFactoryClassName());
		result.setToVariable(getToVariable());
		return result;
	}
	
	/**
	 * Convert a list of subreport return parameter to a list of this generic return parameter
	 * 
	 * @param subreportReturns a not null list of subreport return values
	 * @return a not null list of generic return values
	 */
	public static List<ReturnValueContainer> convertFromSubreportReturn(List<JRSubreportReturnValue> subreportReturns){
		List<ReturnValueContainer> result = new ArrayList<ReturnValueContainer>();
		for(JRSubreportReturnValue subreportReturn : subreportReturns){
			ReturnValueContainer generalReturn = new ReturnValueContainer();
			generalReturn.setCalculation(subreportReturn.getCalculationValue());
			generalReturn.setFromVariable(subreportReturn.getSubreportVariable());
			generalReturn.setToVariable(subreportReturn.getToVariable());
			generalReturn.setIncrementerFactoryClassName(subreportReturn.getIncrementerFactoryClassName());
			result.add(generalReturn);
		}
		return result;
	}
	
	/**
	 * Convert a list of dataset run return parameter to a list of this generic return parameter
	 * 
	 * @param datasetRunReturns a not null list of dataset run return values
	 * @return a not null list of generic return values
	 */
	public static List<ReturnValueContainer> convertFromDatasetRunReturn(List<ReturnValue> datasetRunReturns){
		List<ReturnValueContainer> result = new ArrayList<ReturnValueContainer>();
		for(ReturnValue subreportReturn : datasetRunReturns){
			ReturnValueContainer generalReturn = new ReturnValueContainer();
			generalReturn.setCalculation(subreportReturn.getCalculation());
			generalReturn.setFromVariable(subreportReturn.getFromVariable());
			generalReturn.setToVariable(subreportReturn.getToVariable());
			generalReturn.setIncrementerFactoryClassName(subreportReturn.getIncrementerFactoryClassName());
			result.add(generalReturn);
		}
		return result;
	}
	
	/**
	 * Convert a list of generic return parameter to a list of dataset run return parameter
	 * 
	 * @param returnContainers a not null list of generic return values
	 * @return a not null list of dataset run values
	 */
	public static List<ReturnValue> convertToDatasetRun(List<ReturnValueContainer> returnContainers){
		List<ReturnValue> result = new ArrayList<ReturnValue>();
		for(ReturnValueContainer container : returnContainers){
			DesignReturnValue value = new DesignReturnValue();
			value.setCalculation(container.getCalculation());
			value.setToVariable(container.getToVariable());
			value.setFromVariable(container.getFromVariable());
			value.setIncrementerFactoryClassName(container.getIncrementerFactoryClassName());
			result.add(value);
		}
		return result;
	}
	
	/**
	 * Convert a list of generic return parameter to a list of subreport return parameter
	 * 
	 * @param returnContainers a not null list of generic return values
	 * @return a not null list of subreport values
	 */
	public static List<JRSubreportReturnValue> convertToSubreport(List<ReturnValueContainer> returnContainers){
		List<JRSubreportReturnValue> result = new ArrayList<JRSubreportReturnValue>();
		for(ReturnValueContainer container : returnContainers){
			JRDesignSubreportReturnValue returnValue = new JRDesignSubreportReturnValue();
			returnValue.setCalculation(container.getCalculation());
			returnValue.setIncrementerFactoryClassName(container.getIncrementerFactoryClassName());
			returnValue.setSubreportVariable(container.getFromVariable());
			returnValue.setToVariable(container.getToVariable());
			result.add(returnValue);
		}
		return result;
	}
	
	

}
