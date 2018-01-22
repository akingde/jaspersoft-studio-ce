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
import com.jaspersoft.studio.model.JReportsDTO;
import com.jaspersoft.studio.property.descriptor.returnvalue.RVPropertyPage;
import com.jaspersoft.studio.property.descriptor.subreport.returnvalue.dialog.SubreportRVPropertyEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * A button that when clicked open the subreport return values dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class SPSubreportReturnValuesButton<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {

	/**
	 * The button control
	 */
	private Button button;

	/**
	 * The query of the report
	 */
	private JReportsDTO dto;

	/**
	 * 
	 * @param parent
	 * @param section
	 * @param pDescriptor
	 * @param buttonText
	 *          text on the button
	 */
	public SPSubreportReturnValuesButton(Composite parent, AbstractSection section, T pDescriptor,
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
		button = section.getWidgetFactory().createButton(parent, buttonText, SWT.PUSH);;
		button.setToolTipText(pDescriptor.getDescription());
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SubreportRVPropertyEditor wizard = new SubreportRVPropertyEditor();
				JReportsDTO value = new JReportsDTO();
				value.setjConfig(dto.getjConfig());
				value.setSubreport(dto.getSubreport(), dto.getDataset());
				value.setValue(dto.getValue());
				wizard.setValue(value);
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				UIUtils.resizeAndCenterShell(dialog.getShell(), RVPropertyPage.WIDTH_HINT, -1);
				if (dialog.open() == Dialog.OK)
					section.changeProperty(pDescriptor.getId(), wizard.getValue());
			}
		});
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		if (value instanceof JReportsDTO)
			dto = (JReportsDTO) value;
	}

	@Override
	public Control getControl() {
		return button;
	}

}
