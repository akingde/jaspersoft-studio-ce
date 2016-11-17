/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.List;

import net.sf.jasperreports.engine.JRSubreportReturnValue;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JReportsDTO {
	private JasperReportsConfiguration jConfig;
	private List<JRSubreportReturnValue> value;
	private JRDesignSubreport subreport;

	public void setjConfig(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	public JRDesignSubreport getSubreport() {
		return subreport;
	}

	public void setSubreport(JRDesignSubreport subreport) {
		this.subreport = subreport;
	}

	public List<JRSubreportReturnValue> getValue() {
		return value;
	}

	public void setValue(List<JRSubreportReturnValue> value) {
		this.value = value;
	}

}
