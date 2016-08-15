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
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerDefinitionManager;
import com.jaspersoft.studio.components.chart.property.widget.CustomizerWidgetsDescriptor;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.engine.JRChartCustomizer;

/**
 * Dialog to select a chart customizer definition from the preferences or providing 
 * a {@link JRChartCustomizer} classname
 * 
 * @author Orlandin Marco
 *
 */
public class SelectCustomizerPage extends JSSHelpWizardPage {
	
	/**
	 * The {@link ChartCustomizerDefinition} created from the controls of the preferences customizer (first tab)
	 */
	private ChartCustomizerDefinition valueSelection = null;
	
	/**
	 * Key to be used when a {@link ChartCustomizerDefinition} is created
	 */
	private String definitionKey;
	
	/**
	 * List of the customizer available in the preferences
	 */
	private List<CustomizerWidgetsDescriptor> tableInput;
	
	/**
	 * Special fake entry added to the input of the table to define an user provided customizer, by typing
	 * its classname
	 */
	private CustomizerWidgetsDescriptor userDefinedEntry;
	
	/**
	 * The preferences where the customizer can be selected among the ones defined in the preferences
	 */
	private TableViewer table;
	
	/**
	 * Create a dialog to define a new customizer
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the current report
	 * @param definitionKey an unique new key for the new {@link ChartCustomizerDefinition}
	 * @param dto the dto changed during this wizard page, it should be a copy in case the wizard is cancelled
	 */
	public SelectCustomizerPage(JasperReportsConfiguration jConfig, String definitionKey, CustomizerPropertyExpressionsDTO dto) {
		super("customizerTypeSelection");
		tableInput = new ArrayList<CustomizerWidgetsDescriptor>(CustomizerDefinitionManager.getCustomizerDefinitions(jConfig));
		userDefinedEntry = new CustomizerWidgetsDescriptor();
		userDefinedEntry.setLabel("User defined customizer...");
		tableInput.add(userDefinedEntry);
		this.definitionKey = definitionKey;
	}
	
	/**
	 * Create the table area

	 * @param tab tab where the controls will be created
	 */
	protected void createTableArea(Composite parent){
		table = new TableViewer(parent, SWT.FULL_SELECTION | SWT.SINGLE | SWT.BORDER);
		table.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setContentProvider(new ListContentProvider());
		table.setInput(tableInput);
		table.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if(selection.getFirstElement() != null){
					CustomizerWidgetsDescriptor selCustomizer = (CustomizerWidgetsDescriptor)selection.getFirstElement();
					valueSelection = new ChartCustomizerDefinition(selCustomizer, definitionKey);
				}	
				validate();
			}
		});
	}
	

	/**
	 * Validate the input, looking if at least one entry is selected
	 */
	protected void validate(){
		ISelection seletion = table.getSelection();
		if (seletion == null || seletion.isEmpty()){
			setErrorMessage("You need to select a Chart Customizer from the table");
		}  else {
			setErrorMessage(null);
		}
		getContainer().updateButtons();
	}

	@Override
	public void createControl(Composite parent) {
		setTitle("Select the new Chart Customizer");
		setMessage("Select the type of you chart customizer");

		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		createTableArea(container);	
		setControl(container);
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			validate();
		}
	}

	/**
	 * Check if the current selection is based on a Customizer Definition file or is 
	 * the fake user defined customizer
	 * 
	 * @return true if the current selection is a user defined customizer, so a classname
	 * will be expected in the next step, false otherwise
	 */
	public boolean isUsingCustomDefinition(){
		return (valueSelection != null && valueSelection.getDescriptor() == userDefinedEntry);
	}
	
	/**
	 * Return the {@link ChartCustomizerDefinition} associated to the selection in the table
	 * 
	 * @return a not null {@link ChartCustomizerDefinition}, but only when the page is marked as complete
	 */
	public ChartCustomizerDefinition getSelectedDefinition(){
		return valueSelection;
	}
	
	/**
	 * The page is complete only if something is selected in the table
	 */
	@Override
	public boolean isPageComplete() {
		return getSelectedDefinition() != null && getErrorMessage() == null;
	}
	
	/**
	 * It is possible to flip to the next page only when this one is complete and there
	 * are widgets to show for the selected customizer
	 */
	@Override
	public boolean canFlipToNextPage() {
		boolean value = isPageComplete();
		if (value){
			WidgetsDescriptor descriptor = getSelectedDefinition().getDescriptor();
			if (descriptor != userDefinedEntry && !descriptor.hasWidgets()){
				value = false;
			}
		}
		return value;
	}

	@Override
	protected String getContextName() {
		return null;
	}
}
