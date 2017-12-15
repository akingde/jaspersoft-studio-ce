/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jasper;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.jaspersoft.studio.ExternalStylesManager;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.convert.ReportConverter;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.util.JRExpressionUtil;

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
	 * Load a style template, override the original method by checking if the expression contains an annotation that override
	 * the original expression
	 */
	@Override
	protected void loadReportTemplateStyles(JRReportTemplate template, Set<String> loadedLocations)
	{
		JRExpression sourceExpression = template.getSourceExpression();
		if (sourceExpression != null && sourceExpression.getText() != null)
		{
			String location = null;
			String overridenExpression = ExpressionUtil.extractValueForVariable(MStyleTemplate.PATH_ANNOTATION, sourceExpression.getText());
			if (overridenExpression != null){
				location =  JRExpressionUtil.getSimpleExpressionText(new JRDesignExpression(overridenExpression));
			} else {
				location =  JRExpressionUtil.getSimpleExpressionText(sourceExpression);
			}
			if (location == null)
			{
				JaspersoftStudioPlugin.getInstance().logWarning("Template source expression " + sourceExpression.getText() 
						+ "cannot be evaluated; some styles might remain unresolved.");
			}
			else
			{
				HashSet<String> parentLocations = new HashSet<String>();
				loadTemplateStyles(location, loadedLocations, parentLocations);
			}
		}
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
			JaspersoftStudioPlugin.getInstance().logWarning("Could not load template from location at design time" + location 
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
