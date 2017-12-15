/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter;

import java.io.File;
import java.util.List;

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
	 * the export wizard
	 * 
	 * @return a not null string
	 */
	public String getResourceNameExport();
	
	/**
	 * Return the name of the exported resource used at UI level in 
	 * the import wizard
	 * 
	 * @param exportedContainer container with the exported element that need do be imported
	 * @return a not null string
	 */
	public String getResourceNameImport(File exportedContainer);
	
	/**
	 * Return the list of resources that can be imported by this exporter. The selected
	 * by the user will come back when the method restoreContentFolder is called
	 * 
	 * @param exportedContainer the folder where all the exported resources during there 
	 * export phase are located
	 * @return a not null list of the resources that can be imported by the implementation
	 * from the passed file
	 */
	public List<IResourceDefinition> getRestorableResources(File exportedContainer);
	
	/**
	 * Return the list of resources that can be exported by and implementation of this interface. 
	 * The selected by the user will come back when the method exportContentFolder is called
	 * 
	 * @return a not null list of the resources that can be exported by the implementation
	 */
	public List<IResourceDefinition> getExportableResources();
	
	/**
	 * Return the file pointing to a folder
	 * 
	 * @param resourcesToExport the list of the resources to export, this list will be a subset of 
	 * the list returned by getExportableResources() and the method getData() of each resource can be
	 * used to identify which ones should be exported
	 * 
	 * @return a directory containing the resource exported by this exporter. If null 
	 * nothing will be exported for this
	 */
	public File exportContentFolder(List<IResourceDefinition> resourcesToExport);

	/**
	 * Look in the passed container if there is the resource folder exported during the export phase.
	 * If it is available it will imported into the current configuration 
	 * 
	 * @param exportedContainer the folder where all the exported resources during there 
	 * export phase are located
	 * 
	 * @param resourcesToImport  the list of the resources to import, this list will be a subset of 
	 * the list returned by getImportableResources() and the method getData() of each resource can be
	 * used to identify which ones should be imported
	 */
	public void restoreContentFolder(File exportedContainer, List<IResourceDefinition> resourcesToImport);
	
}
