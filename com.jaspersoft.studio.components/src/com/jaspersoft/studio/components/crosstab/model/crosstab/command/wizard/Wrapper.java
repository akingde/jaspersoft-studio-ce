/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;

public class Wrapper {
	private Object value;
	private AgregationFunctionEnum calculation = AgregationFunctionEnum.UNIQUE;
	
	/**
	 * The base object form where the group is created, typically is an expression referencing a 
	 * field, a variable or a parameter. This could be in some cases be different from the bucket
	 * expression, e.g. when using as base object something of type time/timestamp/date the object
	 * can be enclosed into an aggregation function in the bucket expression
	 */
	private String baseObject;
	
	/**
	 * The type of base object form where the group is created. This could be in some cases be different from the type of 
	 * the bucket expression, e.g. when using as base object something of type time/timestamp/date the object
	 * can be enclosed into an aggregation function in the bucket expression that can return a different
	 * type eg an int for the number of the day
	 */
	private String baseObjectType;

	public Wrapper(Object value) {
		super();
		this.value = value;
		if (value instanceof JRDesignCrosstabColumnGroup) {
			JRDesignCrosstabColumnGroup cg = (JRDesignCrosstabColumnGroup) value;
			baseObject = cg.getBucket().getExpression().getText();
			baseObjectType = cg.getBucket().getValueClassName();
		} else if (value instanceof JRDesignCrosstabRowGroup) {
			JRDesignCrosstabRowGroup rg = (JRDesignCrosstabRowGroup) value;
			baseObject = rg.getBucket().getExpression().getText();
			baseObjectType = rg.getBucket().getValueClassName();
		}
	}

	public String getBaseObjectExpression() {
		return baseObject;
	}

	public Object getValue() {
		return value;
	}

	public void setCalculation(AgregationFunctionEnum calculation) {
		this.calculation = calculation;
	}

	public AgregationFunctionEnum getCalculation() {
		return calculation;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Wrapper)
			return value.equals(((Wrapper) obj).getValue());
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	/**
	 * Return the class of the dataset item used to build this wrapper
	 */
	public String getBaseObjectType() {
		return baseObjectType;
	}

	/**
	 * Return the name of the object without any special syntax like $ etc.. The
	 * value comes from the old expression text which we assume is in the form
	 * $?{AAAAAA}
	 *
	 * @return String
	 */
	public String getLabel() {

		String label = getBaseObjectExpression();
		if (label == null)
			return ""; // this case should never be true.

		label = label.substring(3, label.length() - 1);
		return label;
	}

}
