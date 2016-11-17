/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools.wizards;

import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * Wizard page were all the defined composite elements are listed. They
 * can be selected to be exported
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementsExportWizardPage extends JSSWizardPage {

	/**
	 * The map of all the composite elements shown in the page associated with a flag that means if they 
	 * must be or not exported
	 */
	private HashMap<MCompositeElement, Boolean> selectionMap = new HashMap<MCompositeElement, Boolean>();
	
	/**
	 * The composite element selected at the start
	 */
	private MCompositeElement alreadySelectedElement;
	
	/**
	 * Adapter on the checkbox used to show the available composite elements, when a checkbox change state the map
	 * is updated
	 */
	private SelectionAdapter checkSelected = new SelectionAdapter(){
		
		public void widgetSelected(SelectionEvent e) {
			MCompositeElement def = (MCompositeElement)e.widget.getData();
			Button btn = (Button)e.widget;
			selectionMap.put(def, btn.getSelection());
			getContainer().updateButtons();
		};
	};
	
	/**
	 * Create the page
	 */
	protected CompositeElementsExportWizardPage(MCompositeElement alreadySelectedElement) {
		super("showCompositeElementsPage"); //$NON-NLS-1$
		setTitle(Messages.CompositeElementsExportWizardPage_pageTitle);
		setMessage(Messages.CompositeElementsExportWizardPage_pageDescription);
		this.alreadySelectedElement = alreadySelectedElement;
	}

	@Override
	public void createControl(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(		parent, SWT.V_SCROLL);
		Composite child = new Composite(sc, SWT.NONE);
		child.setLayout(new GridLayout(1, false));
		
		for(MCompositeElement definition : CompositeElementManager.INSTANCE.getAvailableElements()){
			Button selectionButton = new Button(child, SWT.CHECK);
			selectionButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			selectionButton.setText(definition.getName());
			selectionButton.setData(definition);
			Boolean alreadyChecked = definition == alreadySelectedElement;
			selectionButton.setSelection(alreadyChecked);
			selectionButton.addSelectionListener(checkSelected);
			selectionMap.put(definition, alreadyChecked);	
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
	 * which element should be exported
	 * 
	 * @return a not null hash map. The key is a composite element and the value
	 * is to indicate if the  element should be exported
	 */
	protected HashMap<MCompositeElement, Boolean> getSelection(){
		return selectionMap;
	}

	@Override
	protected String getContextName() {
		return null;
	}

	/**
	 * The page is complete when at least one element is marked for the export
	 */
	@Override
	public boolean isPageComplete() {
		for(Boolean value : selectionMap.values()){
			if (value) {
				setErrorMessage(null);
				setDescription(Messages.CompositeElementsExportWizardPage_pageDescription);
				return true;
			}
		} 
		setErrorMessage(Messages.CompositeElementsExportWizardPage_selectOneElementError);
		return false;
	}
}
