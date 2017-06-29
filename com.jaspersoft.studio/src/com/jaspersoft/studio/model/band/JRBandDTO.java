/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
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
