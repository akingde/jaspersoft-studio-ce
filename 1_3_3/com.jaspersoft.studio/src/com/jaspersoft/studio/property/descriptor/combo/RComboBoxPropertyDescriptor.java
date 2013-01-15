/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPRCombo;

public class RComboBoxPropertyDescriptor extends ComboBoxPropertyDescriptor implements IPropertyDescriptorWidget {
	private String[] labels;
	private ComboBoxCellEditor cellEditor;

	public RComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray) {
		super(id, displayName, labelsArray);
		labels = labelsArray;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new RWComboBoxCellEditor(parent, labels, SWT.READ_ONLY);
		if (getValidator() != null) {
			cellEditor.setValidator(getValidator());
		}
		return cellEditor;
	}

	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new RComboBoxLabelProvider(labels);
	}

	public void setItems(String[] items) {
		labels = items;
		if (cellEditor != null && cellEditor.getControl() != null && !cellEditor.getControl().isDisposed())
			cellEditor.setItems(items);
	}

	public String[] getItems() {
		return labels;
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPRCombo(parent, section, this);
	}
}
