/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.templates;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.wizard.WizardPage;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ReportNewWizard;

/**
 * 
 * A template bundle used inside Jaspersoft Studio to contribute custom wizard pages
 * Dynamically when it is used to create a new report. The new pages will be placed
 * after the page where the target folder is chosen and before the last congratulation
 * page
 * 
 * @author Orlandin Marco
 *
 */
public abstract class WizardTemplateBundle extends GenericTemplateBundle {
	
	public WizardTemplateBundle(URL url, boolean isExternal, JasperReportsContext jrContext) throws Exception {
		super(url, isExternal, jrContext);
	}
	
	public WizardTemplateBundle(URL url, JasperReportsContext jrContext) throws Exception {
		super(url, jrContext);
	}

	/**
	 * Return an array of the custom wizard page that will be inserted inside the new report
	 * wizard. Please not that this page must be call explicitly the next and previous page and
	 * provide the information if there are a next page. So in most of cases the need to have
	 * the methods canFilpToNextPage, getNextPage and getPreviousPage overridden 
	 * 
	 * @return a not null array of pages
	 */
	public abstract WizardPage[] getCustomWizardPages();
	
	/**
	 * This method is called when the wizard to create a new report finish. It should 
	 * use the template engine and the informations provided during the wizard to  create
	 * the new report and store it on the disk
	 * 
	 * @param mainWizard the new report wizard where the all the steps are executed
	 * @param monitor a monitor to execute the operation in eclipse 
	 * @return The file of the new report 
	 * @throws CoreException
	 */
	public abstract IFile doFinish(ReportNewWizard mainWizard, IProgressMonitor monitor) throws CoreException;
	
	/**
	 * This method is called when the wizard to create a new report is close (both for cancel or end of the wizard). 
	 * It can be used for example to set to null the references to the page of the report
	 */
	public abstract void wizardClosed();
	
	/**
	 * Copy and Store all the resources provided by the report bundle in the same folder as the new report.
	 * 
	 * @param monitor the monitor to execute the operation
	 * @param reportBundle the bundle of the tempalte report from where the requested resources are read
	 * @param container the target folder of the saved report
	 */
	protected void saveReportBundleResources(final IProgressMonitor monitor, TemplateBundle reportBundle, IContainer container) {
		monitor.subTask(Messages.ReportNewWizard_6);

		if (reportBundle != null){ 
			List<String> resourceNames = reportBundle.getResourceNames();
	
			for (String resourceName : resourceNames) {
				IFile resourceFile = container.getFile(new Path(resourceName));
				InputStream is = null;
				try {
					if (!resourceFile.exists()) {
						is = reportBundle.getResource(resourceName);
						if (is != null) {
							resourceFile.create(is, true, monitor);
						}
					}
				} catch (Exception e) {
					UIUtils.showError(e);
				} finally {
					FileUtils.closeStream(is);
				}
			}
		}
		monitor.done();
	}
	
	/**
	 * Save a report bundle to a file on the disk and return it. It also save in the same folder
	 * all the resources needed by the report 
	 * 
	 * @param bundleToSave the report to save
	 * @param reportWizard the report wizard
	 * @param jConfig the current jasper reports configuration
	 * @param monitor an eclipse monitor to notify the progress of the operation 
	 * @return the result file or null if something goes wrong
	 */
	protected IFile saveBundleIntoFile(ReportBundle bundleToSave, ReportNewWizard reportWizard, JasperReportsConfiguration jConfig, IProgressMonitor monitor){
		ByteArrayInputStream stream = null;
		IFile result = null;
		try {
			result = reportWizard.createTargetFile();		
			String repname = result.getName();
			int lindx = repname.lastIndexOf(".");
			if (lindx > 0 && lindx < repname.length() - 1)
				repname = repname.substring(0, lindx);

			bundleToSave.getJasperDesign().setName(repname);

			// Save the all the files...
			String contents = JRXmlWriterHelper.writeReport(jConfig, bundleToSave.getJasperDesign(), result, false);
			stream = new ByteArrayInputStream(contents.getBytes());
			try {
				if (result.exists()) {
					result.setContents(stream, true, true, monitor);
				} else {
					result.create(stream, true, monitor);
				}
			} finally {
				FileUtils.closeStream(stream);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Return the location of the result files
	 * 
	 * @param the wizard used to create the report
	 * @return the workspace location where the report will be placed
	 */
	protected IContainer getReportContainer(ReportNewWizard reportWizard){
		Map<String, Object> settings = reportWizard.getSettings();
		String containerName = (String)settings.get(ReportNewWizard.CONTAINER_NAME_KEY);
		//The following code store the bundle inside a jrxmlfile
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		// Store the report bundle on file system
		return (IContainer) resource;
	}
	
	/**
	 * Throw a new core exception 
	 * 
	 * @param message message of the exception
	 * 
	 * @throws CoreException
	 */
	protected void throwCoreException(String message) throws CoreException {
		IStatus status = new Status(IStatus.ERROR, "com.jaspersoft.studio", IStatus.OK, message, null); //$NON-NLS-1$
		throw new CoreException(status);
	}
	
	/**
	 * Provide a localization support for this template bundle. Get a key of a string
	 * and return the localized version of it. If the localized version can't be found
	 * then return the key
	 * 
	 * @param key key of the string
	 * @return localized version of the string or the key
	 */
	public String getLocalizedString(String key){
		return MessagesByKeys.getString(key);
	}
}
