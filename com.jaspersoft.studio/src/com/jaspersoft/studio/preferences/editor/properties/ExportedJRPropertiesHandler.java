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
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IPropertyCustomExporter;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.Pair;

/**
 * Exporter used to import/export the global JasperReports properties
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedJRPropertiesHandler implements IExportedResourceHandler, IPropertyCustomExporter {

	/**
	 * Folder name for the exported properties
	 */
	private static final String CONTAINER_NAME = "jrProperties"; //$NON-NLS-1$
	
	/**
	 * File name for the exported properties
	 */
	private static final String FILE_NAME = "backup.properties"; //$NON-NLS-1$
	
	/**
	 * Cache when the list of exportable resource definition is requested, used to avoid multiple calculation
	 */
	private List<IResourceDefinition> cachedExportableResources = null;

	/**
	 * Cache when the list of importable resource definition is requested, used to avoid multiple calculation of the same container
	 */
	private Pair<String, List<IResourceDefinition>> cachedImportableResources = null;
	
	@Override
	public String getResourceNameExport() {
		try{
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			return "JasperReports Properties (" + props.size() + ")"; //$NON-NLS-1$
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return "JasperReports Properties"; //$NON-NLS-1$
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
  	File destDir = new File (exportedContainer, CONTAINER_NAME);
  	File f = new File(destDir, FILE_NAME);
  	FileInputStream is = null;
  	String result = "JasperReports Properties"; //$NON-NLS-1$
  	try{
	    is = new FileInputStream(f);
	    Properties loadedProperties = new Properties();
	    loadedProperties.load(is);
	    result = "JasperReports Properties (" + loadedProperties.size() + ")"; //$NON-NLS-1$
  	} catch (Exception ex){
  		ex.printStackTrace();
  	} finally {
  		FileUtils.closeStream(is);
  	}
		return result;
	}

	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			cachedExportableResources = new ArrayList<IResourceDefinition>();
			try{
				IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
				Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
				for(Object key : props.keySet()){
					Object value = props.get(key);
					BaseResource resource = new BaseResource(key.toString() + "=" + Misc.nvl(value.toString()));
					resource.setData(key);
					cachedExportableResources.add(resource);
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return cachedExportableResources;
	}

	
	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		String containerPath = exportedContainer.getAbsolutePath();
		if (cachedImportableResources == null || 
				!cachedImportableResources.getKey().equals(containerPath)){
			
			File destDir = new File (exportedContainer, CONTAINER_NAME);
			File indexFile = new File(destDir, FILE_NAME);
			InputStream is = null;
			if (indexFile.exists()){
				try{
					List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
		      is = new FileInputStream(indexFile);
		      Properties props = new Properties();
		      props.load(is);	
		    	for(Object key : props.keySet()){
						Object value = props.get(key);
						BaseResource resource = new BaseResource(key.toString() + "=" + Misc.nvl(value.toString()));
						resource.setData(key);
						result.add(resource);
					}
					cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, result);
				} catch (Exception ex){
					JaspersoftStudioPlugin.getInstance().logError(ex); 
					cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, new ArrayList<IResourceDefinition>());
				} finally {
					FileUtils.closeStream(is);
				}
			} else {
				cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, new ArrayList<IResourceDefinition>());
			}
		}
		return cachedImportableResources.getValue();
	}
	
	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {
		OutputStream out = null;
		File destDir = null;
		try{
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			
			//Create the set of the resources that should be exported
			HashSet<String> resourcesToExportSet = new HashSet<String>();
			for(IResourceDefinition definition : resourcesToExport){
				resourcesToExportSet.add((String)definition.getData());
			}
			
			//Remove from the properties the unexported keys
			for(Object key : new ArrayList<Object>(props.keySet())){
				if (!resourcesToExportSet.contains(key.toString())){
					props.remove(key);
				}
			}
			
			//Create the temp folder
			File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
			tempDir.deleteOnExit();
			destDir = new File (tempDir, CONTAINER_NAME);
			if (destDir.exists()) FileUtils.recursiveDelete(destDir);
			destDir.mkdirs();
		
	    out = new FileOutputStream(new File(destDir, FILE_NAME));
	    props.store(out, "JasperReports Properties Backup"); //$NON-NLS-1$
		} catch (Exception ex){
			ex.printStackTrace();
			destDir = null;
		} finally {
			FileUtils.closeStream(out);
		}
		return destDir;
	}

	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
    InputStream is = null;
    try {
			//Create the set of the files to import
			HashSet<String> propertiesToImport = new HashSet<String>();
			for(IResourceDefinition resourceToImport : resourcesToImport){
				propertiesToImport.add(resourceToImport.getData().toString());
			}
    	
    	//Load the stored resources
    	File destDir = new File (exportedContainer, CONTAINER_NAME);
    	File f = new File(destDir, FILE_NAME);
      is = new FileInputStream(f);
      Properties loadedProperties = new Properties();
      loadedProperties.load(is);
      
			//Remove from the properties the unimported keys
			for(Object key : new ArrayList<Object>(loadedProperties.keySet())){
				if (!propertiesToImport.contains(key.toString())){
					loadedProperties.remove(key);
				}
			}
      
      //Check if there are duplicates
      boolean hasDuplicatedProperties = false;
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			Properties oldProperties = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			for(Object key : loadedProperties.keySet()){
				if (oldProperties.getProperty(key.toString()) != null){
					hasDuplicatedProperties = true;
					break;
				}
			}
			
			//If there are duplicates ask what to do
			boolean doit = true;
			if (hasDuplicatedProperties){
				doit = UIUtils.showConfirmation(Messages.ExportedJRPropertiesHandler_duplocatedTitle, 
																					Messages.ExportedJRPropertiesHandler_duplicatedMessage);
			}
			if (doit){
				for (Entry<Object, Object> property : loadedProperties.entrySet()) {
					Object key = property.getKey();
					Object value = property.getValue();
					oldProperties.setProperty(key.toString(), value.toString());
				}
				store.setValue(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES, FileUtils.getPropertyAsString(oldProperties));
			}
    }
    catch (Exception e) { 
    	e.printStackTrace();
    } finally {
    	FileUtils.closeStream(is);
    }

	}

	@Override
	public List<String> getHandledProperties() {
		List<String> result = new ArrayList<String>();
		result.add(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES);
		return result;
	}
}
