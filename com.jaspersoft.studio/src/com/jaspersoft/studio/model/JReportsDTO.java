package com.jaspersoft.studio.model;

import net.sf.jasperreports.engine.design.JasperDesign;

public class JReportsDTO {
	private JasperDesign jasperDesign;
	private Object value;

	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}

	public void setJasperDesign(JasperDesign jasperDesign) {
		this.jasperDesign = jasperDesign;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
