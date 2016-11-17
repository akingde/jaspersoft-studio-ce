/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.customvisualization.creation.ModuleDefinition;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleManager;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.wizards.JSSWizardPage;

public class CustomVisualizationComponentComboPage extends JSSWizardPage {

	private Combo combo;
	
	private StyledText licenseLabel;
	
	private Text projectName;
	
	private ModifyListener nameModified = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			getContainer().updateButtons();
		}
	};
	
	protected CustomVisualizationComponentComboPage() {
		super("moduleSelector");
		setTitle("Modules Selection");
		setDescription("Select the modules and the folder name for your Custom Visualization prject");
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		
		Composite nameSection = new Composite(container, SWT.NONE);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		nameData.horizontalSpan = 2;
		nameSection.setLayout(new GridLayout(2,false));
		nameSection.setLayoutData(nameData);
		
		Label nameLabel = new Label(nameSection, SWT.NONE);
		nameLabel.setText("Component Name");
		
		projectName = new Text(nameSection, SWT.BORDER);
		projectName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		projectName.addModifyListener(nameModified);
		
		
		Composite comboContainer = new Composite(container, SWT.NONE);
		comboContainer.setLayout(new GridLayout(2,false));
		comboContainer.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		
		new Label(comboContainer, SWT.NONE).setText("Used library:");
		
		combo = new Combo(comboContainer, SWT.READ_ONLY);
		
		GridData comboData = new GridData();
		comboData.widthHint = 200;
		combo.setLayoutData(comboData);
		
		List<String> comboItems = new ArrayList<String>();
		List<ModuleDefinition> items = ModuleManager.getModules();
		comboItems.add("");
		combo.setData(items);
		for(ModuleDefinition def : items){
			comboItems.add(def.getModuleVisualName());
		}
		combo.setItems(comboItems.toArray(new String[comboItems.size()]));
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectedIndex = combo.getSelectionIndex();
				if  (selectedIndex > 0){
					ModuleDefinition module = ModuleManager.getModules().get(selectedIndex-1);
					licenseLabel.setText(ModuleManager.getLicenseFile(module));
				} else {
					licenseLabel.setText("");
				}
			}
		});
		
		Group licenseGroup = new Group(container, SWT.NONE);
		licenseGroup.setText("License");
		licenseGroup.setLayout(new GridLayout(1, false));
		licenseGroup.setLayoutData(new GridData(GridData.FILL_BOTH));
		licenseLabel = new StyledText(licenseGroup, SWT.WRAP | SWT.V_SCROLL);
		licenseLabel.setBackground(container.getBackground());
		licenseLabel.setEditable(false);
		GridData licenseData = new GridData(GridData.FILL_BOTH);
		licenseData.widthHint = 50;
		licenseLabel.setLayoutData(licenseData);
	
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	public ModuleDefinition getSelectedModule(){
		if (combo.getSelectionIndex() > 0){
			List<ModuleDefinition> items = ModuleManager.getModules();
			return items.get(combo.getSelectionIndex()-1);
		}
		return null;
	}

	public String getProjectName(){
		return projectName.getText();
	}
	
	@Override
	public boolean isPageComplete() {
		if (combo.getSelectionIndex() == 0){
			setErrorMessage(Messages.CustomVisualizationComponentTablePage_noLibraryError);
			return false;
		}
		if (projectName.getText().trim().isEmpty()) {
			setErrorMessage(Messages.CustomVisualizationComponentTablePage_errorEmpty);
			return false;
		}
		if (projectName.getText().contains(" ")) { //$NON-NLS-1$
			setErrorMessage(Messages.CustomVisualizationComponentTablePage_noSpacesError);
			return false;
		}
	    Path pathContainer = new Path(projectName.getText());
	    if (!pathContainer.isValidPath(projectName.getText())) {
	    	setErrorMessage(Messages.CustomVisualizationComponentTablePage_errorInvalidGeneric);
	    	return false;
	    }
	    if (pathContainer.segmentCount() < 1) {
	    	setErrorMessage(Messages.CustomVisualizationComponentTablePage_errorEmpty);
	    	return false;
	    }
	    if (projectExists(pathContainer.segment(0))) {
	    	setErrorMessage(Messages.CustomVisualizationComponentTablePage_errorProjectExist);
	    	return false;
	    }
	    setErrorMessage(null);
	    return true;
	}
	
  /**
   * Checks if there is a Project with the given name in the Package Explorer
   * 
   * @param projectName the name of the project
   * @return true if the project already exist in the workspace, false otherwise
   */
   protected boolean projectExists(String projectName) {
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      Path containerNamePath = new Path("/" + projectName); //$NON-NLS-1$
      IResource resource = root.findMember(containerNamePath);
      if (resource == null) {
          return false;
      }
      return resource.exists();
  }
}
