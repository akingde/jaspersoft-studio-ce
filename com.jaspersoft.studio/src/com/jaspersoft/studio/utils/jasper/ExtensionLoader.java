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
package com.jaspersoft.studio.utils.jasper;

import net.sf.jasperreports.data.DataAdapterServiceFactory;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.ParameterContributorFactory;
import net.sf.jasperreports.engine.export.MatcherExportFilterMapping;
import net.sf.jasperreports.engine.fonts.FontFamily;
import net.sf.jasperreports.engine.query.JRQueryExecuterFactoryBundle;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactory;
import net.sf.jasperreports.engine.style.StyleProviderFactory;
import net.sf.jasperreports.functions.FunctionsBundle;
import net.sf.jasperreports.repo.PersistenceServiceFactory;
import net.sf.jasperreports.repo.RepositoryService;
import net.sf.jasperreports.util.SecretsProviderFactory;

/**
 * 
 * This class allow to preload the jr extensions to have 
 * them cached as soon as possible. Some extension are stored inside
 * the default context, so shared between reports, other are loaded for the a 
 * specified report since they are report dependent
 * 
 * @author Orlandin Marco
 *
 */
public class ExtensionLoader {

	/**
	 * The extensions loaded for each report
	 */
	private static Class<?>[] reportDependentExtensionsKey = {
			FontFamily.class,
			RepositoryService.class
	};
	
	/**
	 * The extensions loaded for the general context
	 */
	private static Class<?>[] commonExensionKeys = {
		PersistenceServiceFactory.class,
		JRQueryExecuterFactoryBundle.class,
		MatcherExportFilterMapping.class,
		StyleProviderFactory.class,
		DataAdapterServiceFactory.class,
		ScriptletFactory.class,
		ParameterContributorFactory.class,
		SecretsProviderFactory.class,
		FunctionsBundle.class
	};

	
	//CODE USED TO TRACK THE PERFORMANCE OF THE EXTENSIONS PRECACHE
	
 /*private HashMap<Class<?>, Boolean> extensionLoaded = new HashMap<Class<?>, Boolean>();
 
 private long timeStartLoading = -1;
 
 private void checkAllLoaded(){
	 boolean allFound = true;
	 for(Class<?> extensionKey : reportDependentExtensionsKey){
		 if (!extensionLoaded.containsKey(extensionKey)){
			 allFound = false;
			 break;
		 }
	 }
	 if (allFound){
		 long loadingTime = System.currentTimeMillis() - timeStartLoading;
		 System.out.println("time required multi thread "+ loadingTime);
	 }
 }
 
 private synchronized void setLoadedExtenson(Class<?> extensionKey){
	 extensionLoaded.put(extensionKey, true);
	 checkAllLoaded();
 }*/
 
	/**
	 * Load the report dependent extensions on the specified context.
	 * Each extension is loaded inside an independent thread
	 * 
	 * @param context where the properties are loaded
	 */
 public void loadExtension(final JasperReportsContext context){
	// timeStartLoading = System.currentTimeMillis();
	 for(Class<?> extensionKey : reportDependentExtensionsKey){
		 final Class<?> key = extensionKey;
		 new Thread(new Runnable() {
			
			@Override
			public void run() {
				context.getExtensions(key);
				//setLoadedExtenson(key);
			}
		}).start();
	 }
 }

 /**
  * Load the shared extensions inside the common context that can be 
  * Retrieved with DefaultJasperReportsContext.getInstance(). Each extension
  * is loaded inside an independent thread
  */
 public static void loadDefaultProperties(){
	 final DefaultJasperReportsContext context = DefaultJasperReportsContext.getInstance();
	 for(Class<?> extensionKey : commonExensionKeys){
		 final Class<?> key = extensionKey;
		 new Thread(new Runnable() {
			
			@Override
			public void run() {
				context.getExtensions(key);
			}
		}).start();
	 }
 }
}
