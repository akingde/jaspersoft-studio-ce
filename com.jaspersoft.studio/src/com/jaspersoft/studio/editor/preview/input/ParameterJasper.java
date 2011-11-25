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

}
