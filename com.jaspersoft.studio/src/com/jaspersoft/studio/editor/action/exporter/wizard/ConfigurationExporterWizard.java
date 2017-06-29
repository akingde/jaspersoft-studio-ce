/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter.wizard;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.editor.action.exporter.IResourceDefinition;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.ZipUtils;

/**
 * Wizard used to export one or more resources from Jaspersoft Studio
 * 
 * @author Orlandin Marco
 *
 */
public class ConfigurationExporterWizard extends Wizard implements IExportWizard {

	/**
	 * The page where the resources that will be exported can be selected
	 */
	private ShowExportableWizardPage page0;
	
	/**
	 * The page where the target file of the export can be defined
	 */
	private DestinationPage page1;
	
	@Override
	public void addPages() {
		super.addPages();
		page0 = new ShowExportableWizardPage();
		addPage(page0);
		page1 = new DestinationPage();
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
	 * Check every selected exporter to obtain a folder with the exported resources by
	 * that exporter. At the end all the exported resources are compressed into a zip and
	 * the compressed folder deleted
	 */
	@Override
	public boolean performFinish() {
		boolean doit = true;
		//Check if the file already exist
		final File destinationFile = new File(page1.getDestinationPath());
		if (destinationFile.exists()){
			if (!MessageDialog.openConfirm(UIUtils.getShell(), Messages.ConfigurationExporterWizard_fileExistTitle,
																			Messages.ConfigurationExporterWizard_fileExistMessage)){
				doit = false;
			} else {
				FileUtils.recursiveDelete(destinationFile);
			}
		}
		
		if (doit){
			try{
				setNeedsProgressMonitor(true);
				getContainer().run(true, false, new IRunnableWithProgress() {
					
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						Map<IExportedResourceHandler, List<IResourceDefinition>> selectedResources = page0.getSelectedResources();
						List<String> elementsToCompress = new ArrayList<String>();
						monitor.beginTask("Exporting resources", selectedResources.keySet().size());
						for(Entry<IExportedResourceHandler, List<IResourceDefinition>> selectedEntry : selectedResources.entrySet()){
							IExportedResourceHandler exporter = selectedEntry.getKey();
							File elementToCompress = exporter.exportContentFolder(selectedEntry.getValue());
							if (elementToCompress != null){
								elementsToCompress.add(elementToCompress.getAbsolutePath());
							}
							monitor.worked(1);
						}
						ZipUtils zipUtils = new ZipUtils();
						zipUtils.zipFiles(elementsToCompress, destinationFile.getAbsolutePath());
						//delete the compressed folder
						for(String compressedFolder : elementsToCompress){
							FileUtils.recursiveDelete(new File(compressedFolder));
						}
						monitor.done();
					}
				});
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return doit;
	}
}
