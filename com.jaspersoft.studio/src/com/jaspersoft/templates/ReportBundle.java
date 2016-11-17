/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.templates;

import java.io.InputStream;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;

public class ReportBundle {

	
	private TemplateBundle template = null;
	private JasperDesign jasperDesign = null;
	
	public ReportBundle(TemplateBundle template)
	{
		this.template = template;
	}
	
	/**
	 * The list of all the resources available in this bundle.
	 * 
	 * @return
	 */
	public List<String> getResourceNames()
	{
		return template.getResourceNames();
	}

	
	/**
	 * This method allows to get the data of a particular resource which is part of
	 * this bundle (like an image or a jrtx file) which can then be stored in the proper way.
	 * The user is in charge to close each stream.
	 * 
	 * @param name
	 * @return
	 */
	public InputStream getResource(String name)
	{
		return template.getResource(name);
	}


	/**
	 * @return the jasperDesign
	 */
	public JasperDesign getJasperDesign() {
		return jasperDesign;
	}


	/**
	 * @param jasperDesign the jasperDesign to set
	 */
	public void setJasperDesign(JasperDesign jasperDesign) {
		this.jasperDesign = jasperDesign;
	}
	
}
