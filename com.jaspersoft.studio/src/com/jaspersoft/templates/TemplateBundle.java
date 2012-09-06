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
	 * Templates may be organized in categories.
	 * This method return a string with the category name.
	 * 
	 * @return
	 */
	public String getCategory();
	
	
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
	 * @return
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
	
}
