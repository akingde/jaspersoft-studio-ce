/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SPTransparency extends SPNumber {

	private Composite composite;
	
	private Scale scale;
	
	private SelectionListener transparencyChangeListener;
	
	public SPTransparency(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	@Override
	public Control getControl() {
		return composite;
	}

	protected void createComponent(Composite parent) {
		composite = section.getWidgetFactory().createComposite(parent);
		RowLayout layout = new RowLayout(SWT.HORIZONTAL);
		layout.wrap = true;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.center = true;
		composite.setLayout(layout);

		scale = new Scale(composite, SWT.HORIZONTAL);
		scale.setMinimum(0);
		scale.setMaximum(100);
		scale.setIncrement(1);
		scale.setPageIncrement(5);
		RowData rd = new RowData();
		rd.width = 100;
		scale.setLayoutData(rd);
		scale.setToolTipText(pDescriptor.getDescription());
		transparencyChangeListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ftext.setValue(scale.getSelection());
				changeValue();
			}
		};
		scale.addSelectionListener(transparencyChangeListener);
		super.createComponent(composite);
		setBounds(0, 100);
	}
	
	@Override
	protected void changeValue(){
		Number newValue = getValue();
		if (newValue != null) newValue = newValue.floatValue() / 100f;
		boolean valueChanged = section.changeProperty(pDescriptor.getId(), newValue);
		if (valueChanged){
			setData(section.getElement(), section.getElement().getPropertyActualValue(pDescriptor.getId()), newValue);
		}
	}
	
	@Override
	protected void createContextualMenu(APropertyNode node) {
		createContextualMenu(node, scale, pDescriptor.getId().toString());
		createContextualMenu(node, ftext, pDescriptor.getId().toString());
	}

	@Override
	public void setDataNumber(Number resolvedNumber, Number ownNumber) {
		//remove the listener to avoid the setSelection trigger it another time
		scale.removeSelectionListener(transparencyChangeListener);
		if (resolvedNumber != null) {
			int resolvedAlfa = Math.round(100 * resolvedNumber.floatValue());
			int oldpos = ftext.getCaretPosition();
			ftext.setValue(resolvedAlfa);
			scale.setSelection(resolvedAlfa);
			ftext.setInherited(ownNumber == null);
			if (ftext.getText().length() >= oldpos){
				ftext.setSelection(oldpos, oldpos);
			}
		}
		scale.addSelectionListener(transparencyChangeListener);
	}

}
