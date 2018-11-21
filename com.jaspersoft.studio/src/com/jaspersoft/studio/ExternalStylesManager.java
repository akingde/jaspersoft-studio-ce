/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;
import net.sf.jasperreports.repo.RepositoryContext;
import net.sf.jasperreports.repo.RepositoryUtil;
import net.sf.jasperreports.repo.SimpleRepositoryContext;
import net.sf.jasperreports.repo.SimpleRepositoryResourceContext;

/**
 * Class that caches the external styles to improve the performance when resolving their names
 * 
 * @author Orlandin Marco
 *
 */
public class ExternalStylesManager {

	/**
	 * Map of the cached styles, the key is the absolute path to the template file
	 */
	private static HashMap<String, StyleCacheEntry> externalStylesCache = new HashMap<String, StyleCacheEntry>();
	
	/**
	 * Initialize the appropriate listener
	 */
	public static void initListeners(){
		//FOR FUTURE USE, IS CALLED AT START AND CAN INITIALIZE LISTENERS FOR THE TEMPLATE FILES
	}
	
	/**
	 * Map of the expression that was already attempt to evaluate, but since their evaluation
	 * failed they are marked as not valuable, so the next evaluation will be skipped
	 */
	private static HashSet<String> notEvaluableExpressions = new HashSet<String>();
	
	/**
	 * Key when it is raised an event of style not found
	 */
	public static final String STYLE_NOT_FOUND_EVENT = "templateReferenceNotFound";
	
	/**
	 * Key when it is raised an event of style found
	 */
	public static final String STYLE_FOUND_EVENT = "templateReferenceFound";
	
	/**
	 * Fire an event of style found or not found
	 * 
	 * @param event the text of the event, should be STYLE_NOT_FOUND_EVENT or STYLE_FOUND_EVENT
	 * @param element JRelement of the template style
	 */
	private static void fireEvent(String event, Object element){
		if (element instanceof JRChangeEventsSupport){
			JRChangeEventsSupport eventElement = (JRChangeEventsSupport)element;
			eventElement.getEventSupport().firePropertyChange(event, null, null);
		}
	}
	
	/**
	 * Check if a style reference expression is valuable or not 
	 * 
	 * @param projectPath the path of the project where the style is defined
	 * @param expression the text of the expression
	 * @param true if the expression can be evaluated (because it was already evaluated without errors or
	 * because it was never evaluated) false otherwise (during the last attempt to evaluate the expression an
	 * error happen)
	 */
	public static boolean isNotValuable(String projectPath, String expression){
		return (notEvaluableExpressions.contains(projectPath + "." + expression));
	}
	
