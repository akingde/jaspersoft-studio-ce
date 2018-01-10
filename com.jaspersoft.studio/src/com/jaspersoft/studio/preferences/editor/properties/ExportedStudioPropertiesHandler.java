/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IPropertyCustomExporter;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.preferences.GlobalPreferencePage;

import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Exporter used to import/export the Jaspersoft Studio preferences
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedStudioPropertiesHandler implements IExportedResourceHandler {

	/**
	 * Folder name for the exported properties
	 */
	private static final String CONTAINER_NAME = "jssProperties"; //$NON-NLS-1$
	
	/**
	 * File name for the exported properties
	 */
	private static final String FILE_NAME = "backup.properties"; //$NON-NLS-1$
	
	/**
	 * The list of JSS properties that should not be exported. 
	 */
	private static HashSet<String> propertiesBlackList = null;
	
	/**
	 * Return the list of properties that should not be exported. Among this there is 
	 * the logger properties since their change can regard the application ini file
	 * 
	 * @return a not null set of properties id that should not be exported/imported
	 */
	private static HashSet<String> getPropertiesBlacklist(){
		HashSet<String> result = new HashSet<String>();
		result.add(GlobalPreferencePage.LOG_ENABLE);
		result.add(GlobalPreferencePage.LOG4j_FILE);
		result.add(GlobalPreferencePage.LOG_FILE);
		return result;
	}
	
	/**
	 * Used to get the properties that should not be exported, first of all look if the 
	 * information is already available in the static field, if it is so then it is immediately
	 * returned. Otherwise it is created and stored.
	 * It is created from a set of embedded properties plus the properties already handled by
	 * other exporters
	 * 
	 * @return a not null set of properties names that should not be exported
	 */
	protected HashSet<String> getExcludedProeprties(){
		if (propertiesBlackList == null) {
			 propertiesBlackList = getPropertiesBlacklist();
			 for(IExportedResourceHandler definition : ExtensionManager.getContributedExporters()){
				 if (definition instanceof IPropertyCustomExporter){
					 IPropertyCustomExporter customExporter = (IPropertyCustomExporter)definition;
					 propertiesBlackList.addAll(customExporter.getHandledProperties());
				 }
			 }
		}
		return propertiesBlackList;
	}
	
	@Override
	public String getResourceNameExport() {
		try{
			MScopedPreferenceStore store = (MScopedPreferenceStore)JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			HashSet<String> resources = new HashSet<String>();
			IEclipsePreferences pref = store.getQualifierStore();
			resources.addAll(Arrays.asList(pref.keys()));
			int size = resources.size();
			for(String blacklistedProperty : getExcludedProeprties()){
				if (resources.contains(blacklistedProperty)){
					size --;
				}
			}
			return "Jaspersoft Studio Properties (" + size + ")"; //$NON-NLS-1$
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return "Jaspersoft Studio Properties"; //$NON-NLS-1$
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
  	File destDir = new File (exportedContainer, CONTAINER_NAME);
  	File f = new File(destDir, FILE_NAME);
  	FileInputStream is = null;
  	String result = "Jaspersoft Studio Properties"; //$NON-NLS-1$
  	try{
	    is = new FileInputStream(f);
	    Properties loadedProperties = new Properties();
	    loadedProperties.load(is);
	    result = "Jaspersoft Studio Properties (" + loadedProperties.size() + ")"; //$NON-NLS-1$
  	} catch (Exception ex){
  		ex.printStackTrace();
  	} finally {
  		FileUtils.closeStream(is);
  	}
		return result;
	}
	
	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
		File destDir = new File (exportedContainer, CONTAINER_NAME);
		if (destDir.exists() && destDir.list().length > 0){
			result.add(new BaseResource("All Jaspersoft Studio Preferences"));
		}
		return result;
	}

	@Override
	public List<IResourceDefinition> getExportableResources() {
		List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
		try{
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			if ( props.size() > 0) result.add(new BaseResource("All Jaspersoft Studio Preferences"));
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {
		OutputStream out = null;
		File destDir = null;
		if (!resourcesToExport.isEmpty()){
			try{
				MScopedPreferenceStore store = (MScopedPreferenceStore)JaspersoftStudioPlugin.getInstance().getPreferenceStore();
				HashSet<String> resources = new HashSet<String>();
				IEclipsePreferences pref = store.getQualifierStore();
				resources.addAll(Arrays.asList(pref.keys()));
				Properties props = new Properties();
				for(String resource : resources){
					if (!getExcludedProeprties().contains(resource)){
						props.put(resource, store.getString(resource));
					}
				}
				
				//Create the temp folder
				File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
				tempDir.deleteOnExit();
				destDir = new File (tempDir, CONTAINER_NAME);
				if (destDir.exists()) FileUtils.recursiveDelete(destDir);
				destDir.mkdirs();
			
		    out = new FileOutputStream(new File(destDir, FILE_NAME));
		    props.store(out, "Jaspersoft Studio Properties Backup"); //$NON-NLS-1$
			} catch (Exception ex){
				ex.printStackTrace();
				destDir = null;
			} finally {
				FileUtils.closeStream(out);
			}
			return destDir;
		} else {
			return null;
		}
	}

	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
    InputStream is = null;
    if (!resourcesToImport.isEmpty()){
	    try {
	    	//Load the stored resources
	    	File destDir = new File (exportedContainer, CONTAINER_NAME);
	    	File f = new File(destDir, FILE_NAME);
	      is = new FileInputStream(f);
	      Properties loadedProperties = new Properties();
	      loadedProperties.load(is);
	      
	      HashSet<String> missingProperties = new HashSet<String>();
	      for(Object key : loadedProperties.keySet()){
	      	missingProperties.add(key.toString());
	      }
	      
	      MScopedPreferenceStore store = (MScopedPreferenceStore)JaspersoftStudioPlugin.getInstance().getPreferenceStore();
				IEclipsePreferences pref = store.getQualifierStore();
				for(String key : pref.keys()){
					if (!getExcludedProeprties().contains(key)){
						Object loadedValue = loadedProperties.get(key);
						if (loadedValue != null){
							pref.put(key, loadedValue.toString());
							missingProperties.remove(key);
						} else {
							pref.remove(key);
						}
					}
				}
				for(String missingProperty : missingProperties){
					Object loadedValue = loadedProperties.get(missingProperty);
					pref.put(missingProperty, loadedValue != null ? loadedValue.toString() : null);
				}
				store.save();
	    }
	    catch (Exception e) { 
	    	e.printStackTrace();
	    } finally {
	    	FileUtils.closeStream(is);
	    }
    }
	}
}
