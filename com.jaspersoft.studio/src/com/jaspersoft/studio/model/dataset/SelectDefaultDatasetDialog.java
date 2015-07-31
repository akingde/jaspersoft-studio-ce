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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Dialog proposed when a Data Adapter to use as default needs to be selected.
 * 
 * @author Orlandin Marco
 * 
 */
public class SelectDefaultDatasetDialog extends Dialog {
	
	// All widgets stuff
	private Text txtResourcePath;
	private Text txtFilesystemPath;
	private Text txtURL;
	private Button btnWorkspaceResource;
	private Button btnAbsolutePath;
	private Button btnNoDataSource;
	private Button btnUrlRemote;
	private StackLayout grpOptionsLayout;
	private Composite cmpWorkspaceResourceSelection;
	private Composite cmpFilesystemResourceSelection;
	private Composite cmpNoDataAdapter;
	private Composite cmpURL;
	private Group grpOptions;
	private String path = null;

	private JasperReportsConfiguration jConfig;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SelectDefaultDatasetDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(getDialogTitle());
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
				Messages.SelectDefaultDatasetDialog_noDAOption};
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
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
				changeSelectionMode(cmpWorkspaceResourceSelection);
				path = txtResourcePath.getText();
			}
		});
		btnWorkspaceResource.setText(modesAndHeaderTitles[1]);

		btnAbsolutePath = new Button(grpSelectionMode, SWT.RADIO);
		btnAbsolutePath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode(cmpFilesystemResourceSelection);
				path = txtFilesystemPath.getText();
			}
		});
		btnAbsolutePath.setText(modesAndHeaderTitles[2]);

		btnUrlRemote = new Button(grpSelectionMode, SWT.RADIO);
		btnUrlRemote.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode(cmpURL);
				path = txtURL.getText();
			}
		});
		btnUrlRemote.setText(modesAndHeaderTitles[3]);

		btnNoDataSource = new Button(grpSelectionMode, SWT.RADIO);
		btnNoDataSource.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changeSelectionMode(cmpNoDataAdapter);
				path = null;
			}
		});
		btnNoDataSource.setText(modesAndHeaderTitles[4]);

		createOptionsPanel(container);
		
		//Initialize the path with the current value
		if (path == null){
			btnNoDataSource.setSelection(true);
			changeSelectionMode(cmpNoDataAdapter);
		} else {
			File checkAbsolute = new File(path);
			if (checkAbsolute.exists()){
				btnAbsolutePath.setSelection(true);
				txtFilesystemPath.setText(path);
				changeSelectionMode(cmpFilesystemResourceSelection);
			} else {
				if (FileUtils.isValidURL(path)){
					btnUrlRemote.setSelection(true);
					txtURL.setText(path);
					changeSelectionMode(cmpURL);
				} else {
					btnWorkspaceResource.setSelection(true);
					txtResourcePath.setText(path);
					changeSelectionMode(cmpWorkspaceResourceSelection);
				}
			}
		}
		return area;
	}

	/**
	 * Creates the panel with the different options container. A stack layout will be used.
	 */
	private void createOptionsPanel(Composite container) {
		grpOptions = new Group(container, SWT.NONE);
		grpOptions.setText(Messages.ImageSelectionDialog_OptionsGroupTitle);
		grpOptionsLayout = new StackLayout();
		grpOptions.setLayout(grpOptionsLayout);
		grpOptions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		createWSSelectionContainer();
		createFSSelectionContainer();
		createURLOptionsContainer();
		createNoDataSourceContainer();
	}

	/**
	 * Creates the composite container for the workspace Data Adapter selection.
	 */
	private void createWSSelectionContainer() {
		cmpWorkspaceResourceSelection = new Composite(grpOptions, SWT.NONE);
		cmpWorkspaceResourceSelection.setLayout(new GridLayout(2, false));

		Label lblSelectDataAdapterFromWS = new Label(cmpWorkspaceResourceSelection, SWT.NONE);
		lblSelectDataAdapterFromWS.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblSelectDataAdapterFromWS.setText(Messages.SelectDefaultDatasetDialog_workspaceLabel);

		txtResourcePath = new Text(cmpWorkspaceResourceSelection, SWT.BORDER);
		txtResourcePath.setEnabled(false);
		txtResourcePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSelectWsRes = new Button(cmpWorkspaceResourceSelection, SWT.NONE);
		btnSelectWsRes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectDataAdapterFromWorkspace();
			}
		});
		btnSelectWsRes.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnSelectWsRes.setText(Messages.common_browse);
	}

	/**
	 * Creates the composite container for the filesystem Data Adapter selection.
	 */
	private void createFSSelectionContainer() {
		cmpFilesystemResourceSelection = new Composite(grpOptions, SWT.NONE);
		cmpFilesystemResourceSelection.setLayout(new GridLayout(2, false));

		Label lblSelectDataAdapterFromFilesystem = new Label(cmpFilesystemResourceSelection, SWT.NONE);
		lblSelectDataAdapterFromFilesystem.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		lblSelectDataAdapterFromFilesystem.setText(Messages.SelectDefaultDatasetDialog_absoluteLabel);

		txtFilesystemPath = new Text(cmpFilesystemResourceSelection, SWT.BORDER);
		txtFilesystemPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtFilesystemPath.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textModified();
			}
		});

		Button btnSelectFilesystemRes = new Button(cmpFilesystemResourceSelection, SWT.NONE);
		btnSelectFilesystemRes.setText(Messages.common_browse);
		btnSelectFilesystemRes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectDataAdapterFromFilesystem();
			}
		});
	}
	
	/**
	 * Create the composite container for the URL Data Adapter selection.
	 */
	private void createURLOptionsContainer() {
		cmpURL = new Composite(grpOptions, SWT.NONE);
		cmpURL.setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(cmpURL, SWT.NONE);
		lblNewLabel.setText(Messages.SelectDefaultDatasetDialog_urlLabel);

		txtURL = new Text(cmpURL, SWT.BORDER);
		txtURL.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		txtURL.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				textModified();
			}
		});
	}
	
	private void textModified() {
		if (SelectDefaultDatasetDialog.this.getDialogArea() != null && !SelectDefaultDatasetDialog.this.getDialogArea().isDisposed()) {
			if (btnAbsolutePath.getSelection()) {
				// filesystem path...
				String daPath = txtFilesystemPath.getText();
				// Change the standard separator with an universal one
				path = daPath.replace(File.pathSeparatorChar, '/');
			} else if (btnUrlRemote.getSelection()) {
				// URL
				path = txtURL.getText();
			}
		}
	}
	
	/**
	 * Creates the empty composite for no Data Adapter selection.
	 */
	private void createNoDataSourceContainer() {
		cmpNoDataAdapter = new Composite(grpOptions, SWT.NONE);
	}

	/**
	 * When a new data adapter selection mode is selected, shows the dedicated options panel
	 */
	private void changeSelectionMode(Control newTopControl) {
		// Change the top control for the options panel
		grpOptionsLayout.topControl = newTopControl;
		grpOptions.layout();
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
			if (contextfile != null && file.getProject().equals(contextfile.getProject())) {
				filepath = file.getProjectRelativePath().toPortableString().replaceAll(file.getProject().getName() + "/", ""); //$NON-NLS-1$ //$NON-NLS-2$
			} else {
				filepath = file.getRawLocationURI().toASCIIString();
			}
			filepath = filepath.toString().replace(File.pathSeparatorChar, '/');
			txtResourcePath.setText(filepath);
			// Change the standard separator with an universal one
			path = filepath;
		} else {
			// no data adapter selected
			txtResourcePath.setText(""); //$NON-NLS-1$
		}
	}

	/**
	 * Popup the dialog to select the data adapter from the filesystem.
	 */
	private void selectDataAdapterFromFilesystem() {
		FileDialog fd = new FileDialog(Display.getDefault().getActiveShell());
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		fd.setFilterPath(root.getLocation().toOSString());
		fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
		String selection = fd.open();
		if (selection != null) {
			txtFilesystemPath.setText(selection);
		}
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(640, 320);
	}

	/**
	 * Configure required information for the correct dialog functioning.
	 * 
	 * @param jConfig
	 */
	public void configureDialog(JasperReportsConfiguration jConfig, String intialPath) {
		this.jConfig = jConfig;
		if (intialPath != null){
			path = intialPath;
		}
	}

	@Override
	public int open() {
		if (jConfig == null) {
			throw new RuntimeException(Messages.ImageSelectionDialog_Error);
		}
		return super.open();
	}
	
	public String getDataAdapterPath(){
		return path;
	}
}
