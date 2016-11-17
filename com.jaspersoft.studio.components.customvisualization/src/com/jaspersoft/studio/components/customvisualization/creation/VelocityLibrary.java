/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation;

/**
 * 
 * Wrapper to pass a list of libraries to the velocity
 * template  to generate the build.js file
 * 
 * @author Orlandin Marco
 *
 */
public class VelocityLibrary {
	
	/**
	 * The name of the library
	 */
	private String libraryName;
	
	/**
	 * The filename of the library without the extension.
	 * Many times this is equals to the library name
	 */
	private String fileName;
	
	/**
	 * Create the container 
	 * 
	 * @param name The name of the library
	 * @param fileName The filename of the library without the extension.
	 * Many times this is equals to the library name
	 */
	public VelocityLibrary(String name, String fileName){
		this.libraryName = name;
		this.fileName = fileName;
	}

	/**
	 * Return the name of the library
	 * 
	 * @return a not null string
	 */
	public String getName() {
		return libraryName;
	}

	/**
	 * Return the file name of the library
	 * 
	 * @return a not null string
	 */
	public String getFileName() {
		return fileName;
	}
	
	
}
