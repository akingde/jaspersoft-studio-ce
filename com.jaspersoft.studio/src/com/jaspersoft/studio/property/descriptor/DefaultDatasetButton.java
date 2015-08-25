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
package com.jaspersoft.studio.property.descriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.model.dataset.SelectDefaultDatasetWizard;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * A button that when clicked open the return values dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class DefaultDatasetButton extends ASPropertyWidget<ButtonsPropertyDescriptor> {

	/**
	 * The button control
	 */
	private Button button;

	/**
	 * 
	 * @param parent
	 * @param section
	 * @param pDescriptor
	 * @param buttonText
	 *          text on the button
	 */
	public DefaultDatasetButton(Composite parent, AbstractSection section, ButtonsPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
		createButton(parent, Messages.DefaultDatasetButton_setDefaultDAButton);
	}

	@Override
	protected void createComponent(Composite parent) {
	}

	/**
	 * Build the button
	 * 
	 * @param parent
	 *          composite where is placed
	 * @param buttonText
	 *          text on the button
	 */
	protected void createButton(Composite parent, String buttonText) {
		button = section.getWidgetFactory().createButton(parent, buttonText, SWT.PUSH);
		button.setToolTipText(pDescriptor.getDescription());
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (section.getElement() instanceof MReport){
					MReport report = (MReport)section.getElement();
					SelectDefaultDatasetWizard defaultDAwizard = new SelectDefaultDatasetWizard(report, section);
					WizardDialog defaultDAdialog = new WizardDialog(UIUtils.getShell(), defaultDAwizard);
					defaultDAdialog.open();
				} else if (section.getElement() instanceof MDataset){
					MDataset dataset = (MDataset)section.getElement();
					SelectDefaultDatasetWizard defaultDAwizard = new SelectDefaultDatasetWizard(dataset, section);
					WizardDialog defaultDAdialog = new WizardDialog(UIUtils.getShell(), defaultDAwizard);
					defaultDAdialog.open();
				}
			}
		});
	}

	@Override
	public Control getControl() {
		return button;
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {	
	}

}
