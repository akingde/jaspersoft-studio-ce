/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class that represent the configuration of JSS inside the workspace, and provide the methods
 * to read its configuration files
 * 
 * @author Orlandin Marco
 */
public class JSSDescriptor extends IReportDescriptor{
	
	/**
	 * File storage root of the selected JSS installation
	 */
	private File installationStorages;
	
	public JSSDescriptor(File destination){
		super(destination, "", false);
	}
	
	/**
	 * Return the name of this configuration
	 */
	@Override
	public String getName(){
		version = getConfiguration().getProperty("jssbranding_product_version");
		if (version != null) return "JasperSoft Studio  ".concat(version);
		else return "JasperSoft Studio";
	}
	
	/**
	 * Load the file handle to the installation file storage
	 */
	private void loadStorage(){
		installationStorages = new File(destination.getAbsolutePath().concat(ImportUtility.FILE_SEPARATOR).concat(".plugins").concat(ImportUtility.FILE_SEPARATOR).concat("com.jaspersoft.studio"));
	}
	
	@Override
	protected Properties loadConfiguration() {
		Properties prop = new Properties();
		String path = destination.getAbsolutePath().concat(ImportUtility.FILE_SEPARATOR).concat(".plugins")
											.concat(ImportUtility.FILE_SEPARATOR).concat("org.eclipse.core.runtime").concat(ImportUtility.FILE_SEPARATOR)
													.concat(".settings").concat(ImportUtility.FILE_SEPARATOR).concat("com.jaspersoft.studio.prefs");
		File newFile = new File(path);
		try {
			if (newFile.exists()){
				FileInputStream is = new FileInputStream(newFile);
				prop.load(is);
				is.close();
				return prop;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Read and return all the content of a specific storage inside the 
	 * selected JSS installation
	 * 
	 * @param storageName the name of the storage
	 * @return a not null array of the storage content
	 */
	public File[] getStorageResources(String storageName){
		if (installationStorages == null) loadStorage();
		if (installationStorages.exists() && installationStorages.isDirectory()){
			File storage = new File(installationStorages, storageName);
			if (storage.exists()) return storage.listFiles();
		}
		return new File[0];
	}
	
	/**
	 * The server configuration and adapter configuration use the same file in JSS
	 * 
	 * @return the configuration file, could be null if not available
	 */
	@Override
	protected Properties loadServerConfiguration() {
		return super.getConfiguration();
	}
	
}
