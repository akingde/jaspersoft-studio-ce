/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter.wizard;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;

/**
 * Wizard used to import one or more resources on Jaspersoft Studio
 * 
 * @author Orlandin Marco
 *
 */
public class ConfigurationImporterWizard extends Wizard implements IImportWizard {

	/**
	 * Page where the zip containing the resource to import can be selected
	 */
	private SourcePage page0;
	
	/**
	 * Page where the list of resource that can be imported from the zip file
	 * can be selected
	 */
	private ShowImportableWizardPage page1;
	
	@Override
	public void addPages() {
		super.addPages();
		page0 = new SourcePage();
		addPage(page0);
		page1 = new ShowImportableWizardPage();
		addPage(page1);
	}
	
	@Override
	public boolean canFinish() {
		return page0.isPageComplete() && page1.isPageComplete();
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}
	
	/**
	 * Return the folder containing the content of the zip file selected on the 
	 * first step
	 * 
	 * @return a path containing the resources to import
	 */
	protected String getSelectedFile(){
		return page0.getDestinationPath();
	}

	/**
	 * Call the import method of every selected resource export handler, passing to 
	 * it the container with the previously exported resource
	 */
	@Override
	public boolean performFinish() {
		try{
			setNeedsProgressMonitor(true);
			getContainer().run(true, false, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						Map<IExportedResourceHandler, List<IResourceDefinition>> selectedResources = page1.getSelectedResources();
						monitor.beginTask("Restoring Resources", selectedResources.keySet().size());
						final File importContainerLocation = new File(getSelectedFile());
						for(Entry<IExportedResourceHandler, List<IResourceDefinition>> selectedEntry : selectedResources.entrySet()){
							final IExportedResourceHandler currentExporter = selectedEntry.getKey();
							final List<IResourceDefinition> exporterSelection = selectedEntry.getValue();
							UIUtils.getDisplay().syncExec(new Runnable() {				
								@Override
								public void run() {
									currentExporter.restoreContentFolder(importContainerLocation, exporterSelection);
								}
							});
							monitor.worked(1);
						}
						FileUtils.recursiveDelete(importContainerLocation);
						monitor.done();
				}
			});
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return true;
	}


}
