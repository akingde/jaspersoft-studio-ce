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
package com.jaspersoft.studio.preferences.editor.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
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
	private static final HashSet<String> propertiesBlackList = getPropertiesBlacklist();
	
	/**
	 * Return the list of properties that should not be exported. Among this there is 
	 * the logger properties since their change can regard the application ini file
	 * and the JasperReport properties since they are already handled by another exporter
	 * 
	 * @return a not null set of properties id that should not be exported/imported
	 */
	private static HashSet<String> getPropertiesBlacklist(){
		HashSet<String> result = new HashSet<String>();
		result.add(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES);
		result.add(GlobalPreferencePage.LOG_ENABLE);
		result.add(GlobalPreferencePage.LOG4j_FILE);
		result.add(GlobalPreferencePage.LOG_FILE);
		return result;
	}
	
	@Override
	public String getResourceNameExport() {
		try{
			MScopedPreferenceStore store = (MScopedPreferenceStore)JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			HashSet<String> resources = new HashSet<String>();
			IEclipsePreferences pref = store.getQualifierStore();
			resources.addAll(Arrays.asList(pref.keys()));
			int size = resources.size();
			for(String blacklistedProperty : propertiesBlackList){
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
	public File exportContentFolder() {
		OutputStream out = null;
		File destDir = null;
		try{
			MScopedPreferenceStore store = (MScopedPreferenceStore)JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			HashSet<String> resources = new HashSet<String>();
			IEclipsePreferences pref = store.getQualifierStore();
			resources.addAll(Arrays.asList(pref.keys()));
			Properties props = new Properties();
			for(String resource : resources){
				if (!propertiesBlackList.contains(resource)){
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
	}

	@Override
	public void restoreContentFolder(File exportedContainer) {
    InputStream is = null;
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
				if (!propertiesBlackList.contains(key)){
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

	@Override
	public boolean hasRestorableResources(File exportedContainer) {
		File destDir = new File (exportedContainer, CONTAINER_NAME);
		return destDir.exists() && destDir.list().length > 0;
	}

	@Override
	public boolean hasExportableResources() {
		try{
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			return props.size() > 0;
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

}
