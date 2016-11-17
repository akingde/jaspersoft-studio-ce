/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;

import java.util.Arrays;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPRCombo;

public class RComboBoxPropertyDescriptor extends ComboBoxPropertyDescriptor
		implements IPropertyDescriptorWidget, IHelp {
	private String[] labels;
	private ComboBoxCellEditor cellEditor;
	private boolean isReadOnly = true;

	public RComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray) {
		super(id, displayName, labelsArray);
		labels = labelsArray;
		this.isReadOnly = true;
	}

	public RComboBoxPropertyDescriptor(Object id, String displayName, String[] labelsArray, boolean isReadOnly) {
		super(id, displayName, labelsArray);
		labels = labelsArray;
		this.isReadOnly = isReadOnly;
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		cellEditor = new RWComboBoxCellEditor(parent, labels, (isReadOnly ? SWT.READ_ONLY : SWT.NONE));
		if (getValidator() != null)
			cellEditor.setValidator(getValidator());
		HelpSystem.bindToHelp(this, cellEditor.getControl());
		return cellEditor;
	}

	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new RComboBoxLabelProvider(labels);
	}

	public void setItems(String[] items) {
		if (Arrays.equals(items, labels))
			return;
		labels = items;
		if (cellEditor != null && cellEditor.getControl() != null && !cellEditor.getControl().isDisposed())
			cellEditor.setItems(items);
		if (sprCombo != null && !sprCombo.getControl().isDisposed())
			sprCombo.refresh();
	}

	public String[] getItems() {
		return labels;
	}

	private SPRCombo sprCombo;

	public ASPropertyWidget<RComboBoxPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		sprCombo = new SPRCombo(parent, section, this);
		return sprCombo;
	}

	private IHelpRefBuilder refBuilder;

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}
}
