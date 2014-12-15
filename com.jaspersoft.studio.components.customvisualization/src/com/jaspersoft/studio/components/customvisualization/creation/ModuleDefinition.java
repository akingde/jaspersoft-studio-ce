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
package com.jaspersoft.studio.components.customvisualization.creation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;

/**
 * Definition of a Custom visualization component module.
 * A module can be used to create the skeleton of a custom
 * visualization project inside the workspace. It provide 
 * all the information on the component and it's dependencies
 * 
 * @author Orlandin Marco
 *
 */
public class ModuleDefinition {
	
	private String moduleVisualName;
	
	/**
	 * The name of the module
	 */
	private String moduleName;
	
	/**
	 * The url to the license file
	 */
	private String licenseURL;
	
	/**
	 * The url to the library module
	 */
	private String libraryURL;
	
	/**
	 * The name of the variable that will be used for the library
	 * inside the build.js file
	 */
	private String variableName;
	
	/**
	 * The path of the render file for the module
	 */
	private String renderResource;
	
	/**
	 * The path of the CSS file for the module
	 */
	private String cssResource;
	
	/**
	 * Version number of the library
	 */
	private String libraryVersionNumber;
	
	/**
	 * Flag to say if the library need or not the shim
	 */
	private boolean needShim = false;
	
	/**
	 * List of shim dependences, only used if the library is shimmed
	 */
	private List<String> shim_dependencies = new ArrayList<String>();
	
	/**
	 * Name used when exported in shim section, only used if the library is shimmed
	 */
	private String shimExportName;
	
	/**
	 * Dependences of this module
	 */
	private List<ModuleDefinition> requiredLibraries = new ArrayList<ModuleDefinition>();
	
	/**
	 * List of resources that can be used to provide additional samples when a module is 
	 * used to generate a custom visualization component project
	 */
	private List<String> sampleResources = new ArrayList<String>();
	
	/**
	 * Return the module name. It is the same used inside the
	 * render;
	 * 
	 * @return a not null string
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * Set the module name. Must be the same used inside the
	 * render;
	 * 
	 * @param moduleName a not null string
	 */
	public void setModuleName(String moduleName) {
		Assert.isNotNull(moduleName);
		this.moduleName = moduleName;
	}
	
	/**
	 * Module visual name, used to show it during the wizard
	 * 
	 * @return return the module name, can be null
	 */
	public String getModuleVisualName() {
		return moduleVisualName;
	}

	/**
	 * Set the module visual name, used to show the module during the wizard
	 * 
	 * @param moduleVisualName the name of the module
	 */
	public void setModuleVisualName(String moduleVisualName) {
		this.moduleVisualName = moduleVisualName;
	}

	/**
	 * Return the url of the license
	 * 
	 * @return a not null url for the license
	 */
	public String getLicenseURL() {
		return licenseURL;
	}

	/**
	 * Set the license url
	 * 
	 * @param licenseURL a not null string and a valid url
	 */
	public void setLicenseURL(String licenseURL) {
		Assert.isNotNull(licenseURL);
		this.licenseURL = licenseURL;
	}

	/**
	 * Return the url of the library
	 * 
	 * @return an url for the library, can be null
	 * if no library is used by the module.
	 */
	public String getLibraryURL() {
		return libraryURL;
	}

	/**
	 * Set the library url
	 * 
	 * @param libraryURL an url for the library, could 
	 * be null if no library is provided
	 */
	public void setLibraryURL(String libraryURL) {
		this.libraryURL = libraryURL;
	}

	/**
	 * Return the name of the variable to use inside 
	 * the build file for the library
	 * 
	 * @return a not null name for the variable name
	 */
	public String getVariableName() {
		return variableName;
	}

	/**
	 * Set the name of the variable to use inside 
	 * the build file for the library
	 * 
	 * @param name a not null variable name
	 */
	public void setVariableName(String name) {
		Assert.isNotNull(name);
		this.variableName = name;
	}
	
	/**
	 * Set the path of the render resource
	 * 
	 * @param renderResource the path of the render resource.
	 * Can be null if the render resource is not provided
	 */
	public void setRenderResource(String renderResource) {
		this.renderResource = renderResource;
	}

	/**
	 * Read the content of the render resource as string
	 * 
	 * @return the content of the render resource or  null
	 * if it is not provided
	 */
	public String getRenderResource() {
		if (renderResource == null) return null;
		return fetchResource(renderResource);
	}

	/**
	 * Set the path of the css resource
	 * 
	 * @param cssResource the path of the css resource.
	 * Can be null if the css resource is not provided
	 */
	public void setCssResource(String cssResource) {
		this.cssResource = cssResource;
	}

	/**
	 * Read the content of the css resource as string
	 * 
	 * @return the content of the css resource or  null
	 * if it is not provided
	 */
	public String getCssResource(){
		if (cssResource == null) return null;
		return fetchResource(cssResource);
	}
	
	/**
	 * Check if the module library need to be shimmed
	 * 
	 * @return true if the library need to be shimmed,
	 * false otherwise
	 */
	public boolean isNeedShim() {
		return needShim;
	}

