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
package com.jaspersoft.studio.editor.action.exporter.wizard;

import java.io.File;
import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page were all the resource that can be imported into studio from the selected
 * file are listed and can be selected by the user
 * 
 * @author Orlandin Marco
 *
 */
public class ShowImportableWizardPage extends JSSWizardPage {

	/**
	 * The set of exporter that will are selected by the user to import the resources from the
	 * selected container. It is updated when the selection of the user change
	 */
	private HashSet<IExportedResourceHandler> selectionSet = new HashSet<IExportedResourceHandler>();
	
	/**
	 * Composite where the checkbox to load the resources are created
	 */
	private Composite child;
	
	/**
	 * Scrolled composite to contain the check box in the dialog
	 */
	private ScrolledComposite sc;
	
	/**
	 * Adapter on the checkbox used to update the set of slection when the user select
	 * or deselect an element
	 */
	private SelectionAdapter checkSelected = new SelectionAdapter(){
		
		public void widgetSelected(SelectionEvent e) {
			IExportedResourceHandler def = (IExportedResourceHandler)e.widget.getData();
			Button btn = (Button)e.widget;
			if (btn.getSelection()){
				selectionSet.add(def);
			} else {
				selectionSet.remove(def);
			}
			validate();
		}
	};
	
	/**
	 * Create the page
	 */
	protected ShowImportableWizardPage() {
		super("showImportablePage"); //$NON-NLS-1$
		setTitle(Messages.SourcePage_pageDescription);
		setDescription(Messages.ShowImportableWizardPage_pageDescription);
	}

	@Override
	public void createControl(Composite parent) {
		sc = new ScrolledComposite(		parent, SWT.V_SCROLL);
		child = new Composite(sc, SWT.NONE);
		child.setLayout(new GridLayout(1, false));
	
		Label informationLabel = new Label(child, SWT.WRAP);
		informationLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		informationLabel.setText(Messages.ShowImportableWizardPage_informatinLabel);

		sc.setContent(child);
		// Set the minimum size
	  sc.setMinSize(400, child.computeSize(400, SWT.DEFAULT).y);
	  // Expand both horizontally and vertically
	  sc.setExpandHorizontal(true);
	  sc.setExpandVertical(true);
	  setControl(sc);
	  validate();
	}
	
	/**
	 * When the page is make visible recrate the checkbox basing them on the file
	 * selected in the previous step
	 */
	@Override
	public void setVisible(boolean visible) {
		if (visible){
			for(Control control : child.getChildren()){
				control.dispose();
			}
			ConfigurationImporterWizard parentWizard = (ConfigurationImporterWizard)getWizard();
			File importLocation = new File(parentWizard.getSelectedFile());
			for(IExportedResourceHandler definition : ExtensionManager.getContributedExporters()){
				if (definition.hasRestorableResources(importLocation)){
					String name = definition.getResourceName();
					Button selectionButton = new Button(child, SWT.CHECK);
					selectionButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
					selectionButton.setText(name);
					selectionButton.setData(definition);
					selectionButton.addSelectionListener(checkSelected);
				}
			}
			child.layout();
			sc.setMinSize(400, child.computeSize(400, SWT.DEFAULT).y);
			sc.layout();
		}
		super.setVisible(visible);
	}
	
	/**
	 * Return the list of importer that will be used to import a specific resource type
	 * into the current configuration
	 */
	public HashSet<IExportedResourceHandler> getSelection(){
		return selectionSet;
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * Set the page to complete if at least an element is selected
	 */
	protected void validate(){
		setPageComplete(!selectionSet.isEmpty());
	}

}
