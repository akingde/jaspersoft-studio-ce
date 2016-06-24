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

import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.action.exporter.IExportedResourceHandler;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page where all the exportable resources are listed and the user can define
 * which one should be exported in the final file
 * 
 * @author Orlandin Marco
 *
 */
public class ShowExportableWizardPage extends JSSWizardPage {

	/**
	 * List of all the handlers to export a resource are selected by the user. The list is 
	 * updated when the selection of an handler changes
	 */
	private HashSet<IExportedResourceHandler> selectionList = new HashSet<IExportedResourceHandler>();
	
	/**
	 * Adapter on the checkbox used to show the available resource, when a checkbox change state the list
	 * is upaded
	 */
	private SelectionAdapter checkSelected = new SelectionAdapter(){
		
		public void widgetSelected(SelectionEvent e) {
			IExportedResourceHandler def = (IExportedResourceHandler)e.widget.getData();
			Button btn = (Button)e.widget;
			if (btn.getSelection()){
				selectionList.add(def);
			} else {
				selectionList.remove(def);
			}
			validate();
		}
	};
	
	/**
	 * Create the page
	 */
	protected ShowExportableWizardPage() {
		super("showExportablePage"); //$NON-NLS-1$
		setTitle(Messages.DestinationPage_exportWizardTitle);
		setDescription(Messages.ShowExportableWizardPage_pageDescription);
	}

	@Override
	public void createControl(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(		parent, SWT.V_SCROLL);
		Composite child = new Composite(sc, SWT.NONE);
		child.setLayout(new GridLayout(1, false));
	
		Label informationLabel = new Label(child, SWT.WRAP);
		informationLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		informationLabel.setText(Messages.ShowExportableWizardPage_labelText);
		
		for(IExportedResourceHandler definition : ExtensionManager.getContributedExporters()){
			if (definition.hasExportableResources()){
				Button selectionButton = new Button(child, SWT.CHECK);
				selectionButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				selectionButton.setText(definition.getResourceNameExport());
				selectionButton.setSelection(true);
				selectionList.add(definition);
				selectionButton.setData(definition);
				selectionButton.addSelectionListener(checkSelected);
			}
		}
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
	 * Get the elements in the page with the selection flag, to know
	 * which resource should be exported
	 * 
	 * @return a not null list of exporters that will be used to export
	 * the current configuration of studio
	 */
	public HashSet<IExportedResourceHandler> getSelection(){
		return selectionList;
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * Set the page complete if at least an exporter is selected
	 */
	protected void validate(){
		setPageComplete(!selectionList.isEmpty());
	}
}
