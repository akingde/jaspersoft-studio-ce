/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.descriptor.propexpr.JPropertyExpressionsDescriptor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyExpressionEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * A button that when clicked open the edit properties dialog
 * 
 * @author Veaceslav Chicu
 * 
 */
public class SPPropertyExpressionButton extends ASPropertyWidget<JPropertyExpressionsDescriptor> {

	private Button button;
	private MGraphicElement model;

	/**
	 * 
	 * @param parent
	 * @param section
	 * @param pDescriptor
	 * @param buttonText
	 *          text on the button
	 */
	public SPPropertyExpressionButton(Composite parent, AbstractSection section,
			JPropertyExpressionsDescriptor pDescriptor, String buttonText) {
		super(parent, section, pDescriptor);
		createButton(parent, buttonText);
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
				JRPropertyExpressionEditor wizard = new JRPropertyExpressionEditor();
				// clone the object to avoid side effect
				PropertyExpressionsDTO dto = (PropertyExpressionsDTO) model
						.getPropertyValue(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS);
				wizard.setValue(dto);
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK)
					section.changeProperty(JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, wizard.getValue());
			}
		});
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		model = (MGraphicElement) pnode;
	}

	@Override
	public Control getControl() {
		return button;
	}

}
