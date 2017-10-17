/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.swt.widgets.NumericCombo;

/**
 * A combo property widget used only to input and show float numbers
 * 
 * 
 * @author Orlandin Marco
 *
 */
public class SPRWFloatCombo<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {
	
	/**
	 * The combo widget
	 */
	protected NumericCombo combo;

	/**
	 * Boolean flag to know if the widget is refreshing FIXME: should be synchornized
	 */
	private boolean refresh = false;
	
	/**
	 * The edited node
	 */
	protected APropertyNode pnode;
	
	public SPRWFloatCombo(Composite parent, AbstractSection section, T pDescriptor) {
		super(parent, section, pDescriptor);
	}

	/**
	 * Return the combo control
	 */
	@Override
	public Control getControl() {
		return combo;
	}

	protected void createComponent(Composite parent) {
		combo = new NumericCombo(parent, SWT.FLAT, 0, 6);
		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData();
			gd.minimumWidth = 100;
			combo.setLayoutData(gd);
		}
		setNewItems((RWComboBoxPropertyDescriptor) pDescriptor);
		
		
		combo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (refresh)
					return;
				changeValue();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		
		combo.setToolTipText(pDescriptor.getDescription());
	}
	
	protected void changeValue(){
		Number newValue = combo.getValueAsFloat();
		boolean valueChanged = section.changeProperty(pDescriptor.getId(), newValue);
		if (valueChanged){
			setData(section.getElement(), section.getElement().getPropertyActualValue(pDescriptor.getId()), newValue);
		}
	}
	
	@Override
	public void setData(APropertyNode pnode, Object b) {
		setData(pnode, b, b); 
	}

	/**
	 * Re-implement the setData with three parameters to show the inherited
	 * values
	 */
	@Override
	public void setData(APropertyNode pnode, Object resolvedValue, Object elementValue) {
		createContextualMenu(pnode);
		refresh = true;
		this.pnode = pnode;
		combo.setEnabled(pnode.isEditable());
		if (resolvedValue != null) {
			int oldpos = combo.getCaretPosition();
			if (elementValue == null) {
				combo.setDefaultValue((Number)resolvedValue);
			}
			combo.setValue((Number)elementValue);
			if (combo.getText().length() >= oldpos){
				combo.setSelection(new Point(oldpos, oldpos));
			}
		} else if (elementValue != null){
			int oldpos = combo.getCaretPosition();
			combo.setValue((Number)elementValue);
			if (combo.getText().length() >= oldpos){
				combo.setSelection(new Point(oldpos, oldpos));
			}
		} else {
			combo.setValue(null);
		}
		refresh = false;
	}
	
	public void setNewItems(final RWComboBoxPropertyDescriptor pd) {
		combo.setItems(pd.getItems());
	}
}
