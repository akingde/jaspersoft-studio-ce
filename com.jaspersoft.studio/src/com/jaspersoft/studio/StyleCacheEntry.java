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