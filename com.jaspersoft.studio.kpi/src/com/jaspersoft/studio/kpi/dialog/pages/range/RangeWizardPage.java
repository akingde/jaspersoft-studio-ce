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
package com.jaspersoft.studio.kpi.dialog.pages.range;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.kpi.messages.Messages;
import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.combomenu.ComboMenuViewer;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;

/**
 * Wizard page to define or edit a new range
 * 
 * @author Orlandin Marco
 *
 */
public class RangeWizardPage extends WizardPage {

	/**
	 * The modified element if the wizard is used to edit 
	 * an element or null if it is used to crate a new one
	 */
	private RangeDefinition modifiedElement;
	
	/**
	 * The swt spinner to choose the from value of the range
	 */
	private Spinner min;
	
	/**
	 * The swt spinner to choose the to value of the range
	 */
	private Spinner max;
	
	/**
	 * The combo button used to choose the type of the range (good, bad, normal)
	 */
	private ComboMenuViewer colorCombo;
	
	/**
	 * Create the wizard page to edit an element or create a new one
	 * 
	 * @param modifiedElement the element to modify or null if it is a creation
	 */
	public RangeWizardPage(RangeDefinition modifiedElement) {
		super("rangePage"); //$NON-NLS-1$
		this.modifiedElement = modifiedElement;
		if (modifiedElement != null){
			setTitle(Messages.RangeWizardPage_pageTitleEdit);
			setMessage(Messages.RangeWizardPage_pageDescriptionEdit);
		} else {
			setTitle(Messages.RangeWizardPage_pageTitleNew);
			setMessage(Messages.RangeWizardPage_pageDescriptionNew);
		}
	}	
	
	/**
	 * Return the range definition initialized with the values defined
	 * inside the page controls
	 * 
	 * @return a not null range definition
	 */
	public RangeDefinition regenerateRange(){
		return new RangeDefinition(min.getSelection(), max.getSelection(), (String)colorCombo.getSelectionValue());
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2,false));
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		//Create the minimum spinner
		new Label(container, SWT.NONE).setText(Messages.RangePage_fromLabel);
		min = new Spinner(container, SWT.BORDER);
		min.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		min.setMaximum(Integer.MAX_VALUE);
		min.setMinimum(Integer.MIN_VALUE);
		
		//Create the maximum spinner
		new Label(container, SWT.NONE).setText(Messages.RangePage_toLabel);
		max = new Spinner(container, SWT.BORDER);
		max.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		max.setMaximum(Integer.MAX_VALUE);
		max.setMinimum(Integer.MIN_VALUE);
		
		//Initialize the combo items
		new Label(container, SWT.NONE).setText(Messages.RangePage_typeLabel);
		ArrayList<ComboItem> itemsList = new ArrayList<ComboItem>();
		int index = 0;
		for(String names : RangeDefinition.getNames()){
			itemsList.add(new ComboItem(MessagesByKeys.getString(names), true,  null, index, names, names));
			index++;
		}
		
		//Creating the combo popup
		colorCombo = new ComboMenuViewer(container, SWT.NORMAL, SPRWPopUpCombo.getLongest(itemsList));
		//colorCombo.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		colorCombo.setItems(itemsList);
		colorCombo.select(0);
		
		//If it is a modifying operation initialize the widgets
		if (modifiedElement != null){
			min.setSelection(modifiedElement.getMin());
			max.setSelection(modifiedElement.getMax());
			String name = modifiedElement.getType();
			int nameIndex = RangeDefinition.getNames().indexOf(name);
			if (nameIndex != -1){
				colorCombo.select(nameIndex);
			} else {
				colorCombo.select(0);
			}
		}
		setControl(container);
	}
}
