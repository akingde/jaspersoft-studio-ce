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
package com.jaspersoft.studio.editor.action.exporter;

import java.io.File;

/**
 * Interface to implement to define an handler that is used in the import/export of the configuration
 * procedure. This define how to import/export a specific resource
 * 
 * @author Orlandin Marco
 *
 */
public interface IExportedResourceHandler {

	/**
	 * Return the name of the exported resource used at UI level in 
	 * the wizards
	 * 
	 * @return a not null string
	 */
	public String getResourceName();
	
	/**
	 * Return the file pointing to a folder
	 * 
	 * @return a directory containing the resource exported by this exporter. If null 
	 * nothing will be exported for this
	 * 
	 * @return a file pointing to the folder to export or null if there is nothing to export
	 */
	public File exportContentFolder();

	/**
	 * Look in the passed container if there is the resource folder exported during the export phase.
	 * If it is available it will imported into the current configuration 
	 * 
	 * @param exportedContainer the folder where all the exported resources during there 
	 * export phase are located
	 */
	public void restoreContentFolder(File exportedContainer);
	
	/**
	 * Check if in the specified imported container there are resource that
	 * can be imported by an implementation of this class
	 * 
	 * @param exportedContainer the folder where all the exported resources during there 
	 * export phase are located
	 * @return true if there is something to import, false otherwise
	 */
	public boolean hasRestorableResources(File exportedContainer);
	
	/**
	 * Return if this exporter has something to export
	 * 
	 * @return true if this exporter has some resources to export, false otherwise. 
	 */
	public boolean hasExportableResources();
}
