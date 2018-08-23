/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.templates;

import java.util.Map;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * A TemplateEngine produces a ReportBundle starting from a TemplateBundle and a
 * set of user settings provided trough a Map.
 * 
 * 
 * @author gtoffoli
 *
 */
public interface TemplateEngine {

	/**
	 * 
	 * Generates a ReportBundle based on the input.
	 * 
	 * @param template
	 * @param settings
	 * @return
	 * @throws TemplateEngineException
	 */
	public ReportBundle generateReportBundle(TemplateBundle template, Map<String, Object> settings,
			JasperReportsContext jContext) throws TemplateEngineException;

	/**
	 * Set the custom properties and the chosen data adapter on the jasperdesign of
	 * the new report
	 * 
	 * @param bundle
	 * @param dataadapter
	 * @param properties
	 */
	public void setReportDataAdapter(ReportBundle bundle, DataAdapterDescriptor dataadapter, JRPropertiesMap properties,
			JasperReportsConfiguration jConf);

}
