/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.SubreportParameterEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRSubreportParameter;

/**
 * A button that when clicked open the edit query dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class SPSubreportParametersButton<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {

	/**
	 * The button control
	 */
	private Button button;

	/**
	 * The query of the report
	 */
	private JRSubreportParameter[] dto;

	/**
	 * The main dataset of the report
	 */
	private MSubreport msubreport;

	/**
	 * 
	 * @param parent
	 * @param section
	 * @param pDescriptor
	 * @param buttonText
	 *          text on the button
	 */
	public SPSubreportParametersButton(Composite parent, AbstractSection section, T pDescriptor,
			String buttonText) {
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
				SubreportParameterEditor wizard = new SubreportParameterEditor(msubreport);
				wizard.setValue(GenericJSSParameter.convertFrom(dto));
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				if (dialog.open() == Dialog.OK){
					JRSubreportParameter[] values = GenericJSSParameter.convertToSubreport(wizard.getValue());
					section.changeProperty(pDescriptor.getId(), values);
				}
			}
		});
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		if (pnode instanceof MSubreport)
			msubreport = (MSubreport) pnode;
		if (value instanceof JRSubreportParameter[])
			dto = (JRSubreportParameter[]) value;
	}

	@Override
	public Control getControl() {
		return button;
	}

}
