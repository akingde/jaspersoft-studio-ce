/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.repo.RepositoryContext;
import net.sf.jasperreports.repo.RepositoryUtil;
import net.sf.jasperreports.repo.SimpleRepositoryContext;
import net.sf.jasperreports.repo.SimpleRepositoryResourceContext;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Class used to resolve a jasperreports default data adapter, essentially 
 * it provide the API to resolve the path and load the adpater, realoading it
 * if necessary
 * 
 * @author Orlandin Marco
 */
public class DataAdapterLocationResolver {

	/**
	 * The location provided by the JasperReports property
	 */
	private String location;
	
	/**
	 * The JasperReportsConfiguration of the report from where the property comes
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * The file of the report from where the property comes
	 */
	private IFile report;

	/**
	 * Keep a reference to the loaded data adapter to avoid to reload it
	 */
	private DataAdapterDescriptor loadedAdapter = null;
	
	/**
	 * In some cases the location of the data adapter try to be resolve to an absolute
	 * path, if the resolution goes well the result is stored here as cache 
	 */
	private String absoluteLocation = null;
	
	/**
	 * When a data adapter is loaded from a file resource it store the last modified date of the file,
	 * in this way when the data adapter is requested another time it check if it has been changed
	 * and need to be reloaded
	 */
	private long lastModfiedDate = -1;
	
	/**
	 * The time of the last unsuccessful search of the data adapter. This is stored to avoid
	 * when there are multiple request of the adapter to try every time to search it and to left
	 * some time pass between each search
	 */
	private long lastUnsuccessfulSearch = -1;

	/**
	 * The minimum time to way after an unsuccessful of the adapter before to try a new research
	 */
	protected static final Long WAIT_TIME_TO_RESCAN = 10000l;
	
	/**
	 * Create a location resolver for a specific location
	 * 
	 * @param location the location where the data adapter should be
	 * @param jConfig the configuration of the current report
	 * @param report the file of the current report
	 */
	public DataAdapterLocationResolver(String location, JasperReportsConfiguration jConfig, IFile report){
		this.location = location;
		this.report = report;
		this.jConfig = jConfig;
	}

	/**
	 * Try to resolve the location an return the adapter. If the adapter was
	 * already returned before and nothing is changed (file still considered valid)
	 * return the copie in the cache
	 * 
	 * @return the adapter poitned by the location or null if it can't be found
	 */
	public DataAdapterDescriptor getDataAdapter(){
		if (loadedAdapter == null || isObsolete()){
			if (!wasAlreadySearched(location)){
				InputStream datasetStream = getJRDataAdapterStream(location);
				if (datasetStream != null){
					loadedAdapter = FileDataAdapterStorage.readDataADapter(datasetStream, report, jConfig);
					FileUtils.closeStream(datasetStream);
					//update the date of last modified
					String absolutePath = getAbsoluteLocation(location);
					if (absolutePath != null){
						lastModfiedDate = new File(absolutePath).lastModified();
					}
				} else {
					loadedAdapter = null;
					lastUnsuccessfulSearch = System.currentTimeMillis();
				}
			}
		}
		return loadedAdapter;
	}
	
	/**
	 * Uses JasperReports to resolve the location and return an input stream to it
	 * 
	 * @param location the location
	 * @return the stream to the file pointed by the location or null if it can't be found
	 */
	protected InputStream getJRDataAdapterStream(String location){
		InputStream stream = null;
		if (location != null && !location.isEmpty()){
			try{
				String parentPath = report.getParent().getLocation().toFile().getAbsolutePath();
				SimpleRepositoryResourceContext context = SimpleRepositoryResourceContext.of(parentPath);
				RepositoryContext repoContext = SimpleRepositoryContext.of(jConfig, context);
				stream = RepositoryUtil.getInstance(repoContext).getInputStreamFromLocation(location);
			} catch (Exception ex){
			}
		}
		return stream;
	}
	
	/**
	 * Look if there were already a failed attempt to resolve the location
	 * and in this case if the minimum time between two loading attempts is passed
	 * 
	 * 
	 * @param location the location to check
	 * @return true if there were a failed attempt and the time to wait is not passed yet,
	 * false otherwise
	 */
	private boolean wasAlreadySearched(String location){
		if (lastUnsuccessfulSearch != -1){
			long timePast = System.currentTimeMillis() - lastUnsuccessfulSearch;
			if (timePast > WAIT_TIME_TO_RESCAN){
				lastUnsuccessfulSearch = -1;
				return false;
			} else {
				return true;
			}
 		}
		return false;
	}
	
	/**
	 * Try from a relative location to get an absolute location
	 * 
	 * @return The absolute location or null if the relative
	 * can not be resolved
	 */
	private String getAbsoluteLocation(String location){
		if (location != null){
			
			//check in the cache
			if (absoluteLocation != null) {
				return absoluteLocation;
			}
			
			//Check if it is absolute
			File file = new File(location);
			if (file.exists()){
				absoluteLocation = location;
				return location;
			} 
			
			IPath path = new Path(location);
			//Check if it is relative to the folder
			try{ 
				IFile folderFile = report.getParent().getFile(path);
				if (folderFile.exists()){
					String absolutePath = new File(folderFile.getLocationURI()).getAbsolutePath();
					absoluteLocation = absolutePath;
					return absolutePath;
				} 
			} catch (Exception ex){
				
			}
			
			//check if it is relative to the project
			try{ 
				IFile folderFile = report.getProject().getFile(path);
				if (folderFile.exists()){
					String absolutePath = new File(folderFile.getLocationURI()).getAbsolutePath();
					absoluteLocation = absolutePath;
					return absolutePath;
				} 
			} catch (Exception ex){
				
			}
			
			//check if it is a URL file
			if (FileUtils.isValidURL(location)){
				try{
					file = new File(new URI(location));
					if (file.exists()){
						String absolutePath = file.getAbsolutePath();
						absoluteLocation = absolutePath;
						return absolutePath;
					}
				} catch (Exception ex){
					
				}
			}
		} 
		return null;
	}
	
	/**
	 * If we can get an absolute location on the filesystem for 
	 * the resource, check if it was modified since the last loading.
	 * If the resource is not on the filesystem this return always false
	 * since reloading it can have an huge impact on the performances
	 * 
	 * @return true if the resource is changed and need to be reloaded
	 * false otherwise
	 */
	private boolean isObsolete(){
		String absolutePath = getAbsoluteLocation(location);
		if (absolutePath != null){
			File file = new File(absolutePath);
			if (lastModfiedDate == file.lastModified()){
				return false;
			} else {
				lastModfiedDate = file.lastModified();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get an url to the data adapter resource. 
	 */
	public String getAdapterURL(){
		if (FileUtils.isValidURL(location)){
			return location;
		}
		try{
			File resource = FileUtils.findFile(report, location, jConfig);
			if (resource != null && resource.exists()){
				return resource.toURI().toURL().toExternalForm();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
}
