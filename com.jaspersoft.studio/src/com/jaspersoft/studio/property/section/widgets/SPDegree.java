/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.JSSAngleSlider;

public class SPDegree extends SPNumber {
	
	private JSSAngleSlider angleSlider;

	private Composite composite;
	
	private SelectionListener degreeSelectionListener;
	
	public SPDegree(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
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

		angleSlider = new JSSAngleSlider(composite, SWT.NONE);
		angleSlider.setToolTipText(pDescriptor.getDescription());
		degreeSelectionListener = new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				ftext.setValue(angleSlider.getSelection());
				changeValue();
			}
		};
		angleSlider.addSelectionListener(degreeSelectionListener);
		super.createComponent(composite);
	}
	
	@Override
	protected void createContextualMenu(APropertyNode node) {
		createContextualMenu(node, angleSlider, pDescriptor.getId().toString());
		createContextualMenu(node, ftext, pDescriptor.getId().toString());
	}
	
	/**
	 * Avoid to give the focus on the degree control
	 */
	@Override
	protected void focusControl(Control control) {
		super.focusControl(ftext);
	}

	@Override
	public void setDataNumber(Number resolvedNumber, Number ownNumber) {
		super.setDataNumber(resolvedNumber, ownNumber);
		//remove the listener to avoid the setSelection trigger it another time
		angleSlider.removeSelectionListener(degreeSelectionListener);
		
		if (resolvedNumber != null) {
			int degree = Math.abs(resolvedNumber.intValue());
			if (degree > 360){
				degree = BigDecimal.valueOf(degree).remainder(BigDecimal.valueOf(360)).intValue();
			}
			angleSlider.setSelection(degree);
		} else if (ownNumber !=null){
			int degree = Math.abs(ownNumber.intValue());
			if (degree > 360){
				degree = BigDecimal.valueOf(degree).remainder(BigDecimal.valueOf(360)).intValue();
			}
			angleSlider.setSelection(degree);
		} else {
			angleSlider.setSelection(0);
		}
		angleSlider.addSelectionListener(degreeSelectionListener);
	}

}
