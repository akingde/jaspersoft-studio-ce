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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IPropertyCustomExporter;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.templates.TemplateLocationsPreferencePage;

import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Exporter used to import/export the Jaspersoft Studio templates, it will export and import
 * also the folder containing the custom templates
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedStudioTemplatesHandler implements IExportedResourceHandler, IPropertyCustomExporter {

	/**
	 * Folder name for the exported properties
	 */
	private static final String CONTAINER_NAME = "jssReportTemplates"; //$NON-NLS-1$
	
	
	@Override
	public String getResourceNameExport() {
		try{
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			String s = store.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
			List<String> paths = parseString(s);
			return "Jaspersoft Studio Template Folders (" + paths.size() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return "Jaspersoft Studio Template Folders"; //$NON-NLS-1$
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
  	File destDir = new File (exportedContainer, CONTAINER_NAME);
  	String result = "Jaspersoft Studio Template Folders"; //$NON-NLS-1$
  	if (destDir.exists()){
  		result = "Jaspersoft Studio Template Folders (" + destDir.list().length + ")"; //$NON-NLS-1$ //$NON-NLS-2$
  	}
		return result;
	}

	/**
	 * Convert the string in the preferences containing the all the path to a list where every
	 * entry is a single path
	 * 
	 * @param stringList the paths property in the preferences
	 * @return a not null list of string with every custom path
	 */
	protected List<String> parseString(String stringList) {
    StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator + "\n\r");//$NON-NLS-1$
    ArrayList<String> v = new ArrayList<String>();
    while (st.hasMoreElements()) {
        v.add(st.nextElement().toString());
    }
    return v;
	}
	
	/**
	 * From a list of path create a single string to store in the preferences
	 * 
	 * @param items a not null listof path
	 * @return a not null string that can be stored in the preferences
	 */
	protected String createList(List<String> items) {
    StringBuffer path = new StringBuffer("");//$NON-NLS-1$
    for (String item : items) {
        path.append(item);
        path.append(File.pathSeparator);
    }
    return path.toString();
	}
	
	/**
	 * Get an unique folder name to be used when storing all the templates in the exported
	 * folder
	 * 
	 * @param usedNames the already used names, at the end the new name must be inserted in this
	 * set manually
	 * @param baseName the base name
	 * @return an unique name
	 */
	protected String getFolderName(HashSet<String> usedNames, String baseName){
		if (!usedNames.contains(baseName)) return baseName;
		int index  = 1;
		String newName = baseName + "_" + index; //$NON-NLS-1$
		while(usedNames.contains(newName)){
			index++;
			newName = baseName + "_" + index; //$NON-NLS-1$
		}
		return newName;
	}
	
	@Override
	public File exportContentFolder() {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String s = store.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
		List<String> paths = parseString(s);
		if (paths.isEmpty()) return null;
		
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, CONTAINER_NAME);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
	
		HashSet<String> usedNames = new HashSet<String>();
		for(String path : paths){
			File filePath = new File(path);
			if (filePath.exists() && filePath.isDirectory() && filePath.list().length > 0){
				String folderName = getFolderName(usedNames, filePath.getName());
				usedNames.add(folderName);
				File containerfolder = new File(destDir, folderName);
				try {
					org.apache.commons.io.FileUtils.copyDirectory(filePath, containerfolder);
				} catch (IOException e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
			}
		}
		return destDir;
	}

	@Override
	public void restoreContentFolder(File exportedContainer) {
		FolderDestinationDialog destDialog = new FolderDestinationDialog(UIUtils.getShell(), Messages.ExportedStudioTemplatesHandler_dialogTitle, 
																																													Messages.ExportedStudioTemplatesHandler_dialogDescription );
  	destDialog.open();
  	if (destDialog.getDirectory() != null){
      MScopedPreferenceStore store = (MScopedPreferenceStore)JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			IEclipsePreferences pref = store.getQualifierStore();
  		String s = store.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
  		List<String> paths = parseString(s);
  		
  		File importedDir = new File (exportedContainer, CONTAINER_NAME);
  		File targetDir = new File(destDialog.getDirectory());
  		for(File templateFolder : importedDir.listFiles()){
  			try {
  				File targetFile = new File(targetDir, templateFolder.getName());
					org.apache.commons.io.FileUtils.copyDirectory(templateFolder, targetFile);
	  			paths.add(targetFile.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
  		}
  		
  		String newPaths = createList(paths);
  		pref.put(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST, newPaths);
  		try {
				store.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			String s = store.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
			List<String> paths = parseString(s);
			return paths.size() > 0;
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * This class handle the template location property, so it return the property handled
	 */
	@Override
	public List<String> getHandledProperties() {
		List<String> result = new ArrayList<String>();
		result.add(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
		return result;
	}
}
