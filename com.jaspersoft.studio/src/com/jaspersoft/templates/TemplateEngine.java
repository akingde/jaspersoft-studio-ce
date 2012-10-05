package com.jaspersoft.templates;

import java.util.Map;

/**
 * A TemplateEngine produces a ReportBundle starting from a TemplateBundle and a set of user settings provided
 * trough a Map.
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
	public ReportBundle generateReportBundle(TemplateBundle template,  Map<String, Object> settings) throws TemplateEngineException;
	
}
