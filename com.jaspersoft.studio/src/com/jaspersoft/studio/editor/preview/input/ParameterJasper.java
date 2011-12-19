package com.jaspersoft.studio.editor.preview.input;

import net.sf.jasperreports.engine.design.JRDesignParameter;

public class ParameterJasper implements IParameter {
	private JRDesignParameter param;

	public ParameterJasper(JRDesignParameter param) {
		this.param = param;
	}

	public String getName() {
		return param.getName();
	}

	public Class<?> getValueClass() {
		return param.getValueClass();
	}

	public String getDescription() {
		return param.getDescription();
	}

	public String getLabel() {
		return param.getName();
	}

	public boolean isMandatory() {
		return false;
	}

	public boolean isReadOnly() {
		return false;
	}

	public boolean isStrictMin() {
		return false;
	}

	public String getMinValue() {
		return null;
	}

	public boolean isStrictMax() {
		return false;
	}

	public String getMaxValue() {
		return null;
	}

	public String getPattern() {
		return null;
	}
}
