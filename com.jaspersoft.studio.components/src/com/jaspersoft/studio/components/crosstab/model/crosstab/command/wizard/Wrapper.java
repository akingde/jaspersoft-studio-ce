package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;

public class Wrapper {
	private Object value;
	private AgregationFunctionEnum calculation = AgregationFunctionEnum.UNIQUE;
	private String oldExpText;

	public Wrapper(Object value) {
		super();
		this.value = value;
		if (value instanceof JRDesignCrosstabColumnGroup) {
			JRDesignCrosstabColumnGroup cg = (JRDesignCrosstabColumnGroup) value;
			oldExpText = cg.getBucket().getExpression().getText();
		} else if (value instanceof JRDesignCrosstabRowGroup) {
			JRDesignCrosstabRowGroup rg = (JRDesignCrosstabRowGroup) value;
			oldExpText = rg.getBucket().getExpression().getText();
		}
	}

	public String getOldExpText() {
		return oldExpText;
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
}