	/**
	 * Check if a style reference expression is valuable or not 
	 * 
	 * @param template the model that contains the reference information
	 * @param true if the expression can be evaluated (because it was already evaluated without errors or
	 * because it was never evaluated) false otherwise (during the last attempt to evaluate the expression an
	 * error happen)
	 */
	public static boolean isNotValuable(MStyleTemplate template){
		JasperReportsConfiguration jConf = template.getJasperConfiguration();
		IFile project = (IFile) jConf.get(FileUtils.KEY_FILE);
		String projectPath = project.getLocation().toPortableString();
		
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) template.getValue();
		String expression =  jrTemplate.getSourceExpression().getText();
		return (notEvaluableExpressions.contains(projectPath + "." + expression));
	}
	
	/**
	 * Add a new expression of a template style to the not valuable expressions
	 * 
	 * @param projectPath the path of the project where the style is defined
	 * @param expression the text of the expression
	 */
	public static void addNotValuableExpression(String projectPath, String expression){
		notEvaluableExpressions.add(projectPath + "." + expression);
	}
	
	/**
	 * Reload a style, ignoring if it expression was already evaluated before
	 * 
	 * @param template a template style element, the value inside the model must be an
	 * instance of JRDesignReportTemplate
	 * @param fireEvents true if the reloaded styles should be notified with an event when the load
	 * operation ends, false otherwise
	 */
	protected static void refreshTemplte(ANode template, boolean fireEvents){
		JasperReportsConfiguration jConf = template.getJasperConfiguration();
		IFile project = (IFile) jConf.get(FileUtils.KEY_FILE);
		String projectPath = project.getLocation().toPortableString();
		
		JRDesignReportTemplate jrTemplate = (JRDesignReportTemplate) template.getValue();
		JRExpression jrExpression = jrTemplate.getSourceExpression();
		if (jrExpression != null){
			String expression =  jrExpression.getText();
			
			notEvaluableExpressions.remove(projectPath + "." + expression);
			//Recalculate the style overwriting the cache
			String evaluatedExpression = evaluateStyleExpression(jrTemplate, project, jConf);
			if (evaluatedExpression != null) {
				//It is not a local file, try to resolve it with the repository serivce
				String key = projectPath + evaluatedExpression;
				externalStylesCache.remove(key);
				JRTemplate loadedTemplate = getTemplate(jConf, evaluatedExpression);
				if (loadedTemplate != null){
					 if (fireEvents){
						 fireEvent(STYLE_FOUND_EVENT, jrTemplate);
					 }
					 return;
				}			
			}
		}
		fireEvent(STYLE_NOT_FOUND_EVENT, jrTemplate);
	}
	
	/**
	 * Load a template style from its location. It handle a cache to store
	 * the templates loaded
	 * 
	 * @param jConf the configuration of the current opened file
	 * @param location a not null location for the template
	 * @return reference to the loaded template or null if it can't be found 
	 */
	public static JRTemplate getTemplate(JasperReportsConfiguration jConf, String location){
		return getTemplate(jConf, location, false);
	}
	
	/**
	 * Load a template style from its location. It handle a cache to store
	 * the templates loaded. It trigger also the refresh of the report drawer if the
	 * call is not a nested one. A nested call could occur if the loaded template as 
	 * other templates inside
	 * 
	 * @param jConf the configuration of the current opened file
	 * @param location a not null location for the template
	 * @param isNestedCall flag used to know if it is a nested call. If it is not a nested
	 * call once the template is loaded trigger a refresh of the drawer 
	 * @return reference to the loaded template or null if it can't be found 
	 */
	protected static JRTemplate getTemplate(JasperReportsConfiguration jConf, String location, boolean isNestedCall){
		if (jConf != null && location != null){
			IFile file = (IFile) jConf.get(FileUtils.KEY_FILE);
			String reportPath = file.getLocation().toPortableString();
			String key = reportPath + location;
			if (externalStylesCache.containsKey(key)){
				return externalStylesCache.get(key).getTemplate();
			} else {
				try {
					String parentPath = file.getParent().getLocation().toFile().getAbsolutePath();
					SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(parentPath);
					RepositoryContext repoContext = SimpleRepositoryContext.of(jConf, context);
					byte[] data = RepositoryUtil.getInstance(repoContext).getBytesFromLocation(location);
					if (data != null){
						JRTemplate resolvedTemplate = JRXmlTemplateLoader.load(new ByteArrayInputStream(data));
						if (resolvedTemplate != null){
							//bytestream was found and it is of a valid template
							List<JRStyle> templateStyles = new ArrayList<JRStyle>();
							loadTemplateStyle(resolvedTemplate, location, jConf, templateStyles);
							StyleCacheEntry cacheEntry = new StyleCacheEntry(resolvedTemplate, jConf, templateStyles);
							externalStylesCache.put(key, cacheEntry);
							if (!isNestedCall) jConf.refreshCachedStyles();
							return resolvedTemplate;
						} else {
							//bytestram was found but it is not a valid template, cache an empty entry
							StyleCacheEntry cacheEntry = new StyleCacheEntry(null, jConf, new ArrayList<JRStyle>());
							externalStylesCache.put(key, cacheEntry);
						}
					} else {
						//bytesteam was not found, cache an empty entry. Will need a manual refresh to reevaluate the template
						//or a resource change event, that wipe the cache
						StyleCacheEntry cacheEntry = new StyleCacheEntry(null, jConf, new ArrayList<JRStyle>());
						externalStylesCache.put(key, cacheEntry);
					}
				} catch (JRException e) {
					e.printStackTrace();
				} catch (JRRuntimeException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * Check if the template on a specific path is a valid TemplateStyle. If the template is 
	 * was still unresolved it is resolved and checked. If it is valid it is also cached, since
	 * it can be requested later
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the report where the template is, mustbe not null
	 * @param location the path to resolve the template, must be not null
	 * @return true if the template can be resolved and it is valid, false otherwise
	 */
	public static boolean validateTemplate(JasperReportsConfiguration jConfig, String location){
			IFile file = (IFile) jConfig.get(FileUtils.KEY_FILE);
			String reportPath = file.getLocation().toPortableString();
			String key = reportPath + location;
			if (externalStylesCache.containsKey(key)){
				return externalStylesCache.get(key).getTemplate() != null;
			} else {
				try{
					String parentPath = file.getParent().getLocation().toFile().getAbsolutePath();
					SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(parentPath);
					RepositoryContext repoContext = SimpleRepositoryContext.of(jConfig, context);
					byte[] data = RepositoryUtil.getInstance(repoContext).getBytesFromLocation(location);
					if (data != null){
						JRTemplate template = JRXmlTemplateLoader.load(new ByteArrayInputStream(data));
						if (template != null){
							//synce the template was found save it in the cache
							List<JRStyle> templateStyles = new ArrayList<JRStyle>();
							loadTemplateStyle(template, location, jConfig, templateStyles);
							StyleCacheEntry cacheEntry = new StyleCacheEntry(template, jConfig, templateStyles);
							externalStylesCache.put(key, cacheEntry);
							return true;
						} else {
							return false;
						}
					}
					return true;
				} catch (Exception ex){
					ex.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(ex);
					return false;
				}
		}
	}
	
	/**
	 * Find all the styles inside a template, and also in the other templates nested in it. If a template is referenced
	 * more times in the hierarchy then it is skipped
	 * 
	 * @param template the template, must be not null
	 * @param location the location of the template
	 * @param jConfig the {@link JasperReportsConfiguration} of the current file
	 * @param result list where the found styles are stored, must be not null
	 */
	protected static void loadTemplateStyle(JRTemplate template, String location, JasperReportsConfiguration jConfig, List<JRStyle> result){
		Set<String> loadedLocations = new HashSet<String>();
		loadedLocations.add(location);
		JRTemplateReference[] includedTemplates = template.getIncludedTemplates();
		
		for(JRStyle style : template.getStyles()){
			result.add(style);
		}
		
		if (includedTemplates != null)
		{
			for (int i = 0; i < includedTemplates.length; i++)
			{
				JRTemplateReference reference = includedTemplates[i];
				loadTemplateStyles(jConfig, reference.getLocation(), loadedLocations, result);
			}
		}
		
	}
	
	/**
	 * Recursive method to load a template defined inside another template
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the current file
	 * @param location the location of the template to load
	 * @param loadedLocations the set of already loaded locations, to avoid to load more time the same resource
	 * @param result list where the found styles are placed 
	 */
	protected static void loadTemplateStyles(JasperReportsConfiguration jConfig, String location, Set<String> loadedLocations, List<JRStyle> result)
	{
		if (!loadedLocations.contains(location)){
			loadedLocations.add(location);
			JRTemplate template = getTemplate(jConfig, location, true);
			if (template != null){
				for(JRStyle style : template.getStyles()){
					result.add(style);
				}
				JRTemplateReference[] includedTemplates = template.getIncludedTemplates();
				if (includedTemplates != null)
				{
					for (int i = 0; i < includedTemplates.length; i++)
					{
						JRTemplateReference reference = includedTemplates[i];
						loadTemplateStyles(jConfig, reference.getLocation(), loadedLocations, result);
					}
				}
			}
		}
	}
	
	/**
	 * Reload a style, ignoring if it expression was already evaluated before
	 * 
	 * @param template a template style element, the value inside the model must be an
	 * instance of JRDesignReportTemplate
	 */
	public static void refreshStyle(ANode template){
		refreshTemplte(template, true);
	}
	
	/**
	 * Reload a style reference (so doesn't need to resolve the expression) and update the styles
	 * map
	 * 
	 * @param template a template style element, the value inside the model must be an
	 * instance of JRDesignReportTemplate
	 * @param parent the parten template style if this reference is contained into it, otherwise null
	 */
	public static void refreshStyleReference(ANode template, MStyleTemplate parent){
		JasperReportsConfiguration jConf = template.getJasperConfiguration();
		IFile project = (IFile) jConf.get(FileUtils.KEY_FILE);
		String projectPath = project.getLocation().toPortableString();
		JRTemplateReference jrTemplate = (JRTemplateReference) template.getValue();
		String location = jrTemplate.getLocation();
		if (location != null) {
			String key = projectPath + location;
			externalStylesCache.remove(key);
			JRTemplate loadedTemplate = getTemplate(jConf, location);
			if (loadedTemplate != null){
				fireEvent(STYLE_FOUND_EVENT, jrTemplate);
			} else {
				fireEvent(STYLE_NOT_FOUND_EVENT, jrTemplate);
			}
		}
	}
	
	/**
	 * Get the value of the expression of the style, but check also if there is an annotation defined
	 * with the @path value
	 */
	protected static String getExpressionValue(JRExpression styleExpression){
		String expString = styleExpression != null ? styleExpression.getText() : "";
		String variableStaticPath = ExpressionUtil.extractValueForVariable(MStyleTemplate.PATH_ANNOTATION, expString);
		if (variableStaticPath != null){
			return variableStaticPath;
		}
		return expString;
	}
	
	/**
	 * Resolve an expression and return the reference to the style or null if it can not be resolve
	 * 
	 * @param styleExpression expression of the external style
	 * @param project project of the report
	 * @param jConfig Configuration of the report to evaluate the expression
	 * @return path of the style or null if the expression can't be resolved
	 */
	public static String evaluateStyleExpression(JRReportTemplate style, IFile project, JasperReportsConfiguration jConfig){	
		String evaluatedExpression = null;
		String projectPath = project.getLocation().toPortableString();
		JRExpression styleExpression = style.getSourceExpression();
		String expString = getExpressionValue(styleExpression);
		try{
			//Check first if there are previous failed attempt to evaluate the expression
			if (!isNotValuable(projectPath, expString)){
				evaluatedExpression =  ExpressionUtil.cachedExpressionEvaluationString(new JRDesignExpression(expString), jConfig); 
				if (evaluatedExpression == null){
					//The expression is not valuable, add it to the map
					addNotValuableExpression(projectPath, expString);
					fireEvent(STYLE_NOT_FOUND_EVENT, style);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			//The expression is not valuable, add it to the map
			addNotValuableExpression(projectPath, expString);
			fireEvent(STYLE_NOT_FOUND_EVENT, style);
		}
		return evaluatedExpression;
	}
	
	
	/**
	 * If the expression of an external style can be resolved then return all the jrstyle defined inside
	 * otherwise return an empty list
	 * 
	 * @param styleExpression expression of the external style
	 * @param project project of the report
	 * @param jConfig Configuration of the report to evaluate the expression
	 * @return Not null list of styles inside the external style associated with the project and expression
	 */
	public static List<JRStyle> getStyles(JRReportTemplate style, IFile project, JasperReportsConfiguration jConfig) {
		String evaluatedExpression = evaluateStyleExpression(style, project, jConfig);
		if (evaluatedExpression != null) {
			String projectPath = project.getLocation().toPortableString();
			String key = projectPath + evaluatedExpression;
			if (externalStylesCache.containsKey(key)){
				return externalStylesCache.get(key).getStyles();
			} else {
				JRTemplate loadedTemplate = getTemplate(jConfig, evaluatedExpression);
				if (loadedTemplate != null){
					fireEvent(STYLE_FOUND_EVENT, style);
					return externalStylesCache.get(key).getStyles();
				} else {
					fireEvent(STYLE_NOT_FOUND_EVENT, style);
				}
			}
		}
		return new ArrayList<JRStyle>();
	}
	
	public static boolean isTemplateValid(MStyleTemplate template){
		JasperReportsConfiguration jConfig = template.getJasperConfiguration();
		IFile project = (IFile) jConfig.get(FileUtils.KEY_FILE);
		return isTemplateValid((JRReportTemplate)template.getValue(), project, jConfig);
	}
	
	public static boolean isTemplateValid(JRReportTemplate style, IFile project, JasperReportsConfiguration jConfig){
		String evaluatedExpression = evaluateStyleExpression(style, project, jConfig);
		if (evaluatedExpression != null) {
			String projectPath = project.getLocation().toPortableString();
			String key = projectPath + evaluatedExpression;
			if (externalStylesCache.containsKey(key)){
				return externalStylesCache.get(key).getTemplate() != null;
			} else {
				JRTemplate loadedTemplate = getTemplate(jConfig, evaluatedExpression);
				if (loadedTemplate != null){
					return externalStylesCache.get(key).getTemplate() != null;
				}
			}
		}
		return false;
	}
	
	/**
	 * Given a list of JRStyles and a styles name search inside the list a JRStyle
	 * with that name and return it
	 * 
	 * @param jrStylesList list where the style is searched
	 * @param searchedName name of the searched style
	 * @return the style in the list with the requested name, or null if it can't be found
	 */
	private static JRStyle searchStyleInList(List<JRStyle> jrStylesList, String searchedName){
		for(JRStyle style : jrStylesList){
			if (searchedName.equals(style.getName())){
				return style;
			}
		}
		return null;
	}
	
	/**
	 * Search in all the external styles template of a report a style with a specific name
	 * and return it. If it can't be found it return null, and if there are more styles in 
	 * different templates with the searched name then the first one found is returned
	 * 
	 * @param styleName the name of the style searched
	 * @param jConfig jasper configuration of the report 
	 * @return a JRStyle reference of the searched style or null if it can't be found between 
	 * the defined external styles
	 */
	public static JRStyle getExternalStyle(String styleName, JasperReportsConfiguration jConfig){
		JasperDesign design = jConfig.getJasperDesign();
		if (design != null){
			IFile project = (IFile) jConfig.get(FileUtils.KEY_FILE);
			for (JRReportTemplate template : design.getTemplatesList()){
				List<JRStyle> loadedStyles = getStyles(template, project, jConfig);
				JRStyle searchedStyle = searchStyleInList(loadedStyles, styleName);
				if (searchedStyle != null) return searchedStyle;
			}
		}
		return null;
	}
	
	/**
	 * Unload for a specific configuration all the template styles requested by it
	 * 
	 * @param jConfig a not null {@link JasperReportsConfiguration}, who requested the load
	 * of the styles
	 */
	public synchronized static void removeCachedStyles(JasperReportsConfiguration jConfig){
		List<Entry<String, StyleCacheEntry>> entries = new ArrayList<Entry<String,StyleCacheEntry>>(externalStylesCache.entrySet());
		for(Entry<String, StyleCacheEntry> entry : entries){
			if (entry.getValue().getConfig() == jConfig){
				externalStylesCache.remove(entry.getKey());
			}
		}
	}
	
	/**
	 * Remove a single style from the cache
	 * 
	 * @param jConfig the config of the report where the template is defined
	 * @param template the template that should be removed from the cache
	 */
	public synchronized static void removeCachedStyle(JasperReportsConfiguration jConfig, JRReportTemplate template){
		IFile project = (IFile) jConfig.get(FileUtils.KEY_FILE);
		String evaluatedExpression = evaluateStyleExpression(template, project, jConfig);
		if (evaluatedExpression != null) {
			String projectPath = project.getLocation().toPortableString();
			String key = projectPath + evaluatedExpression;
			externalStylesCache.remove(key);
		}
	}
}
