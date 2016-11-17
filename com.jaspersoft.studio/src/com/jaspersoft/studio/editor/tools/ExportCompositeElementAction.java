/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import java.io.File;
import java.io.IOException;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.ZipUtils;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.wizards.CompositeElementsExportWizard;
import com.jaspersoft.studio.messages.Messages;
/**
 * Action to export one or more composite elements from the palette and 
 * save them as a zip file
 * 
 * @author Orlandin Marco
 *
 */
public class ExportCompositeElementAction extends Action {

	/**
	 * The palette entry to export
	 */
	private CompositeElementTemplateCreationEntry elementToExport;

	public ExportCompositeElementAction(CompositeElementTemplateCreationEntry elementToDelete) {
		super();
		setText(Messages.ExportCompositeElementAction_actionName);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/export.png")); //$NON-NLS-1$
		this.elementToExport = elementToDelete;
	}

	public void run() {
		MCompositeElement element = elementToExport.getTemplate();
		CompositeElementsExportWizard wizard = new CompositeElementsExportWizard(element);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.setPageSize(200, 200);
		if (dialog.open() == Dialog.OK){
			String destinationPath = getDestinationPath(element);
			if (destinationPath != null){
				File destinationFile = new File(destinationPath);
				if (destinationFile.exists()){
					boolean overwrite = UIUtils.showConfirmation(Messages.ExportCompositeElementAction_fileExistTitle, 
																											 Messages.ExportCompositeElementAction_fileExistDescription);
					if (overwrite){
						destinationFile.delete();
					} else {
						return;
					}
				}
				File tempFolder = createTempFolder();
				CompositeElementManager.INSTANCE.exportCompositeElement(wizard.getSelectedElements(), tempFolder);
				ZipUtils zipUtils = new ZipUtils();
				zipUtils.zipFolderContent(tempFolder.getAbsolutePath(), destinationPath);
				try {
					FileUtils.deleteDirectory(tempFolder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public boolean isEnabled() {
		return elementToExport != null;
	}

	/**
	 * Open a file dialog to select the composite elements zip
	 * 
	 * @return the path of zip or null if the operation was cancelled
	 */
	private String getDestinationPath(MCompositeElement element){
		FileDialog dialog = new FileDialog(UIUtils.getShell(), SWT.SAVE);
    	dialog.setFilterExtensions(new String[] { "*.zip"});  //$NON-NLS-1$
    	dialog.setFilterPath(System.getProperty("user.home"));  //$NON-NLS-1$
    	dialog.setFileName(element.getName() + ".zip"); //$NON-NLS-1$
    	return dialog.open();
	}
	
	/**
	 * Create an unique temp folder where the content of the zip of the
	 * composite elements is extracted 
	 * 
	 * @return a temp empty and existing folder
	 */
	private File createTempFolder(){
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));  //$NON-NLS-1$
		File destFolder = new File(tmpDir, "compositeElementExport"); //$NON-NLS-1$
		int index = 0;
		while(destFolder.exists()){
			destFolder = new File(tmpDir, "compositeElementExport"+index); //$NON-NLS-1$
			index++;
		}
		destFolder.deleteOnExit();
		return destFolder;
	}
}
