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
package com.jaspersoft.studio.jasper;

import java.util.Map;

import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ReportConverter;

/**
 * Custom report converter that allow the external access to the 
 * styles map.
 */
public class JSSReportConverter extends ReportConverter {

	public JSSReportConverter(JasperReportsContext jasperReportsContext, JRReport report, boolean ignoreContent) {
		super(jasperReportsContext, report, ignoreContent);
	}
	
	/**
	 * Return the styles map of the report converter
	 * 
	 * @return a not null styles map that contains every style resource, both internal and external
	 */
	public Map<String, JRStyle> getStylesMap(){
		return stylesMap;
	}
	
	/**
	 * Force the recreation of the JasperPrint and the reload of the styles, in this way
	 * all the external styles will updated
	 */
	public void refreshCachedStyles(){
		convert(false);
	}

}
