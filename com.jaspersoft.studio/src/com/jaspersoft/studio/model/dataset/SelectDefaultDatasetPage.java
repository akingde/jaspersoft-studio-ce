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
package com.jaspersoft.studio.model.dataset;

import java.io.File;

import net.sf.jasperreports.eclipse.util.FileUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Controls shown when a Data Adapter to use as default needs to be selected.
 * 
 * @author Orlandin Marco
 * 
 */
public class SelectDefaultDatasetPage extends JSSHelpWizardPage {
	
	// All widgets stuff
	private Text pathText;
	private Button browseWorkspace;
	private Button browseFilesystem;
	private Button btnWorkspaceResource;
	private Button btnAbsolutePath;
	//private Button btnNoDataSource;
	private Button btnUrlRemote;
	private Button btnCustom;
	private Group grpOptions;
	private String path = null;
	private Label descriptionLabel;

	private JasperReportsConfiguration jConfig;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectDefaultDatasetPage(JasperReportsConfiguration jConfig, String intialPath) {
		super("defaultDAPage"); //$NON-NLS-1$
		setTitle(getDialogTitle());
		setDescription(Messages.SelectDefaultDatasetDialog_dialogDescription);
		this.jConfig = jConfig;
		if (intialPath != null){
			path = intialPath;
		}
	}
	
	/**
	 * @return the title for the dialog
	 */
	protected String getDialogTitle() {
		return Messages.SelectDefaultDatasetDialog_dialogTitle;
	}

