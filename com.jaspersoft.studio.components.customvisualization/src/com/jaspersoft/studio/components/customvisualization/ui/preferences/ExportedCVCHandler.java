/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.preferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.preference.IPreferenceStore;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.editor.action.exporter.BaseResource;
import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IPropertyCustomExporter;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.MScopedPreferenceStore;
import net.sf.jasperreports.eclipse.ui.FolderDestinationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Pair;

/**
 * Exporter used to import/export the Custom Visualization components
 * 
 * @author Orlandin Marco
 *
 */
public class ExportedCVCHandler implements IExportedResourceHandler, IPropertyCustomExporter {

	/**
	 * Folder name for the exported properties
	 */
	private static final String CONTAINER_NAME = "cvComponents"; //$NON-NLS-1$
	
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
		return "Custom Vistualization Components (" + getExportableResources().size() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	@Override
	public String getResourceNameImport(File exportedContainer) {
		return "Custom Vistualization Components (" + getRestorableResources(exportedContainer).size() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
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
							return name.endsWith(".json");
						}
					});
					for(File properitesFile : propertiesFiles){
						ComponentDescriptor cd = readComponentDescriptor(properitesFile);
						if (cd != null){
							String componentName = cd.getLabel();
							BaseResource templateResource = new BaseResource(componentName);
						    templateResource.setData(properitesFile.getAbsolutePath());
						    result.add(templateResource);
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
	
			IPreferenceStore store = JaspersoftStudioPlugin.getInstance().getPreferenceStore();
			String paths = store.getString(CVCDescriptorsPreferencePage.RESOURCE_PATHS);
			List<String> pathsList = parseString(paths);
			for (String dir : pathsList) {
				File componentsLocation = new File(dir);
				File[] definitionFiles = componentsLocation.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".json");
					}
				});
				for (File f : definitionFiles) {
					ComponentDescriptor cd = readComponentDescriptor(f);
					if (cd != null){
				    	BaseResource templateResource = new BaseResource(cd.getLabel());
				    	templateResource.setData(f.getAbsolutePath());
				    	cachedExportableResources.add(templateResource);
					}
				}
			} 
		}
		return cachedExportableResources;
	}
	
	private ComponentDescriptor readComponentDescriptor(File resource) {
		ObjectMapper mapper = new ObjectMapper();
		InputStream is = null;
		ComponentDescriptor result = null;
		try {
			is = new FileInputStream(resource);
			BufferedReader in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			result = mapper.readValue(in, ComponentDescriptor.class);
		} catch (Exception ex){
			JaspersoftStudioPlugin.getInstance().logError(ex);
		} finally {
			FileUtils.closeStream(is);
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
	
	protected void removeUnwantedComponents(File destinationFolder, HashSet<String> allowedTemplates){
		File[] definitionFiles = destinationFolder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".json");
			}
		});
		for(File definitionFile : definitionFiles){
			if (!allowedTemplates.contains(definitionFile.getName())){
				ComponentDescriptor componentToRemove = readComponentDescriptor(definitionFile);
				
				//delete the thumbnail
				File thumbnail = new File(definitionFile.getParent(), componentToRemove.getThumbnail());
				thumbnail.delete();
				
				//Delete the other files with the same name of the definition but with different extension
				final String definitionFileName = FilenameUtils.removeExtension(definitionFile.getName());
				File[] filesToDelete = destinationFolder.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						String fileNameWithOutExt = FilenameUtils.removeExtension(name);
						return fileNameWithOutExt.equals(definitionFileName) || 
								fileNameWithOutExt.equals(definitionFileName + ".min");
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
		String paths = store.getString(CVCDescriptorsPreferencePage.RESOURCE_PATHS);
		List<String> pathsList = parseString(paths);
		if (pathsList.isEmpty()) return null;
		
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		tempDir.deleteOnExit();
		File destDir = new File (tempDir, CONTAINER_NAME);
		if (destDir.exists()) FileUtils.recursiveDelete(destDir);
		destDir.mkdirs();
		
		//Create the set of the resources that should be exported
		HashMap<String, HashSet<String>> componentsToExportMap = new HashMap<String, HashSet<String>>();
		for(IResourceDefinition definition : resourcesToExport){
			File templateDefinition = new File(definition.getData().toString());
			File contentFolder = templateDefinition.getParentFile();
			String templateDefinitionName = templateDefinition.getName();
			HashSet<String> templateNames = componentsToExportMap.get(contentFolder.getAbsolutePath());
			if (templateNames == null){
				templateNames = new HashSet<String>();
				componentsToExportMap.put(contentFolder.getAbsolutePath(), templateNames);
			}
			templateNames.add(templateDefinitionName);
		}
		
		//Start the export operation
		HashSet<String> usedNames = new HashSet<String>();
		for(String path : pathsList){
			File filePath = new File(path);
			HashSet<String> componentsToExportSet = componentsToExportMap.get(path);
			if (filePath.exists() && filePath.isDirectory() && filePath.list().length > 0 && 
					componentsToExportSet != null && componentsToExportSet.size() > 0){
				String folderName = getFolderName(usedNames, filePath.getName());
				usedNames.add(folderName);
				File containerfolder = new File(destDir, folderName);
				try {
					org.apache.commons.io.FileUtils.copyDirectory(filePath, containerfolder);
					
					//REMOVE UNWANTED TEMPLATES
					removeUnwantedComponents(containerfolder, componentsToExportSet);
					
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
			String s = store.getString(CVCDescriptorsPreferencePage.RESOURCE_PATHS);
			List<String> paths = parseString(s);
  		
			File importedDir = new File (exportedContainer, CONTAINER_NAME);
			File targetDir = new File(destDialog.getDirectory());
  		
			//Create the set of the resources that should be imported
			HashMap<String, HashSet<String>> componentsToImportMap = new HashMap<String, HashSet<String>>();
			for(IResourceDefinition definition : resourcesToImport){
				File componentDefinition = new File(definition.getData().toString());
				File contentFolder = componentDefinition.getParentFile();
				String templateDefinitionName = componentDefinition.getName();
				HashSet<String> templateNames = componentsToImportMap.get(contentFolder.getAbsolutePath());
				if (templateNames == null){
					templateNames = new HashSet<String>();
					componentsToImportMap.put(contentFolder.getAbsolutePath(), templateNames);
				}
				templateNames.add(templateDefinitionName);
			}
  		
	  		for(File templateFolder : importedDir.listFiles()){
	  			HashSet<String> componentsToImportSet = componentsToImportMap.get(templateFolder.getAbsolutePath());
	  			if (componentsToImportSet != null && !componentsToImportSet.isEmpty()){
	  				try {
	  					File targetFile = new File(targetDir, templateFolder.getName());
	  					org.apache.commons.io.FileUtils.copyDirectory(templateFolder, targetFile);
	  					paths.add(targetFile.getAbsolutePath());
	  					removeUnwantedComponents(templateFolder, componentsToImportSet);
	  				} catch (IOException e) {
	  					e.printStackTrace();
	  					JaspersoftStudioPlugin.getInstance().logError(e);
	  				}
	  			}
	  		}
	  		
	  		String newPaths = createList(paths);
	  		pref.put(CVCDescriptorsPreferencePage.RESOURCE_PATHS, newPaths);
	  		try {
				store.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This class handle the custom visualization components location property, so it return the property handled
	 */
	@Override
	public List<String> getHandledProperties() {
		List<String> result = new ArrayList<String>();
		result.add(CVCDescriptorsPreferencePage.RESOURCE_PATHS);
		return result;
	}
}
