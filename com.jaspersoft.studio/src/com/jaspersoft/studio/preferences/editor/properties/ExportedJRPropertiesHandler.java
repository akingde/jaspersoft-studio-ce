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
import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FilePrefUtil;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Exporter used to import/export the global JasperReports properties
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedJRPropertiesHandler implements IExportedResourceHandler {

	/**
	 * Folder name for the exported properties
	 */
	private static final String CONTAINER_NAME = "jrProperties"; //$NON-NLS-1$
	
	/**
	 * File name for the exported properties
	 */
	private static final String FILE_NAME = "backup.properties"; //$NON-NLS-1$
	
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
	public File exportContentFolder() {
		OutputStream out = null;
		File destDir = null;
		try{
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			Properties props = FileUtils.load(store.getString(FilePrefUtil.NET_SF_JASPERREPORTS_JRPROPERTIES));
			
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
	public void restoreContentFolder(File exportedContainer) {
    InputStream is = null;
    try {
    	//Load the stored resources
    	File destDir = new File (exportedContainer, CONTAINER_NAME);
    	File f = new File(destDir, FILE_NAME);
      is = new FileInputStream(f);
      Properties loadedProperties = new Properties();
      loadedProperties.load(is);
      
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
