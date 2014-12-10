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
package com.jaspersoft.studio.components.customvisualization.creation.wizard;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.customvisualization.creation.ModuleDefinition;
import com.jaspersoft.studio.components.customvisualization.creation.ModuleManager;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page where the user can select the module (essentially a Custom Visualization
 * component example) to create a new project with inside the skeleton of a custom
 * visualization component
 * 
 * @author Orlandin Marco
 *
 */
public class CustomVisualizationComponentTablePage extends JSSWizardPage {

	/**
	 * Label provider for the table, the first column has the module name, 
	 * the second the version number and the third a label to know if the module
	 * need to be downloaded or it is saved locally
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ModuleLableProvider extends LabelProvider implements ITableLabelProvider{ 

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			if (columnIndex == 0){
				return ((ModuleDefinition)element).getModuleName();
			} else if (columnIndex == 1){
				ModuleDefinition module = (ModuleDefinition)element;
				return module.getVersionNumber();
			}	
			return null;
		}
		
	};
	
	/**
	 * viewer for the table
	 */
	private CheckboxTableViewer viewer;
	
	/**
	 * the table when the user can select a module that will be used as skeleton
	 */
	private Table table;

	/**
	 * Text area where the project name can be provided
	 */
	private Text projectName;
	
	/**
	 * Provider for the table labels
	 */
	private ModuleLableProvider labelProvider = new ModuleLableProvider();
	
	/**
	 * Modify listener for the projectName, made to re-validate the 
	 * page when something changes
	 */
	private ModifyListener nameModified = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			getContainer().updateButtons();
		}
	};
	
	/**
	 * Create the page
	 */
	public CustomVisualizationComponentTablePage() {
		super("moduleSelector"); //$NON-NLS-1$
		setTitle(Messages.CustomVisualizationComponentTablePage_pageTitle);
		setDescription(Messages.CustomVisualizationComponentTablePage_pageDescription);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1,false));
	
		// CREATE THE PROJECT NAME AREA
		
		Composite nameSection = new Composite(container, SWT.NONE);
		GridData nameData = new GridData(GridData.FILL_HORIZONTAL);
		nameSection.setLayout(new GridLayout(2,false));
		nameSection.setLayoutData(nameData);
		
		Label nameLabel = new Label(nameSection, SWT.NONE);
		nameLabel.setText(Messages.CustomVisualizationComponentTablePage_projectName);
		projectName = new Text(nameSection, SWT.BORDER);
		projectName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		projectName.addModifyListener(nameModified);
		
		//CREATE THE TABLE AREA
		
		viewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER); 
		viewer.setLabelProvider(labelProvider);
		viewer.setContentProvider(new ListContentProvider());
		table = viewer.getTable();
		GridData tableData = new GridData(GridData.FILL_BOTH);
		tableData.widthHint = 250;
		table.setLayoutData(tableData);
		
		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(75, 200, true));
		tlayout.addColumnData(new ColumnWeightData(25, 50, true));
		//tlayout.addColumnData(new ColumnWeightData(50, 75, true));
		table.setLayout(tlayout);
		table.setHeaderVisible(true);
		TableColumn[] column = new TableColumn[2];
		column[0] = new TableColumn(table, SWT.NONE);
		column[0].setText(Messages.CustomVisualizationComponentTablePage_nameCol);
		
		column[1] = new TableColumn(table, SWT.NONE);
		column[1].setText(Messages.CustomVisualizationComponentTablePage_versionCol);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

		
		attachCellEditors();
		viewer.setInput(ModuleManager.getModules());
		
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * Attach the listeners to the table viewer
	 */
	private void attachCellEditors() {
		
		//Check listener, allow only to have one module selected. It show
		//also the license of the checked module
		viewer.addCheckStateListener(new ICheckStateListener() {
			
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				viewer.setSelection(new StructuredSelection(event.getElement()));
				if (event.getChecked()){
					for(Object element : ModuleManager.getModules()){
						if (element != event.getElement()){
							viewer.setChecked(element, false);
						}
					}
				}
				//Store the selected module to share it with the other pages
				if (viewer.getCheckedElements().length>0) getSettings().put(CustomVisualizationComponentWizard.SELECTED_MODULE_KEY, viewer.getCheckedElements()[0]);
				else getSettings().remove(CustomVisualizationComponentWizard.SELECTED_MODULE_KEY);
				getContainer().updateButtons();
			}
		});
	}
	
	/**
	 * Get the first (and the only one) checked module inside 
	 * the table
	 * 
	 * @return the checked module inside the table or null if no module is checked
	 */
	public ModuleDefinition getSelectedModule(){
		if ( viewer.getCheckedElements().length > 0){
			return (ModuleDefinition)viewer.getCheckedElements()[0];
		}
		return null;
	}

	/**
	 * Return the current project name
	 * 
	 * @return a project name
	 */
	public String getProjectName(){
		return projectName.getText();
	}
	
	/**
	 * Validate the input of the page. The input is valid if:
	 * 
	 * -A module is selected
	 * -The name of the project is not empty and dosen't contains spaces
	 * -The Name of the project is not already used
	 */
	@Override
	public boolean isPageComplete() {
		if (viewer.getCheckedElements().length == 0){
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
