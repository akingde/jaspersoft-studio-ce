/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.List;

import net.sf.jasperreports.engine.JRSubreportReturnValue;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JReportsDTO {
	private JasperReportsConfiguration jConfig;
	private List<JRSubreportReturnValue> value;
	private JRDesignSubreport prop1;

	public void setjConfig(JasperReportsConfiguration jConfig) {
		this.jConfig = jConfig;
	}

	public JasperReportsConfiguration getjConfig() {
		return jConfig;
	}

	public JRDesignSubreport getProp1() {
		return prop1;
	}

	public void setProp1(JRDesignSubreport prop1) {
		this.prop1 = prop1;
	}

	public List<JRSubreportReturnValue> getValue() {
		return value;
	}

	public void setValue(List<JRSubreportReturnValue> value) {
		this.value = value;
	}

}
