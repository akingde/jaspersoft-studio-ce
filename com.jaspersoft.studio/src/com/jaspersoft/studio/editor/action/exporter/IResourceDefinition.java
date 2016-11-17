/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter;

/**
 * A definition for a resource that can be exported trough the import/export 
 * configuration function
 * 
 * @author Orlandin Marco
 *
 */
public interface IResourceDefinition {

	/**
	 * Return the name of the resource exported
	 * 
	 * @return a not null human readable name
	 */
	public String getName();
	
	/**
	 * Return an object that is used during the import/export procedure to 
	 * identify the resource that need to be imported and exported. The type
	 * of the object and how it is used depends by the importer itslef
	 * 
	 * @return a generic identifier
	 */
	public Object getData();
	
}