	/**
	 * set if the module library need to be shimmed
	 * 
	 * @param needShim true if the library need to be shimmed,
	 * false otherwise
	 */
	public void setNeedShim(boolean needShim) {
		this.needShim = needShim;
	}

	/**
	 * Get the shimmed library export name. This is used
	 * only when the library is requested to be shimmed
	 * 
	 * @return the library export name that can be used
	 * in the shim section of the build.js file
	 */
	public String getShimExportName() {
		return shimExportName;
	}

	/**
	 * Set the shimmed library export name. This is used
	 * only when the library is requested to be shimmed
	 * 
	 * @param shimExportName the library export name that can be used
	 * in the shim section of the build.js file
	 */
	public void setShimExportName(String shimExportName) {
		this.shimExportName = shimExportName;
	}

	/**
	 * Get the list of dependences that the library uses when shimmed. 
	 * This is used only when the library is requested to be shimmed
	 * 
	 * @return the list of libraries that will be inside the shim section
	 * for this library as dependences. The result is always not null
	 */
	public List<String> getShimDependencies() {
		return shim_dependencies;
	}
	
	/**
	 * Add a library the list of dependences that the library uses when shimmed. 
	 * This is used only when the library is requested to be shimmed
	 * 
	 * @param dependency the name of the library to add to the dependences, must
	 * be not null
	 */
	public void addShimDependency(String dependency){
		Assert.isNotNull(dependency);
		shim_dependencies.add(dependency);
	}
	
	/**
	 * Return the version number of the library as string
	 * 
	 * @return a not null version number
	 */
	public String getLibraryVersionNumber() {
		return libraryVersionNumber;
	}

	/**
	 * Set the version number of the library as string
	 * 
	 * @param versionNumber a not null version number
	 */
	public void setVersionNumber(String versionNumber) {
		Assert.isNotNull(versionNumber);
		this.libraryVersionNumber = versionNumber;
	}
	
	/**
	 * Add a required library to this module.
	 * 
	 * @param library the library to add, must be not null
	 */
	public void addRequiredLibrary(ModuleDefinition library){
		Assert.isNotNull(library);
		requiredLibraries.add(library);
	}
	
	/**
	 * Return a not null list of the required libraries from
	 * this module
	 *  
	 * @return a not null list of ModuleDefinition. Each module
	 * contains a library used by this module
	 */
	public List<ModuleDefinition> getRequiredLibraries(){
		return requiredLibraries;
	}
	
	/**
	 * Return the list of sample resources path
	 * 
	 * @return a not null list of path. The resource can be 
	 * read by using the getResource method with the path as
	 * parameter
	 */
	public List<String> getSampleResources() {
		return sampleResources;
	}

	/**
	 * Add a sample resource to the module. If a resorce with the
	 * same path was added before then this method does nothing
	 * 
	 * @param sampleResourcePath the path to the resource
	 */
	public void addSampleResource(String sampleResourcePath) {
		String path = sampleResourcePath.trim();
		if (!sampleResources.contains(path)){
			sampleResources.add(path);
		}
	}
	
	public InputStream getResource(String resourceName){
		URL url = getClass().getClassLoader().getResource(resourceName);
		try {
			return url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return null;
	}

	/**
	 * Return the module name followed by it's version
	 */
	@Override
	public String toString() {
		return getModuleName() + " " + getLibraryVersionNumber();
	}
	
	/**
	 * Return a file name used to store the library and it's license
	 * on the disk as local copy. The name is read as the last segment
	 * of the library url. 
	 * 
	 * @return The name of the library, or null if no library was defined
	 * for the module
	 */
	public String getLibraryFilename(){
		String url = getLibraryURL();
		if (url == null) return null;
		return url.substring(url.lastIndexOf("/")+1);
	}

	/**
	 * Return the license of the library as file. It is download
	 * using the license url
	 * 
	 * @return the library license, or null if it can't be found
	 */
	public File fetchLicense(){
		try{
			URL resource = new URL(licenseURL);
			InputStream is = resource.openStream();   
			String tempDir = System.getProperty("java.io.tmpdir");
			File tempFile = new File(tempDir, "license");
			int counter = 0;
			while(tempFile.exists()){
				tempFile = new File(tempDir, "license_"+counter);
				counter++;
			}
			FileOutputStream outputStream = new FileOutputStream(tempFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			return tempFile;
		} catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return a resource of the module. It is returned the 
	 * content of the resource as string
	 * 
	 * @param the path and name of the resource
	 * @return the content of the resource as string or null if it can't be found
	 */
	private String fetchResource(String path){
		try{
			URL url = getClass().getClassLoader().getResource(path);
			String result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line + "\n";
		 	}
		 	in.close();
	
	    return result;
		} catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return the library of the module as file. It is download
	 * using the library url
	 * 
	 * @return the library resource, or null if it can't be found
	 */
	public File fetchLibrary(){
		try{
			URL resource = new URL(libraryURL);
			InputStream is = resource.openStream();   
			String tempDir = System.getProperty("java.io.tmpdir");
			File tempFile = new File(tempDir, getLibraryFilename());
			if (tempFile.exists()) tempFile.delete();
			FileOutputStream outputStream = new FileOutputStream(tempFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			return tempFile;
		} catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
}
