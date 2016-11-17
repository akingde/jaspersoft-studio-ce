/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter.wizard;

import java.io.File;
import java.util.UUID;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.ZipUtils;

/**
 * 
 * This is the page of the Resource Import wizard that will provide the
 * controls to define the path of the zip file that contains the exported
 * configuration
 * 
 * @author Orlandin Marco
 *
 */
public class SourcePage extends JSSHelpWizardPage {
	
	/**
	 * Text field where the path is shown
	 */
	private Text pathText = null;
	
	/**
	 * Path inserted in the textarea when the dialog is advanced, this is done so the path
	 * can be read even when the control are disposed
	 */
	private String pathString;
		
	/**
	 * Build the class
	 * 
	 * @param reportFile file of the report exported as template
	 */
	protected SourcePage() {
		super("importresources"); //$NON-NLS-1$
		setTitle(Messages.SourcePage_importTitle);
		setDescription(Messages.SourcePage_pageDescription);
		setErrorMessage(Messages.SourcePage_errorFileNotSelected);
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * Return a full path that represent the destination on the filesystem of the template
	 * 
	 * @return a string that represent the folder containing the previously exported resources
	 */
	public String getDestinationPath(){
		return pathString;
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite pathComposite = new Composite(parent, SWT.NONE);
		pathComposite.setLayout(new GridLayout(3,false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		pathComposite.setLayoutData(gd);
		new Label(pathComposite,SWT.NONE).setText(Messages.SourcePage_locatinLabel);
		
		pathText = new Text(pathComposite, SWT.BORDER);
		pathText.setEditable(false);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		pathText.setLayoutData(gd);
		
		Button browseButton = new Button(pathComposite, SWT.NONE);
		browseButton.setText(Messages.ResourcePage_browseButton);
		browseButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.OPEN);
		    fd.setText(Messages.SourcePage_dialogText);
		    String[] filterExt = { "*.zip" }; //$NON-NLS-1$
		    fd.setFilterExtensions(filterExt);
		    String zipLocation = fd.open();
		    if (zipLocation != null && new File(zipLocation).exists()){
		    	try{
		    		//if there was a previously loaded folder delete it
		    		if (pathString != null){
		    			FileUtils.recursiveDelete(new File(pathString));
		    		}
		    		//load the new resource
		    		pathString = extractAndValidateZip(zipLocation);
			    	pathText.setText(zipLocation);
			    	if (!hasImportableContent(pathString)){
			    		//the extracted folder doesn't contains any restorable resource
			    		setErrorMessage(Messages.SourcePage_errorImprtableEmprty);
			    	} else setErrorMessage(null);
		    	} catch (Exception ex){
		    		pathString = null;
		    		pathText.setText(""); //$NON-NLS-1$
			    	setErrorMessage(Messages.SourcePage_errorFileError);
			    	UIUtils.showError(Messages.SourcePage_errorFileError, ex);
		    	}
		    }
		    getContainer().updateButtons();
			}
		});
		setControl(pathComposite);
	}
	
	@Override
	public boolean isPageComplete() {
		return super.isPageComplete() && getErrorMessage() == null;
	}
	
	/**
	 * Check if the content of the selected zip has at least an importable resource
	 * 
	 * @param path folder where the zip was extracted
	 * @return true if in the passed folder there is at least an importable resource, false
	 * otherwise
	 */
	protected boolean hasImportableContent(String path){
		File contentFolder = new File(path);
		for(IExportedResourceHandler importer : ExtensionManager.getContributedExporters()){
			if (importer.getRestorableResources(contentFolder).size() > 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Extract the selected zip file into the temp folder and return its path
	 * 
	 * @param path the selected zip file
	 * @return the absolute path to the folder containing the content of the zip file
	 */
	protected String extractAndValidateZip(String path){
		File tempDir = new File(System.getProperty("java.io.tmpdir")); //$NON-NLS-1$
		File unzipLocation = new File(tempDir, UUID.randomUUID().toString());
		if (unzipLocation.exists()){
			FileUtils.recursiveDelete(unzipLocation);
		}
		unzipLocation.mkdirs();
		unzipLocation.deleteOnExit();
		ZipUtils zipUtils = new ZipUtils();
		zipUtils.unZipFiles(path, unzipLocation.getAbsolutePath());
		return unzipLocation.getAbsolutePath();
	}
}
