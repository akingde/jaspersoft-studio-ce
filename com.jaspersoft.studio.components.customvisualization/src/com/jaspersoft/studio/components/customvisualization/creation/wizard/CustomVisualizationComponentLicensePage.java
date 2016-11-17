/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation.wizard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.components.customvisualization.creation.ModuleDefinition;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleManager;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page where the user can select a library used by the chosen module
 * and view its license
 * 
 * @author Orlandin Marco
 *
 */
public class CustomVisualizationComponentLicensePage extends JSSWizardPage {

	/**
	 * Thread to download a license in background. It will notify when
	 * the download is finished
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class LicenseDownloader implements Runnable {
		
		/**
		 * The module for whose the license is downloaded
		 */
		private ModuleDefinition handledModule;
		
		/**
		 * Create the runnable code
		 * 
		 * @param handledModule The module for whose the license is downloaded
		 */
		public LicenseDownloader(ModuleDefinition handledModule) {
			this.handledModule = handledModule;
		}
		
		/**
		 * Download the license, remove the thread from the list of running thread
		 * and notify the download complete
		 */
		@Override
		public void run() {
			String license = ModuleManager.getLicenseFile(handledModule);
			downloadingLicenses.remove(handledModule);
			refreshLicenseText(handledModule, license);
		}
	};
	
	/**
	 * Selection adapter for the combo
	 */
	private SelectionAdapter comboSelected = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
			comboSelectionChange();
		};
	};

	/**
	 * Styled text where the label of currently selected module will be displayed.
	 * This is the license of the library selected in the combo
	 */
	private StyledText licenseLabel;
	
	/**
	 * Combo with a list of all the libraries used by the currently selected module
	 */
	private Combo libraryName;
	
	/**
	 * The currently selected module
	 */
	private ModuleDefinition currentModule = null;
	
	/**
	 * Set of all the running thread to download a license. If a module definition
	 * is inside here it means that its license is currently under download
	 */
	private HashSet<ModuleDefinition> downloadingLicenses = new HashSet<ModuleDefinition>();
	
	/**
	 * Map that keep paired a module with its name
	 */
	private HashMap<String, ModuleDefinition> usedModules = new HashMap<String, ModuleDefinition>();
	
	/**
	 * Boolean flag to check if the user has accepted the licenses
	 */
	private boolean licensesAccepted = false;
	
	/**
	 * Create the page
	 */
	public CustomVisualizationComponentLicensePage() {
		super("licensePage"); //$NON-NLS-1$
		setTitle(Messages.CustomVisualizationComponentLicensePage_pageTitle);
		setDescription(Messages.CustomVisualizationComponentLicensePage_pageDescription);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1,false));
	
		// CREATE THE PROJECT NAME AREA
		
		libraryName = new Combo(container, SWT.READ_ONLY);
		libraryName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		libraryName.addSelectionListener(comboSelected);
		
		//CREATE THE LICENSE AREA
		
		Group licenseGroup = new Group(container, SWT.NONE);
		licenseGroup.setText(Messages.CustomVisualizationComponentTablePage_licenseGroup);
		licenseGroup.setLayout(new GridLayout(1, false));
		licenseGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		licenseLabel = new StyledText(licenseGroup, SWT.WRAP | SWT.V_SCROLL);
		licenseLabel.setBackground(container.getBackground());
		licenseLabel.setEditable(false);
		GridData licenseData = new GridData(GridData.FILL_BOTH);
		licenseData.widthHint = 200;
		licenseLabel.setLayoutData(licenseData);
		
		//CREATE THE ACCEPT BUTTON
		final Button licenseButton = new Button(container, SWT.CHECK);
		licenseButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		licenseButton.setText(Messages.CustomVisualizationComponentLicensePage_acceptLicensesButton);
		licenseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				licensesAccepted = licenseButton.getSelection();
				getContainer().updateButtons();
			}
			
		});
		
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * When set to visible if the selected module is changed refresh the licenses
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			ModuleDefinition selectedModule = (ModuleDefinition)getSettings().get(CustomVisualizationComponentWizard.SELECTED_MODULE_KEY);
			if (selectedModule != null && selectedModule != currentModule) {
				currentModule = selectedModule;
				fetchLicense(selectedModule);
				List<String> comboItems = new ArrayList<String>();
				usedModules.clear();
				findComboItems(selectedModule, comboItems);
				libraryName.setItems(comboItems.toArray(new String[comboItems.size()]));
				libraryName.select(0);
				comboSelectionChange();
			}
		}
	}
	
	/**
	 * Launch a thread to get the license of a module if it is not
	 * available locally. Also recursively call this method on the
	 * dependences of the module
	 * 
	 * @param module the module for which the license is searched
	 */
	private void fetchLicense(ModuleDefinition module){
		if (!ModuleManager.isLicenseLocal(module)){
			LicenseDownloader downloader = new LicenseDownloader(module);
			downloadingLicenses.add(module);
			new Thread(downloader).start();
			for(ModuleDefinition dep : module.getRequiredLibraries()){
				fetchLicense(dep);
			}
		}
	}
	
	/**
	 * Search recursively all the libraries used by a module and from its
	 * dependencies
	 * 
	 * @param module current module
	 * @param result list where the name of the found libraries are placed
	 */
	private void findComboItems(ModuleDefinition module, List<String> result){
		result.add(module.getLibraryFilename());
		usedModules.put(module.getLibraryFilename(), module);
		for(ModuleDefinition dep : module.getRequiredLibraries()){
			findComboItems(dep, result);
		}
	}
	
	/**
	 * If in the combo is selected the module passed as first parameter the license
	 * area will be set with the text of the second parameter. Called to notify the
	 * finish of the download of a license
	 * 
	 * @param module  the module for which the license was downloaded
	 * @param licenseText the license
	 */
	private void refreshLicenseText(final ModuleDefinition module, final String licenseText){
		UIUtils.getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				synchronized (usedModules) {
					if (licenseLabel != null && !licenseLabel.isDisposed()){
						if (libraryName.getText().equals(module.getLibraryFilename())){
							licenseLabel.setText(licenseText);
						}
					}
				}
			}
		});
	}
	
	/**
	 * Called when the combo selection changes to show the correct string
	 */
	private void comboSelectionChange(){
		ModuleDefinition selectedModule = usedModules.get(libraryName.getText());
		synchronized (usedModules) {
			if (selectedModule != null && licenseLabel != null && !licenseLabel.isDisposed()){
				if (downloadingLicenses.contains(selectedModule) || ModuleManager.isLicenseLocal(selectedModule)){
					licenseLabel.setText(Messages.CustomVisualizationComponentLicensePage_downloadLabel);
					if (!downloadingLicenses.contains(selectedModule)){
						LicenseDownloader downloader = new LicenseDownloader(selectedModule);
						downloadingLicenses.add(selectedModule);
						new Thread(downloader).start();
					}
				} else {
					licenseLabel.setText(ModuleManager.getLicenseFile(selectedModule));
				}
			}
		}
	}
	
	/**
	 * The page is complete only when the licenses are accepted
	 */
	@Override
	public boolean isPageComplete() {
		return licensesAccepted;
	}
}
