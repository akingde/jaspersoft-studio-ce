/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

/**
 * 
 * Wrapper to pass a list of shimmed libraries to the velocity
 * template  to generate the build.js file
 * 
 * @author Orlandin Marco
 *
 */
public class VelocityShimLibrary {

	/**
	 * Name of the library
	 */
	private String libraryName;
	
	/**
	 * Dependencies of the libraries, every dependency must be 
	 * between quote and divided from the others by a comma
	 */
	private String dependencies;
	
	/**
	 * Name used to export the library
	 */
	private String exportName;
	
	/**
	 * Create the shimmed library container
	 * 
	 * @param libraryName Name of the library
	 * @param exportName  Name used to export the library
	 */
	public VelocityShimLibrary(String libraryName, String exportName){
		this.libraryName = libraryName;
		this.exportName = exportName;
		dependencies = null;
	}
	
	/**
	 * Create the shimmed library container
	 * 
	 * @param libraryName Name of the library
	 * @param exportName  Name used to export the library
	 * @param dependencies Dependencies of the libraries, every dependency must be 
	 * between quote and divided from the others by a comma
	 */
	public VelocityShimLibrary(String libraryName, String exportName, String dependencies){
		this(libraryName, exportName);
		if (dependencies != null && !dependencies.trim().isEmpty()){
			this.dependencies = dependencies;
		}
	}

	/**
	 * Get the name of the shimmed library
	 * 
	 * @return a not null string
	 */
	public String getName() {
		return libraryName;
	}

	/**
	 * Return the dependencies of the library
	 * 
	 * @return every dependency are between quote and divided from the others 
	 * by a comma. Can be null if there aren't dependencies
	 */
	public String getDependencies() {
		return dependencies;
	}

	/**
	 * Return the name to re-export the library
	 * 
	 * @return a not null string
	 */
	public String getExportName() {
		return exportName;
	}
	
	/**
	 * Check if the library has dependecies
	 * 
	 * @return true if it has dependecies, false otherwise
	 */
	public boolean hasDependencies(){
		return dependencies != null;
	}
}
