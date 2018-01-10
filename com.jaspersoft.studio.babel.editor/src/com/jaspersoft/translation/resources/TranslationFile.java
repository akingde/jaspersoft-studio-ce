/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.translation.resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Translation resource that represent a file on the filesystem
 * 
 * @author Orlandin Marco
 *
 */
public class TranslationFile implements ITranslationResource {

	/**
	 * The path of the file
	 */
	private String filePath;
	
	/**
	 * The name of the file
	 */
	private String fileName;
	
	/**
	 * Create an instance of the class
	 * 
	 * @param filePath path of the represented file
	 * @param fileName name of the represented file
	 */
	public TranslationFile(String filePath, String fileName){
		this.filePath = filePath;
		this.fileName = fileName;
	}
	
	/**
	 * Return the name of the represented file
	 */
	@Override
	public String getResourceName() {
		return fileName;
	}

	/**
	 * Return the path of the represented file
	 */
	@Override
	public String getResourcePath() {
		return filePath;
	}

	/**
	 * Since the represented resource is a file it can't have children,
	 * so this method return always an empty array
	 */
	@Override
	public List<ITranslationResource> getChildren() {
		return new ArrayList<ITranslationResource>();
	}

	/**
	 * Return true since the represented resource is a file
	 */
	@Override
	public boolean isFile() {
		return true;
	}

}
