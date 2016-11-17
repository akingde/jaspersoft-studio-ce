/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio;

import java.util.List;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;

/**
 * Entry for a cached template style
 * 
 * @author Orlandin Marco
 *
 */
public class StyleCacheEntry {
	
	/**
	 * The loaded temolate
	 */
	private JRTemplate template;
	
	/**
	 * The {@link JasperReportsConfiguration} who request the loading. In other word
	 * it is the {@link JasperReportsConfiguration} of the report where the template is 
	 * used
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The list of styles provided by the template
	 */
	private List<JRStyle> loadedStyles;
	
	public StyleCacheEntry(JRTemplate template, JasperReportsConfiguration jConfig, List<JRStyle> styles){
		this.template = template;
		this.jConfig = jConfig;
		this.loadedStyles = styles;
	}
	
	public JRTemplate getTemplate(){
		return template;
	}
	
	public JasperReportsConfiguration getConfig(){
		return jConfig;
	}
	
	public List<JRStyle> getStyles(){
		return loadedStyles;
	}
}
