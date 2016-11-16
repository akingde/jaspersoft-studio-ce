/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.widgets;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.book.messages.Messages;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.wizards.PartRVPropertyEditor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.ButtonsPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.returnvalue.RVPropertyPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * A button that when clicked open the return values dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class SPPartReturnValuesButton extends ASPropertyWidget<ButtonsPropertyDescriptor> {

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
	public SPPartReturnValuesButton(Composite parent, AbstractSection section, ButtonsPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
		createButton(parent, Messages.SPPartReturnValuesButton_returnValuesButton);
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
				PartRVPropertyEditor wizard = new PartRVPropertyEditor((MReportPart)section.getElement());
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				UIUtils.resizeAndCenterShell(dialog.getShell(), RVPropertyPage.WIDTH_HINT, -1);
				if (dialog.open() == Dialog.OK)
					wizard.saveData();
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
