/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.exporter.wizard;

import org.eclipse.jface.wizard.IWizardPage;
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

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * 
 * This is the page of the Resource Export wizard that will provide the
 * controls to define the path of the zip file that will contains the exported
 * configuration
 * 
 * @author Orlandin Marco
 *
 */
public class DestinationPage extends JSSHelpWizardPage {
	
	/**
	 * Text field where the exporting path is shown
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
	protected DestinationPage() {
		super("exportresources"); //$NON-NLS-1$
		setTitle(Messages.DestinationPage_exportWizardTitle);
		setDescription(Messages.DestinationPage_pageDescription);
		setPageComplete(false);
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * Return a full path that represent the destination on the filesystem of the zip file
	 * 
	 * @return a string that represent the destination of the zip that will contains the configuration
	 */
	public String getDestinationPath(){
		return pathString;
	}
	
	@Override
	public IWizardPage getNextPage() {
		pathString = pathText.getText();
		return super.getNextPage();
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite pathComposite = new Composite(parent, SWT.NONE);
		pathComposite.setLayout(new GridLayout(3,false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		pathComposite.setLayoutData(gd);
		new Label(pathComposite,SWT.NONE).setText(Messages.DestinationPage_destinationLabel);
		
		pathText = new Text(pathComposite, SWT.BORDER);
		pathText.setEditable(false);
		gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
		pathText.setLayoutData(gd);
		
		Button browseButton = new Button(pathComposite, SWT.NONE);
		browseButton.setText(Messages.ResourcePage_browseButton);
		browseButton.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(UIUtils.getShell(), SWT.SAVE);
		    fd.setText(Messages.DestinationPage_dialogTitle);
		    fd.setFileName("configuration.zip");
		    String[] filterExt = { "*.zip" }; //$NON-NLS-1$
		    fd.setFilterExtensions(filterExt);
		    pathString = fd.open();
		    if (pathString != null){
		    	pathText.setText(pathString);
		    	setPageComplete(true);
		    }
			}
		});
		setControl(pathComposite);
	}
}
