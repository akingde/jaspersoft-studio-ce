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
