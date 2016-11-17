/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.creation.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.customvisualization.creation.ModuleDefinition;
import com.jaspersoft.studio.components.customvisualization.messages.Messages;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page where the user can see a list of the library that will be 
 * used by selected module and created in the workspace of the new project
 * 
 * @author Orlandin Marco
 *
 */
public class CustomVisualizationComponentSummaryPage extends JSSWizardPage {

	/**
	 * Composite where the list of libraries is created
	 */
	private Composite libraryList;
	
	/**
	 * Create the page
	 */
	public CustomVisualizationComponentSummaryPage() {
		super("moduleSummary"); //$NON-NLS-1$
		setTitle(Messages.CustomVisualizationComponentSummaryPage_title);
		setDescription(Messages.CustomVisualizationComponentSummaryPage_description);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1,false));
		
		Label header = new Label(container, SWT.NONE);
		header.setText(Messages.CustomVisualizationComponentSummaryPage_headerLabel);
		header.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		libraryList = new Composite(container, SWT.NONE);
		GridLayout libraryListLayout = new GridLayout(1,false);
		libraryListLayout.marginLeft = 10;
		libraryList.setLayout(libraryListLayout);
		libraryList.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label footer = new Label(container, SWT.NONE);
		footer.setText(Messages.CustomVisualizationComponentSummaryPage_footerLabel);
		footer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		setControl(container);
	}
	
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) createLabels();
	}
	
	/**
	 * Dispose all the labels for the library and create
	 * new labels for the currently selected module
	 */
	private void createLabels(){
		ModuleDefinition selectedElement = (ModuleDefinition)getSettings().get(CustomVisualizationComponentWizard.SELECTED_MODULE_KEY);
		for(Control child : libraryList.getChildren()){
			child.dispose();
		}
		if (selectedElement != null){
			createLabel(selectedElement);
		}
		libraryList.getParent().layout(true,true);
	}
	
	/**
	 * Recursive method to create a label for a module in the libraryList
	 * composite, and recursively one for all it's dependencies
	 * 
	 * @param module The module
	 */
	private void createLabel(ModuleDefinition module){
		Label elementLabel = new Label(libraryList, SWT.NONE);
		elementLabel.setText(module.getLibraryFilename() + " " + module.getLibraryVersionNumber());
		elementLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		for(ModuleDefinition dep : module.getRequiredLibraries()){
			createLabel(dep);
		}
	}

	@Override
	protected String getContextName() {
		return null;
	}
}
