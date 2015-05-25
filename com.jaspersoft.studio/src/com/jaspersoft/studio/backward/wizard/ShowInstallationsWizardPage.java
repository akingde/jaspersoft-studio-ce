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
package com.jaspersoft.studio.backward.wizard;

import java.text.MessageFormat;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.backward.JRBackwardManager;
import com.jaspersoft.studio.backward.JRDefinition;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page were all the older supported version of JR are listed. They
 * can be downloaded or deleted if downloaded previously
 * 
 * @author Orlandin Marco
 *
 */
public class ShowInstallationsWizardPage extends JSSWizardPage {

	/**
	 * The map of all the jrdefinition shown in the page associated with a flag that means if they 
	 * must be or not in the storage
	 */
	private HashMap<JRDefinition, Boolean> selectionMap = new HashMap<JRDefinition, Boolean>();
	
	/**
	 * Adapter on the checkbox used to show the available jr, when a checkbox change state the map
	 * is upaded
	 */
	private SelectionAdapter checkSelected = new SelectionAdapter(){
		
		public void widgetSelected(SelectionEvent e) {
			JRDefinition def = (JRDefinition)e.widget.getData();
			Button btn = (Button)e.widget;
			selectionMap.put(def, btn.getSelection());
		};
	};
	
	/**
	 * Create the page
	 */
	protected ShowInstallationsWizardPage() {
		super("showInstallationPage"); //$NON-NLS-1$
		setTitle(Messages.ShowInstallationsWizardPage_pageTitle);
		setMessage(Messages.ShowInstallationsWizardPage_pageDescription);
	}

	@Override
	public void createControl(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(		parent, SWT.V_SCROLL);
		Composite child = new Composite(sc, SWT.NONE);
		child.setLayout(new GridLayout(1, false));
	
		Label informationLabel = new Label(child, SWT.WRAP);
		informationLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		informationLabel.setText(Messages.ShowInstallationsWizardPage_labelText);
		
		for(JRDefinition definition : JRBackwardManager.INSTANCE.getDefinitions()){
			Button selectionButton = new Button(child, SWT.CHECK);
			selectionButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			selectionButton.setText(MessageFormat.format(Messages.ShowInstallationsWizardPage_jrLabel, new Object[]{definition.getVersion()}));
			selectionButton.setData(definition);
			Boolean alreadyPresent = JRBackwardManager.INSTANCE.checkJRInstallation(definition);
			selectionButton.setSelection(alreadyPresent);
			selectionButton.addSelectionListener(checkSelected);
			selectionMap.put(definition, alreadyPresent);	
		}
		sc.setContent(child);
		// Set the minimum size
	  sc.setMinSize(400, child.computeSize(400, SWT.DEFAULT).y);
	  // Expand both horizontally and vertically
	  sc.setExpandHorizontal(true);
	  sc.setExpandVertical(true);
	  setControl(sc);
	}
	
	/**
	 * Get the elements in the page with the selection flag, to know
	 * which element should be deleted and which one must be donwloaded
	 * 
	 * @return a not null hash map. The key is a jrdefinition and the value
	 * is to indicate if the definition should be or not in the storage
	 */
	protected HashMap<JRDefinition, Boolean> getSelection(){
		return selectionMap;
	}

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_BACKWARD_COMPILER;
	}

}
