/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.templates;

import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;



/**
 * A template bundle is a class that describes a particular template which is the set of files that makes up the
 * basic structure that will be processed by the template engine.
 * 
 * @author gtoffoli
 *
 */
public interface TemplateBundle {
	
	/**
	 * The name of the template
	 * 
	 * @return
	 */
	public String getLabel();
	
	/**
	 * Return an instance of the template engine to be used with this template bundle.
	 * The template engine can be specific of each template.
	 * 
	 * @return TemplateEngine
	 */
	public TemplateEngine getTemplateEngine();
	
	
	
	/**
	 * The main JasperDesign. Many templates may store template information
	 * directly inside the primary jrxml.
	 * 
	 * @return a not null {@link JasperDesign}
	 */
	public JasperDesign getJasperDesign();
	
	
	/**
	 * The list of all the resources available in this bundle.
	 * Resource names are mostly files that are part of a bundle (i.e. images, fonts or jrtx files).
	 * The template engine may decide to just copy this resources inside the
	 * final report bundle.
	 * 
	 * @return
	 */
	public List<String> getResourceNames();
	
	/**
	 * This method allows to get the data of a particular resource which is part of
	 * this bundle (like an image or a jrtx file) which can be used by the template
	 * engine to generate the final ReportBundle.
	 * The user is in charge to close the stream.
	 * 
	 * @param name
	 * @return
	 */
	public InputStream getResource(String name);
	
	/**
	 * Return a property for the template
	 * 
	 * @param properyName the name of the property
	 * @return The value of the property or null if it isn't found
	 */
	public Object getProperty(String properyName);
	
	/**
	 * Check if the TemplateBundle is internal to Jaspersoft Studio or it is load
	 * from an external location
	 * 
	 * @return True if the Template bundle is load from an external location, otherwise
	 * false
	 */
	public boolean isExternal();
	
	/**
	 * Checks if the template bundle can be used when creating a sub-report
	 * 
	 * @return <code>true</code> if supports exists, <code>false</code> otherwise
	 */
	public boolean hasSupportForSubreport(); 
}
