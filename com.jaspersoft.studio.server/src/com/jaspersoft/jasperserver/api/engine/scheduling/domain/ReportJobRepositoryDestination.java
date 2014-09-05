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

package com.jaspersoft.jasperserver.api.engine.scheduling.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.metadata.common.domain.Resource;

import java.io.Serializable;

/**
 * Contains attributes related to the generation of repository resources
 * for report job output files.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobRepositoryDestination.java 38683 2013-10-09 02:39:26Z carbiv $
 * @since 1.0
 * @see ReportJob#getContentRepositoryDestination()
 */
@JasperServerAPI
public class ReportJobRepositoryDestination implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private int version = ReportJob.VERSION_NEW;
	private String folderURI;
	private boolean sequentialFilenames;
	private boolean overwriteFiles;
	private String outputDescription;
	private String timestampPattern;
    private boolean saveToRepository = true;
    private String defaultReportOutputFolderURI;
    private boolean usingDefaultReportOutputFolderURI = false;
    private String outputLocalFolder = null;
    private FTPInfo outputFTPInfo = null;

	/**
	 * Creates an empty object.
	 */
	public ReportJobRepositoryDestination() {
	}

	/**
	 * Returns the ID of this object.
	 * 
	 * <p>
	 * The ID is automatically generated when the job for which the notification
	 * is defined gets saved in the scheduler.
	 * </p>
	 * 
	 * @return the ID of this object
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the ID of this object.
	 * 
	 * <p>
	 * The ID needs to be set when updating a report job, but not when saving
	 * a new job.
	 * </p>
	 * 
	 * @param id the object ID
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Returns the persistent version of this object.
	 * 
	 * <p>
	 * The version is automatically set when a repository destination object is retrieved
	 * (along with the job) from the scheduling service.
	 * </p>
	 * 
	 * @return the persistent version
	 * @see ReportJob#VERSION_NEW
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the persistent version for this object.
	 * 
	 * <p>
	 * The version doesn't have to be set when saving a new report job,
	 * but it needs to be set when updating a report job (along with its 
	 * repository destination object).
	 * </p>
	 * 
	 * @param version the persistent repository destination version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * Returns the repository URI/path of the folder under which job output
	 * resources would be created.
	 * 
	 * @return the repository output folder
	 */
	public String getFolderURI() {
		return folderURI;
	}

	/**
	 * Returns the repository URI/path of the folder under which job output
	 * resources are to be be created.
	 * 
	 * <p>
	 * The job owner should have write permission on the output folder.
	 * </p>
	 * 
	 * @param folder the URI/path of the repository output folder
	 * @see ReportJob#setBaseOutputFilename(String)
	 */
	public void setFolderURI(String folder) {
		this.folderURI = folder;
	}

	/**
	 * Decides if a timestamp is to be added to the names of the job output 
	 * resources.
	 * 
	 * <p>
	 * The timestamp added to the output resource names are created from the 
	 * job execution time using the specified pattern.
	 * </p>
	 * 
	 * @return <code>true</code> if the job output resource names should include
	 * a timestamp
	 * @see ReportJob#getBaseOutputFilename()
	 * @see #getTimestampPattern()
	 */
	public boolean isSequentialFilenames() {
		return sequentialFilenames;
	}

	/**
	 * Specifies whether a timestamp is to be added to the names of the job 
	 * output resources. 
	 * 
	 * <p>
	 * This is usually required when a job occurs severa time and the output
	 * from each execution needs to be kept in the repository.
	 * </p>
	 * 
	 * @param sequentialFilenames <code>true</code> if the job output resource 
	 * names should include a timestamp
	 */
	public void setSequentialFilenames(boolean sequentialFilenames) {
		this.sequentialFilenames = sequentialFilenames;
	}

	/**
	 * Decides whether the scheduler can overwrite files in the repository when
	 * saving job output resources.
	 * 
	 * <p>
	 * If the flag is not set, the job would fail if the repository already
	 * contains a resource with the same name as one of the job output resources.
	 * If the flag is set and the job owner does not have the permission to 
	 * overwrite an existing resource, the job execution will also fail.
	 * </p>
	 * 
	 * @return <code>true</code> if the scheduler should overwrite repository
	 * resources that have the same names as the job output resources
	 */
	public boolean isOverwriteFiles() {
		return overwriteFiles;
	}

	/**
	 * Specifies whether the scheduler should overwrite files in the repository
	 * when saving job output resources.
	 * 
	 * @param overwriteFiles
	 * @see #isOverwriteFiles()
	 */
	public void setOverwriteFiles(boolean overwriteFiles) {
		this.overwriteFiles = overwriteFiles;
	}

	/**
	 * Returns a description to be used for job output resources.
	 * 
	 * <p>
	 * The description will be used as is for all output resources.
	 * </p>
	 * 
	 * @return the output resource description, or <code>null<code> if not set
	 * @since 3.0
	 * @see Resource#getDescription()
	 */
	public String getOutputDescription() {
		return outputDescription;
	}

	/**
	 * Sets the description that should be used for job output resources.
	 * 
	 * @param outputDescription the job output resources description
	 * @since 3.0
	 */
	public void setOutputDescription(String outputDescription) {
		this.outputDescription = outputDescription;
	}

	/**
	 * Returns the pattern to be used for the timestamp included in the job
	 * output resource names.
	 * 
	 * <p>
	 * If no pattern has been specified, <code>yyyyMMddHHmm</code> will be used.
	 * </p>
	 * 
	 * @return the timestamp pattern to use for the output resource names
	 * @since 3.0
	 * @see #isSequentialFilenames()
	 */
	public String getTimestampPattern() {
		return timestampPattern;
	}

	/**
	 * Sets a date pattern to be used for the timestamp included in job output
	 * resource names.
	 * 
	 * <p>
	 * The pattern should be a valid pattern as defined by 
	 * <code>java.text.SimpleDateFormat</code> and can only contain underscores,
	 * dots and dashes as token separators.
	 * </p>
	 * 
	 * @param timestampPattern
	 * @since 3.0
	 * @see #setSequentialFilenames(boolean)
	 */
	public void setTimestampPattern(String timestampPattern) {
		this.timestampPattern = timestampPattern;
	}

	/**
	 * Decides whether the scheduler is enabled to write the files to the repository
	 *
	 * @return <code>true</code> if the scheduler should save a copy to repository
     * @since 4.7
	 */
	public boolean isSaveToRepository() {
		return saveToRepository;
	}

	/**
	 * Specifies whether the scheduler should write files to the repository
	 *
	 * @param saveToRepository
	 * @see #isSaveToRepository()
     * @since 4.7
	 */
	public void setSaveToRepository(boolean saveToRepository) {
		this.saveToRepository = saveToRepository;
	}

    /**
     * Returns the default scheduled report output folder URI of the job owner
     *
     * @return  the default scheduled report output folder URI of the job owner
     * @see #setDefaultReportOutputFolderURI(String folderURI)
     * @since 4.7
     */
	public String getDefaultReportOutputFolderURI() {
         return defaultReportOutputFolderURI;
    }

    /**
     * Sets the default scheduled report output folder URI of the job owner
     *
     * @param  defaultReportOutputFolderURI default scheduled report output folder URI of the job owner
     * @see #getDefaultReportOutputFolderURI()
     * @since 4.7
     */
	public void setDefaultReportOutputFolderURI(String defaultReportOutputFolderURI) {
        this.defaultReportOutputFolderURI = defaultReportOutputFolderURI;
    }

    /**
	 * Decides whether export the output files to default report output folder URI of the job owner
	 *
	 * @return <code>true</code> if the scheduler should save a copy to default report output folder URI of the job owner
     * @see #setUsingDefaultReportOutputFolderURI(boolean usingDefaultReportOutputFolderURI)
     * @since 4.7
	 */
	public boolean isUsingDefaultReportOutputFolderURI() {
		return usingDefaultReportOutputFolderURI;
	}

	/**
	 * Specifies whether the scheduler should write files to default report output folder URI of the job owner
	 *
	 * @param usingDefaultReportOutputFolderURI
	 * @see #isUsingDefaultReportOutputFolderURI()
     * @since 4.7
	 */
	public void setUsingDefaultReportOutputFolderURI(boolean usingDefaultReportOutputFolderURI) {
		this.usingDefaultReportOutputFolderURI = usingDefaultReportOutputFolderURI;
	}

  /**
   * Convenience constructor that returns a distinct copy of the input ReportJob.
   * All of the copy's Object members are themselves copies as well.
   *
   * We're deliberately avoiding using clone()
   *
   * @param dest
   * @return
   */
  public ReportJobRepositoryDestination(ReportJobRepositoryDestination dest) {
    // skip id and version which are generated
      this.setId(dest.getId());
      this.setFolderURI(dest.getFolderURI());
      this.setSequentialFilenames(dest.isSequentialFilenames());
      this.setOverwriteFiles(dest.isOverwriteFiles());
      this.setOutputDescription(dest.getOutputDescription());
      this.setTimestampPattern(dest.getTimestampPattern());
      this.setSaveToRepository(dest.isSaveToRepository());
      this.setDefaultReportOutputFolderURI(dest.getDefaultReportOutputFolderURI());
      this.setUsingDefaultReportOutputFolderURI(dest.isUsingDefaultReportOutputFolderURI());
      this.setOutputLocalFolder(dest.getOutputLocalFolder());
      this.setOutputFTPInfo(new FTPInfo(dest.getOutputFTPInfo()));
  }
	/**
	 * Returns the output local path of the folder under which job output
	 * resources would be created.
	 *
	 * @return the output folder
	 */
    public String getOutputLocalFolder() {
        return outputLocalFolder;
    }

	/**
	 * Returns the output local path of the folder under which job output
	 * resources are to be be created.
	 *
	 * @param outputLocalFolder the path of the local output folder
	 * @see ReportJob#setBaseOutputFilename(String)
	 */
    public void setOutputLocalFolder(String outputLocalFolder) {
        this.outputLocalFolder = outputLocalFolder;
    }

	/**
	 * Returns the output FTP location information which job output
	 * resources would be created.
	 *
	 * @return FTP information of the output folder
	 */
    public FTPInfo getOutputFTPInfo() {
        return outputFTPInfo;
    }

    /**
	 * Returns the output FTP location information which job output
	 * resources are to be be created.
	 *
	 * @param outputFTPInfo FTP information of the output folder
	 * @see ReportJob#setBaseOutputFilename(String)
	 */
    public void setOutputFTPInfo(FTPInfo outputFTPInfo) {
        this.outputFTPInfo = outputFTPInfo;
    }

}
