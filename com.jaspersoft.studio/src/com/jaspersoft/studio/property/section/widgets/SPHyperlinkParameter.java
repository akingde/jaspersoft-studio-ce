/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.engine.JRHyperlinkParameter;

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
import com.jaspersoft.studio.property.descriptor.hyperlink.parameter.HyperlinkParameterPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPHyperlinkParameter<T extends IPropertyDescriptor> extends SPText<T> {

	private Button btn;
	
	private JRHyperlinkParameter[] parameters;
	
	public SPHyperlinkParameter(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected void createComponent(Composite parent) {
		super.createComponent(parent);
		ftext.setEnabled(false);

		btn = section.getWidgetFactory().createButton(parent, "...", SWT.PUSH);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				HyperlinkParameterPropertyDescriptor pd = (HyperlinkParameterPropertyDescriptor) pDescriptor;
				ParameterEditor wizard = new ParameterEditor();
				wizard.setValue(GenericJSSParameter.convertFrom(parameters));
				wizard.setExpressionContext(pd.getExpContext());
				WizardDialog dialog = new WizardDialog(ftext.getShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK){
					JRHyperlinkParameter[] parameters = GenericJSSParameter.convertToHyperlink(wizard.getValue());
					section.changeProperty(pDescriptor.getId(), parameters);
				}
			}
		});
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		parameters = (JRHyperlinkParameter[]) b;
		HyperlinkParameterPropertyDescriptor pd = (HyperlinkParameterPropertyDescriptor) pDescriptor;
		String text = pd.getLabelProvider().getText(b);
		if (text != null) {
			ftext.setText(text);
		} else {
			ftext.setText("");
		}
	}
	
	public Control getButton(){
		return btn;
	}
}
