/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.widgets;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRSubreportParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;

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
import com.jaspersoft.studio.book.wizards.PartPropertyEditor;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.ButtonsPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * A button that when clicked open the parameters dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class SPPartParametersButton extends ASPropertyWidget<ButtonsPropertyDescriptor> {

	/**
	 * The button control
	 */
	private Button button;

	/**
	 * Create the button widget
	 * 
	 * @param parent the parent of the button
	 * @param section the section where the button is placed
	 * @param pDescriptor the descriptor handle by the button
	 * @param buttonText text on the button
	 */
	public SPPartParametersButton(Composite parent, AbstractSection section, ButtonsPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
		createButton(parent, Messages.SPPartParametersButton_parametersButton);
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
				MReportPart part = (MReportPart)section.getElement();
				PartPropertyEditor wizard = new PartPropertyEditor(part);
				wizard.setExpressionContext(getContext(part));
				List<GenericJSSParameter> genericValues = GenericJSSParameter.convertFrom(part.getSubreportComponent().getParameters());
				wizard.setValue(genericValues);
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK){
					JRSubreportParameter[] values = GenericJSSParameter.convertToSubreport(wizard.getValue());
					section.changeProperty(MReportPart.COMPONENT_PARAMETERS, values);
				}
			}
		});
	}
	
	/**
	 * Return an expression context for the current element
	 * 
	 * @param part a not null MReportPart from where the expression context will be calculated
	 * @return a not null expression context that can be used inside the expression editor
	 */
	private ExpressionContext getContext(MReportPart part){
		JRDataset mainDS = part.getJasperDesign().getMainDataset();
		ExpressionContext exprContext = new ExpressionContext((JRDesignDataset) mainDS, part.getJasperConfiguration());
		return exprContext;
	}

	@Override
	public Control getControl() {
		return button;
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {	
	}

}
