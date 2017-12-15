/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.templates;

import java.util.List;

import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.templates.TemplateBundle;


public interface TemplateProvider {

		/**
		 * Return a list of TemplateBundle that could be handled by this engine
		 */
		public List<TemplateBundle> getTemplateBundles();

		/**
		 * Return the key that identify this engine
		 */
		public String getProviderKey();
		
		/**
		 * return a human readable name for the engine
		 */
		public String getProviderName();
		
		/**
		 * Get a JasperDesign and check if that JasperDesign can be used as Template and processed
		 * by the engine used inside this provider
		 * 
		 * @param jrContext context of the design to check
		 * @param design the design to check
		 * @return a List of founded error, the list is void if no error are found
		 */
		public List<String> validateTemplate(JasperReportsContext jrContext, JasperDesign design);
}
