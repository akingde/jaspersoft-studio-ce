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
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IPropertyCustomExporter;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.templates.TemplateLocationsPreferencePage;
import com.jaspersoft.studio.wizards.BuiltInCategories;

import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.ui.FolderDestinationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;

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
		return "Jaspersoft Studio Templates (" + getExportableResources().size() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
		return "Jaspersoft Studio Templates (" + getRestorableResources(exportedContainer).size() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public List<IResourceDefinition> getRestorableResources(File exportedContainer) {
		String containerPath = exportedContainer.getAbsolutePath();
		if (cachedImportableResources == null || 
				!cachedImportableResources.getKey().equals(containerPath)){
			
			List<IResourceDefinition> result = new ArrayList<IResourceDefinition>();
			File importedDir = new File (exportedContainer, CONTAINER_NAME);
			if (importedDir.exists()){
	  		for(File templateFolder : importedDir.listFiles()){
					File[] propertiesFiles = templateFolder.listFiles(new FilenameFilter() {
						
						@Override
						public boolean accept(File dir, String name) {
							return name.endsWith(".properties");
						}
					});
					for(File properitesFile : propertiesFiles){
						FileInputStream is = null;
						try{
							is = new FileInputStream(properitesFile);
					    Properties props = new Properties();
					    props.load(is);		
					    String templtateName = props.getProperty(BuiltInCategories.NAME_KEY);
					    if (templtateName != null) {
					    	BaseResource templateResource = new BaseResource(templtateName);
					    	templateResource.setData(properitesFile.getAbsolutePath());
					    	result.add(templateResource);
					    }
						} catch(Exception ex){
							
						} finally {
							FileUtils.closeStream(is);
						}
					}
	  		}
			}
			cachedImportableResources = new Pair<String, List<IResourceDefinition>>(containerPath, result);
		}
		return cachedImportableResources.getValue();
	}

	@Override
	public List<IResourceDefinition> getExportableResources() {
		if (cachedExportableResources == null) {
			cachedExportableResources = new ArrayList<IResourceDefinition>();

			try{
				IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
				String s = store.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
				List<String> paths = parseString(s);
				for(String path : paths){
					File templateLocation = new File(path);
					File[] propertiesFiles = templateLocation.listFiles(new FilenameFilter() {
						
						@Override
						public boolean accept(File dir, String name) {
							return name.endsWith(".properties");
						}
					});
					for(File properitesFile : propertiesFiles){
						FileInputStream is = null;
						try{
							is = new FileInputStream(properitesFile);
					    Properties props = new Properties();
					    props.load(is);		
					    String templtateName = props.getProperty(BuiltInCategories.NAME_KEY);
					    if (templtateName != null) {
					    	BaseResource templateResource = new BaseResource(templtateName);
					    	templateResource.setData(properitesFile.getAbsolutePath());
					    	cachedExportableResources.add(templateResource);
					    }
						} catch(Exception ex){
							
						} finally {
							FileUtils.closeStream(is);
						}
					}
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
			
		}
		return cachedExportableResources;
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
	
	protected void removeUnwantedTemplates(File destinationFolder, HashSet<String> allowedTemplates){
		File[] propertiesFiles = destinationFolder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".properties");
			}
		});
		for(File templateFile : propertiesFiles){
			if (!allowedTemplates.contains(templateFile.getName())){
				final String templateToDelete = FilenameUtils.removeExtension(templateFile.getName());
				File[] filesToDelete = destinationFolder.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						String fileNameWithOutExt = FilenameUtils.removeExtension(name);
						return fileNameWithOutExt.equals(templateToDelete);
					}
				});
				for(File fileToDelete : filesToDelete){
					fileToDelete.delete();
				}
			}
		}
	}
	
	@Override
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport) {
		IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
		String s = store.getString(TemplateLocationsPreferencePage.TPP_TEMPLATES_LOCATIONS_LIST);
		List<String> paths = parseString(s);
		if (paths.isEmpty()) return null;
		
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, CONTAINER_NAME);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		
		//Create the set of the resources that should be exported
		HashMap<String, HashSet<String>> templatesToExportMap = new HashMap<String, HashSet<String>>();
		for(IResourceDefinition definition : resourcesToExport){
			File templateDefinition = new File(definition.getData().toString());
			File contentFolder = templateDefinition.getParentFile();
			String templateDefinitionName = templateDefinition.getName();
			HashSet<String> templateNames = templatesToExportMap.get(contentFolder.getAbsolutePath());
			if (templateNames == null){
				templateNames = new HashSet<String>();
				templatesToExportMap.put(contentFolder.getAbsolutePath(), templateNames);
			}
			templateNames.add(templateDefinitionName);
		}
		
		//Start the export operation
		HashSet<String> usedNames = new HashSet<String>();
		for(String path : paths){
			File filePath = new File(path);
			HashSet<String> templatesToExportSet = templatesToExportMap.get(path);
			if (filePath.exists() && filePath.isDirectory() && filePath.list().length > 0 && 
													templatesToExportSet != null && templatesToExportSet.size() > 0){
				String folderName = getFolderName(usedNames, filePath.getName());
				usedNames.add(folderName);
				File containerfolder = new File(destDir, folderName);
				try {
					org.apache.commons.io.FileUtils.copyDirectory(filePath, containerfolder);
					
					//REMOVE UNWANTED TEMPLATES
					removeUnwantedTemplates(containerfolder, templatesToExportSet);
					
				} catch (IOException e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
			}
		}
		return destDir;
	}

	@Override
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport) {
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
  		
  		//Create the set of the resources that should be exported
  		HashMap<String, HashSet<String>> templatesToImportMap = new HashMap<String, HashSet<String>>();
  		for(IResourceDefinition definition : resourcesToImport){
  			File templateDefinition = new File(definition.getData().toString());
  			File contentFolder = templateDefinition.getParentFile();
  			String templateDefinitionName = templateDefinition.getName();
  			HashSet<String> templateNames = templatesToImportMap.get(contentFolder.getAbsolutePath());
  			if (templateNames == null){
  				templateNames = new HashSet<String>();
  				templatesToImportMap.put(contentFolder.getAbsolutePath(), templateNames);
  			}
  			templateNames.add(templateDefinitionName);
  		}
  		
  		for(File templateFolder : importedDir.listFiles()){
  			HashSet<String> templatesToImportSet = templatesToImportMap.get(templateFolder.getAbsolutePath());
  			if (templatesToImportSet != null && !templatesToImportSet.isEmpty()){
  				try {
    				File targetFile = new File(targetDir, templateFolder.getName());
  					org.apache.commons.io.FileUtils.copyDirectory(templateFolder, targetFile);
  	  			paths.add(targetFile.getAbsolutePath());
  	  			removeUnwantedTemplates(templateFolder, templatesToImportSet);
  				} catch (IOException e) {
  					e.printStackTrace();
  					JaspersoftStudioPlugin.getInstance().logError(e);
  				}
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
