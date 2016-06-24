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
import java.util.Set;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ReportConverter;

/**
 * Custom report converter that allow the external access to the 
 * styles map.
 */
public class JSSReportConverter extends ReportConverter {
	
	/**
	 * Flag used to keep track if there is a refresh in progress
	 */
	protected boolean loadingTamplateStyles = false;

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
	 * all the external styles will updated. Only a refresh at time can be done
	 * 
	 * @return true if the refresh of the styles was done, otherwise false. It could return false
	 * because there is already a refresh in progress when it is called
	 */
	public boolean refreshCachedStyles(){
		if (!loadingTamplateStyles){
			loadingTamplateStyles = true;
			convert(false);
			loadingTamplateStyles = false;
			return true;
		} 
		return false;
	}
	
	/**
	 * Load a style template, override the original method by looking first inside the JSS {@link ExternalStylesManager} cache
	 */
	@Override
	protected void loadTemplateStyles(String location, Set<String> loadedLocations, Set<String> parentLocations)
	{
		if (!parentLocations.add(location))
		{
			throw 
				new JRRuntimeException(
					EXCEPTION_MESSAGE_KEY_CIRCULAR_DEPENDENCY_FOUND,  
					new Object[]{location} 
					);
		}
		
		if (!loadedLocations.add(location))
		{
			//already loaded
			return;
		}
		
		JRTemplate template = null;
		try
		{
			template = ExternalStylesManager.getTemplate((JasperReportsConfiguration)getJasperReportsContext() ,location);
		}
		catch (Exception e)
		{
			JaspersoftStudioPlugin.getInstance().logWarning("Could not load template from location " + location 
					+ "; some styles might remain unresolved.", e);
			return;
		}
		
		if (template != null){
			JRTemplateReference[] includedTemplates = template.getIncludedTemplates();
			if (includedTemplates != null)
			{
				for (int i = 0; i < includedTemplates.length; i++)
				{
					JRTemplateReference reference = includedTemplates[i];
					loadTemplateStyles(reference.getLocation(), loadedLocations, parentLocations);
				}
			}		
			collectStyles(template.getStyles());
		}
	}

}