	/**
	 * Returns an array of strings containing the title for the modes section, plus the title of every mode.
	 * <p>
	 * 
	 * Default implementation would return 5 strings, including 1 title and the following 4 modes:
	 * <ol>
	 * <li>workspace resource;</li>
	 * <li>absolute path in filesystem;</li>
	 * <li>URL;</li>
	 * <li>no default datasource;</li>
	 * </ol>
	 * 
	 * @return the title and labels for the group of modes
	 */
	protected String[] getModesAndHeaderTitles() {
		return new String[] { Messages.SelectDefaultDatasetDialog_modeLabel, Messages.SelectDefaultDatasetDialog_workspaceOption,
				Messages.SelectDefaultDatasetDialog_absoluteOption,
				Messages.SelectDefaultDatasetDialog_urlOption,
				Messages.SelectDefaultDatasetDialog_noDAOption,
				Messages.SelectDefaultDatasetPage_customDAAction};
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, true));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		String[] modesAndHeaderTitles = getModesAndHeaderTitles();

		Group grpSelectionMode = new Group(container, SWT.NONE);
		grpSelectionMode.setText(modesAndHeaderTitles[0]);
		grpSelectionMode.setLayout(new GridLayout(1, false));
		grpSelectionMode.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1));

		btnWorkspaceResource = new Button(grpSelectionMode, SWT.RADIO);
		btnWorkspaceResource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode();
				path = pathText.getText();
				getWizard().getContainer().updateButtons();
			}
		});
		btnWorkspaceResource.setText(modesAndHeaderTitles[1]);

		btnAbsolutePath = new Button(grpSelectionMode, SWT.RADIO);
		btnAbsolutePath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode();
				path = pathText.getText();
				getWizard().getContainer().updateButtons();
			}
		});
		btnAbsolutePath.setText(modesAndHeaderTitles[2]);

		btnUrlRemote = new Button(grpSelectionMode, SWT.RADIO);
		btnUrlRemote.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode();
				path = pathText.getText();
				getWizard().getContainer().updateButtons();
			}
		});
		btnUrlRemote.setText(modesAndHeaderTitles[3]);

		/*btnNoDataSource = new Button(grpSelectionMode, SWT.RADIO);
		btnNoDataSource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode(cmpNoDataAdapter);
				path = null;
				getWizard().getContainer().updateButtons();
			}
		});
		btnNoDataSource.setText(modesAndHeaderTitles[4]);*/

		btnCustom = new Button(grpSelectionMode, SWT.RADIO);
		btnCustom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode();
				path = pathText.getText();
				getWizard().getContainer().updateButtons();
			}
		});
		btnCustom.setText(modesAndHeaderTitles[5]);

		
		createOptionsPanel(container);
		
		//Initialize the path with the current value
		if (path == null || path.trim().isEmpty()){
			btnWorkspaceResource.setSelection(true);
			changeSelectionMode();
		} else {
			pathText.setText(path);
			File checkAbsolute = new File(path);
			if (checkAbsolute.exists()){
				btnAbsolutePath.setSelection(true);
				changeSelectionMode();
			} else {
				if (FileUtils.isValidURL(path)){
					btnUrlRemote.setSelection(true);
					changeSelectionMode();
				} else {
					IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
					if (resource instanceof IFile && ((IFile)resource).exists()){
						btnWorkspaceResource.setSelection(true);
						changeSelectionMode();
					} else {
						btnCustom.setSelection(true);
						changeSelectionMode();
					}
				}
			}
		}
		getWizard().getContainer().updateButtons();
		setControl(container);
	}

	/**
	 * Creates the panel with the different options container. A stack layout will be used.
	 */
	private void createOptionsPanel(Composite container) {
		grpOptions = new Group(container, SWT.NONE);
		grpOptions.setText(Messages.SelectDefaultDatasetPage_pathLabel);
		grpOptions.setLayout(new GridLayout(1,false));
		grpOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		
		descriptionLabel = new Label(grpOptions, SWT.NONE);
		descriptionLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Composite controlContainer = new Composite(grpOptions, SWT.NONE);
		controlContainer.setLayout(new GridLayout(3,false));
		controlContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		pathText = new Text(controlContainer, SWT.BORDER);
		pathText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		pathText.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textModified();
			}
		}); 
		
		createWSSelectionContainer(controlContainer);
		createFSSelectionContainer(controlContainer);
	}

	/**
	 * When a new data adapter selection mode is selected, shows the dedicated options panel
	 */
	private void changeSelectionMode() {
		if (btnAbsolutePath.getSelection()){
			((GridData)browseFilesystem.getLayoutData()).exclude = false;
			browseFilesystem.setVisible(true);
			
			((GridData)browseWorkspace.getLayoutData()).exclude = true;
			browseWorkspace.setVisible(false);
		} else if (btnWorkspaceResource.getSelection()){
			((GridData)browseFilesystem.getLayoutData()).exclude = true;
			browseFilesystem.setVisible(false);
			
			((GridData)browseWorkspace.getLayoutData()).exclude = false;
			browseWorkspace.setVisible(true);
		} else {
			((GridData)browseFilesystem.getLayoutData()).exclude = true;
			browseFilesystem.setVisible(false);
			
			((GridData)browseWorkspace.getLayoutData()).exclude = true;
			browseWorkspace.setVisible(false);
		}
		
		if (btnAbsolutePath.getSelection()){
			descriptionLabel.setText(Messages.SelectDefaultDatasetDialog_absoluteLabel);
		} else if (btnWorkspaceResource.getSelection()){
			descriptionLabel.setText(Messages.SelectDefaultDatasetDialog_workspaceLabel);
		} else if (btnUrlRemote.getSelection()){
			descriptionLabel.setText(Messages.SelectDefaultDatasetDialog_urlLabel);
		} else if (btnCustom.getSelection()){
			descriptionLabel.setText(Messages.SelectDefaultDatasetPage_customDADescription);
		}
		grpOptions.layout(true, true);
		getWizard().getContainer().updateButtons();
	}
	
	
	/**
	 * Creates the composite container for the workspace Data Adapter selection.
	 */
	private void createWSSelectionContainer(Composite container) {

		browseWorkspace = new Button(container, SWT.NONE);
		browseWorkspace.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectDataAdapterFromWorkspace();
			}
		});
		
		GridData data = new GridData();
		data.exclude = true;
		browseWorkspace.setLayoutData(data);
		browseWorkspace.setText(Messages.common_browse);
		browseWorkspace.setVisible(false);
	}

	/**
	 * Creates the composite container for the filesystem Data Adapter selection.
	 */
	private void createFSSelectionContainer(Composite container) {
		browseFilesystem = new Button(container, SWT.NONE);
		browseFilesystem.setText(Messages.common_browse);
		browseFilesystem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectDataAdapterFromFilesystem();
			}
		});
		
		GridData data = new GridData();
		data.exclude = true;
		browseFilesystem.setLayoutData(data);
		browseFilesystem.setVisible(false);
	}


	
	/**
	 * Creates the empty composite for no Data Adapter selection.
	 */
	/*private void createNoDataSourceContainer() {
		cmpNoDataAdapter = new Composite(grpOptions, SWT.NONE);
	}*/
	
	private void textModified() {
		if (btnAbsolutePath.getSelection()) {
			// filesystem path...
			String daPath = pathText.getText().trim();
			// Change the standard separator with an universal one
			path = daPath.replace(File.pathSeparatorChar, '/');
		} else {
			path = pathText.getText().trim();
		} 
		getWizard().getContainer().updateButtons();
	}

	/**
	 * Popup the dialog to select the data adapter from workspace.
	 */
	private void selectDataAdapterFromWorkspace() {
		FilteredResourcesSelectionDialog fd = new FilteredResourcesSelectionDialog(Display.getCurrent().getActiveShell(),
				false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
		fd.setInitialPattern("*.xml");//$NON-NLS-1$
		if (fd.open() == Dialog.OK) {
			IFile file = (IFile) fd.getFirstResult();
			IFile contextfile = (IFile) jConfig.get(FileUtils.KEY_FILE);
			String filepath = null;
			if (contextfile != null){
				filepath = FileUtils.getFileRelativePath(contextfile, file);
			} else {
				filepath = file.getLocation().toPortableString().replaceAll(file.getProject().getName() + "/", ""); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			filepath = filepath.toString().replace(File.pathSeparatorChar, '/');
			pathText.setText(filepath);
			// Change the standard separator with an universal one
			path = filepath;
		}
	}
	
	/**
	 * Check if the provided relative path from the report file point
	 * to an existing resource
	 * 
	 * @param location the relative path
	 * @return true if the path point to an existing resource, false otherwise
	 */
	private boolean isInWorkspace(String location){
		IPath path = new Path(location);
		IFile report = (IFile) jConfig.get(FileUtils.KEY_FILE);
		//Check if it is relative to the folder
		try{ 
			IFile folderFile = report.getParent().getFile(path);
			if (folderFile.exists()){
				return true;
			} 
		} catch (Exception ex){
			
		}
		//check if it is relative to the project
		try{ 
			IFile folderFile = report.getProject().getFile(path);
			if (folderFile.exists()){
				return true;
			} 
		} catch (Exception ex){
			
		}
		return false;
	}

	/**
	 * Popup the dialog to select the data adapter from the filesystem.
	 */
	private void selectDataAdapterFromFilesystem() {
		FileDialog fd = new FileDialog(Display.getDefault().getActiveShell());
		if (path == null || !new File(path).exists()){
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			fd.setFilterPath(root.getLocation().toOSString());
		} else {
			fd.setFilterPath(path);
		}
		fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
		String selection = fd.open();
		if (selection != null) {
			pathText.setText(selection);
		}
	}

	public String getDataAdapterPath(){
		return path;
	}

	/**
	 * Check if the page is complete, the page is complete if the provided absolute
	 * path is not empty or if the provided url is not empty and valid. If the absolute
	 * path is not empty but doesn't point to a file then a warning message is shown
	 */
	@Override
	public boolean isPageComplete() {
		if (btnAbsolutePath.getSelection()){
			if (path == null || path.isEmpty()){
				setMessage(Messages.SelectDefaultDatasetDialog_errorAbsoluteEmpty, WARNING);
			} else {
				File file = new File(path);
				if (!file.exists() || file.isDirectory()){
					setMessage(Messages.SelectDefaultDatasetDialog_warningAbsoluteNotFound, WARNING);
				} else {
					setMessage(Messages.SelectDefaultDatasetDialog_dialogDescription);
				}
			}
		} else if (btnUrlRemote.getSelection()){
			if (path == null || path.isEmpty()){
				setMessage(Messages.SelectDefaultDatasetDialog_errorURLEmpty, WARNING);
			} else if(!FileUtils.isValidURL(path)){
				setMessage(Messages.SelectDefaultDatasetDialog_errorURLInvalid, WARNING);
			} else {
				setMessage(Messages.SelectDefaultDatasetDialog_dialogDescription);
			}
		} else if (btnWorkspaceResource.getSelection()){
			if (path == null || path.isEmpty()){
				setMessage(Messages.SelectDefaultDatasetDialog_errorAbsoluteEmpty, WARNING);
			}  else if (!isInWorkspace(path)){
				setMessage(Messages.SelectDefaultDatasetDialog_warningAbsoluteNotFound, WARNING);
			} else {
				setMessage(Messages.SelectDefaultDatasetDialog_dialogDescription);
			}
		} else {
			setMessage(Messages.SelectDefaultDatasetDialog_dialogDescription);
		} 
		return true;
	}

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_DEFAULT_DATA_ADAPTER;
	}
}
