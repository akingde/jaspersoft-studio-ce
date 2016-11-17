/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.ZipUtils;

import org.apache.commons.io.FileUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.wizards.CompositeElementImportWizard;
import com.jaspersoft.studio.messages.Messages;

/**
 * Action to import one or more composite elements from a zip file into the palette
 * 
 * @author Orlandin Marco
 *
 */
public class ImportCompositeElementAction extends Action {

	public ImportCompositeElementAction() {
		super();
		setText(Messages.ImportCompositeElementAction_actionName);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/import.png")); //$NON-NLS-1$
	}

	public void run() {
		String destinationPath = getDestinationPath();
		if (destinationPath != null){
			File tempFolder = createTempFolder();
			ZipUtils zipUtils = new ZipUtils();
			zipUtils.unZipFiles(destinationPath, tempFolder.getAbsolutePath());
			List<MCompositeElement> elementsFound = CompositeElementManager.INSTANCE.loadCompositeElements(tempFolder);
			if (!elementsFound.isEmpty()){
				CompositeElementImportWizard wizard = new CompositeElementImportWizard(elementsFound);
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.setPageSize(200, SWT.DEFAULT);
				dialog.open();
			}
			try {
				FileUtils.deleteDirectory(tempFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Open a file dialog to select the destination of the composite elements zip
	 * 
	 * @return the path to use for the destination zip or null if the operation was cancelled
	 */
	private String getDestinationPath(){
		FileDialog dialog = new FileDialog(UIUtils.getShell(), SWT.OPEN);
		dialog.setFilterExtensions(new String[] { "*.zip"});  //$NON-NLS-1$
		dialog.setFilterPath(System.getProperty("user.home")); //$NON-NLS-1$
    	return dialog.open();
	}
	
	/**
	 * Create an unique temp folder where place the elements before to do the zip
	 * 
	 * @return a temp empty and existing folder
	 */
	private File createTempFolder(){
		File tmpDir = new File(System.getProperty("java.io.tmpdir"));  //$NON-NLS-1$
		File destFolder = new File(tmpDir, "compositeElementImport"); //$NON-NLS-1$
		int index = 0;
		while(destFolder.exists()){
			destFolder = new File(tmpDir, "compositeElementImport"+index); //$NON-NLS-1$
			index++;
		}
		destFolder.deleteOnExit();
		return destFolder;
	}
}
