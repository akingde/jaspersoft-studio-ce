/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.band;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.ExpressionReturnValue;
import net.sf.jasperreports.engine.design.JRDesignBand;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JRBandDTO {
	private JasperReportsConfiguration jConfig;
	private List<ExpressionReturnValue> value;
	private JRDesignBand subreport;

	public void setjConfig(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	public JRDesignBand getBand() {
		return subreport;
	}

	public void setBand(JRDesignBand subreport) {
		this.subreport = subreport;
	}

	public List<ExpressionReturnValue> getValue() {
		if (value == null)
			value = new ArrayList<ExpressionReturnValue>();
		return value;
	}

	public void setValue(List<ExpressionReturnValue> value) {
		this.value = value;
	}

}
